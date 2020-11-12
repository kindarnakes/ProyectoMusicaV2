package org.ciclo.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.ciclo.MainApp;
import org.ciclo.Utils.Utils;
import org.ciclo.model.ISong;
import org.ciclo.model.Song;
import org.ciclo.model.SongDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
    @FXML
    RadioButton titleFilter;
    @FXML
    RadioButton artistFilter;
    @FXML
    RadioButton discFilter;
    @FXML
    TextField filter;

    private ObservableList<Song> _list;
    private FilteredList<Song> _listFiltered;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        tableExample.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                update();
            }
        });
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filter();
        });
    }

    public void updateTable() {
        _list = FXCollections.observableList(SongDAO.listAll());
        _listFiltered = new FilteredList<>(_list);
        tableExample.setItems(_listFiltered);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDuration()).asObject());
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getName()));
        c4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisc().getArtist().getName()));
    }

    public void delete() {
        if (tableExample.getSelectionModel().getSelectedItem() != null){
            SongDAO songDAO = new SongDAO(tableExample.getSelectionModel().getSelectedItem());
            PopUpControler pop = Utils.popUpWithController("Borrado", "¿Seguro que desea borrar: " + songDAO.getName(), true);
            if(pop.getAcept()) {
                songDAO.remove();
                updateTable();
            }
        }
    }

    public void form() throws IOException {
        MainApp.setRoot("SongForm");
    }

    public void update() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/fxml/SongForm.fxml"));
            root = loader.load();
            SongFormController test = loader.getController();
            test.setId(tableExample.getSelectionModel().getSelectedItem() != null ? tableExample.getSelectionModel().getSelectedItem().getId() : 0);
            test.showData();
            Scene scene = this.tableExample.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filter(){
        if(titleFilter.isSelected()){
            Predicate<Song> songPredicate = i -> i.getName().startsWith(filter.getText());
            if(_listFiltered != null){_listFiltered.setPredicate(songPredicate);}
        }else if(artistFilter.isSelected()){

            Predicate<Song> songPredicate = i -> i.getDisc().getArtist().getName().startsWith(filter.getText());
            if(_listFiltered != null){_listFiltered.setPredicate(songPredicate);}
        }else if(discFilter.isSelected()){
            Predicate<Song> songPredicate = i -> i.getDisc().getName().startsWith(filter.getText());
            if(_listFiltered != null){_listFiltered.setPredicate(songPredicate);}

        }
    }

    public void title(){
        artistFilter.setSelected(false);
        discFilter.setSelected(false);
        titleFilter.setSelected(true);
    }
    public void artist(){
        artistFilter.setSelected(true);
        discFilter.setSelected(false);
        titleFilter.setSelected(false);
    }
    public void discFilter(){
        artistFilter.setSelected(false);
        discFilter.setSelected(true);
        titleFilter.setSelected(false);
    }

}
