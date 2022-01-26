package interfaces;

import classess.Switch;
import javafx.collections.ObservableList;

public interface SwitchTable {
    /**
     *
     * @return
     */
    ObservableList<Switch> getWS();

    void add(Switch device);

    void clear();

    void update(Switch device);

    void delete(Switch device);
}
