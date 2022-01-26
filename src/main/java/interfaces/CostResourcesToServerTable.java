package interfaces;

import classess.CostServerToResource;
import javafx.collections.ObservableList;

public interface CostResourcesToServerTable {
    /**
     *
     * @return
     */
    ObservableList<CostServerToResource> getResources();

    void add(CostServerToResource costServerToResource);

    void clear();

    void update(CostServerToResource costServerToResource);

    void delete(CostServerToResource costServerToResource);
}
