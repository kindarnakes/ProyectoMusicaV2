package org.ciclo.model;

import org.ciclo.model.connectManager.Connect;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        if(p instanceof PlaylistDAO){
            int i = p.getId();
            p = new Playlist(p.getName(), p.getDescription(), p.getCreator(), p.getSusbcribers(), p.getSongs());
            p.setId(i);
        }
        Session session = manager.unwrap(Session.class);
        if(p.getCreator().getId() != null) {
            session.load(p.getCreator(), p.getCreator().getId());
            this.setCreator(p.getCreator());
        }else{
            try{
            Query qu = manager.createNamedQuery("getUserAnon");
            User u = (User) qu.getSingleResult();
            this.setCreator(u);
            p.setCreator(u);
            }catch (PersistenceException ex){
            }
        }
            this.setId(p.getId());
            this.setName(p.getName());
            this.setDescription(p.getDescription());
            this.setSongs(p.getSongs());
            this.setSusbcribers(p.getSusbcribers());
        manager.getTransaction().commit();
        manager.close();
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
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Query qu = manager.createNamedQuery("getAllPlaylist");
        List<Playlist> playlist = qu.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return playlist;
    }

    /**
     * List all the playlist with that id
     *
     * @param id unique for all the playlist
     * @return The playlist with that ide
     */

    public static Playlist List_Playlist_By_Id(Integer id) {
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Playlist p = manager.find(Playlist.class, id);
        manager.getTransaction().commit();
        manager.close();
        return p;
    }

    /**
     * List all the playlist with that name
     *
     * @param name of the playlists
     * @return The playlsts whit that name
     */

    public static List<Playlist> listByName(String name) {
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Query qu = manager.createNamedQuery("getNamed");
        qu.setParameter("name", name);
        List<Playlist> playlist = qu.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return playlist;
    }

    /**
     * List all the playlist with that song
     *
     * @param song of the playlist
     * @return The playlist with that songs
     */


    public static List<Playlist> listBySong(Song song) {
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Query qu = manager.createNamedQuery("getBySong");
        qu.setParameter("id", song.getId());
        List<Playlist> playlist = qu.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return playlist;
    }

    /**
     * List all playlist with that Creator
     *
     * @param user the creator of the playlist
     * @return The playlist with that creator
     */

    public static List<Playlist> listUserCreated(User user) {
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Query qu = manager.createNamedQuery("getByUser");
        qu.setParameter("id", user.getId());
        List<Playlist> playlist = qu.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return playlist;
    }

    /**
     * Update a playlist
     *
     * @return true if the playlist has been updated, false if not
     */


    public boolean update() {
        boolean update = false;
        Playlist p = new Playlist(this.getName(), this.getDescription(), this.getCreator(), this.getSusbcribers(), this.getSongs());
        p.setId(this.getId());
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        p = manager.merge(p);
        manager.getTransaction().commit();
        manager.close();

        if (this.equals(p)) {
            update = true;
        }

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
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Playlist p = manager.find(Playlist.class, id);
        if (p != null) {
            manager.remove(p);
            removed = true;
        }
        manager.getTransaction().commit();
        manager.close();
        return removed;
    }

    /**
     * Remove a playlist
     *
     * @return true if the playlist has been removed, false if not
     */


    public boolean remove() {
        boolean removed = false;

        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Playlist p = manager.find(Playlist.class, this.getId());
        if (p != null) {
            manager.remove(p);
            removed = true;
        }
        manager.getTransaction().commit();
        manager.close();

        return removed;
    }

    /**
     * Save and insert a playlist
     *
     * @return true if the playlist has been inserted, false if not
     */

    public boolean save() {
        boolean saved = false;
        Playlist p = new Playlist(this.getName(), this.getDescription(), this.getCreator(), this.getSusbcribers(), this.getSongs());
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Session session = manager.unwrap(Session.class);
        session.saveOrUpdate(p);
        saved = manager.contains(p);
        manager.getTransaction().commit();
        manager.close();
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
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Playlist p = new Playlist(this.getName(), this.getDescription(), this.getCreator(), this.getSusbcribers(), this.getSongs());
        p.setId(this.getId());
        p = manager.merge(p);
        song = manager.merge(song);
        p.addSong(song);
        added = p.getSongs().contains(song);
        manager.getTransaction().commit();
        manager.close();
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
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Playlist p = new Playlist(this.getName(), this.getDescription(), this.getCreator(), this.getSusbcribers(), this.getSongs());
        p.setId(this.getId());
        p = manager.merge(p);
        song = manager.merge(song);
        p.removeSong(song);
        remove = !p.getSongs().contains(song);
        manager.getTransaction().commit();
        manager.close();
        return remove;
    }

    /**
     * List all the playlist with that suscribers
     *
     * @param user the user of the playlist
     * @return The playlist with that suscribers
     */


    public static List<Playlist> listPlaylistSuscribers(User user) {
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Query qu = manager.createNamedQuery("getBySubscriber");
        qu.setParameter("id", user.getId());
        List<Playlist> playlist = qu.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return playlist;

    }

    /**
     * Load the song of the playlist
     *
     * @return true if the song has been loaded, false if not
     */

    public boolean loadSongs() {
        boolean loaded = false;
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Playlist p = new Playlist(this.getName(), this.getDescription(), this.getCreator(), this.getSusbcribers(), this.getSongs());
        p.setId(this.getId());
        p = manager.merge(p);
        p.getSongs().forEach(song -> {
        });
        this.setSongs(p.getSongs());
        manager.getTransaction().commit();
        manager.close();
        if(this.getSongs() != null){
            loaded = true;
        }
        return loaded;
    }

}
