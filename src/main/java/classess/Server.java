package classess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server extends Device {
    private int maxCapacity;
    private double cost;
    private int usedCapacity;
    private List<InfResource> infResources;
    private String nameServer;

    public Server(int idDevice, int maxCapacity, double cost, int usedCapacity, List<InfResource> infResources, String nameServer, int timeToWorkTransact) {
        super(idDevice, nameServer, timeToWorkTransact);
        this.maxCapacity = maxCapacity;
        this.cost = cost;
        this.usedCapacity = usedCapacity;
        this.infResources = infResources;
        this.nameServer = nameServer;
    }


    //region getters and setters


    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(int usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public List<InfResource> getInfResources() {
        return infResources;
    }

    public void setInfResources(List<InfResource> infResources) {
        this.infResources = infResources;
    }

    //endregion

    public int getFree() {
        return maxCapacity - usedCapacity;
    }

    public void addResource(InfResource infResource) {
        try {
            this.infResources.add(infResource);
            usedCapacity += infResource.getVolume();
        } catch (Exception ex) {

        }
    }

//    private double calculatePrice() {
//        return cost * infResources.stream().mapToInt(it -> it.getVolume()).sum();
//    }

    public double calculatePrice(double koefSendToServ, double koefPlace) {
        final double costForPlace = cost * infResources.stream().mapToInt(it -> it.getVolume()).sum() * koefPlace;
        double costForSend = 0.0;

        for (InfResource infResource : getInfResources()) {
            costForSend += infResource.getCostSendToServer().get(this).doubleValue();
        }

        return costForPlace + costForSend * koefSendToServ;
    }

    public Server(int idDevice, int maxCapacity, double cost, String nameServer, int timeToWorkTransact) {
        super(idDevice, nameServer, timeToWorkTransact);
        this.maxCapacity = maxCapacity;
        this.cost = cost;
        this.usedCapacity = 0;
        this.infResources = new ArrayList<>();
        this.nameServer = nameServer;

    }

//    public Server(int idDevice, int maxCapacity, double cost, int usedCapacity, List<InfResource> infResources, int timeToWorkTransact) {
//        super(idDevice, "Server" + idDevice, timeToWorkTransact);
//        this.maxCapacity = maxCapacity;
//        this.cost = cost;
//        this.usedCapacity = usedCapacity;
//        this.infResources = infResources;
//    }

    @Override
    public String toString() {
        return nameServer;
    }
}
