package org.ciclo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


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
        List<Artist> artists = new ArrayList<>();
        return artists;
    }

    /**
     * List artists by id
     *
     * @param id unique for all the artist
     * @return the artist with that id
     */
    public static Artist List_Artist_By_Id(Integer id) {
        Artist artist = new Artist();
        return artist;
    }

    /**
     * List artists by name
     *
     * @param name unique for all the artist
     * @return the artist with that name
     */

    public static Artist List_Artist_By_Name(String name) {
        Artist artist = new Artist();
        return artist;
    }

    /**
     * Save and insert a new artist
     *
     * @return true if the artist has been inserted, false if not
     */
    public boolean Insert_Artist() {
        boolean result = false;

        return result;
    }

    /**
     * Update a artist
     *
     * @return true if the artist has been updated, false if not
     */

    public boolean Update_Artist() {
        boolean result = false;

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

        return result;
    }

    /**
     * Load all the disc from the artist
     *
     * @return
     */

    public boolean loadDiscs() {
        boolean loaded = false;

        return loaded;
    }


}
