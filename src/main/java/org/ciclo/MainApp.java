package org.ciclo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ciclo.model.Song;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;


public class MainApp extends Application {


    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loadFXML("Main");
        scene = new Scene(root, root.prefWidth(0), root.prefHeight(0));
        stage.setScene(scene);
        stage.setTitle("ProyectoMusica");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.getWindow().setHeight(root.prefHeight(0) + 20);
        scene.getWindow().setWidth(root.prefWidth(0) + 20);
        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/View/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        launch();


    }
}
