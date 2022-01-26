package interfaces.impls;

import classess.CostServerToResource;
import interfaces.CostResourcesToServerTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectCostResourcesToServerTableImpl implements CostResourcesToServerTable {

    ObservableList<CostServerToResource> costResourcesToServerTables = FXCollections.observableArrayList();

    @Override
    public ObservableList<CostServerToResource> getResources() {
        return costResourcesToServerTables;
    }

    @Override
    public void add(CostServerToResource costServerToResource) {
        costResourcesToServerTables.add(costServerToResource);
    }

    @Override
    public void clear() {
        costResourcesToServerTables.clear();
    }

    @Override
    public void update(CostServerToResource costServerToResource) {

    }

    @Override
    public void delete(CostServerToResource costServerToResource) {

    }
}
