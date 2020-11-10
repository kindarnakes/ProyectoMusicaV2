package org.ciclo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.ciclo.MainApp;
import org.ciclo.model.User;
import org.ciclo.model.UserDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestTableController implements Initializable {

    @FXML
    TableView<User> tableExample;
    @FXML
    TableColumn<User, String> c1;
    @FXML
    TableColumn<User, String> c2;
    @FXML
    TableColumn<User, String> c3;

    private ObservableList<User> _list;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    public void updateTable() {
        _list = FXCollections.observableList(UserDAO.listAll());
        tableExample.setItems(_list);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoto()));
    }

    public void delete() {
        UserDAO userDAO = new UserDAO(tableExample.getSelectionModel().getSelectedItem());
        userDAO.remove();
        updateTable();
    }

    public void form() throws IOException {
        MainApp.setRoot("FormTest");
    }

    public void update() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/fxml/FormTest.fxml"));
            root = loader.load();
            TestFormController test = loader.getController();
            test.setId(tableExample.getSelectionModel().getSelectedItem() !=null? tableExample.getSelectionModel().getSelectedItem().getId():0);
            test.showData();
            Scene scene = this.tableExample.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
