package org.ciclo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.ciclo.MainApp;
import org.ciclo.Utils.Utils;
import org.ciclo.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlaylistCreateFormController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextArea description;
    @FXML
    Label error;
    @FXML
    ChoiceBox<String> creator;

    PlaylistDAO playlistDAO;

    Controller c = new Controller();

    public void save() throws IOException {
        String userEmail = creator.getSelectionModel().getSelectedItem();
        User u = c.listUserByEmail(userEmail!=null?userEmail:"");
        if (name.getText() != null && !name.getText().equals("") && u != null) {
            if(c.createPlaylist(name.getText(), description.getText(), u)){
                List<Playlist> playlists = c.listPlaylistByName(name.getText());
                playlistDAO = new PlaylistDAO(playlists.get(playlists.size()-1));
                update();
            }else{
                Utils.popUp("Error de guardado", "No se ha podido guardar");
            }
        } else {
            error.setText("Debe escribir un nombre válido");
        }
    }


    public void back() throws IOException {
        MainApp.setRoot("PlaylistTable");

    }

    public void update() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/fxml/PlaylistUpdateForm.fxml"));
            root = loader.load();
            PlaylistUpdateFormController test = loader.getController();
            test.setId(playlistDAO.getId());
            test.showData();
            Scene scene = this.name.getScene();
            scene.getWindow().setHeight(root.prefHeight(0) + 20);
            scene.getWindow().setWidth(root.prefWidth(0) + 20);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(User u:c.listAllUser()){
            creator.getItems().add(u.getEmail());
        }
    }




}
