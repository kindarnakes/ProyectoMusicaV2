package org.ciclo.Utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.ciclo.MainApp;
import org.ciclo.controller.PopUpControler;

import java.io.IOException;

public class Utils {
    /**
     * Show modal window
     *
     * @param title   title of the new window
     * @param message message of the new window
     */
    public static void popUp(String title, String message) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/fxml/PopUp.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            PopUpControler popUpControler = loader.getController();
            popUpControler.setMensaje(message);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PopUpControler popUpWithController(String title, String message, boolean acceptWindow) {
        Parent root;
        PopUpControler pop = null;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/fxml/PopUp.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            PopUpControler popUpControler = loader.getController();
            popUpControler.setMensaje(message);
            stage.setScene(new Scene(root));
            if (acceptWindow) {
                popUpControler.setAceptionWindow();
            }
            stage.showAndWait();
            pop = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pop;
    }


    public static void newWindow(String view, String title, Modality modal) {
        try {
            Parent root = FXMLLoader.load(MainApp.class.getResource(view));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(modal);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object newWindowWithController(String view, String title, Modality modal) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(view));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(modal);
            controller = loader.getController();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }
}