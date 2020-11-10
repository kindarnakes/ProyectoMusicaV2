package org.ciclo.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.ciclo.model.User;
import org.ciclo.model.UserDAO;

public class FXMLController implements Initializable {

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

    private void updateTable() {
        _list = FXCollections.observableList(UserDAO.listAll());
        tableExample.setItems(_list);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoto()));
    }

    public void delete(){
        UserDAO userDAO = new UserDAO(tableExample.getSelectionModel().getSelectedItem());
        userDAO.remove();
        updateTable();
    }
}
