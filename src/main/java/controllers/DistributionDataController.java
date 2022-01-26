package controllers;

import classess.*;
import classess.InfResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import interfaces.impls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.converter.DoubleStringConverter;
import methods.BranchAndBoundsMethod;
import methods.DistributeGeneticAlgorithMethod;
import methods.RandomDistributionMethod;
import methods.SimulationSendTransact;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Evsin on 2019-01
 *
 */
public class DistributionDataController implements Initializable {

    private static Integer ID_SERVERS = 0;
    private static Integer ID_RESOURCES = 0;
    private static Integer WORK_STATION_COUNT = 0;
    private static Integer SWITCHES_COUNT = 0;
    public GridPane gridPanelWs;
    public GridPane gridPanelSw;
    public Button butFillgridSwToServer;
    public GridPane gridPaneSwToServer;
    public ChoiceBox choiceRes;
    public ChoiceBox choiceWs;
    public TextArea textAreaResultPath;
    public TextField textFieldServerTimetoWork;
    public TableColumn columnServerTimeToWork;
    public TextField textFieldError;

    //region collectable
    CollectServerTableImpl collectServerTable = new CollectServerTableImpl();
    CollectInfResourceTableImpl collectInfResourceTable = new CollectInfResourceTableImpl();
    CollectCostResourcesToServerTableImpl collectCostResourcesToServerTable = new CollectCostResourcesToServerTableImpl();
    ObservableList<ResultDistributeModel> resultDistributeModel = FXCollections.observableArrayList();
    WorkStanceTableImpl collectWS = new WorkStanceTableImpl();
    CollectSwitchTableImpl collectSwitchTable = new CollectSwitchTableImpl();
    static TextField[][] textFieldsWsToSw;
    static TextField[][] textFieldsSwToServer;
    //endregion

    //region server controls
    @FXML
    private Button addServerButton;

    @FXML
    private TextField textFieldNameServer;

    @FXML
    private TextField textFieldCapacityServer;

    @FXML
    private TextField textFieldCost;

    @FXML
    private TableView<Server> tableServers;

    @FXML
    private TableColumn<Server, String> columnServerName;

    @FXML
    private TableColumn<Server, String> columnServerCapacity;

    @FXML
    private TableColumn<Server, String> columnServerCost;

    //endregion

    //region resource controls

    @FXML
    TextField textFieldNameResource;
    @FXML
    TextField textFieldVolumeResource;
    @FXML
    TableView tableInfResource;
    @FXML
    TableColumn<InfResource, String> columnResourceName;
    @FXML
    TableColumn<InfResource, String> columnResourceValue;
    @FXML
    TableView tableServerAndResources;
    @FXML
    TableColumn<CostServerToResource, String> columnServerInCostResToServ;
    @FXML
    TableColumn columnCostInCostResToServ;

    //endregion

    //region answer controls
    @FXML
    TextField koefCostSendToServer;
    @FXML
    TextField koefCostPlace;
    @FXML
    TreeTableView<ResultDistributeModel> tableResult;
    @FXML
    TreeTableColumn<ResultDistributeModel, String> tableResultName;
    @FXML
    TreeTableColumn<ResultDistributeModel, String> tableResultFree;
    @FXML
    TreeTableColumn<ResultDistributeModel, String> tableResultUsed;
    @FXML
    TreeTableColumn<ResultDistributeModel, String> tableResultCost;

    //endregion

    //region switches
    public TextField textFieldSwitchName;
    public TextField textFieldTimeToWork;
    public TableView tableSwitches;
    public TableColumn columnSwitchName;
    public TableColumn columnSwitchTimeToWork;
    public Button butFillgridWsToSw;
    public GridPane gridPaneWsToSw;
    //endregion

    //region компьютеры
    public TextField nameWS;
    public Button butAddWS;
    public TableView tableWS;
    public TableColumn columnNameWS;
    //endregion

    /**
     * Добавление сервера
     * @param actionEvent
     */
    public void addServerClick(ActionEvent actionEvent) {

        if (collectServerTable.getServers().stream().filter(it -> it.getNameServer().equals(textFieldNameServer.getText())).count() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("This name is used.");

            alert.showAndWait().ifPresent(rs -> {

            });
            return;
        }


        collectServerTable.add(
                new Server(
                        ID_SERVERS++,
                        Integer.parseInt(textFieldCapacityServer.getText()),
                        Double.parseDouble(textFieldCost.getText()),
                        textFieldNameServer.getText(),
                        Integer.parseInt(textFieldServerTimetoWork.getText())));
        textFieldNameServer.clear();
        textFieldCapacityServer.clear();
        textFieldCost.clear();
        textFieldServerTimetoWork.clear();
    }

