package classess;

import methods.HelperMethods;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.genetics.*;

import java.util.*;
import java.util.stream.Collectors;

public class ResourcePopulation implements Population {

    List<ResourceChromosome> chromosomeList;
    int populationLimit;
    final OnePointCrossover onePointCrossover = new OnePointCrossover();
    final BinaryMutation binaryMutation = new BinaryMutation();
    final List<GeneticAlgoritmConstraint> geneticAlgoritmConstraints;

    public ResourcePopulation(List<ResourceChromosome> chromosomeList, List<GeneticAlgoritmConstraint> geneticAlgoritmConstraints) {
        this.chromosomeList = chromosomeList;
        populationLimit = chromosomeList.size();
        this.geneticAlgoritmConstraints = geneticAlgoritmConstraints;
    }

    public ResourcePopulation(List<ResourceChromosome> chromosomeList, int populationLimit, List<GeneticAlgoritmConstraint> geneticAlgoritmConstraints) {
        this.chromosomeList = chromosomeList;
        this.populationLimit = populationLimit;
        this.geneticAlgoritmConstraints = geneticAlgoritmConstraints;
    }

    public ResourcePopulation(int populationSize, List<Double> goalFunction, List<GeneticAlgoritmConstraint> geneticAlgoritmConstraints, int serverSize, int resourceSize) {
        this.geneticAlgoritmConstraints = geneticAlgoritmConstraints;
        this.chromosomeList = initialResourceChromosome(populationSize, goalFunction, serverSize, resourceSize);
        this.populationLimit = populationSize;
    }

    private List<ResourceChromosome> initialResourceChromosome(int populationSize, List<Double> goalFunction, int serverSize, int resourceSize) {
        List<ResourceChromosome> resourceChromosomes = new ArrayList<>();

        try {
            for (int i = 0; i < populationSize; i++) {
                ResourceChromosome resourceChromosome = new ResourceChromosome(getRandomChromosome(serverSize, resourceSize), goalFunction);
                while (!checkConstraint(resourceChromosome)) {
                    resourceChromosome = new ResourceChromosome(getRandomChromosome(serverSize, resourceSize), goalFunction);
                }
                resourceChromosomes.add(resourceChromosome);
            }
        }catch (Exception ex) {

        }

        return resourceChromosomes;
    }

    private List<Integer> getRandomChromosome(int serverSize, int resourceSize) {
        Random random = new Random();
        List<Integer> integerList = new ArrayList<>();

        for (int i = 0; i < resourceSize; i++) {
            int indexSelectedServer = random.nextInt(serverSize);
            final double[] zeroDoubleArray = HelperMethods.createZeroDoubleArray(indexSelectedServer, serverSize);
            for (double v : zeroDoubleArray) integerList.add((Integer)(int)v);
        }

        return integerList;
    }

    @Override
    public int getPopulationSize() {
        return this.chromosomeList.size();
    }

    @Override
    public int getPopulationLimit() {
        return this.populationLimit;
    }

    @Override
    public Population nextGeneration() {
        List<ResourceChromosome> newGenChromosome = new ArrayList<>();
        newGenChromosome.addAll(chromosomeList);

        try {
            for (int i = 0; i < chromosomeList.size(); i++) {
                for (int j = 0; j < chromosomeList.size(); j++) {
                    if (i == j) continue;

                    final ChromosomePair crossover = onePointCrossover.crossover(chromosomeList.get(i), chromosomeList.get(j));
                    final Chromosome first = crossover.getFirst();
                    final Chromosome second = crossover.getSecond();

                    if (checkConstraint((ResourceChromosome) first)) newGenChromosome.add((ResourceChromosome) first);
                    if (checkConstraint((ResourceChromosome) second)) newGenChromosome.add((ResourceChromosome) second);
                    final Chromosome mutate = binaryMutation.mutate(chromosomeList.get(i));
                    if (checkConstraint((ResourceChromosome) mutate)) newGenChromosome.add((ResourceChromosome) mutate);
                }
            }

            final List<ResourceChromosome> collect = newGenChromosome.stream().sorted(Comparator.comparing(ResourceChromosome::fitness))
                    .limit(populationLimit).collect(Collectors.toList());

            ResourcePopulation chromosomes = new ResourcePopulation(collect, geneticAlgoritmConstraints);
            return chromosomes;
        } catch (Exception ex) {
            ResourcePopulation chromosomes = new ResourcePopulation(chromosomeList, chromosomeList.size() * 2, geneticAlgoritmConstraints);
            return chromosomes;
        }
    }

    private boolean checkConstraint(ResourceChromosome chromosome) throws Exception {
        boolean result = true;

        for (GeneticAlgoritmConstraint geneticAlgoritmConstraint : geneticAlgoritmConstraints) {
            if (!geneticAlgoritmConstraint.checkConstraint(chromosome.representation)) result = false;
        }

        return result;
    }

    @Override
    public void addChromosome(Chromosome chromosome) throws NumberIsTooLargeException {
        chromosomeList.add((ResourceChromosome) chromosome);
    }

    @Override
    public Chromosome getFittestChromosome() {
        return this.chromosomeList.stream().reduce((a, b) -> a.fitness() < b.fitness()? a : b).get();
    }

    @Override
    public Iterator<Chromosome> iterator() {
        return null;
    }
}
