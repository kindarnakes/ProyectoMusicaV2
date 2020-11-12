package org.ciclo.controller;

import javafx.stage.Modality;
import org.ciclo.MainApp;
import org.ciclo.Utils.Utils;

import java.io.IOException;

public class MainWindowController {

    public void songs() throws IOException {
        MainApp.setRoot("SongTable");
    }
    public void discs() throws IOException {
        MainApp.setRoot("DiscTable");
    }
    public void playlists() throws IOException {
        MainApp.setRoot("PlaylistTable");
    }
    public void users(){
        Utils.newWindow("/View/fxml/SongTable.fxml", "Canciones", Modality.APPLICATION_MODAL);
    }
    public void artists(){
        Utils.newWindow("/View/fxml/SongTable.fxml", "Canciones", Modality.APPLICATION_MODAL);
    }
}
