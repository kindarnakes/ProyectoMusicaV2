package org.ciclo.model;

import org.ciclo.model.connectManager.Connect;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends User {
    /**
     * Constructor
     */
    public UserDAO() {
        super();
    }

    /**
     * Parametrized constructor
     *
     * @param u user to update
     */


    public UserDAO(User u) {
        this.setCreated(u.getCreated());
        this.setEmail(u.getEmail());
        this.setId(u.getId());
        this.setName(u.getName());
        this.setPhoto(u.getPhoto());
        this.setSuscribed(u.getSubscribed());
    }

    /**
     * Constructor
     *
     * @param id of the user
     */

    public UserDAO(Integer id) {
        this(UserDAO.listById(id));
    }

    /**
     * List all the users
     *
     * @return All the users
     */


    public static List<User> listAll() {
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Query qu = manager.createNamedQuery("getAllUsers");
        List<User> user = qu.getResultList();
        manager.getTransaction().commit();
        manager.close();

        return user;
    }

    /**
     * List of the user with that ide
     *
     * @param id unique for all the users
     * @return The user with that id
     */

    public static User listById(Integer id) {
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        User user = manager.find(User.class, id);
        user.getCreated().forEach(playlist -> {
        });
        user.getSubscribed().forEach(playlist -> {
        });
        manager.getTransaction().commit();
        manager.close();
        return user;
    }

    /**
     * List the user with that name
     *
     * @param name the name of the user
     * @return The user with that name
     */

    public static List<User> listByName(String name) {
        List<User> user = new ArrayList<>();

        return user;
    }

    /**
     * List the user with that email
     *
     * @param email unique for all the user
     * @return The user with that email
     */

    public static User listByEmail(String email) {
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Query qu = manager.createNamedQuery("getUserByEmail");
        qu.setParameter("email", email);
        User user = (User) qu.getSingleResult();
        user.getCreated().forEach(playlist -> {

        });
        manager.getTransaction().commit();
        manager.close();
        return user;
    }

    /**
     * Save and insert a user
     *
     * @return true if the user has been inserted and saved, false if not
     */

    public boolean save() {
        boolean saved = false;
        User u = new User(this.getName(), this.getPhoto(), this.getEmail());
        u.setCreated(this.getCreated());
        u.setSuscribed(this.getSubscribed());
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Session session = manager.unwrap(Session.class);
        session.saveOrUpdate(u);
        saved = manager.contains(u);
        manager.getTransaction().commit();
        manager.close();

        return saved;
    }

    /**
     * Update a user
     *
     * @return true if the user has been updated, false if not
     */


    public boolean update() {
        boolean update = false;
        User u = new User(this.getName(), this.getPhoto(), this.getEmail());
        u.setCreated(this.getCreated());
        u.setSuscribed(this.getSubscribed());
        u.setId(this.getId());
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        u = manager.merge(u);
        manager.getTransaction().commit();
        manager.close();

        if (this.equals(u)) {
            update = true;
        }


        return update;
    }

    /**
     * Remove a user with that id
     *
     * @param id unique for all the users
     * @return true if the user has been removed, false if not
     */

    public static boolean remove(Integer id) {
        boolean removed = false;
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        User u = manager.find(User.class, id);
        if (u != null) {
            manager.remove(u);
            removed = true;
        }
        manager.getTransaction().commit();
        manager.close();

        return removed;
    }

    /**
     * Remove a user
     *
     * @return true if the user has been removed, false if not
     */


    public boolean remove() {
        boolean removed = false;

        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        User u = manager.find(User.class, this.getId());
        if (u != null) {
            manager.remove(u);
            removed = true;
        }
        manager.getTransaction().commit();
        manager.close();

        return removed;
    }

    /**
     * Remove a user with that email
     *
     * @param email uniqe for all the user
     * @return true if the user has been removed, false if not
     */

    public static boolean remove(String email) {
        boolean removed = false;

        return removed;
    }

    /**
     * Subscribe a user in a playlist
     *
     * @param playlist the playlist in which I want to insert
     * @return true if the user has been inserted in the playlist, false if not
     */

    public boolean subscribe(Playlist playlist) {
        boolean subscribed = false;
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        User u = new User(this.getName(), this.getPhoto(), this.getEmail());
        u.setCreated(this.getCreated());
        u.setSuscribed(this.getSubscribed());
        u.setId(this.getId());
        u = manager.merge(u);
        playlist = manager.merge(playlist);
        u.subscribe(playlist);
        subscribed = u.getSubscribed().contains(playlist);
        manager.getTransaction().commit();
        manager.close();

        return subscribed;
    }

    /**
     * Unsubscribe a user of a playlist
     *
     * @param playlist the playlist in which I want to unsusvribed
     * @return true if the user has been unsubcribe in the playlist, false if not
     */


    public boolean unsubscribe(Playlist playlist) {
        boolean unsubscribed = false;
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        User u = new User(this.getName(), this.getPhoto(), this.getEmail());
        u.setCreated(this.getCreated());
        u.setSuscribed(this.getSubscribed());
        u.setId(this.getId());
        u = manager.merge(u);
        playlist = manager.merge(playlist);
        u.unsubscribe(playlist);
        unsubscribed = !u.getSubscribed().contains(playlist);
        manager.getTransaction().commit();
        manager.close();

        return unsubscribed;
    }

    /**
     * Load all user creator
     *
     * @return true if the user has been loaded, false if not
     */

    public boolean loadCreated() {
        boolean load = false;

        return load;
    }

    /**
     * Load all user subcribed
     *
     * @return true if the user has been loaded, false if not
     */

    public boolean loadSubscribed() {
        boolean load = false;

        return load;
    }

    /**
     * Load all user susbcriber in a playlist
     *
     * @param playlist the playlist wich list its user suscribers
     * @return The list of the user susbcribers in a playlist
     */

    public static List<User> listUserSubscribed(Playlist playlist) {
        List<User> user = new ArrayList<>();

        return user;

    }

    public User toUser() {
        User u = new User(this.getName(), this.getPhoto(), this.getEmail());
        u.setId(this.getId());

        return u;
    }

}
