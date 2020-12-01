package org.ciclo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        this.setComments(u.getComments());
        this.setCreated(u.getCreated());
        this.setEmail(u.getEmail());
        this.setId(u.getId());
        this.setName(u.getName());
        this.setPhoto(u.getPhoto());
        this.setReproductions(u.getReproductions());
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
        List<User> user = new ArrayList<>();

        return user;
    }

    /**
     * List of the user with that ide
     *
     * @param id unique for all the users
     * @return The user with that id
     */

    public static User listById(Integer id) {
        User user = new User();

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
        User user = new User();

        return user;
    }

    /**
     * Save and insert a user
     *
     * @return true if the user has been inserted and saved, false if not
     */

    public boolean save() {
        boolean saved = false;

        return saved;
    }

    /**
     * Update a user
     *
     * @return true if the user has been updated, false if not
     */


    public boolean update() {
        boolean update = false;

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

        return removed;
    }

    /**
     * Remove a user
     *
     * @return true if the user has been removed, false if not
     */


    public boolean remove() {
        boolean removed = false;

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

}
