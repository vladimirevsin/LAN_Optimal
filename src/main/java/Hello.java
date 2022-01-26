import classess.DataInputSimulate;
import classess.ResultSimulatePath;
import classess.Server;
import classess.Switch;
import methods.SimulationSendTransact;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;

public class Hello {
    public static void main(String[] args) {

//        List<String> switches = Arrays.asList("Switch1", "Switch2", "Switch3");
//        List<String> servers = Arrays.asList("Server1", "Server2");

//        List<String> wss = Arrays.asList("ws1", "ws2");
//        Server server1 = new Server(1, 15, 2, "Server1", 5);
//        Server server2 = new Server(2, 7, 1, "Server2", 7);

//        Switch switch1 = new Switch(1, 3);
//        Switch switch2 = new Switch(2, 5);
//        Switch switch3 = new Switch(3, 5);
//        Switch switch4 = new Switch(4, 5);
//        Switch switch5 = new Switch(5, 5);

//        List<Server> servers = Arrays.asList(server1, server2);
//        List<Switch> switches = Arrays.asList(switch1, switch2, switch3, switch4, switch5);
//
//        double[][] weigthsSS = new double[][] {
//                Arrays.asList(0.0, 0.0, 2.0, 0.0, 0.0).stream().mapToDouble(Double::doubleValue).toArray(),
//                Arrays.asList(0.0, 0.0, 1.0, 0.0, 0.0).stream().mapToDouble(Double::doubleValue).toArray(),
//                Arrays.asList(1.0, 2.0, 0.0, 2.0, 3.0).stream().mapToDouble(Double::doubleValue).toArray(),
//                Arrays.asList(0.0, 0.0, 2.0, 0.0, 0.0).stream().mapToDouble(Double::doubleValue).toArray(),
//                Arrays.asList(0.0, 0.0, 3.0, 0.0, 0.0).stream().mapToDouble(Double::doubleValue).toArray(),
//                Arrays.asList(0.0, 0.0, 0.0, 0.0, 1.0).stream().mapToDouble(Double::doubleValue).toArray(),
//                Arrays.asList(0.0, 0.0, 0.0, 2.0, 0.0).stream().mapToDouble(Double::doubleValue).toArray(),
//
//        };
//
//        double[][] weigthsWS = new double[][] {
//                Arrays.asList(1.0, 0.0).stream().mapToDouble(Double::doubleValue).toArray(),
//                Arrays.asList(2.0, 0.0).stream().mapToDouble(Double::doubleValue).toArray(),
//        };
//
//
//
//        DataInputSimulate dataInputSimulate = new DataInputSimulate(
//                wss.get(1),
//                server2,
//                switches,
//                servers,
//                wss,
//                weigthsSS,
//                weigthsWS
//        );
//
//        try {
//            final ResultSimulatePath simulate = SimulationSendTransact.simulate(dataInputSimulate, 4);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //final GraphPath<String, DefaultWeightedEdge> shortPath = SimulationSendTransact.findShortPath(switches.get(0), servers.get(1), switches, servers, weigths);

//        List<Server> servers = new ArrayList<>();
//        List<InfResource> infResources = new ArrayList<>();
//        Server server1 = new Server(15, 2);
//        Server server2 = new Server(7, 1);
//        Server server3 = new Server(30, 4);
//        Server server4 = new Server(30, 5);
//        Server server5 = new Server(35, 7);
//        Server server6 = new Server(47, 5);
//
//        servers.add(server1);
//        servers.add(server2);
//        servers.add(server3);
//        servers.add(server4);
//        servers.add(server5);
//        servers.add(server6);
//
//        InfResource infResource1 = new InfResource(1, 3);
//
//        Hashtable<Server, Double> tableRes1 = new Hashtable<>();
//        tableRes1.put(server1, 2.41);
//        tableRes1.put(server2, 1.21);
//        tableRes1.put(server3, 3.2);
//        tableRes1.put(server5, 4.2);
//        tableRes1.put(server6, 3.2);
//        tableRes1.put(server4, 2.5);
//
//
//        infResource1.setCostSendToServer(tableRes1);
//
//        InfResource infResource2 = new InfResource(2, 20);
//
//        Hashtable<Server, Double> tableRes2 = new Hashtable<>();
//        tableRes2.put(server1, 2.21);
//        tableRes2.put(server2, 1.16);
//        tableRes2.put(server3, 4.1);
//        tableRes2.put(server5, 4.2);
//        tableRes2.put(server6, 3.2);
//        tableRes2.put(server4, 2.5);
//
//        infResource2.setCostSendToServer(tableRes2);
//
//        InfResource infResource3 = new InfResource(3, 5);
//
//        Hashtable<Server, Double> tableRes3 = new Hashtable<>();
//        tableRes3.put(server1, 1.12);
//        tableRes3.put(server2, 3.14);
//        tableRes3.put(server3, 8.1);
//        tableRes3.put(server5, 4.2);
//        tableRes3.put(server6, 3.2);
//        tableRes3.put(server4, 2.5);
//
//        infResource3.setCostSendToServer(tableRes3);
//
//
//        InfResource infResource4 = new InfResource(4, 7);
//
//        Hashtable<Server, Double> tableRes4 = new Hashtable<>();
//        tableRes4.put(server1, 1.2);
//        tableRes4.put(server2, 4.2);
//        tableRes4.put(server3, 2.6);
//        tableRes4.put(server4, 1.12);
//        tableRes4.put(server5, 3.14);
//        tableRes4.put(server6, 8.1);
//
//        infResource4.setCostSendToServer(tableRes4);
//
//
//        InfResource infResource5 = new InfResource(5, 9);
//
//        Hashtable<Server, Double> tableRes5 = new Hashtable<>();
//        tableRes5.put(server1, 4.31);
//        tableRes5.put(server2, 2.1);
//        tableRes5.put(server3, 2.1);
//        tableRes5.put(server4, 1.2);
//        tableRes5.put(server5, 4.2);
//        tableRes5.put(server6, 2.6);
//
//        infResource5.setCostSendToServer(tableRes5);
//
//
//        InfResource infResource6 = new InfResource(6, 2);
//
//        Hashtable<Server, Double> tableRes6 = new Hashtable<>();
//        tableRes6.put(server1, 2.4);
//        tableRes6.put(server2, 3.2);
//        tableRes6.put(server3, 2.0);
//        tableRes6.put(server4, 1.2);
//        tableRes6.put(server5, 4.2);
//        tableRes6.put(server6, 2.6);
//
//        infResource6.setCostSendToServer(tableRes6);
//
//        InfResource infResource7 = new InfResource(1, 3);
//
//        Hashtable<Server, Double> tableRes7 = new Hashtable<>();
//        tableRes7.put(server1, 2.41);
//        tableRes7.put(server2, 1.21);
//        tableRes7.put(server3, 3.2);
//        tableRes7.put(server5, 4.2);
//        tableRes7.put(server6, 3.2);
//        tableRes7.put(server4, 2.5);
//
//
//        infResource7.setCostSendToServer(tableRes7);
//
//        InfResource infResource8 = new InfResource(2, 20);
//
//        Hashtable<Server, Double> tableRes8 = new Hashtable<>();
//        tableRes8.put(server1, 2.21);
//        tableRes8.put(server2, 1.16);
//        tableRes8.put(server3, 4.1);
//        tableRes8.put(server5, 4.2);
//        tableRes8.put(server6, 3.2);
//        tableRes8.put(server4, 2.5);
//
//        infResource8.setCostSendToServer(tableRes8);
//
//        InfResource infResource9 = new InfResource(3, 5);
//
//        Hashtable<Server, Double> tableRes9 = new Hashtable<>();
//        tableRes9.put(server1, 1.12);
//        tableRes9.put(server2, 3.14);
//        tableRes9.put(server3, 8.1);
//        tableRes9.put(server5, 4.2);
//        tableRes9.put(server6, 3.2);
//        tableRes9.put(server4, 2.5);
//
//        infResource9.setCostSendToServer(tableRes9);
//
//
//        InfResource infResource10 = new InfResource(4, 7);
//
//        Hashtable<Server, Double> tableRes10 = new Hashtable<>();
//        tableRes10.put(server1, 1.2);
//        tableRes10.put(server2, 4.2);
//        tableRes10.put(server3, 2.6);
//        tableRes10.put(server4, 1.12);
//        tableRes10.put(server5, 3.14);
//        tableRes10.put(server6, 8.1);
//
//        infResource10.setCostSendToServer(tableRes10);
//
//
//        InfResource infResource11 = new InfResource(5, 9);
//
//        Hashtable<Server, Double> tableRes11 = new Hashtable<>();
//        tableRes11.put(server1, 4.31);
//        tableRes11.put(server2, 2.1);
//        tableRes11.put(server3, 2.1);
//        tableRes11.put(server4, 1.2);
//        tableRes11.put(server5, 4.2);
//        tableRes11.put(server6, 2.6);
//
//        infResource11.setCostSendToServer(tableRes11);
//
//
//        InfResource infResource12 = new InfResource(6, 2);
//
//        Hashtable<Server, Double> tableRes12 = new Hashtable<>();
//        tableRes12.put(server1, 2.4);
//        tableRes12.put(server2, 3.2);
//        tableRes12.put(server3, 2.0);
//        tableRes12.put(server4, 1.2);
//        tableRes12.put(server5, 4.2);
//        tableRes12.put(server6, 2.6);
//
//        infResource12.setCostSendToServer(tableRes12);
//
//
//
//        infResources.add(infResource1);
//        infResources.add(infResource2);
//        infResources.add(infResource3);
//        infResources.add(infResource4);
//        infResources.add(infResource5);
//        infResources.add(infResource6);
//        infResources.add(infResource7);
//        infResources.add(infResource8);
//        infResources.add(infResource9);
//        infResources.add(infResource10);
//        infResources.add(infResource11);
//        infResources.add(infResource12);
//
//        DataToDistribute dataToDistribute = new DataToDistribute(servers, infResources, 0.7, 0.3);
//
//        final PlaceServer placeServerGeneticAlgoritm = DistributeGeneticAlgorithMethod.distribute(dataToDistribute);
//        final PlaceServer placeServerBranchAndBounds = BranchAndBoundsMethod.distributeResources(dataToDistribute);
//        int a = 0;
//        double CROSSOVER_RATE = 0.6;
//        double MUTATION_RATE = 0.3;
//        int TOURNAMENT_ARITY = 1;
//        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(new OnePointCrossover<Character>(),
//                CROSSOVER_RATE, new RandomKeyMutation(), MUTATION_RATE,
//                new TournamentSelection(TOURNAMENT_ARITY));
//
//
//

//
//        Population initial = getInitialPopulation();

        //region initchrome
//        List<Double> functionParameters = Arrays.asList(11.0, 23.0, 41.0, 32.0, 21.0, 34.0, 26.0, 27.0, 41.0);
//        List<Integer> integerList = Arrays.asList(0, 0, 1, 0, 1, 0, 1, 0, 0);
//        List<Integer> integerList2 = Arrays.asList(0, 1, 0, 0, 1, 0, 0, 0, 1);
//        List<Integer> integerList3 = Arrays.asList(1, 0, 0, 1, 0, 0, 1, 0, 0);
//        List<Integer> integerList4 = Arrays.asList(0, 0, 1, 0, 0, 1, 1, 0, 0);
//        List<ResourceChromosome> chromosomeList = new ArrayList<>();
//
//        ResourceChromosome chromosome1 = new ResourceChromosome(integerList, functionParameters);
//        ResourceChromosome chromosome2 = new ResourceChromosome(integerList2, functionParameters);
//        ResourceChromosome chromosome3 = new ResourceChromosome(integerList3, functionParameters);
//        ResourceChromosome chromosome4 = new ResourceChromosome(integerList4, functionParameters);
//
//        chromosomeList.add(chromosome1);
//        chromosomeList.add(chromosome2);
//        chromosomeList.add(chromosome3);
//        chromosomeList.add(chromosome4);
        //endregion

        //ResourcePopulation chromosomes = new ResourcePopulation(chromosomeList, geneticAlgoritmConstraints);

//        //chromosomes.nextGeneration();
//
//        StoppingCondition stoppingCondition = new StoppingCondition() {
//
//            int generation = 0;
//
//            @Override
//            public boolean isSatisfied(Population population) {
//                generation++;
//
//                System.out.println("generation:" + generation);
//
//                if (generation == 1000) return true;
//
//                final double fitness = population.getFittestChromosome().fitness();
//                if (Precision.equals(fitness, 25.0, 1e-6)) return true;
//                else return false;
//            }
//        };
//
//        final Population evolve = geneticAlgorithm.evolve(chromosomes, stoppingCondition);
//        final Chromosome fittestChromosome = evolve.getFittestChromosome();
//
//        int a = 0;

        //final PlaceServer placeServer = RandomDistributionMethod.randomDistribute(servers, infResources);

    }

//    private static Population getInitialPopulation() {
//
//        }
//
//        Population chromosomes = new ElitisticListPopulation();
//    }
//
//    public Population evolve(Population initial, StoppingCondition condition) {
//        Population current = initial;
//        while (!condition.isSatisfied(current)) {
//            current = nextGeneration(current);
//        }
//
//        return current;
//    }
//
//    public Population nextGeneration(Population current) {
//
//    }

}
