package org.ciclo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class PopUpControler {

    @FXML
    private Button aceptar;
    @FXML
    private Button cancel;
    @FXML
    protected Label estado;

    private boolean acept;

    @FXML
    public void initialize() {
        acept = false;
    }

    public void setAceptionWindow(){
        aceptar.setDisable(false);
        aceptar.setVisible(true);
        cancel.setText("Cancelar");
    }
    public void setMensaje(String s) {

        String error = "";
        String lines = s;
        while (lines.length() > 75) {
            error += lines.substring(0, 75) + "\n";
            lines = lines.substring(75);
        }
        error += lines;
        estado.setText(error);
    }

    public void back() {
        Stage stage = (Stage) this.aceptar.getScene().getWindow();
        stage.close();
    }

    public void acept(){
        acept = true;
        back();
    }

    public boolean getAcept(){
        return acept;
    }


}
