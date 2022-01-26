package classess;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PlaceServer {

    private int code;
    private String message;
    private List<Server> servers;
    private Stack<InfResource> infResources;

    public PlaceServer() {
        servers = new ArrayList<>();
        infResources = new Stack<>();
    }

    public PlaceServer(int code, String message, List<Server> servers) {
        this.code = code;
        this.message = message;
        this.servers = servers;
    }

    public PlaceServer(int code, String message, List<Server> servers, Stack<InfResource> infResources) {
        this.code = code;
        this.message = message;
        this.servers = servers;
        this.infResources = infResources;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public Stack<InfResource> getInfResources() {
        return infResources;
    }

    public void setInfResources(Stack<InfResource> infResources) {
        this.infResources = infResources;
    }
}
