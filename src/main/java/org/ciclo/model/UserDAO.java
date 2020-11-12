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
     * @param id of the user
     */

    public UserDAO(Integer id) {
        this(UserDAO.listById(id));
    }
    /**
     * List all the users
     * @return All the users
     */


    public static List<User> listAll() {
        List<User> user = new ArrayList<>();
        String sql = "SELECT id, nombre, foto, correo FROM usuario";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery()
        ) {


            while (rs != null && rs.next()) {
                User aux = new User();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setPhoto(rs.getString("foto"));
                aux.setEmail(rs.getString("correo"));

                user.add(aux);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    /**
     * List of the user with that ide
     * @param id unique for all the users
     * @return The user with that id
     */

    public static User listById(Integer id) {
        User user = new User();
        String sql = "SELECT id, nombre, foto, correo FROM usuario WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            User aux = new User();
            if (rs != null && rs.next()) {
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setEmail(rs.getString("correo"));
                aux.setPhoto(rs.getNString("foto"));

                user = aux;
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    /**
     * List the user with that name
     * @param name the name of the user
     * @return The user with that name
     */

    public static List<User> listByName(String name) {
        List<User> user = new ArrayList<>();
        String sql = "SELECT id, nombre, foto, correo FROM usuario WHERE nombre = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            while (rs != null && rs.next()) {

                User aux = new User();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setEmail(rs.getString("correo"));
                aux.setPhoto(rs.getNString("foto"));

                user.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    /**
     * List the user with that email
     * @param email unique for all the user
     * @return The user with that email
     */

    public static User listByEmail(String email) {
        User user = null;
        String sql = "SELECT id, nombre, foto, correo FROM usuario WHERE correo = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                User aux = new User();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setEmail(rs.getString("correo"));
                aux.setPhoto(rs.getNString("foto"));

                user = aux;
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    /**
     * Save and insert a user
     * @return true if the user has been inserted and saved, false if not
     */

    public boolean save() {
        boolean saved = false;

        String sql = "INSERT INTO usuario(nombre, foto, correo) VALUES (?, ?, ?)";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect()
        ) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, this.getName());
            st.setString(2, this.getPhoto());
            st.setString(3, this.getEmail());

            int i = st.executeUpdate();
            if (i > 1) {
                saved = true;
            }

            sql = "SElECT id FROM usuario WHERE correo=?";
            st = conn.prepareStatement(sql);
            st.setString(1, this.getEmail());
            ResultSet rs = st.executeQuery();
            if (rs != null && rs.next()) {
                this.setId(rs.getInt("id"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return saved;
    }
    /**
     * Update a user
     * @return true if the user has been updated, false if not
     */


    public boolean update() {
        boolean update = false;
        String sql = "UPDATE usuario SET nombre = ?, foto = ?, correo = ?  WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, this.getName());
            st.setString(2, this.getPhoto());
            st.setString(3, this.getEmail());
            st.setInt(4, this.getId());

            int i = st.executeUpdate();
            if (i > 0) {
                update = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return update;
    }
    /**
     * Remove a user with that id
     * @param id unique for all the users
     * @return true if the user has been removed, false if not
     */

    public static boolean remove(Integer id) {
        boolean removed = false;
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id);
            int i = st.executeUpdate();
            if (i > 0) {
                removed = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return removed;
    }
    /**
     * Remove a user
     * @return true if the user has been removed, false if not
     */


    public boolean remove() {
        boolean removed = false;
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, this.getId());
            int i = st.executeUpdate();
            if (i > 0) {
                removed = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return removed;
    }
    /**
     * Remove a user with that email
     * @param email uniqe for all the user
     * @return true if the user has been removed, false if not
     */

    public static boolean remove(String email) {
        boolean removed = false;
        String sql = "DELETE FROM usuario WHERE email = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, email);
            int i = st.executeUpdate();
            if (i > 0) {
                removed = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return removed;
    }
    /**
     * Subscribe a user in a playlist
     * @param playlist the playlist in which I want to insert
     * @return  true if the user has been inserted in the playlist, false if not
     */

    public boolean subscribe(Playlist playlist) {

        boolean subscribed = false;
        String sql = "INSERT INTO suscripcion(id_usuario, id_lista) VALUES (?,?)";

        try (Connection conn = org.ciclo.model.connect.Connection.getConnect();
             PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, this.getId());
            st.setInt(2, playlist.getId());
            int i = st.executeUpdate();
            if (i > 0) {
                subscribed = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return subscribed;
    }
    
    /**
     * Unsubscribe a user of a playlist
     * @param playlist the playlist in which I want to unsusvribed
     * @return  true if the user has been unsubcribe in the playlist, false if not
     */


    public boolean unsubscribe(Playlist playlist) {

        boolean unsubscribed = false;
        String sql = "DELETE FROM suscripcion WHERE id_usuario = ?, id_playlist = ?)";

        try (Connection conn = org.ciclo.model.connect.Connection.getConnect();
             PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, this.getId());
            st.setInt(2, playlist.getId());
            int i = st.executeUpdate();
            if (i > 0) {
                unsubscribed = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return unsubscribed;
    }
    /**
     * Load all user creator
     * @return true if the user has been loaded, false if not
     */

    public boolean loadCreated() {
        boolean load = false;

        Set<IPlaylists> created = new TreeSet<>(PlaylistDAO.listUserCreated(this));
        if (!created.isEmpty()) {
            this.setCreated(created);
            load = true;
        }

        return load;
    }
    /**
     * Load all user subcribed
     * @return true if the user has been loaded, false if not
     */

    public boolean loadSubscribed() {
        boolean load = false;


        Set<IPlaylists> created = new TreeSet<>(PlaylistDAO.listPlaylistSuscribers(this));
        if (!created.isEmpty()) {
            this.setCreated(created);
            load = true;
        }

        return load;
    }
    /**
     * Load all user susbcriber in a playlist
     * @param playlist the playlist wich list its user suscribers
     * @return The list of the user susbcribers in a playlist
     */

    public static List<User> listUserSubscribed(Playlist playlist) {
        List<User> user = new ArrayList<>();
        Integer id_playlist = playlist.getId();

        String sql = "SELECT id, nombre, correo, foto "
                + "FROM usuario  "
                + "WHERE id IN (SELECT id_usuario FROM suscripcion WHERE id_lista=?)";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id_playlist);


            ResultSet rs = st.executeQuery();
            while (rs != null && rs.next()) {
                User aux = new User();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setEmail(rs.getString("correo"));
                aux.setPhoto(rs.getString("foto"));


                user.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;

    }

}
