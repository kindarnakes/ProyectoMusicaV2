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

import org.ciclo.model.ArtistDAO;
import org.ciclo.model.DiscDAO;
import org.ciclo.model.Song;
import org.ciclo.model.SongDAO;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.net.ConnectException;
import java.util.List;

public class Run {



    public static void main(String[] args) {
        MainApp.main(args);
/*
        SongDAO.listAll().forEach(song -> {
            System.out.println(song.getDisc().getArtist().getName());
        });

        System.out.println("asda");

        System.out.println(SongDAO.listById(1));



 */








}}
    /*
        EntityManager manager=Connect.getManager();
        List<Song> autores=manager.createQuery("FROM Autor").getResultList();
        System.out.println("Autores: "+autores.size());
        for(Song a:autores) {
            System.out.println(a);
        }
        manager.close();


     */