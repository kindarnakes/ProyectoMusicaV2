package org.ciclo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.ciclo.model.connectManager.Connect;

public class ArtistDAO extends Artist {

    /**
     * Constructor
     */
    public ArtistDAO() {
        super();
    }

    /**
     * Parametrized constructor
     *
     * @param a Artis to update
     */
    public ArtistDAO(Artist a) {
        this.setId(a.getId());
        this.setName(a.getName());
        this.setNationality(a.getFrom());
        this.setPhoto(a.getPhoto());
    }

    /**
     * Constructor
     *
     * @param id id of the artist
     */
    public ArtistDAO(Integer id) {
        this(ArtistDAO.List_Artist_By_Id(id));
    }

    /**
     * List all the artist
     *
     * @return All the artist
     */
    public static List<Artist> List_All_Artist() {

        EntityManager manager = Connect.getManager();
        List<Artist> artists = manager.createQuery("FROM Artist").getResultList();
        for (Artist artist : artists) {
            System.out.println(artist);
        }
        manager.close();

        return artists;
    }

    /**
     * List artists by id
     *
     * @param id unique for all the artist
     * @return the artist with that id
     */
    public static Artist List_Artist_By_Id(Integer id) {
        EntityManager manager = Connect.getManager();
        Artist artist = manager.find(Artist.class, id);
        System.out.println(artist);
        manager.close();
        return artist;
    }

    /**
     * List artists by name
     *
     * @param name unique for all the artist
     * @return the artist with that name
     */
    public static Artist List_Artist_By_Name(String name) {

        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Query qu = manager.createNamedQuery("getNamed");
        qu.setParameter("name", name);
        Artist artist = (Artist) qu.getSingleResult();
        manager.getTransaction().commit();
        manager.close();
        return artist;

    }

    /**
     * Save and insert a new artist
     *
     * @return true if the artist has been inserted, false if not
     */
    public boolean Insert_Artist() {
        boolean result = false;

        Artist artist = new Artist(this.getName(), this.getPhoto(), this.getFrom());
        artist.setDiscs(this.getDiscs());
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        if (artist != null) {
            manager.persist(artist);
        }
        manager.getTransaction().commit();
        manager.close();
        return result;
    }

    /**
     * Update a artist
     *
     * @return true if the artist has been updated, false if not
     */
    public boolean Update_Artist() {
        boolean result = false;

        Artist artist = new Artist(this.getName(), this.getPhoto(), this.getFrom());

        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        if (artist != null) {
            manager.merge(artist);
            result = true;
        }

        manager.getTransaction().commit();
        manager.close();

        return result;
    }

    /**
     * Remove a artist
     *
     * @param id unique for all the artist
     * @return true if the artist has been removed, false if not
     */
    public static boolean Remove_Artist_by_Id(Integer id) {
        boolean result = false;
        Artist artist = new Artist();
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();

        if (artist != null) {
            artist = manager.find(Artist.class, id);
            manager.remove(artist);
            result = true;
        }

        manager.getTransaction().commit();
        manager.close();
        return result;
    }

    /**
     * Remove a artist by name
     *
     * @param name unique for all the artist
     * @return true if the artist has been removed, false if not
     */
    public static boolean Remove_Artist_by_Name(String name) {
        boolean result = false;
       
        return result;
    }

    /**
     * Remove a artist
     *
     * @return true if the artist has been removed, false if not
     */
    public boolean remove_Artist() {
        boolean result = false;
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Artist a = manager.find(Artist.class, this.getId());
        if (a != null) {
            manager.remove(a);
            result = true;
        }
        manager.getTransaction().commit();
        manager.close();
        return result;
    }
    
    /**
     * Load all the disc from the artist
     *
     * @return
     */
    public boolean loadDiscs() {      
        boolean loaded = false;
        
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Artist a = new Artist(this.getName(),this.getFrom(),this.getPhoto());
        a.setDiscs(this.getDiscs());  
        a.setId(this.getId());
        a = manager.merge(a);
        a.getDiscs().forEach(disc -> {
        });
        this.setDiscs(a.getDiscs());
        manager.getTransaction().commit();
        manager.close();
        if(this.getDiscs()!= null){
            loaded = true;
        }
        return loaded;
        
    }

}
