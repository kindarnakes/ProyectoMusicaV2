package org.ciclo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PlaylistDAO extends Playlist {
    /**
     * Constructor
     */

    public PlaylistDAO() {
        super();
    }

    /**
     * Parametrized constructor
     *
     * @param p playlist to update
     */

    public PlaylistDAO(Playlist p) {
        this.setCreator(p.getCreator());
        this.setId(p.getId());
        this.setName(p.getName());
        this.setDescription(p.getDescription());
        this.setSongs(p.getSongs());
        this.setSusbcribers(p.getSusbcribers());
    }

    /**
     * Constructor
     *
     * @param id id of the playlist
     */

    public PlaylistDAO(Integer id) {
        this(PlaylistDAO.List_Playlist_By_Id(id));
    }

    /**
     * List all playlist
     *
     * @return All playlist
     */

    public static List<Playlist> List_All_Playlist() {
        List<Playlist> playlist = new ArrayList<>();

        return playlist;
    }

    /**
     * List all the playlist with that id
     *
     * @param id unique for all the playlist
     * @return The playlist with that ide
     */

    public static Playlist List_Playlist_By_Id(Integer id) {
        Playlist p = new Playlist();

        return p;
    }

    /**
     * List all the playlist with that name
     *
     * @param name of the playlists
     * @return The playlsts whit that name
     */

    public static List<Playlist> listByName(String name) {
        List<Playlist> playlists = new ArrayList<>();

        return playlists;
    }

    /**
     * List all the playlist with that song
     *
     * @param song of the playlist
     * @return The playlist with that songs
     */


    public static List<Playlist> listBySong(Song song) {
        List<Playlist> playlists = new ArrayList<>();

        return playlists;
    }

    /**
     * List all playlist with that Creator
     *
     * @param user the creator of the playlist
     * @return The playlist with that creator
     */

    public static List<Playlist> listUserCreated(User user) {
        List<Playlist> playlists = new ArrayList<>();

        return playlists;
    }

    /**
     * Update a playlist
     *
     * @return true if the playlist has been updated, false if not
     */


    public boolean update() {
        boolean update = false;


        return update;
    }

    /**
     * Remove a playlist
     *
     * @param id unique of all the playlist
     * @return true if the playlist has been removed, false if not
     */

    public static boolean remove(Integer id) {
        boolean removed = false;

        return removed;
    }

    /**
     * Remove a playlist
     *
     * @return true if the playlist has been removed, false if not
     */


    public boolean remove() {
        boolean removed = false;

        return removed;
    }

    /**
     * Save and insert a playlist
     *
     * @return true if the playlist has been inserted, false if not
     */

    public boolean save() {
        boolean saved = false;

        return saved;
    }

    /**
     * Add song to a playlist
     *
     * @param song the song to add
     * @return true if the playlist has been added, false if not
     */

    public boolean addSong(Song song) {
        boolean added = false;

        return added;
    }

    /**
     * Remove a song in that playlist
     *
     * @param song the song to remove
     * @return true if the song has been removed, false if not
     */

    public boolean removeSong(Song song) {
        boolean remove = false;

        return remove;
    }

    /**
     * List all the playlist with that suscribers
     *
     * @param user the user of the playlist
     * @return The playlist with that suscribers
     */


    public static List<Playlist> listPlaylistSuscribers(User user) {
        List<Playlist> playlist = new ArrayList<>();

        return playlist;

    }

    /**
     * Load the song of the playlist
     *
     * @return true if the song has been loaded, false if not
     */

    public boolean loadSongs() {
        boolean loaded = false;


        return loaded;
    }

}
