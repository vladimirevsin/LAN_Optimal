package classess;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ResultDistributeModel {

    public String nameDevice;
    public int freeCapacity;
    public int usedCapacity;
    public Double cost;

    public List<ResultDistributeModel> childs = new ArrayList<>();

    public int getFreeCapacity() {
        return freeCapacity;
    }

    public void setFreeCapacity(int freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(int usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<ResultDistributeModel> getChilds() {
        return childs;
    }

    public void setChilds(List<ResultDistributeModel> childs) {
        this.childs = childs;
    }
}
