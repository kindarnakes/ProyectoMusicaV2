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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.ciclo.MainApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.ciclo.Utils.Utils;
import org.ciclo.model.Artist;
import org.ciclo.model.ArtistDAO;
import org.ciclo.model.Disc;
import org.ciclo.model.PlaylistDAO;

public class ArtistTableController implements Initializable {

    @FXML
    TableView<Artist> tableExample;
    @FXML
    TableColumn<Artist, String> c1;
    @FXML
    TableColumn<Artist, String> c2;
    @FXML
    TableColumn<Artist, String> c3;
    @FXML
    TextField filter;

    private ObservableList<Artist> _list;
    private FilteredList<Artist> _listArtistFiltered;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        tableExample.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 ) {
                    update();
                }
            });

        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filter();
        });

    }

    private void filter() {

        Predicate<Artist> songPredicate = i -> i.getName().startsWith(filter.getText());
        if( _listArtistFiltered != null){_listArtistFiltered.setPredicate(songPredicate);}
    }

    public void updateTable() {
        _list = FXCollections.observableList(ArtistDAO.List_All_Artist());
        _listArtistFiltered = new FilteredList<>(_list);
        tableExample.setItems(_listArtistFiltered);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFrom()));
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoto()));
    }

    public void delete() {

        if (tableExample.getSelectionModel().getSelectedItem() != null){
            ArtistDAO artistDAO = new ArtistDAO(tableExample.getSelectionModel().getSelectedItem());
            PopUpControler pop = Utils.popUpWithController("Borrado", "¿Seguro que desea borrar: " + artistDAO.getName(), true);
            if(pop.getAcept()) {
                artistDAO.loadDiscs();
                if(artistDAO.getDiscs().isEmpty()){
                    artistDAO.remove_Artist();
                    updateTable();
                }else{
                    Utils.popUp("Error", "Primero debe borrar los discos");
                }
            }
        }
    }

    public void form() throws IOException {
        MainApp.setRoot("ArtistForm");
    }

    public void update() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/fxml/ArtistForm.fxml"));
            root = loader.load();
            ArtistFormController test = loader.getController();
            test.setId(tableExample.getSelectionModel().getSelectedItem() !=null? tableExample.getSelectionModel().getSelectedItem().getId():0);
            test.showData();
            Scene scene = this.tableExample.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back() throws IOException {
        MainApp.setRoot("Main");
    }

}
