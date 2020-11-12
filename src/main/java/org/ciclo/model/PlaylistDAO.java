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
        String sql = "SELECT * FROM lista_reproduccion";
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            List<User> users = UserDAO.listAll();
            while (rs != null && rs.next()) {
                Playlist p = new Playlist();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("nombre"));
                p.setDescription(rs.getString("descripcion"));

                boolean find = false;
                int index = 0;
                for (int i = 0; i < users.size() && !find; i++) {
                    if (users.get(i).getId() == rs.getInt("id_creador")) {
                        find = true;
                        index = i;
                    }
                }
                if (find) {
                    p.setCreator(users.get(index));
                } else {
                    User user = new User("anonimo", "anonimo", "anonimo");
                    p.setCreator(user);
                }
                playlist.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        String sql = "SELECT id, nombre, descripcion, id_creador FROM lista_reproduccion WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = UserDAO.listById(rs.getInt("id_creador"));
                Playlist aux = new Playlist();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setDescription(rs.getNString("descripcion"));
                aux.setCreator(u);
                p = aux;
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        String sql = "SELECT id, nombre, descripcion, id_creador FROM lista_reproduccion WHERE nombre = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            List<User> users = UserDAO.listAll();
            while (rs != null && rs.next()) {

                Playlist aux = new Playlist();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setDescription(rs.getString("descripcion"));
                boolean find = false;
                int index = 0;
                for (int i = 0; i < users.size() && !find; i++) {
                    if (users.get(i).getId() == rs.getInt("id_creador")) {
                        find = true;
                        index = i;
                    }
                }
                aux.setCreator(users.get(index));
                playlists.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        String sql = "SELECT id, nombre, descripcion, id_creador " +
                "FROM lista_reproduccion " +
                "WHERE id IN (SELECT id_lista FROM lista_cancion WHERE id_cancion = ?)";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            List<User> users = UserDAO.listAll();
            st.setInt(1, song.getId());
            ResultSet rs = st.executeQuery();
            while (rs != null && rs.next()) {
                Playlist p = new Playlist();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("nombre"));
                p.setDescription(rs.getString("descripcion"));

                boolean find = false;

                for (int i = 0; i < users.size() && !find; i++) {
                    if (users.get(i).getId() == rs.getInt("id_creador")) {
                        p.setCreator(users.get(i));
                        find = true;
                    }
                }
                playlists.add(p);
            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        Integer id_user = user.getId();
        String sql = "SELECT id, nombre, descripcion " +
                "FROM lista_reproduccion " +
                "WHERE id_creador = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id_user);
            ResultSet rs = st.executeQuery();
            while (rs != null && rs.next()) {
                Playlist aux = new Playlist();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setDescription(rs.getString("descripcion"));
                aux.setCreator(user);
                playlists.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return playlists;
    }

    /**
     * Update a playlist
     *
     * @return true if the playlist has been updated, false if not
     */


    public boolean update() {
        boolean update = false;
        String sql = "UPDATE lista_reproduccion SET nombre = ?, descripcion = ?, id_creador = ? WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, this.getName());
            st.setString(2, this.getDescription());
            st.setInt(3, this.getCreator().getId());
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
     * Remove a playlist
     *
     * @param id unique of all the playlist
     * @return true if the playlist has been removed, false if not
     */

    public static boolean remove(Integer id) {
        boolean removed = false;
        String sql = "DELETE FROM lista_reproduccion WHERE id = ?";
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
     * Remove a playlist
     *
     * @return true if the playlist has been removed, false if not
     */


    public boolean remove() {
        boolean removed = false;
        String sql = "DELETE FROM lista_reproduccion WHERE id = ?";
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
     * Save and insert a playlist
     *
     * @return true if the playlist has been inserted, false if not
     */

    public boolean save() {
        boolean saved = false;

        String sql = "INSERT INTO lista_reproduccion(nombre, descripcion, id_creador) VALUES (?, ?, ?)";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect()
        ) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, this.getName());
            st.setString(2, this.getDescription());
            st.setInt(3, this.getCreator().getId());
            st.executeUpdate();
            saved = true;
            sql = "SELECT id FROM lista_reproduccion WHERE nombre = ? ORDER BY id DESC LIMIT 1";
            st = conn.prepareStatement(sql);
            st.setString(1, this.getName());
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
     * Add song to a playlist
     *
     * @param song the song to add
     * @return true if the playlist has been added, false if not
     */

    public boolean addSong(Song song) {
        boolean added = false;


        String sql = "INSERT INTO lista_cancion(id_lista, id_cancion) VALUES (?,?)";

        try (Connection conn = org.ciclo.model.connect.Connection.getConnect();
             PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, this.getId());
            st.setInt(2, song.getId());
            int i = st.executeUpdate();
            if (i > 0) {
                added = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


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
        String sql = "DELETE FROM lista_cancion WHERE id_lista = ? AND id_cancion = ?";

        try (Connection conn = org.ciclo.model.connect.Connection.getConnect();
             PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, this.getId());
            st.setInt(2, song.getId());
            int i = st.executeUpdate();
            if (i > 0) {
                remove = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        Integer id_user = user.getId();

        String sql = "SELECT id, nombre, descripcion, id_creador "
                + "FROM lista_reproduccion  "
                + "WHERE id IN (SELECT id_lista FROM suscripcion WHERE id_usuario=?)";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id_user);
            List<User> luser = UserDAO.listAll();


            ResultSet rs = st.executeQuery();
            while (rs != null && rs.next()) {
                Playlist aux = new Playlist();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setDescription(rs.getString("descripcion"));

                boolean find = false;
                for (int i = 0; i < luser.size() && !find; i++) {
                    if (rs.getInt("id_creador") == luser.get(i).getId()) {
                        aux.setCreator(luser.get(i));
                        find = true;

                    }
                }
                if (find == false) {
                    aux.setCreator(new User());
                }
                playlist.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return playlist;

    }

    /**
     * Load the song of the playlist
     *
     * @return true if the song has been loaded, false if not
     */

    public boolean loadSongs() {
        boolean loaded = false;

        setSongs(new TreeSet<>(SongDAO.ListSongByPlaylist(this)));

        if (this.getSongs() != null) {
            loaded = true;
        }

        return loaded;
    }

}
