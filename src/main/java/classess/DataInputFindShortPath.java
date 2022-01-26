package classess;

import java.util.List;

public class DataInputFindShortPath {
    private Switch switchConnected;
    private Server serverDestination;
    private List<Switch> switches;
    private List<Server> servers;
    private double[][] weigths;

    public DataInputFindShortPath(Switch switchConnected, Server serverDestination, List<Switch> switches, List<Server> servers, double[][] weigths) {
        this.switchConnected = switchConnected;
        this.serverDestination = serverDestination;
        this.switches = switches;
        this.servers = servers;
        this.weigths = weigths;
    }

    public Switch getSwitchConnected() {
        return switchConnected;
    }

    public void setSwitchConnected(Switch switchConnected) {
        this.switchConnected = switchConnected;
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

    public double[][] getWeigths() {
        return weigths;
    }

    public void setWeigths(double[][] weigths) {
        this.weigths = weigths;
    }
}
