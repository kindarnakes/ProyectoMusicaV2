package org.ciclo;

import org.ciclo.model.Artist;
import org.ciclo.model.ArtistDAO;
import org.ciclo.model.Song;
import org.ciclo.model.SongDAO;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        //MainApp.main(args);

        List<Song> songs = SongDAO.listByName("19 dias y 500 noches");
        songs.forEach((song) -> {
            System.out.println("---->" + song.getId() + ". " + song.getName() + " ----- Disco: " + song.getDisc().getName() + " ------- Artista: " + song.getDisc().getArtist().getName());
        });

    }
}
