package org.ciclo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.ciclo.MainApp;
import org.ciclo.Utils.Utils;
import org.ciclo.model.Disc;
import org.ciclo.model.DiscDAO;
import org.ciclo.model.SongDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SongFormController implements Initializable {

    @FXML
    TextField name;
    @FXML
    Spinner<Integer> duration;
    @FXML
    Label error;

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

        if (name.getText() != null && !name.getText().equals("")) {
            if (tableExample.getSelectionModel().getSelectedItem() != null) {
                if (id == 0) {

                    c.createSong(name.getText(), tableExample.getSelectionModel().getSelectedItem(), duration.getValue(), null);

                } else {

                    c.updateSong(songDAO, name.getText(), tableExample.getSelectionModel().getSelectedItem(), duration.getValue());
                }

                try {
                    back();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Utils.popUp("Debe seleccionar disco", "Debe seleccionar a que disco pertenece la canción en la tabla");
            }
        } else {
            error.setText("Debe escribir un nombre válido");
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void back() throws IOException {
        MainApp.setRoot("SongTable");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        duration.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 3600, 120));

    }

    public void showData() {
        if (id != 0) {
            songDAO = new SongDAO(id);
            name.setText(songDAO.getName());
            duration.getValueFactory().setValue(songDAO.getDuration());
            tableExample.getSelectionModel().select(songDAO.getDisc());
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
