package org.ciclo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class ArtistDAO extends Artist {

    //Querys
    final static String INSERT = "INSERT INTO artista(nombre, nacionalidad, foto) VALUES(?, ?, ?)";
    final static String UPDATE = "UPDATE artista SET nombre = ?, nacionalidad = ?, foto = ? WHERE id = ?";
    final static String DELETE_by_Id = "DELETE FROM artista WHERE id = ?";
    final static String DELETE_by_Name = "DELETE FROM artista WHERE nombre = ?";
    final static String SELECT_All = "SELECT * FROM artista";
    final static String SELECT_by_Id = "SELECT id, nombre, nacionalidad, foto FROM artista WHERE id = ?";
    final static String SELECT_by_Name = "SELECT id, nombre, nacionalidad, foto FROM artista WHERE nombre = ?";

    public ArtistDAO() {
        super();
    }

    public ArtistDAO(Artist a) {
        this.setId(a.getId());
        this.setName(a.getName());
        this.setNationality(a.getFrom());
        this.setPhoto(a.getPhoto());
    }

    public ArtistDAO(Integer id) {
        this(ArtistDAO.List_Artist_By_Id(id));
    }

    public static List<Artist> List_All_Artist() {
        List<Artist> artists = new ArrayList<>();
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(SELECT_All);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs != null && rs.next()) {
                Artist a = new Artist();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("nombre"));
                a.setNationality(rs.getString("nacionalidad"));
                a.setPhoto(rs.getString("foto"));
                artists.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return artists;
    }


    public static Artist List_Artist_By_Id(Integer id) {
        Artist artist = new Artist();
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(SELECT_by_Id)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Artist aux = new Artist();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setNationality(rs.getString("nacionalidad"));
                aux.setPhoto(rs.getString("foto"));
                artist = aux;
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return artist;
    }

    public static Artist List_Artist_By_Name(String name) {
        Artist artist = new Artist();
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(SELECT_by_Name)
        ) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Artist aux = new Artist();
            aux.setId(rs.getInt("id"));
            aux.setName(rs.getString("nombre"));
            aux.setNationality(rs.getString("nacionalidad"));
            aux.setPhoto(rs.getString("foto"));
            artist = aux;
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return artist;
    }

    public boolean Insert_Artist() {
        boolean result = false;
        String sql;
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect()
        ) {
            PreparedStatement ps = c.prepareStatement(INSERT);
            ps.setString(1, this.getName());
            ps.setString(2, this.getFrom());
            ps.setString(3, this.getPhoto());
            int i = ps.executeUpdate();
            if (i > 1) {
                result = true;
            }

            sql = "SELECT id FROM artista WHERE nombre = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, this.getName());
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                this.setId(rs.getInt("id"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public boolean Update_Artist() {
        boolean result = false;
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(UPDATE)
        ) {
            ps.setString(1, this.getName());
            ps.setString(2, this.getFrom());
            ps.setString(3, this.getPhoto());
            int i = ps.executeUpdate();
            if (i > 1) {
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return result;
    }

    public static boolean Remove_Artist_by_Id(Integer id) {
        boolean result = false;
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(DELETE_by_Id)
        ) {
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i > 1) {
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static boolean Remove_Artist_by_Name(String name) {
        boolean result = false;
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(DELETE_by_Name)
        ) {
            ps.setString(1, name);
            int i = ps.executeUpdate();
            if (i > 1) {
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean remove_Artist() {
        boolean result = false;
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(DELETE_by_Id)
        ) {
            ps.setInt(1, this.getId());
            int i = ps.executeUpdate();
            if (i > 1) {
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean loadDiscs() {
        boolean loaded = false;
        this.setDiscs(new TreeSet<>(DiscDAO.searchByAuthor(this)));
        if (!this.getDiscs().isEmpty()) {
            loaded = true;
        }
        return loaded;
    }


}
