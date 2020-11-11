package org.ciclo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.ciclo.MainApp;
import org.ciclo.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SongFormController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField email;

    @FXML
    TableView<Disc> tableExample;
    @FXML
    TableColumn<Disc, String> c1;
    @FXML
    TableColumn<Disc, String> c2;
    @FXML
    TableColumn<Disc, String> c3;

    ObservableList<Disc> _list;
    SongDAO songDAO = null;

    int id = 0;

    public void save() {
        Controller c = new Controller();
        if (id == 0) {

            c.createSong(name.getText(), tableExample.getSelectionModel().getSelectedItem(), Integer.parseInt(email.getText()), null);

        } else {

            c.updateSong(songDAO, name.getText(), tableExample.getSelectionModel().getSelectedItem(), Integer.parseInt(email.getText()));
        }

        try {
            back();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void back() throws IOException {
            MainApp.setRoot("TestTable");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();

    }

    public void showData(){
        if (id != 0) {
            songDAO = new SongDAO(id);
            name.setText(songDAO.getName());
            email.setText(songDAO.getDuration().toString());
            tableExample.getSelectionModel().select((Disc)songDAO.getDisc());
        }
    }

    public void updateTable() {
        _list = FXCollections.observableList(DiscDAO.listAll());
        tableExample.setItems(_list);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReleaseDate().toString()));
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArtist().getName()));
    }

}
