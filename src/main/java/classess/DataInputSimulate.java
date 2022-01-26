package classess;

import java.util.List;

/**
 * Входные данные для определения времени достижения сервера
 */
public class DataInputSimulate {

    /**
     * Компьютер, с которого исходит сообщение
     */
    private String sourceWorkStation;

    /**
     * Сервер, на котором расположен требуемый ресурс
     */
    private Server serverDestination;

    /**
     * Список коммутаторов
     */
    private List<Switch> switches;

    /**
     * Список серверов
     */
    private List<Server> servers;

    /**
     * Список рабочих станций
     */
    private List<String> workStations;

    /**
     * Время переправления данных между коммутаторами и серверами
     */
    private double[][] weigthsBetweenSwitchAndServer;

    /**
     * Время перенаправления данных между рабочими станциями и коммутаторами
     */
    private double[][] weigthsBetweenWorkStationAndSwitch;

    public DataInputSimulate(String sourceWorkStation, Server serverDestination, List<Switch> switches, List<Server> servers, List<String> workStations, double[][] weigthsBetweenSwitchAndServer, double[][] weigthsBetweenWorkStationAndSwitch) {
        this.sourceWorkStation = sourceWorkStation;
        this.serverDestination = serverDestination;
        this.switches = switches;
        this.servers = servers;
        this.workStations = workStations;
        this.weigthsBetweenSwitchAndServer = weigthsBetweenSwitchAndServer;
        this.weigthsBetweenWorkStationAndSwitch = weigthsBetweenWorkStationAndSwitch;
    }

    public String getSourceWorkStation() {
        return sourceWorkStation;
    }

    public void setSourceWorkStation(String sourceWorkStation) {
        this.sourceWorkStation = sourceWorkStation;
    }

    public Server getServerDestination() {
        return serverDestination;
    }

    public void setServerDestination(Server serverDestination) {
        this.serverDestination = serverDestination;
    }

    public List<Switch> getSwitches() {
        return switches;
    }

    public void setSwitches(List<Switch> switches) {
        this.switches = switches;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public List<String> getWorkStations() {
        return workStations;
    }

    public void setWorkStations(List<String> workStations) {
        this.workStations = workStations;
    }

    public double[][] getWeigthsBetweenSwitchAndServer() {
        return weigthsBetweenSwitchAndServer;
    }

    public void setWeigthsBetweenSwitchAndServer(double[][] weigthsBetweenSwitchAndServer) {
        this.weigthsBetweenSwitchAndServer = weigthsBetweenSwitchAndServer;
    }

    public double[][] getWeigthsBetweenWorkStationAndSwitch() {
        return weigthsBetweenWorkStationAndSwitch;
    }

    public void setWeigthsBetweenWorkStationAndSwitch(double[][] weigthsBetweenWorkStationAndSwitch) {
        this.weigthsBetweenWorkStationAndSwitch = weigthsBetweenWorkStationAndSwitch;
    }
}
