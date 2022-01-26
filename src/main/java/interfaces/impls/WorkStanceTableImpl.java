package interfaces.impls;

import classess.Device;
import interfaces.WorkStanceTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkStanceTableImpl implements WorkStanceTable {

    ObservableList<Device> devices = FXCollections.observableArrayList();

    @Override
    public ObservableList<Device> getWS() {
        return devices;
    }

    @Override
    public void add(Device device) {
        devices.add(device);
    }

    @Override
    public void clear() {
        devices.clear();
    }

    @Override
    public void update(Device device) {

    }

    @Override
    public void delete(Device device) {

    }
}
