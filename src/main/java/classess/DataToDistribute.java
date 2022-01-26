package classess;

import java.util.List;

public class DataToDistribute {
    private List<Server> servers;
    private List<InfResource> infResources;
    private double koefCostPlaceOnServer;
    private double koefCostSendToServer;

    public DataToDistribute(List<Server> servers, List<InfResource> infResources, double koefCostPlaceOnServer, double koefCostSendToServer) {
        this.servers = servers;
        this.infResources = infResources;
        this.koefCostPlaceOnServer = koefCostPlaceOnServer;
        this.koefCostSendToServer = koefCostSendToServer;
    }

    public DataToDistribute(List<Server> servers, List<InfResource> infResources) {
        this.servers = servers;
        this.infResources = infResources;
        this.koefCostPlaceOnServer = 1;
        this.koefCostSendToServer = 1;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public List<InfResource> getInfResources() {
        return infResources;
    }

    public void setInfResources(List<InfResource> infResources) {
        this.infResources = infResources;
    }

    public double getKoefCostPlaceOnServer() {
        return koefCostPlaceOnServer;
    }

    public void setKoefCostPlaceOnServer(double koefCostPlaceOnServer) {
        this.koefCostPlaceOnServer = koefCostPlaceOnServer;
    }

    public double getKoefCostSendToServer() {
        return koefCostSendToServer;
    }

    public void setKoefCostSendToServer(double koefCostSendToServer) {
        this.koefCostSendToServer = koefCostSendToServer;
    }
}
