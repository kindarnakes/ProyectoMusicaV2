package org.ciclo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.ciclo.MainApp;
import org.ciclo.Utils.Utils;
import org.ciclo.model.Playlist;
import org.ciclo.model.PlaylistDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class PlaylistTableController implements Initializable {

    @FXML
    TableView<Playlist> tableExample;
    @FXML
    TableColumn<Playlist, String> c1;
    @FXML
    TableColumn<Playlist, String> c2;
    @FXML
    TableColumn<Playlist, String> c3;
    @FXML
    TextField filter;

    private ObservableList<Playlist> _list;
    private FilteredList<Playlist> _listFilteredList;


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

    private void filter() {

        Predicate<Playlist> playPredicate = i -> i.getName().startsWith(filter.getText());
        if( _listFilteredList != null){_listFilteredList.setPredicate(playPredicate);}
    }

    public void updateTable() {
        _list = FXCollections.observableList(PlaylistDAO.List_All_Playlist());
        _listFilteredList = new FilteredList<>(_list);
        tableExample.setItems(_listFilteredList);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCreator().getName()));
    }

    public void delete() {
        if (tableExample.getSelectionModel().getSelectedItem() != null){
            PlaylistDAO playlistDAO= new PlaylistDAO(tableExample.getSelectionModel().getSelectedItem());
            PopUpControler pop = Utils.popUpWithController("Borrado", "¿Seguro que desea borrar: " + playlistDAO.getName(), true);
            if(pop.getAcept()) {
                playlistDAO.remove();
                updateTable();
            }
        }
    }

    public void form() throws IOException {
        MainApp.setRoot("PlaylistCreateForm");
    }

    public void update() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/fxml/PlaylistUpdateForm.fxml"));
            root = loader.load();
            PlaylistUpdateFormController test = loader.getController();
            test.setId(tableExample.getSelectionModel().getSelectedItem() != null ? tableExample.getSelectionModel().getSelectedItem().getId() : 0);
            test.showData();
            Scene scene = this.tableExample.getScene();
            scene.getWindow().setHeight(root.prefHeight(0) + 20);
            scene.getWindow().setWidth(root.prefWidth(0) + 20);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void back() throws IOException {
        MainApp.setRoot("Main");
    }

}