    private void createInitializeTable() {
        Server server1 = new Server(0, 15, 2, "Server1", 0);
        Server server2 = new Server(1, 7, 1, "Server2", 0);
        Server server3 = new Server(2, 30, 4, "Server3", 0);
//        Server server4 = new Server(3, 30, 5, "Server4", 0);
//        Server server5 = new Server(4, 35, 7, "Server5", 0);
//        Server server6 = new Server(5, 47, 5, "Server6", 0);

        collectServerTable.add(server1);
        collectServerTable.add(server2);
        collectServerTable.add(server3);
//        collectServerTable.add(server4);
//        collectServerTable.add(server5);
//        collectServerTable.add(server6);


        Hashtable<Server, Double> tableRes1 = new Hashtable<>();
        tableRes1.put(server1, 2.41);
        tableRes1.put(server2, 1.21);
        tableRes1.put(server3, 3.2);
//        tableRes1.put(server4, 2.5);
//        tableRes1.put(server5, 4.2);
//        tableRes1.put(server6, 3.2);

        InfResource infResource1 = new InfResource(1, "Res1", 3, tableRes1);


        Hashtable<Server, Double> tableRes2 = new Hashtable<>();
        tableRes2.put(server1, 2.21);
        tableRes2.put(server2, 1.16);
        tableRes2.put(server3, 4.1);
//        tableRes2.put(server4, 2.5);
//        tableRes2.put(server5, 4.2);
//        tableRes2.put(server6, 3.2);
//
        InfResource infResource2 = new InfResource(2, "Res2", 20, tableRes2);


        Hashtable<Server, Double> tableRes3 = new Hashtable<>();
        tableRes3.put(server1, 1.12);
        tableRes3.put(server2, 3.14);
        tableRes3.put(server3, 8.1);
//        tableRes3.put(server4, 2.5);
//        tableRes3.put(server5, 4.2);
//        tableRes3.put(server6, 3.2);

        InfResource infResource3 = new InfResource(3, "Res3", 5, tableRes3);


        Hashtable<Server, Double> tableRes4 = new Hashtable<>();
        tableRes4.put(server1, 1.2);
        tableRes4.put(server2, 4.2);
        tableRes4.put(server3, 2.6);
//        tableRes4.put(server4, 1.12);
//        tableRes4.put(server5, 3.14);
//        tableRes4.put(server6, 8.1);

//        InfResource infResource4 = new InfResource(4, "Res4", 7, tableRes4);
//
//        Hashtable<Server, Double> tableRes5 = new Hashtable<>();
//        tableRes5.put(server1, 4.31);
//        tableRes5.put(server2, 2.1);
////        tableRes5.put(server3, 2.1);
////        tableRes5.put(server4, 1.2);
////        tableRes5.put(server5, 4.2);
////        tableRes5.put(server6, 2.6);
//
//
//        InfResource infResource5 = new InfResource(5, "Res5" ,9, tableRes5);
//
//        Hashtable<Server, Double> tableRes6 = new Hashtable<>();
//        tableRes6.put(server1, 2.4);
//        tableRes6.put(server2, 3.2);
////        tableRes6.put(server3, 2.0);
////        tableRes6.put(server4, 1.2);
////        tableRes6.put(server5, 4.2);
////        tableRes6.put(server6, 2.6);
////
//        InfResource infResource6 = new InfResource(6, "Res6", 2, tableRes6);
//
//        Hashtable<Server, Double> tableRes7 = new Hashtable<>();
//        tableRes7.put(server1, 2.41);
////        tableRes7.put(server2, 1.21);
////        tableRes7.put(server3, 3.2);
////        tableRes7.put(server5, 4.2);
////        tableRes7.put(server6, 3.2);
////        tableRes7.put(server4, 2.5);
//
//
//
//        InfResource infResource7 = new InfResource(1, "Res7", 3, tableRes7);
//
//        Hashtable<Server, Double> tableRes8 = new Hashtable<>();
//        tableRes8.put(server1, 2.21);
//        tableRes8.put(server2, 1.16);
//        tableRes8.put(server3, 4.1);
//        tableRes8.put(server5, 4.2);
//        tableRes8.put(server6, 3.2);
//        tableRes8.put(server4, 2.5);
//
//
//        InfResource infResource8 = new InfResource(2, "Res8", 20, tableRes8);
//
//        Hashtable<Server, Double> tableRes9 = new Hashtable<>();
//        tableRes9.put(server1, 1.12);
//        tableRes9.put(server2, 3.14);
//        tableRes9.put(server3, 8.1);
//        tableRes9.put(server5, 4.2);
//        tableRes9.put(server6, 3.2);
//        tableRes9.put(server4, 2.5);
//
//
//        InfResource infResource9 = new InfResource(3, "Res9", 5, tableRes9);
//
//
//        Hashtable<Server, Double> tableRes10 = new Hashtable<>();
//        tableRes10.put(server1, 1.2);
//        tableRes10.put(server2, 4.2);
//        tableRes10.put(server3, 2.6);
//        tableRes10.put(server4, 1.12);
//        tableRes10.put(server5, 3.14);
//        tableRes10.put(server6, 8.1);
//
//        InfResource infResource10 = new InfResource(4, "Res10" , 7, tableRes10);
//
//        Hashtable<Server, Double> tableRes11 = new Hashtable<>();
//        tableRes11.put(server1, 4.31);
//        tableRes11.put(server2, 2.1);
//        tableRes11.put(server3, 2.1);
//        tableRes11.put(server4, 1.2);
//        tableRes11.put(server5, 4.2);
//        tableRes11.put(server6, 2.6);
//
//        InfResource infResource11 = new InfResource(5, "Res11", 9, tableRes11);
//
//        Hashtable<Server, Double> tableRes12 = new Hashtable<>();
//        tableRes12.put(server1, 2.4);
//        tableRes12.put(server2, 3.2);
//        tableRes12.put(server3, 2.0);
//        tableRes12.put(server4, 1.2);
//        tableRes12.put(server5, 4.2);
//        tableRes12.put(server6, 2.6);
//
//        InfResource infResource12 = new InfResource(6, "Res12", 2, tableRes12);

        collectInfResourceTable.add(infResource1);
        collectInfResourceTable.add(infResource2);
        collectInfResourceTable.add(infResource3);
//        collectInfResourceTable.add(infResource4);
//        collectInfResourceTable.add(infResource5);
//        collectInfResourceTable.add(infResource6);
//        collectInfResourceTable.add(infResource7);
//        collectInfResourceTable.add(infResource8);
//        collectInfResourceTable.add(infResource9);
//        collectInfResourceTable.add(infResource10);
//        collectInfResourceTable.add(infResource11);
//        collectInfResourceTable.add(infResource12);

        Device ws1  = new Device(1 , "WS1" , 0);
        Device ws2  = new Device(2 , "WS2" , 0);
        Device ws3  = new Device(3 , "WS3" , 0);

        collectWS.add(ws1 );
        collectWS.add(ws2 );
        collectWS.add(ws3 );

        Switch sw1  = new Switch(1 ,  2, "sw1" );
        Switch sw2  = new Switch(2 ,  2, "sw2" );
        Switch sw3  = new Switch(3 ,  2, "sw3" );

        collectSwitchTable.add(sw1 );
        collectSwitchTable.add(sw2 );
        collectSwitchTable.add(sw3 );

    }

