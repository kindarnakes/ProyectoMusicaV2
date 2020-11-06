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

    public SongDAO(Song s) {
        this.setId(s.getId());
        this.setDuration(s.getDuration());
        this.setName(s.getName());
        this.setGenre(null);
        this.setDisc(s.getDisc()); //Pointer to same object
        this.setList(s.getLists()); //Pointer to same object
        this.setReproductions(null);
    }

    public SongDAO(Integer id) {
        this(SongDAO.listById(id));
    }

    public static List<Song> listAll() {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT id, nombre, duracion FROM cancion";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery()
        ) {
            List<Disc> discs = DiscDAO.listAll();
            while (rs != null && rs.next()) {
                Song aux = new Song();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setDuration(rs.getInt("duracion"));
                boolean find = false;
                int index = 0;
                for (int i = 0; i < discs.size() && !find; i++) {
                    if (discs.get(i).getId() == rs.getInt("id_disco")) {
                        find = true;
                        index = i;
                    }
                }
                aux.setDisc(discs.get(index));
                songs.add(aux);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return songs;
    }

    public static Song listById(Integer id) {
        Song song = new Song();
        String sql = "SELECT id, nombre, duracion FROM cancion WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Disc disc = DiscDAO.listById(rs.getInt("id_disco"));
                Song aux = new Song();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setDuration(rs.getInt("duracion"));
                aux.setDisc(disc);
                song = aux;
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return song;
    }

    public static List<Song> listByName(String name) {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT id, nombre, duracion, id_disco FROM cancion WHERE nombre = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs != null && rs.next()) {
                Disc disc = DiscDAO.listById(rs.getInt("id_disco"));//Name not UNIQUE but not have many repetitions
                Song aux = new Song();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setDuration(rs.getInt("duracion"));
                aux.setDisc(disc);
                songs.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return songs;
    }

    public boolean update() {
        boolean update = false;
        String sql = "UPDATE cancion SET nombre = ?, duration = ?, id_disco = ? FROM cancion WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, this.getName());
            st.setInt(2, this.getDuration());
            st.setInt(3, this.getDisc().getId());
            st.setInt(4, this.getId());
            int i = st.executeUpdate();
            if (i > 1) {
                update = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return update;
    }

    public boolean save() {
        boolean saved = false;

        String sql = "INSERT INTO cancion(nombre, duration, id_disco) VALUES (?, ?, ?)";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect()
        ) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, this.getName());
            st.setInt(2, this.getDuration());
            st.setInt(3, this.getDisc().getId());
            int i = st.executeUpdate();
            if (i > 1) {
                saved = true;
            }

            sql = "SElECT id FROM canciones WHERE nombre = ? ORDER BY id DESC LIMIT 1";
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

    public static boolean remove(Integer id) {
        boolean removed = false;
        String sql = "DELETE FROM canciones WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id);
            int i = st.executeUpdate();
            if (i > 1) {
                removed = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return removed;
    }

    public boolean remove() {
        boolean removed = false;
        String sql = "DELETE FROM canciones WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, this.getId());
            int i = st.executeUpdate();
            if (i > 1) {
                removed = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return removed;
    }

    public static List<Song> searchByDisc(Disc disc){
        List<Song> songs = new ArrayList<>();
        Integer id_disc = disc.getId();

        String sql = "SELECT id, nombre, duracion FROM cancion WHERE id_disco = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id_disc);
            ResultSet rs = st.executeQuery();
            while (rs != null && rs.next()) {
                Song aux = new Song();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setDuration(rs.getInt("duracion"));
                aux.setDisc(disc);
                songs.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }




        return songs;
    }


}
