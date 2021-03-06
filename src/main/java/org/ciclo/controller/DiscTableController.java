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
import org.ciclo.model.Disc;
import org.ciclo.model.DiscDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class DiscTableController implements Initializable {

    @FXML
    TableView<Disc> tableExample;
    @FXML
    TableColumn<Disc, String> c1;
    @FXML
    TableColumn<Disc, String> c2;
    @FXML
    TableColumn<Disc, String> c3;
    @FXML
    TableColumn<Disc, String> c4;
    @FXML
    TextField filter;

    private ObservableList<Disc> _list;
    private FilteredList<Disc> _listFiltered;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        Predicate<Disc> songPredicate = i -> i.getName().startsWith(filter.getText());
        if (_listFiltered != null) {
            _listFiltered.setPredicate(songPredicate);
        }
    }

    public void update() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/fxml/DiscForm.fxml"));
            root = loader.load();
            DiscFormController test = loader.getController();
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

    public void updateTable() {
        _list = FXCollections.observableList(DiscDAO.listAll());
        _listFiltered = new FilteredList<>(_list);
        tableExample.setItems(_listFiltered);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReleaseDate().toString()));
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoto()));
        c4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArtist().getName()));
    }

    public void form() throws IOException {
        MainApp.setRoot("DiscForm");
    }

    public void delete() {
        if (tableExample.getSelectionModel().getSelectedItem() != null) {
            DiscDAO discDAO = new DiscDAO(tableExample.getSelectionModel().getSelectedItem());
            PopUpControler pop = Utils.popUpWithController("Borrado", "¿Seguro que desea borrar: " + discDAO.getName(), true);
            if (pop.getAcept()) {
                discDAO.loadSongs();
                if (!discDAO.getSongs().isEmpty()) {
                    Utils.popUp("Error", "Primero debe borrar las canciones");
                } else {
                    discDAO.remove();
                    updateTable();
                }
            }
        }
    }

    public void back() throws IOException {
        MainApp.setRoot("Main");
    }
}
