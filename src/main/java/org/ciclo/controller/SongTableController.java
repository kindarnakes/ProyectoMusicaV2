package org.ciclo.controller;

import javafx.beans.property.SimpleIntegerProperty;
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
import org.ciclo.MainApp;
import org.ciclo.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SongTableController implements Initializable {

    @FXML
    TableView<Song> tableExample;
    @FXML
    TableColumn<Song, String> c1;
    @FXML
    TableColumn<Song, Integer> c2;
    @FXML
    TableColumn<Song, String> c3;
    @FXML
    TableColumn<Song, String> c4;

    private ObservableList<Song> _list;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        tableExample.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 ) {
                    update();
                }
            });
    }

    public void updateTable() {
        _list = FXCollections.observableList(SongDAO.listAll());
        tableExample.setItems(_list);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDuration()).asObject());
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getName()));
        c4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getArtist().getName()));
    }

    public void delete() {
        SongDAO songDAO = new SongDAO(tableExample.getSelectionModel().getSelectedItem());
        songDAO.remove();
        updateTable();
    }

    public void form() throws IOException {
        MainApp.setRoot("FormTest");
    }

    public void update() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/fxml/SongForm.fxml"));
            root = loader.load();
            SongFormController test = loader.getController();
            test.setId(tableExample.getSelectionModel().getSelectedItem() !=null? tableExample.getSelectionModel().getSelectedItem().getId():0);
            test.showData();
            Scene scene = this.tableExample.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
