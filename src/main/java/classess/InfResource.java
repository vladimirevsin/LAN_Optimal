package classess;

import javax.annotation.PostConstruct;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class InfResource {

    private int resourceId;
    private int volume;
    private String nameResource;
    transient private Hashtable<Server, Double> costSendToServer;
    private Hashtable<String, Double> costSendToNameServer = new Hashtable<>();

    public InfResource(int resourceId, int volume) {
        this.resourceId = resourceId;
        this.volume = volume;
        this.nameResource = "resource #" + resourceId;
        this.costSendToServer = new Hashtable<>();
    }

    public InfResource(int resourceId, String nameResource, int volume, Hashtable<Server, Double> costSendToServer) {
        this.resourceId = resourceId;
        this.volume = volume;
        this.nameResource = nameResource;
        this.costSendToServer = costSendToServer;
        this.setCostSendToNameServer();
    }

    public String getNameResource() {
        return nameResource;
    }

    public Hashtable<String, Double> getCostSendToNameServer() {
        return costSendToNameServer;
    }

    public void setCostSendToNameServer(Hashtable<String, Double> costSendToNameServer) {
        this.costSendToNameServer = costSendToNameServer;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    public Hashtable<Server, Double> getCostSendToServer() {
        return costSendToServer;
    }

    public void setCostSendToServer(Hashtable<Server, Double> costSendToServer) {
        this.costSendToServer = costSendToServer;
        this.setCostSendToNameServer();
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setCostSendToNameServer() {
        Enumeration<Server> servers = this.costSendToServer.keys();
        boolean hasNext =servers.hasMoreElements();
        while (hasNext) {
            Server server = servers.nextElement();
            this.costSendToNameServer.put(server.getNameServer(), this.costSendToServer.get(server));
            hasNext =servers.hasMoreElements();
        }
    }

    public void setServers(List<Server> servers) {
        this.costSendToServer = new Hashtable<>();
        Enumeration<String> serverNames = this.costSendToNameServer.keys();
        boolean hasNext =serverNames.hasMoreElements();
        while (hasNext) {
            String nameServer = serverNames.nextElement();
            Server server = servers.stream().filter(it -> it.getNameServer().equals(nameServer)).findFirst().get();
            this.costSendToServer.put(server, this.costSendToNameServer.get(nameServer));
            hasNext =serverNames.hasMoreElements();
        }

    }

    @Override
    public String toString() {
        return nameResource;
    }
}
