package interfaces.impls;

import classess.InfResource;
import interfaces.InfResourceTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Evsin on 2019-01
 *
 */
public class CollectInfResourceTableImpl implements InfResourceTable {

    ObservableList<InfResource> infResources = FXCollections.observableArrayList();

    @Override
    public ObservableList<InfResource> getResources() {
        return infResources;
    }

    @Override
    public void add(InfResource infResource) {
        infResources.add(infResource);
    }

    @Override
    public void update(InfResource infResource) {

    }

    @Override
    public void delete(InfResource infResource) {

    }
}