    /**
     * Инициализация
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //region servers
        columnServerName.setCellValueFactory(new PropertyValueFactory<Server, String>("nameServer"));
        columnServerCapacity.setCellValueFactory(new PropertyValueFactory<Server, String>("maxCapacity"));
        columnServerCost.setCellValueFactory(new PropertyValueFactory<Server, String>("cost"));
        columnServerTimeToWork.setCellValueFactory(new PropertyValueFactory<Server, String>("timeToWorkTransaction"));
        //tableServers.getColumns().addAll(columnServerName, columnServerCapacity, columnServerCost);
        tableServers.setItems(collectServerTable.getServers());
        //endregion

        //region resources

        columnResourceName.setCellValueFactory(new PropertyValueFactory<InfResource, String>("nameResource"));
        columnResourceValue.setCellValueFactory(new PropertyValueFactory<InfResource, String>("volume"));
        columnServerInCostResToServ.setCellValueFactory(new PropertyValueFactory<CostServerToResource, String>("serverDestination"));

        tableInfResource.setItems(collectInfResourceTable.getResources());

        tableServerAndResources.setItems(collectCostResourcesToServerTable.getResources());
        columnCostInCostResToServ.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        columnCostInCostResToServ.setCellValueFactory(new PropertyValueFactory<CostServerToResource, String>("cost"));

        //endregion

        //region distribute

        tableResultName.setCellValueFactory((TreeTableColumn.CellDataFeatures<ResultDistributeModel, String> param) -> {
            if (param.getValue() == null) {
                return new SimpleStringProperty("");
            } else if (param.getValue().getValue() == null) {
                return new SimpleStringProperty("");
            } else {
                String result = param.getValue().getValue().nameDevice == null ? "" : param.getValue().getValue().nameDevice;
                return new SimpleStringProperty(result);
            }
        });

        tableResultFree.setCellValueFactory((TreeTableColumn.CellDataFeatures<ResultDistributeModel, String> param) -> {
            if (param.getValue() == null) {
                return new SimpleStringProperty("");
            } else if (param.getValue().getValue() == null) {
                return new SimpleStringProperty("");
            } else {
                return new SimpleStringProperty(String.valueOf(param.getValue().getValue().freeCapacity));
            }
        });

        tableResultUsed.setCellValueFactory((TreeTableColumn.CellDataFeatures<ResultDistributeModel, String> param) -> {
            if (param.getValue() == null) {
                return new SimpleStringProperty("");
            } else if (param.getValue().getValue() == null) {
                return new SimpleStringProperty("");
            } else {
                return new SimpleStringProperty(String.valueOf(param.getValue().getValue().usedCapacity));
            }
        });

        tableResultCost.setCellValueFactory((TreeTableColumn.CellDataFeatures<ResultDistributeModel, String> param) -> {
            if (param.getValue() == null) {
                return new SimpleStringProperty("");
            }
            else if (param.getValue().getValue() == null) {
                return new SimpleStringProperty("");
            }
            else {
                return new SimpleStringProperty(String.valueOf(param.getValue().getValue().cost));
            }
        });

        //endregion

        //createInitializeTable();

        //region simulate
        columnNameWS.setCellValueFactory(new PropertyValueFactory<Device, String>("nameDevice"));
        tableWS.setItems(collectWS.getWS());

        columnSwitchName.setCellValueFactory(new PropertyValueFactory<Switch, String>("nameDevice"));
        columnSwitchTimeToWork.setCellValueFactory(new PropertyValueFactory<Switch, String>("timeToWorkTransaction"));

        tableSwitches.setItems(collectSwitchTable.getWS());
        //endregion

        choiceRes.setItems(collectServerTable.getServers());
        choiceWs.setItems(collectWS.getWS());
    }

    /**
     * Добавление ресурса
     * @param actionEvent
     */
    public void addResouceClick(ActionEvent actionEvent) {
        if (collectInfResourceTable.getResources().stream().filter(it -> it.getNameResource().equals(textFieldNameResource.getText())).count() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Сообщение");
            alert.setHeaderText("Данное имя уже используется");

            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });

