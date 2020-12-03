package org.ciclo.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.ciclo.MainApp;
import org.ciclo.model.Artist;
import org.ciclo.model.ArtistDAO;
import org.ciclo.model.Disc;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ArtistFormController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField from;
    @FXML
    TextField photo;
    @FXML
    TableView<Disc> table;
    @FXML
    TableColumn<Disc, String> c1;
    @FXML
    TableColumn<Disc, LocalDate> c2;

    int id = 0;
    ArtistDAO artist;

    public void save() {

        if (id == 0) {
            Artist artist = new Artist(name.getText(), photo.getText(), from.getText());
            ArtistDAO artistDAO = new ArtistDAO(artist);
            artistDAO.Insert_Artist();
        } else {
            ArtistDAO artistDAO = artist;
            artistDAO.setName(name.getText());
            artistDAO.setNationality(from.getText());
            artistDAO.setPhoto(photo.getText());
            artistDAO.Update_Artist();
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
        MainApp.setRoot("ArtistTable");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void showData() {
        if (id != 0) {
            artist = new ArtistDAO(id);
            name.setText(artist.getName());
            from.setText(artist.getFrom());
            photo.setText(artist.getPhoto());
            artist.loadDiscs();

            table.setItems(FXCollections.observableList(new ArrayList(artist.getDiscs())));
            c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            c2.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getReleaseDate()));
        }
    }

}
