package org.ciclo.controller;

import org.ciclo.MainApp;

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

    public void users() throws IOException {
        MainApp.setRoot("UserTable");
    }

    public void artists() throws IOException {
        MainApp.setRoot("ArtistTable");
    }
}
