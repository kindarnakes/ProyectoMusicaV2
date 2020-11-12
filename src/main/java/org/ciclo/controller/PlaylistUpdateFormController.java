package org.ciclo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.ciclo.MainApp;
import org.ciclo.Utils.Utils;
import org.ciclo.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlaylistUpdateFormController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextArea description;

    @FXML
    TableView<Song> tableExample;
    @FXML
    TableColumn<Song, String> c1;
    @FXML
    TableColumn<Song, String> c2;
    @FXML
    TableColumn<Song, String> c3;
    @FXML
    TableColumn<Song, String> c4;

    private Controller c = new Controller();

    @FXML
    TableView<ISong> tableExample1;
    @FXML
    TableColumn<Song, String> c11;
    @FXML
    TableColumn<Song, String> c21;
    @FXML
    TableColumn<Song, String> c31;
    @FXML
    TableColumn<Song, String> c41;

    ObservableList<Song> _list;
    ObservableList<ISong> _listIncluded;
    PlaylistDAO playlistDAO = null;

    int id = 0;

    public void save() {

        if (name.getText() != null && !name.getText().equals("")) {
            if (tableExample.getSelectionModel().getSelectedItem() != null) {
                if (id == 0) {



                } else {


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
            //error.setText("Debe escribir un nombre válido");
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void back() throws IOException {
        MainApp.setRoot("PlaylistTable");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableExample.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Song song = tableExample.getSelectionModel().getSelectedItem();
                if( song != null && c.addSongToPlaylist(playlistDAO, song)){
                    _list.remove(song);
                    _listIncluded.add(song);
                }
            }
        });

        tableExample1.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Song song = tableExample.getSelectionModel().getSelectedItem();
                if( song != null && c.removeSongToPlaylist(playlistDAO, song)){
                    _list.add(song);
                    _listIncluded.remove(song);
                }

            }
        });

    }

    public void showData() {
        if (id != 0) {
            playlistDAO = new PlaylistDAO(id);
            name.setText(playlistDAO.getName());
            description.setText(playlistDAO.getDescription());
            playlistDAO.loadSongs();

            _listIncluded = FXCollections.observableList(new ArrayList<>(playlistDAO.getSongs()));
            tableExample1.setItems(_listIncluded);
            c11.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            c21.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getName()));
            c31.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getArtist().getName()));
            c41.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getReleaseDate().toString()));
        }
    }

    public void updateTable() {
        _list = FXCollections.observableList(SongDAO.listAll());
        for(ISong p: _listIncluded){
            _list.remove(p);
        }
        tableExample.setItems(_list);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getName()));
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getArtist().getName()));
        c4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getReleaseDate().toString()));
    }

}
