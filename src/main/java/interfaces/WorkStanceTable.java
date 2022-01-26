package interfaces;

import classess.Device;
import javafx.collections.ObservableList;

public interface WorkStanceTable {
    /**
     *
     * @return
     */
    ObservableList<Device> getWS();

    void add(Device device);

    void clear();

    void update(Device device);

    void delete(Device device);
}
