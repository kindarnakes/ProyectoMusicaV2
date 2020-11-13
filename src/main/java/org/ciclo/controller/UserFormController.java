/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.ciclo.MainApp;
import org.ciclo.Utils.Utils;
import org.ciclo.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * @author Agustín
 */
public class UserFormController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField email;
    @FXML
    TextField photo;
    @FXML
    TableView<Playlist> table1;
    @FXML
    TableColumn<Playlist, String> created;
    @FXML
    TableView<IPlaylists> table2;
    @FXML
    TableView<IPlaylists> table;
    @FXML
    TableColumn<Playlist, String> c1;
    @FXML
    TableColumn<Playlist, String> c2;
    @FXML
    TableColumn<Playlist, String> c3;
    @FXML
    TableColumn<Playlist, String> c11;
    @FXML
    TableColumn<Playlist, String> c21;
    @FXML
    TableColumn<Playlist, String> c31;
    @FXML
    Label error;
    @FXML
    Label error1;
    @FXML
    TextField filter;

    ObservableList<Playlist> _list;
    ObservableList<IPlaylists> _listSubscribed;
    FilteredList<Playlist> _listFiltered;
    FilteredList<IPlaylists> _listSubscribedFiltered;

    Controller c = new Controller();
    UserDAO userDAO;

    int id = 0;

    public void showData() {
        userDAO = new UserDAO(id);
        name.setText(userDAO.getName());
        email.setText(userDAO.getEmail());
        photo.setText(userDAO.getPhoto());
        userDAO.loadSubscribed();
        userDAO.loadCreated();

        _listSubscribed = FXCollections.observableList(new ArrayList<>(userDAO.getSubscribed()));
        _listSubscribedFiltered = new FilteredList<>(_listSubscribed);
        table.setItems(_listSubscribedFiltered);
        c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCreator().getName()));

        table2.setItems(FXCollections.observableList(new ArrayList<>(userDAO.getCreated())));
        created.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));


    }

    public void setId(int i) {
        id = i;
    }

    public void updateTable() {
        _list = FXCollections.observableList(PlaylistDAO.List_All_Playlist());
        for (IPlaylists p : userDAO.getSubscribed()) {
            _list.remove(p);
        }
        _listFiltered = new FilteredList<>(_list);
        table1.setItems(_listFiltered);
        c11.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        c21.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        c31.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCreator().getName()));
    }

    public void save() throws IOException {
        if (name.getText() != null && !name.getText().equals("")) {
            if (email.getText() != null && !email.getText().equals("") && email.getText().matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")) {
                if(id == 0){
                    if (c.createUser(name.getText(), email.getText(), photo.getText())) {
                        update();
                        back();
                    } else {
                        Utils.popUp("Error de guardado", "No se ha podido guardar");
                    }
                }else{
                    if (c.updateUser(this.userDAO, name.getText(), email.getText(), photo.getText())) {
                        update();
                        back();
                    } else {
                        Utils.popUp("Error de guardado", "No se ha podido guardar");
                    }
                }
            }else{
                error1.setText("Debe escribir un correo válido");
            }
        } else {
            error.setText("Debe escribir un nombre válido");
        }
    }

    private void update() {
        this.id = UserDAO.listByEmail(email.getText()).getId();

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/fxml/UserUpdateForm.fxml"));
            root = loader.load();
            UserFormController test = loader.getController();
            test.setId(id);
            test.showData();
            Scene scene = this.name.getScene();
            scene.getWindow().setHeight(root.prefHeight(0) + 20);
            scene.getWindow().setWidth(root.prefWidth(0) + 20);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back() throws IOException {
        MainApp.setRoot("UserTable");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(table != null && table1 != null && filter != null){
            table1.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    Playlist playlist = table1.getSelectionModel().getSelectedItem();
                    if (playlist != null && c.userUnSubscribePlaylist(this.userDAO, playlist)) {
                        _list.remove(playlist);
                        _listSubscribed.add(playlist);
                    }
                }
            });

            table.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    Playlist playlist = (Playlist) table.getSelectionModel().getSelectedItem();
                    if (playlist != null && c.userSubscribePlaylist(this.userDAO, playlist)) {
                        if (_list == null || _list.size() == 0) {
                            updateTable();
                        }
                        _list.add(playlist);
                        _listSubscribed.remove(playlist);
                    }

                }
            });

            filter.textProperty().addListener((observable, oldValue, newValue) -> {
                filter();
            });
        }
    }

    private void filter() {

        Predicate<IPlaylists> playPredicate = i -> i.getName().startsWith(filter.getText());
        if (_listFiltered != null) {
            _listFiltered.setPredicate(playPredicate);
        }
        _listSubscribedFiltered.setPredicate(playPredicate);
    }
}
