package controllers;

import classess.Device;
import classess.Switch;
import interfaces.impls.CollectSwitchTableImpl;
import interfaces.impls.WorkStanceTableImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulateControls implements Initializable {

    static int WORK_STATION_COUNT = 0;
    static int SWITCHES_COUNT = 0;


    public TextField textFieldSwitchName;
    public TextField textFieldTimeToWork;
    public TableView tableSwitches;
    public TableColumn columnSwitchName;
    public TableColumn columnSwitchTimeToWork;
    public Button butFillgridWsToSw;
    public GridPane gridPaneWsToSw;

    /**
     * Список компьютеров
     */
    WorkStanceTableImpl collectWS = new WorkStanceTableImpl();

    /**
     * Список коммутаторов
     */
    CollectSwitchTableImpl collectSwitchTable = new CollectSwitchTableImpl();

    //region компьютеры
    public TextField nameWS;
    public Button butAddWS;
    public TableView tableWS;
    public TableColumn columnNameWS;
    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnNameWS.setCellValueFactory(new PropertyValueFactory<Device, String>("nameDevice"));
        tableWS.setItems(collectWS.getWS());

        columnSwitchName.setCellValueFactory(new PropertyValueFactory<Switch, String>("nameDevice"));
        columnSwitchTimeToWork.setCellValueFactory(new PropertyValueFactory<Switch, String>("timeToWorkTransaction"));

        tableSwitches.setItems(collectSwitchTable.getWS());
    }

    public void addServerClick(ActionEvent actionEvent) {
    }

    public void crearServer(ActionEvent actionEvent) {
    }


}
