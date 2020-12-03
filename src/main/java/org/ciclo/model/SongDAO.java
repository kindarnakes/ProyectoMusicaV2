package org.ciclo.model;

import org.ciclo.model.connectManager.Connect;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class SongDAO extends Song {

    static EntityManager manager;


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
        this.setDisc(s.getDisc()); //Pointer to same object
        this.setList(s.getLists()); //Pointer to same object
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
        manager = Connect.getManager();
        List<Song> songs = manager.createQuery("FROM Song").getResultList();
        manager.close();

        return songs;
    }

    /**
     * List the song with that id
     *
     * @param id unique for all the song
     * @return The song with that id
     */

    public static Song listById(Integer id) {
        EntityManager manager = Connect.getManager();
        Song song = manager.find(Song.class, id);
        System.out.println(song);
        manager.close();
        return song;
    }

    /**
     * List all the song with that name
     *
     * @param name the name of the song
     * @return The songs with that name
     */

    public static List<Song> listByName(String name) {
        EntityManager manager = Connect.getManager();
        List<Song> song = (List<Song>) manager.createQuery("Select s FROM Song s WHERE s.name = :songName").setParameter("songName", name).getResultList();

        return song;

    }


    /**
     * Update a song
     *
     * @return true if the song has been updated, false if not
     */

    public boolean update() {
        boolean update = false;
        Song song = new SongDAO();

        manager = Connect.getManager();
        manager.getTransaction().begin();
        if (song != null) {
            manager.merge(this);
            update = true;
        }

        manager.getTransaction().commit();
        manager.close();

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
        manager = Connect.getManager();
        manager.getTransaction().begin();
        manager.createQuery("DELETE s FROM Song s WHERE s._Id = :idSong").setParameter("idSong", id);
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
