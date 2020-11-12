package org.ciclo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import org.ciclo.model.Artist;
import org.ciclo.model.ArtistDAO;
import org.ciclo.model.User;
import org.ciclo.model.UserDAO;

public class UserTableController implements Initializable {

    @FXML
    TableView<User> tableExample;
    @FXML
    TableColumn<User, String> name;
    @FXML
    TableColumn<User, String> email;
    @FXML
    TableColumn<User, String> photo;
    @FXML
    TextField filter;

    private ObservableList<User> _list;
    private FilteredList<User> _listUserFiltered;

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

        Predicate<User> songPredicate = i -> i.getName().startsWith(filter.getText());
        if (_listUserFiltered != null) {
            _listUserFiltered.setPredicate(songPredicate);
        }
    }

    public void updateTable() {
        _list = FXCollections.observableList(UserDAO.listAll());
        _listUserFiltered = new FilteredList<>(_list);
        tableExample.setItems(_listUserFiltered);
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        photo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoto()));
    }

    public void delete() {

        if (tableExample.getSelectionModel().getSelectedItem() != null) {
            UserDAO userDAO = new UserDAO(tableExample.getSelectionModel().getSelectedItem());
            PopUpControler pop = Utils.popUpWithController("Borrado", "¿Seguro que desea borrar: " + userDAO.getName(), true);
            if (pop.getAcept()) {
                updateTable();
            }
        }
    }


public void form() throws IOException {
        MainApp.setRoot("UserCreateForm");
    }

    public void update() {
        Parent root;

try {
            FXMLLoader loader = new FXMLLoader(MainApp.class  

    .getResource("/View/fxml/UserCreateForm.fxml"));
            root  = loader.load();
    UserFormController test = loader.getController();

    test.setId (tableExample.getSelectionModel

    ().getSelectedItem() !=null? tableExample.getSelectionModel().getSelectedItem().getId():0);
    test.showData ();
    Scene scene = this.tableExample.getScene();

    scene.setRoot (root);
}
catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back() throws IOException {
        MainApp.setRoot("Main");
    }
}
