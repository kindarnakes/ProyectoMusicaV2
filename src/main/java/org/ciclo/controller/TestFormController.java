package org.ciclo.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.ciclo.MainApp;
import org.ciclo.model.User;
import org.ciclo.model.UserDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestFormController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField email;
    @FXML
    TextField photo;

    int id = 0;
    User user;

    public void save() {

        if (id == 0) {
            User user = new User(name.getText(), photo.getText(), email.getText());
            UserDAO userDAO = new UserDAO(user);
            userDAO.save();
        } else {
            UserDAO userDAO = (UserDAO) user;
            userDAO.setName(name.getText());
            userDAO.setEmail(email.getText());
            userDAO.setPhoto(photo.getText());
            userDAO.update();
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
            MainApp.setRoot("TestTable");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void showData(){
        if (id != 0) {
            user = new UserDAO(id);
            name.setText(user.getName());
            email.setText(user.getEmail());
            photo.setText(user.getPhoto());
        }
    }

}
