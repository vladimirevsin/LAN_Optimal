package classess;

public class CostServerToResource {
    private Server serverDestination;
    private Double cost;

    public CostServerToResource(Server serverDestination, Double cost) {
        this.serverDestination = serverDestination;
        this.cost = cost;
    }

    public Server getServerDestination() {
        return serverDestination;
    }

    public void setServerDestination(Server serverDestination) {
        this.serverDestination = serverDestination;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
