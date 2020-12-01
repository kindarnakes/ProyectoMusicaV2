package org.ciclo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDAO extends Song {


    /**
     * Constructor
     */
    public SongDAO() {
        super();
    }

    /**
     * Parametrized constructor
     *
     * @param s song to update
     */

    public SongDAO(Song s) {
        this.setId(s.getId());
        this.setDuration(s.getDuration());
        this.setName(s.getName());
        this.setGenre(null);
        this.setDisc(s.getDisc()); //Pointer to same object
        this.setList(s.getLists()); //Pointer to same object
        this.setReproductions(null);
    }

    /**
     * Constructor
     *
     * @param id of the Song
     */

    public SongDAO(Integer id) {
        this(SongDAO.listById(id));
    }

    /**
     * List all the songs
     *
     * @return All the songs
     */

    public static List<Song> listAll() {
        List<Song> songs = new ArrayList<>();

        return songs;
    }

    /**
     * List the song with that id
     *
     * @param id unique for all the song
     * @return The song with that id
     */

    public static Song listById(Integer id) {
        Song song = new Song();

        return song;
    }

    /**
     * List all the song with that name
     *
     * @param name the name of the song
     * @return The songs with that name
     */

    public static List<Song> listByName(String name) {
        List<Song> songs = new ArrayList<>();

        return songs;
    }

    /**
     * Update a song
     *
     * @return true if the song has been updated, false if not
     */

    public boolean update() {
        boolean update = false;

        return update;
    }

    /**
     * Save and insert a somg
     *
     * @return true if the song has been inserted, false if not
     */

    public boolean save() {
        boolean saved = false;


        return saved;
    }

    /**
     * Remove a song with that id
     *
     * @param id unique for all the song
     * @return true if the song has been removed, false if not
     */

    public static boolean remove(Integer id) {
        boolean removed = false;

        return removed;
    }

    /**
     * Remove the song
     *
     * @return true if the song has been removed, false if not
     */

    public boolean remove() {
        boolean removed = false;

        return removed;
    }

    /**
     * List all the song in that disc
     *
     * @param disc the disc to insert the song
     * @return true if the song has been inserted in the disc, false if not
     */

    public static List<Song> searchByDisc(Disc disc) {
        List<Song> songs = new ArrayList<>();

        return songs;
    }

    /**
     * List all song in a playlist
     *
     * @param playlist the playlist that contains the discs
     * @return The list of the song in a playlist
     */

    public static List<Song> ListSongByPlaylist(Playlist playlist) {
        List<Song> songs = new ArrayList<>();

        return songs;
    }


}
