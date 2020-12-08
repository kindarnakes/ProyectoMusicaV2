package org.ciclo.model;

import java.util.ArrayList;
import org.ciclo.model.connectManager.Connect;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.PersistenceException;

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
        EntityManager manager = null;
        try {
            manager = Connect.getManager();
            artists = manager.createQuery("FROM Artist").getResultList();
            manager.clear();
        } catch (PersistenceException ex) {
            if (manager != null) {
                manager.close();
            }
        }
        return artists;
    }

    /**
     * List artists by id
     *
     * @param id unique for all the artist
     * @return the artist with that id
     */
    public static Artist List_Artist_By_Id(Integer id) {
        EntityManager manager = null;
        Artist artist = new Artist();
        try {
            manager = Connect.getManager();
            artist = manager.find(Artist.class, id);
            manager.close();
        } catch (PersistenceException ex) {
            if (manager != null) {
                manager.close();
            }
        }
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
        EntityManager manager = null;
        try {
            manager = Connect.getManager();
            manager.getTransaction().begin();
            Query qu = manager.createNamedQuery("getNamed");
            qu.setParameter("name", name);
            artist = (Artist) qu.getSingleResult();
            manager.getTransaction().commit();
            manager.close();
        } catch (PersistenceException ex) {
            if (manager != null) {
                manager.close();
            }
        }

        return artist;

    }

    /**
     * Save and insert a new artist
     *
     * @return true if the artist has been inserted, false if not
     */
    public boolean Insert_Artist() {
        boolean result = false;
        EntityManager manager = null;
        try {
            Artist artist = new Artist(this.getName(), this.getPhoto(), this.getFrom());
            artist.setDiscs(this.getDiscs());
            manager = Connect.getManager();
            manager.getTransaction().begin();
            if (artist != null) {
                manager.persist(artist);
            }
            manager.getTransaction().commit();
            manager.close();
        } catch (PersistenceException ex) {
            if (manager != null) {
                manager.close();
            }
        }

        return result;
    }

    /**
     * Update a artist
     *
     * @return true if the artist has been updated, false if not
     */
    public boolean Update_Artist() {
        boolean result = false;
        EntityManager manager = null;
        try {
            Artist artist = new Artist(this.getName(), this.getPhoto(), this.getFrom());
            artist.setId((this.getId()));
            manager = Connect.getManager();
            manager.getTransaction().begin();
            if (artist != null) {
                manager.merge(artist);
                result = true;
            }

            manager.getTransaction().commit();
            manager.close();
        } catch (PersistenceException ex) {
            if (manager != null) {
                manager.close();
            }
        }

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
        EntityManager manager = null;

        try {
            Artist artist = new Artist();
            manager = Connect.getManager();
            manager.getTransaction().begin();

            if (artist != null) {
                artist = manager.find(Artist.class, id);
                manager.remove(artist);
                result = true;
            }

            manager.getTransaction().commit();
            manager.close();
        } catch (PersistenceException ex) {
            if (manager != null) {
                manager.close();
            }
        }

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
        EntityManager manager = null;
        try {
            manager = Connect.getManager();
            manager.getTransaction().begin();
            Artist a = manager.find(Artist.class, this.getId());
            if (a != null) {
                manager.remove(a);
                result = true;
            }
            manager.getTransaction().commit();
            manager.close();
        } catch (PersistenceException ex) {
            if (manager != null) {
                manager.close();
            }
        }

        return result;
    }

    /**
     * Load all the disc from the artist
     *
     * @return
     */
    public boolean loadDiscs() {
        boolean loaded = false;
        EntityManager manager = null;
        Artist a = new Artist();

        try {
            manager = Connect.getManager();
            manager.getTransaction().begin();
            if (this.getDiscs() != null) {
                a = manager.find(Artist.class, this.getId());

                a.setId(this.getId());

                a.getDiscs().forEach(disc -> {
                });
                this.setDiscs(a.getDiscs());

                manager.getTransaction().commit();
                manager.close();
                if (this.getDiscs() != null) {
                    loaded = true;
                }
            }

        } catch (PersistenceException ex) {
            if (manager != null) {
                manager.close();
            }
        }
        return loaded;

    }

}
