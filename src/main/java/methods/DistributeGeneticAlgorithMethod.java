package methods;

import classess.*;
import org.apache.commons.math3.genetics.*;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.util.Precision;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DistributeGeneticAlgorithMethod {

    private static double CROSSOVER_RATE = 0.6;
    private static double MUTATION_RATE = 0.3;
    private static int TOURNAMENT_ARITY = 1;
    public static double[] ex = new double[100];

    public static PlaceServer distribute(DataToDistribute dataToDistribute) {
        List<GeneticAlgoritmConstraint> listConstraints = createConstraints(dataToDistribute);
        final List<Double> goalFunction = Arrays.stream(HelperMethods.createParameterGoalFunction(dataToDistribute)).boxed().collect(Collectors.toList());

        ResourcePopulation resourcePopulation = new ResourcePopulation(10, goalFunction, listConstraints, dataToDistribute.getServers().size(), dataToDistribute.getInfResources().size());

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(new OnePointCrossover<Character>(),
                CROSSOVER_RATE, new RandomKeyMutation(), MUTATION_RATE,
                new TournamentSelection(TOURNAMENT_ARITY));

        StoppingCondition stoppingCondition = stoppingCondition();

        final Population evolve = geneticAlgorithm.evolve(resourcePopulation, stoppingCondition);
        final ResourceChromosome fittestChromosome = (ResourceChromosome) evolve.getFittestChromosome();

        final List<Integer> answer = fittestChromosome.representation;

        for (int i = 0; i < answer.size(); i++) {
            if (answer.get(i) == 1) {
                final int indexResource = (int)Math.floor(i / dataToDistribute.getServers().size());
                final int indexServer = i - indexResource * dataToDistribute.getServers().size();
                dataToDistribute.getServers().get(indexServer).addResource(dataToDistribute.getInfResources().get(indexResource));
            }
        }

        return new PlaceServer(0, "Ok", dataToDistribute.getServers());
    }

    private static List<GeneticAlgoritmConstraint> createConstraints(DataToDistribute dataToDistribute) {
        final List<Server> servers = dataToDistribute.getServers();
        final List<InfResource> infResources = dataToDistribute.getInfResources();
        List<GeneticAlgoritmConstraint> geneticAlgoritmConstraints = new ArrayList<>();

        for (int i = 0; i < servers.size(); i++) {
            double[] paramConstraints = HelperMethods.createZeroDoubleArray(-1, infResources.size() * servers.size());
            for(int j = 0; j < infResources.size(); j++) {
                paramConstraints[j * servers.size() + i] = infResources.get(j).getVolume();
            }

            geneticAlgoritmConstraints.add(new GeneticAlgoritmConstraint(paramConstraints, EnumTypeEqual.LEQ, servers.get(i).getMaxCapacity()));
        }

        for (int i = 0; i < infResources.size(); i++) {
            double[] paramConstraints = HelperMethods.createZeroDoubleArray(-1, infResources.size() * servers.size());

            for (int j = 0; j < servers.size(); j++) {
                paramConstraints[i * servers.size() + j] =  infResources.get(i).getVolume();
            }

            geneticAlgoritmConstraints.add(new GeneticAlgoritmConstraint(paramConstraints,
                    EnumTypeEqual.EQ, infResources.get(i).getVolume()));
        }

        return geneticAlgoritmConstraints;
    }

    private static StoppingCondition stoppingCondition() {
        StoppingCondition stoppingCondition = new StoppingCondition() {

            int generation = 0;

            @Override
            public boolean isSatisfied(Population population) {
                final double fitness = population.getFittestChromosome().fitness();
                ex[generation] = fitness;
                generation++;
                if (generation == 100) return true;

                return false;
            }
        };
        return stoppingCondition;
    }
}
