package org.ciclo;

import org.ciclo.model.Artist;
import org.ciclo.model.Disc;
import org.ciclo.model.Playlist;
import org.ciclo.model.Song;
import org.ciclo.model.connectManager.Connect;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Run {



    public static void main(String[] args) {
        //MainApp.main(args);
        EntityManager manager = Connect.getManager();


        /*Playlist s = manager.find(Playlist.class, 1);
        System.out.println(s.getName());*/


        //Artist a = manager.find(Artist.class, 1);
        Disc d = manager.find(Disc.class, 1);
        Song s = new Song("prueba", 56, d);

        manager.getTransaction().begin();
        manager.persist(s);
        System.out.println(s.getId() + " " + s.getDisc().getName() + " " + s.getDisc().getArtist().getName());
        manager.getTransaction().commit();



        /*Artist a = ArtistDAO.List_Artist_By_Id(2);
        ArtistDAO artistDAO = new ArtistDAO(a);

        artistDAO.loadDiscs();

        artistDAO.getDiscs().forEach((Disc) ->{
            DiscDAO disc = new DiscDAO((Disc) Disc);
            disc.loadSongs();
            System.out.println("Autor: " + disc.getArtist().getName() + " Disco: " + disc.getName());
            disc.getSongs().forEach((song) ->{
                System.out.println("\t" + song.getName());
            });
        } );
        System.out.println();

        List<Disc> song = DiscDAO.listByName("19 dias y 500 noches");
        song.forEach((s)->{
            System.out.println(s.getName() + " " + s.getArtist().getName());
        });*/

    }
}
