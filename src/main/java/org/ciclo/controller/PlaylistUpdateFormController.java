package org.ciclo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.util.function.Predicate;

public class PlaylistUpdateFormController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextArea description;
    @FXML
    ChoiceBox<String> creator;
    @FXML
    Label error;
    @FXML
    RadioButton titleFilter;
    @FXML
    RadioButton artistFilter;
    @FXML
    RadioButton discFilter;
    @FXML
    TextField filter;

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

    private final Controller c = new Controller();

    @FXML
    TableView<Song> tableExample1;
    @FXML
    TableColumn<Song, String> c11;
    @FXML
    TableColumn<Song, String> c21;
    @FXML
    TableColumn<Song, String> c31;
    @FXML
    TableColumn<Song, String> c41;

    ObservableList<Song> _list;
    ObservableList<Song> _listIncluded;
    FilteredList<Song> _listFiltered;
    FilteredList<Song> _listIncludedFiltered;
    PlaylistDAO playlistDAO = null;

    int id = 0;

    public void save() throws IOException {

        String userEmail = creator.getSelectionModel().getSelectedItem();
        User u = c.listUserByEmail(userEmail != null ? userEmail : "");
        if (u != null) {
            if (name.getText() != null && !name.getText().equals("")) {
                if (c.updatePlaylist(playlistDAO, name.getText(), description.getText(), u)) {
                    back();
                } else {
                    Utils.popUp("Error de guardado", "No se ha podido guardar");
                }
            } else {
                error.setText("Debe escribir un nombre válido");
            }
        } else {
            Utils.popUp("Error", "Debe elegir el usuario");
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
                if (song != null && c.addSongToPlaylist(playlistDAO, song)) {
                    _list.remove(song);
                    _listIncluded.add(song);
                }
            }
        });

        tableExample1.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Song song = (Song) tableExample1.getSelectionModel().getSelectedItem();
                if (song != null && c.removeSongToPlaylist(playlistDAO, song)) {
                    if (_list == null || _list.size() == 0) {
                        updateTable();
                    }
                    _list.add(song);
                    _listIncluded.remove(song);
                }

            }
        });
        for (User u : c.listAllUser()) {
            creator.getItems().add(u.getEmail());
        }
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filter();
        });

    }

    public void showData() {
        if (id != 0) {
            playlistDAO = new PlaylistDAO(id);
            name.setText(playlistDAO.getName());
            description.setText(playlistDAO.getDescription());
            playlistDAO.loadSongs();

            _listIncluded = FXCollections.observableList(new ArrayList<>(playlistDAO.getSongs()));
            _listIncludedFiltered = new FilteredList<>(_listIncluded);
            tableExample1.setItems(_listIncludedFiltered);
            c11.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            c21.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getName()));
            c31.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getArtist().getName()));
            c41.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getReleaseDate().toString()));
            creator.getSelectionModel().select(playlistDAO.getCreator().getEmail());
        }
    }

    public void updateTable() {
        _list = FXCollections.observableList(SongDAO.listAll());
        for (Song p : _listIncluded) {
            _list.remove(p);
        }
        _listFiltered = new FilteredList<>(_list);
        tableExample.setItems(_listFiltered);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getName()));
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getArtist().getName()));
        c4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getReleaseDate().toString()));
    }

    public void filter() {
        if (titleFilter.isSelected()) {
            Predicate<Song> songPredicate = i -> i.getName().startsWith(filter.getText());
            Predicate<Song> isongPredicate = i -> i.getName().startsWith(filter.getText());
            if (_listFiltered != null) {
                _listFiltered.setPredicate(songPredicate);
            }
            _listIncludedFiltered.setPredicate(isongPredicate);
        } else if (artistFilter.isSelected()) {

            Predicate<Song> songPredicate = i -> i.getDisc().getArtist().getName().startsWith(filter.getText());
            Predicate<Song> isongPredicate = i -> i.getDisc().getArtist().getName().startsWith(filter.getText());
            if (_listFiltered != null) {
                _listFiltered.setPredicate(songPredicate);
            }
            _listIncludedFiltered.setPredicate(isongPredicate);
        } else if (discFilter.isSelected()) {
            Predicate<Song> songPredicate = i -> i.getDisc().getName().startsWith(filter.getText());
            Predicate<Song> isongPredicate = i -> i.getDisc().getName().startsWith(filter.getText());
            if (_listFiltered != null) {
                _listFiltered.setPredicate(songPredicate);
            }
            _listIncludedFiltered.setPredicate(isongPredicate);

        }
    }

    public void title() {
        artistFilter.setSelected(false);
        discFilter.setSelected(false);
        titleFilter.setSelected(true);
    }

    public void artist() {
        artistFilter.setSelected(true);
        discFilter.setSelected(false);
        titleFilter.setSelected(false);
    }

    public void discFilter() {
        artistFilter.setSelected(false);
        discFilter.setSelected(true);
        titleFilter.setSelected(false);
    }

}
