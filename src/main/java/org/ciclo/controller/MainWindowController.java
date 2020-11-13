package org.ciclo.controller;

import org.ciclo.MainApp;
import org.ciclo.Utils.Utils;

import java.io.IOException;

public class MainWindowController {

    public void songs() throws IOException {
        if(checkConnection())
        MainApp.setRoot("SongTable");
    }

    public void discs() throws IOException {
        if(checkConnection())
        MainApp.setRoot("DiscTable");
    }

    public void playlists() throws IOException {
        if(checkConnection())
        MainApp.setRoot("PlaylistTable");
    }

    public void users() throws IOException {
        if(checkConnection())
        MainApp.setRoot("UserTable");
    }

    public void artists() throws IOException {
        if(checkConnection())
        MainApp.setRoot("ArtistTable");
    }

    private boolean checkConnection(){
        boolean connect = Utils.checkConnection();
        if(!connect){
            Utils.popUp("Error", "No se puede conectar a la base de datos");
        }
        return connect;
    }
}