            return;
        }

        Hashtable<Server, Double> serverDoubleHashtable = new Hashtable<>();

        for(CostServerToResource costServerToResource : collectCostResourcesToServerTable.getResources())
            serverDoubleHashtable.put(costServerToResource.getServerDestination(), costServerToResource.getCost());

        collectInfResourceTable.add(new classess.InfResource(ID_RESOURCES++, textFieldNameResource.getText(), Integer.parseInt(textFieldVolumeResource.getText()), serverDoubleHashtable));

        textFieldNameResource.clear();
        textFieldVolumeResource.clear();
    }

    public void editCost(TableColumn.CellEditEvent cellEditEvent) {
        CostServerToResource costServerToResource = (CostServerToResource) tableServerAndResources.getSelectionModel().getSelectedItem();
        costServerToResource.setCost((Double) cellEditEvent.getNewValue());
    }

    /**
     * Заполнение таблицы стоимостей
     * @param actionEvent
     */
    public void fillTable(ActionEvent actionEvent) {
        collectCostResourcesToServerTable.clear();

        for (Server row: tableServers.getItems()) {
            collectCostResourcesToServerTable.add(new CostServerToResource(row, 1.0));
        }
    }

    /**
     * Решение с использованием генетического алгоритма
     * @param actionEvent
     */
    public void solveWithGAClick(ActionEvent actionEvent) {
        final ObservableList<Server> servers = FXCollections.observableArrayList();
        servers.addAll(collectServerTable.getServers());

        DataToDistribute dataToDistribute = new DataToDistribute(collectServerTable.getServers(), collectInfResourceTable.getResources(), Double.parseDouble(koefCostPlace.getText()), Double.parseDouble(koefCostSendToServer.getText()));
        for (Server server: collectServerTable.getServers()) {
            server.setUsedCapacity(0);
            server.getInfResources().clear();
        }

        final PlaceServer distribute = DistributeGeneticAlgorithMethod.distribute(dataToDistribute);
        showResultTable(distribute, dataToDistribute.getKoefCostSendToServer(), dataToDistribute.getKoefCostPlaceOnServer());
    }

    public void showResultTable(PlaceServer distribute, double koefSendToServ, double koefPlace) {

        TreeItem<ResultDistributeModel> root = new TreeItem<>();

        ResultDistributeModel resultDistributeModel = new ResultDistributeModel();
        for (Server server : distribute.getServers()) {
            ResultDistributeModel serverModel = new ResultDistributeModel();
            serverModel.nameDevice = server.getNameServer();
            serverModel.freeCapacity = server.getMaxCapacity() - server.getUsedCapacity();
            serverModel.usedCapacity = server.getUsedCapacity();
            serverModel.cost = server.calculatePrice(koefSendToServ, koefPlace);

            TreeItem<ResultDistributeModel> serverItem = new TreeItem<>(serverModel);

            for (InfResource infResource : server.getInfResources()) {
                ResultDistributeModel resourceModel = new ResultDistributeModel();
                resourceModel.nameDevice = infResource.getNameResource();
                resourceModel.usedCapacity = infResource.getVolume();
                resourceModel.cost = 0.0;
                resourceModel.freeCapacity = 0;

                TreeItem<ResultDistributeModel> resourceItem = new TreeItem<>(resourceModel);
                serverItem.getChildren().add(resourceItem);
            }

            root.getChildren().add(serverItem);
            tableResult.setRoot(root);
        }
    }

    /**
     * Решить с использованием метода ветвей и границ
     * @param actionEvent
     */
    public void solveWithBranchAndBounds(ActionEvent actionEvent) {
        final ObservableList<Server> servers = FXCollections.observableArrayList();
        servers.addAll(collectServerTable.getServers());

        DataToDistribute dataToDistribute = new DataToDistribute(servers, collectInfResourceTable.getResources(), Double.parseDouble(koefCostPlace.getText()), Double.parseDouble(koefCostSendToServer.getText()));
        for (Server server: collectServerTable.getServers()) {
            server.setUsedCapacity(0);
            server.getInfResources().clear();
        }

        final PlaceServer distribute = BranchAndBoundsMethod.distributeResources(dataToDistribute);
        if (distribute.getCode() == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Solve cannot be found with current source data");

            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {

                }
            });

            return;
        } else {
            showResultTable(distribute, dataToDistribute.getKoefCostSendToServer(), dataToDistribute.getKoefCostPlaceOnServer());
        }
    }

    /**
     * Решить с использованием метода случайного распределения
     * @param actionEvent
     */
    public void solveStohastic(ActionEvent actionEvent) {
        final ObservableList<Server> servers = FXCollections.observableArrayList();
        servers.addAll(collectServerTable.getServers());

        DataToDistribute dataToDistribute = new DataToDistribute(servers, collectInfResourceTable.getResources(), Double.parseDouble(koefCostPlace.getText()), Double.parseDouble(koefCostSendToServer.getText()));
        for (Server server: collectServerTable.getServers()) {
            server.setUsedCapacity(0);
            server.getInfResources().clear();
        }

        final PlaceServer distribute = RandomDistributionMethod.randomDistribute(dataToDistribute);
        showResultTable(distribute, dataToDistribute.getKoefCostSendToServer(), dataToDistribute.getKoefCostPlaceOnServer());
    }

    public void crearServer(ActionEvent actionEvent) {
        collectServerTable.getServers().clear();
        collectInfResourceTable.getResources().clear();
        collectCostResourcesToServerTable.getResources().clear();
    }

    public void clearResource(ActionEvent actionEvent) {
        collectInfResourceTable.getResources().clear();
    }

    public void addWS(ActionEvent actionEvent) {
        collectWS.add(new Device(WORK_STATION_COUNT++, nameWS.getText(), 0));
        nameWS.clear();
    }

    public void addSwitchClick(ActionEvent actionEvent) {
        collectSwitchTable.add(new Switch(SWITCHES_COUNT++, Integer.parseInt(textFieldTimeToWork.getText()), textFieldSwitchName.getText()));
        textFieldSwitchName.clear();
        textFieldTimeToWork.clear();
    }

    public void crearSwitch(ActionEvent actionEvent) {
    }

    public void fillgridWsToSw(ActionEvent actionEvent) {
        int countWs = collectWS.getWS().size();

        for (int i = 0; i < countWs; i++) {
            TextField label = new TextField(collectWS.getWS().get(i).getNameDevice());
            label.setDisable(true);
            gridPaneWsToSw.add(label, i + 1, 0, 1, 1);
        }

        int countSw = collectSwitchTable.getWS().size();
        TextField label2 = new TextField("SwToWs");
        label2.minWidth(150);
        label2.setDisable(true);
        gridPaneWsToSw.add(label2, 0, 0, 1, 1);

        for (int i = 0; i < countSw; i++) {
            TextField label = new TextField(collectSwitchTable.getWS().get(i).getNameDevice());
            label.minWidth(150);
            label.setDisable(true);
            gridPaneWsToSw.add(label, 0, i + 1, 1, 1);
        }

        textFieldsWsToSw = new TextField[countWs][];
        for (int i = 0; i < countWs; i++) {
            TextField[] textFieldsRow = new TextField[countSw];

            for (int j = 0; j < countSw; j++) {
                TextField textFieldWeigth = new TextField();
                textFieldWeigth.setText("0");
                textFieldsRow[j] = textFieldWeigth;
                //gridPaneWsToSw.add(textFieldWeigth, i + 1, j + 1, 1, 1);
            }

            textFieldsWsToSw[i] = textFieldsRow;
        }

        for (int i = 0; i < countWs; i++)
            for (int j = 0; j < countSw; j++)
                gridPaneWsToSw.add(textFieldsWsToSw[i][j], i + 1, j + 1, 1, 1);
    }


    public void fillgridSwToServer(ActionEvent actionEvent) {
        int countSw = collectSwitchTable.getWS().size();
        TextField label2 = new TextField("SwToServer");
        label2.minWidth(150);
        label2.setDisable(true);
        gridPaneSwToServer.add(label2, 0, 0, 1, 1);

        for (int i = 0; i < countSw; i++) {
            TextField label = new TextField(collectSwitchTable.getWS().get(i).getNameDevice());
            label.minWidth(200);
            label.setDisable(true);
            gridPaneSwToServer.add(label, 0, i + 1, 1, 1);
        }

        int countServer = collectServerTable.getServers().size();

        for (int i = 0; i < countSw; i++) {
            TextField label = new TextField(collectSwitchTable.getWS().get(i).getNameDevice());
            label.minWidth(200);
            label.setDisable(true);
            gridPaneSwToServer.add(label, i + 1, 0, 1, 1);
        }

        for (int i = countSw; i < countSw + countServer; i++) {
            TextField label = new TextField(collectServerTable.getServers().get(i - countSw).getNameServer());
            label.setPrefWidth(200);
            label.setDisable(true);
            gridPaneSwToServer.add(label, 0, i + 1, 1, 1);
        }

        textFieldsSwToServer = new TextField[countSw][];

        for (int i = 0; i < countSw; i++) {
            TextField[] textFieldsWServers = new TextField[countSw + countServer];

            for (int j = 0; j < countSw + countServer; j++) {
                TextField textFieldWeigth = new TextField();
                textFieldWeigth.setText("0");
                textFieldsWServers[j] = textFieldWeigth;
                //gridPaneWsToSw.add(textFieldWeigth, i + 1, j + 1, 1, 1);
            }

            textFieldsSwToServer[i] = textFieldsWServers;
        }

        for (int i = 0; i < countSw; i++)
            for (int j = 0; j < countSw + countServer; j++)
                gridPaneSwToServer.add(textFieldsSwToServer[i][j], i + 1, j + 1, 1, 1);


    }

    public void simulate(ActionEvent actionEvent) {
        double[][] weightsWsToSw = new double[textFieldsWsToSw.length][];

        for (int i = 0; i < textFieldsWsToSw.length; i++) {
            double[] weightsRow = new double[textFieldsWsToSw[i].length];

            for (int j = 0; j < textFieldsWsToSw[i].length; j++) {
                weightsRow[j] = Double.parseDouble(textFieldsWsToSw[i][j].getText());
            }

            weightsWsToSw[i] = weightsRow;
        }

        double[][] weightsSwToServer = new double[textFieldsSwToServer.length][];

        for (int i = 0; i < textFieldsSwToServer.length; i++) {
            double[] weightsRow = new double[textFieldsSwToServer[i].length];

            for (int j = 0; j < textFieldsSwToServer[i].length; j++) {
                weightsRow[j] = Double.parseDouble(textFieldsSwToServer[i][j].getText());
            }

            weightsSwToServer[i] = weightsRow;
        }

        List<String> wsNames = collectWS.getWS().stream().map(Device::getNameDevice).collect(Collectors.toList());

//        final FilteredList<Server> filtered = collectServerTable.getServers()
//                .filtered(it -> it.getInfResources().stream()
//                        .filter(res -> res == (InfResource) choiceRes.getSelectionModel().getSelectedItem())
//                        .count() > 0);
//
//        if (filtered.size() == 0) {
//            return;
//        }

        final Server serverDestination = ((Server) choiceRes.getSelectionModel().getSelectedItem());

        DataInputSimulate dataInputSimulate = new DataInputSimulate(
                ((Device) choiceWs.getSelectionModel().getSelectedItem()).getNameDevice(),
                serverDestination,
                collectSwitchTable.getWS(),
                collectServerTable.getServers(),
                wsNames,
                tranponate(weightsSwToServer),
                weightsWsToSw
        );

        try {
            final ResultSimulatePath simulate = SimulationSendTransact.simulate(dataInputSimulate, Integer.parseInt(textFieldError.getText()));
            String result = simulate.getPath() + "\n\n";

            for (String path: simulate.getSeparateSection()) {
                result += path + "\n";
            }

            result += "\nGeneral time: " + simulate.getWeigthPath();

            textAreaResultPath.setText(result);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(e.getMessage());

            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {

                }
            });
        }
    }

    private double[][] tranponate(double[][] matrix) {
        double[][] result = new double[matrix[0].length][];

        for (int i = 0; i < matrix[0].length; i++) {
            double[] row = new double[matrix.length];

            for (int j = 0; j < matrix.length; j++)
                row[j] = matrix[j][i];

            result[i] = row;
        }

        return result;
    }

    public void saveFile(ActionEvent actionEvent) {
        DataToDistribute dataToDistribute = new DataToDistribute(collectServerTable.getServers(), collectInfResourceTable.getResources(), 0.7, 0.3);

        Gson json = new GsonBuilder().serializeNulls().create();
        String dataToDist = json.toJson(dataToDistribute);

        FileWriter file = null;
        try {
            file = new FileWriter("dtd.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try  {
            file.append(dataToDist);
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void loadFile(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Load data");
        alert.setHeaderText("Your current data will be lost");

        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {

                collectServerTable.getServers().clear();
                collectInfResourceTable.getResources().clear();

                List<String> strings = null;
                try {
                    strings = Files.readAllLines(Paths.get("dtd.json"), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Gson json = new GsonBuilder().serializeNulls().create();
                DataToDistribute dataToDistribute = json.fromJson(strings.get(0), DataToDistribute.class);

                for (int i = 0; i < dataToDistribute.getInfResources().size(); i++) {
                    dataToDistribute.getInfResources().get(i).setServers(dataToDistribute.getServers());
                }

                for(Server server : dataToDistribute.getServers())
                    collectServerTable.add(server);

                for(InfResource infResource : dataToDistribute.getInfResources())
                    collectInfResourceTable.add(infResource);
            }
            if (rs == ButtonType.CANCEL) {
                return;
            }
        });



    }

    public void exit(ActionEvent actionEvent) {

    }

    public void clickItem(ActionEvent actionEvent) {

    }

    public void aboutProgram(ActionEvent actionEvent) {
        String about = "This program is designed for the optimal distribution of information resources on network nodes. All rights reserved. Evsin V.A., Tikhonov N.A., Vorobyev S.P. Copyright 2019";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, about, ButtonType.OK);
        alert.show();
    }

    public void editResource(MouseEvent mouseEvent) {
        int a = 0;
    }
}
