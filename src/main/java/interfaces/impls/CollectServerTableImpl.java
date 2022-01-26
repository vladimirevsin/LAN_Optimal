package interfaces.impls;

import classess.Server;
import interfaces.ServerTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectServerTableImpl implements ServerTable {

    private ObservableList<Server> servers = FXCollections.observableArrayList();

    @Override
    public ObservableList<Server> getServers() {
        return servers;
    }

    @Override
    public void add(Server server) {
        this.servers.add(server);
    }

    @Override
    public void update(Server server) {

    }

    @Override
    public void delete(Server server) {
        this.servers.remove(server);
    }
}
