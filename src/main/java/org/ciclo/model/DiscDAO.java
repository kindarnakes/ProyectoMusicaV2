package org.ciclo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class DiscDAO extends Disc {
    /**
     * Constructor
     */
    public DiscDAO() {

        super();
    }

    /**
     * Parametrized constructor
     *
     * @param d Disc to update
     */

    public DiscDAO(Disc d) {
        this.setArtist(d.getArtist());
        this.setId(d.getId());
        this.setName(d.getName());
        this.setPhoto(d.getPhoto());
        this.setReleaseDate(d.getReleaseDate());
        this.setSongs(d.getSongs());

    }

    /**
     * Constructor
     *
     * @param id of the Disc
     */

    public DiscDAO(Integer id) {
        this(DiscDAO.listById(id));
    }

    /**
     * List all disc
     *
     * @return All discs
     */

    public static List<Disc> listAll() {
        List<Disc> disc = new ArrayList<>();

        return disc;
    }

    /**
     * List all disc by id
     *
     * @param id unique for all the discs
     * @return the disc with that id
     */

    public static Disc listById(Integer id) {
        Disc disc = new Disc();

        return disc;
    }

    /**
     * List all disc with that name
     *
     * @param name of the discs
     * @return the discs with that name
     */


    public static List<Disc> listByName(String name) {
        List<Disc> disc = new ArrayList<>();

        return disc;
    }

    /**
     * Update disc
     *
     * @return true if the disc has been updated, false if not
     */

    public boolean update() {
        boolean update = false;


        return update;
    }

    /**
     * Remove the disc with that id
     *
     * @param id unique for all the discs
     * @return true if the artist has been removed, false if not
     */

    public static boolean remove(Integer id) {
        boolean removed = false;

        return removed;
    }

    /**
     * Remove the disc
     *
     * @return true if the disc has been removed, false if not
     */

    public boolean remove() {
        boolean removed = false;

        return removed;
    }

    /**
     * Save and insert a disc
     *
     * @return true if the disc has been inserted, false if not
     */

    public boolean save() {
        boolean saved = false;

        return saved;
    }

    /**
     * List  the disc with that artist
     *
     * @param artist of that disc
     * @return Discs with that artist
     */

    public static List<Disc> searchByAuthor(Artist artist) {
        List<Disc> discs = new ArrayList<>();

        return discs;

    }

    /**
     * Load all the song by disc
     *
     * @return true if the song has been loaded, false if not
     */

    public boolean loadSongs() {
        boolean loaded = false;

        return loaded;
    }

}
