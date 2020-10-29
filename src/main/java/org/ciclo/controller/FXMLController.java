package org.ciclo.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class FXMLController implements Initializable {

    @FXML
    TreeTableView<String> tableExample;
    @FXML
    TreeTableColumn<String, String> c1;
    @FXML
    TreeTableColumn<String, String> c2;
    @FXML
    TreeTableColumn<String, String> c3;
    @FXML
    TreeTableColumn<String, String> c4;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
