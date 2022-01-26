package interfaces.impls;

import classess.Switch;
import interfaces.SwitchTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectSwitchTableImpl implements SwitchTable {

    ObservableList<Switch> switches = FXCollections.observableArrayList();

    @Override
    public ObservableList<Switch> getWS() {
        return switches;
    }

    @Override
    public void add(Switch device) {
        switches.add(device);
    }

    @Override
    public void clear() {
        switches.clear();
    }

    @Override
    public void update(Switch device) {

    }

    @Override
    public void delete(Switch device) {

    }
}
