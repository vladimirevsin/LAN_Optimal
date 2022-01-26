package methods;

import classess.*;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SimulationSendTransact {

    /**
     * Поиск кратчайшего пути от коммутатора до сервера
     *
     * @param dataInputFindShortPath данные для определения кратчайшего маршрута в графе
     * @return граф пути
     */
    public static GraphPath<Device, PublicWeightedEdge> findShortPath(DataInputFindShortPath dataInputFindShortPath) {

        final int countSwitches = dataInputFindShortPath.getSwitches().size();
        final List<Switch> switches = dataInputFindShortPath.getSwitches();
        final List<Server> servers = dataInputFindShortPath.getServers();
        final double[][] weigths = dataInputFindShortPath.getWeigths();
        SimpleDirectedWeightedGraph<Device, PublicWeightedEdge> graph =
                new SimpleDirectedWeightedGraph<Device, PublicWeightedEdge>
                        (PublicWeightedEdge.class);


        for (Switch currentSwitch : dataInputFindShortPath.getSwitches())
            graph.addVertex(currentSwitch);

        for(Server currentServer: dataInputFindShortPath.getServers())
            graph.addVertex(currentServer);

        for (int i = 0; i < weigths.length; i++) {
            if (i < countSwitches) {
                for (int j = 0; j < weigths[i].length; j++) {
                    if (i <= j) continue;
                    if (weigths[i][j] != 0) {
                        PublicWeightedEdge edge = graph.addEdge(switches.get(j), switches.get(i));
                        graph.setEdgeWeight(edge, weigths[i][j]);
                    }
                }
            } else {
                for (int j = 0; j < weigths[i].length; j++) {
                    if (weigths[i][j] != 0) {
                        PublicWeightedEdge edge = graph.addEdge(switches.get(j), servers.get(i - countSwitches));
                        graph.setEdgeWeight(edge, weigths[i][j]);
                    }
                }
            }
        }

        final GraphPath<Device, PublicWeightedEdge> pathBetween = DijkstraShortestPath.findPathBetween(graph, dataInputFindShortPath.getSwitchConnected(), dataInputFindShortPath.getServerDestination());
        return pathBetween;
    }

    /**
     * Симуляция отправления транзакта до сервера
     * @param dataInputSimulate входные данные для симуляции
     * @param error погрешность определения работы
     * @return
     * @throws Exception
     */
    public static ResultSimulatePath simulate(DataInputSimulate dataInputSimulate, int error) throws Exception {
        String path = ""; //Итоговый маршрут от компьютера до сервера и обратно
        double weigth = 0.0; //Итоговое время
        double firstWeigth = 0.0; //Время маршрута от компьютера до коммуатора
        List<String> sepPath = new ArrayList<>(); //Список маршрутов

        final int indexWorkStations = dataInputSimulate.getWorkStations().indexOf(dataInputSimulate.getSourceWorkStation());
        final double[][] weigthsBetweenWorkStationAndSwitch = dataInputSimulate.getWeigthsBetweenWorkStationAndSwitch();
        int indexSwitch = -1;
        for (int i = 0; i < weigthsBetweenWorkStationAndSwitch.length; i++)
            if (weigthsBetweenWorkStationAndSwitch[indexWorkStations][i] > 0) {
                indexSwitch = i;
                firstWeigth = weigthsBetweenWorkStationAndSwitch[indexWorkStations][i];
                weigth += getTimeToWorkInRoute(firstWeigth, error);
                break;
            }

        if (indexSwitch == -1) throw new Exception("Connection between server and computer not found! Please, check out your connection!");

        Switch connectedSwitch = dataInputSimulate.getSwitches().get(indexSwitch);

        path += String.format("%s->", dataInputSimulate.getSourceWorkStation());
        sepPath.add(String.format("Route: %s->%s; Time: %s", dataInputSimulate.getSourceWorkStation(), connectedSwitch.getNameSwitch(), weigth));

        DataInputFindShortPath dataInputFindShortPath = new DataInputFindShortPath(
                connectedSwitch,
                dataInputSimulate.getServerDestination(),
                dataInputSimulate.getSwitches(),
                dataInputSimulate.getServers(),
                dataInputSimulate.getWeigthsBetweenSwitchAndServer()
        );

        final GraphPath<Device, PublicWeightedEdge> shortPath = findShortPath(dataInputFindShortPath);
        for (PublicWeightedEdge sp : shortPath.getEdgeList()) {

            final Device source = (Device) sp.getSource();
            final Device target = (Device) sp.getTarget();
            final double timeToWorkInRoute = getTimeToWorkInRoute(sp.getWeight(), error);
            final double timeToWork = source.getTimeToWork(error);

            path += String.format("%s->", source.getNameDevice());
            sepPath.add(String.format("Processing on the device: %s; Time: %s",source.getNameDevice(), timeToWork));
            sepPath.add(String.format("Route: %s->%s; Time: %s", source.getNameDevice(), target.getNameDevice(), timeToWorkInRoute));
            weigth += timeToWorkInRoute + timeToWork;
        }

        path += String.format("%s->", shortPath.getEndVertex().getNameDevice());

        for (int i = shortPath.getEdgeList().size() - 1; i >= 0; i--) {
            final Device source = (Device) shortPath.getEdgeList().get(i).getSource();
            final Device target = (Device) shortPath.getEdgeList().get(i).getTarget();
            final double timeToWorkInRoute = getTimeToWorkInRoute(shortPath.getEdgeList().get(i).getWeight(), error);
            source.getTimeToWorkTransaction();
            final double timeToWork = target.getTimeToWork(error);
            weigth += timeToWorkInRoute + timeToWork;

            path += String.format("%s->", source.getNameDevice());
            sepPath.add(String.format("Processing on the device: %s; Time: %s", target.getNameDevice(), timeToWork));
            sepPath.add(String.format("Route: %s->%s; Time: %s", target.getNameDevice(), source.getNameDevice(), timeToWorkInRoute));
        }
        final double lastRoute = getTimeToWorkInRoute(firstWeigth, error);
        final double timeToWork = connectedSwitch.getTimeToWork(error);
        weigth += lastRoute + timeToWork;


        path += String.format("%s", dataInputSimulate.getSourceWorkStation());
        sepPath.add(String.format("Processing on the device: %s; Time: %s", connectedSwitch.getNameSwitch(), timeToWork));
        sepPath.add(String.format("Route: %s->%s; Time: %s", connectedSwitch.getNameSwitch(), dataInputSimulate.getSourceWorkStation(), lastRoute));

        return new ResultSimulatePath(path, weigth, sepPath);
    }

    public static double getTimeToWorkInRoute(double weigth, int workTimeError) {
        Random random = new Random();
        final double result = weigth + random.nextInt(2 * workTimeError) - workTimeError;
        return result <= 0? weigth : result;
    }
}
