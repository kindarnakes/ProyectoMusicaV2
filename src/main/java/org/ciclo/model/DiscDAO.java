package org.ciclo.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscDAO extends Disc {
	/**
     * Constructor
     */
    public DiscDAO() {
    	
        super();
    }
    
    public DiscDAO(Disc d) {
    	this.setArtist(d.getArtist());
    	this.setId(d.getId());
    	this.setName(d.getName());
    	this.setPhoto(d.getPhoto());
    	this.setReleaseDate(d.getReleaseDate());
    	this.setSongs(d.getSongs());
    	
    }
    
    public static List<Disc> listAll() {
        List<Disc> disc = new ArrayList<>();
        String sql = "SELECT id, nombre, fecha_publicacion, foto, id_artista FROM disco";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery()
        ) {
            List<Artist> artists = ArtistDAO.List_All_Artist();
            while (rs != null && rs.next()) {
                Disc aux = new Disc();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setReleaseDate(rs.getDate("fecha_publicacion").toLocalDate());
                aux.setPhoto(rs.getNString("foto"));
                boolean find = false;
                int index = 0;
                for(int i = 0; i<artists.size() && !find; i++){
                    if(artists.get(i).getId() == rs.getInt("id_artista")){
                        find = true;
                        index = i;
                    }
                }
                aux.setArtist(artists.get(index));
                disc.add(aux);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return disc;
    }
    
    public static Disc listById(Integer id) {
        Disc disc=new Disc();
        String sql = "SELECT id, nombre, fecha_publicacion, foto, id_artista FROM disco WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            Artist art =ArtistDAO.List_Artist_By_Id(rs.getInt("id_artista")).get(0);
                Disc aux = new Disc();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setReleaseDate(rs.getDate("fecha_publicacion").toLocalDate());
                aux.setPhoto(rs.getNString("foto"));
                aux.setArtist(art);
                disc=aux;
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return disc;
    }
    

    public static List<Disc> listByName(String name) {
        List<Disc> disc = new ArrayList<>();
        String sql = "SELECT id, nombre, fecha_publicacion, id_artista, foto FROM disco WHERE nombre = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
        ) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs != null && rs.next()) {
            	Artist art =ArtistDAO.List_Artist_By_Id(rs.getInt("id_artista")).get(0);
                Disc aux = new Disc();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setReleaseDate(rs.getDate("fecha_publicacion").toLocalDate());
                aux.setPhoto(rs.getNString("foto"));
                aux.setArtist(art);
               
                disc.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return disc;
    }

    public boolean update(){
        boolean update =false;
        String sql = "UPDATE disco SET nombre = ?, fecha_publicacion = ?, id_artista = ?, foto = ? FROM disco WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
        ) {
            st.setString(1, this.getName());
            st.setDate(2, Date.valueOf(this.getReleaseDate()));
            st.setInt(3, this.getArtist().getId());
            st.setString(4, this.getPhoto());
            st.setInt(5, this.getId());
           
            int i = st.executeUpdate();
            if(i>1){
                update = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return update;
    }
    public static  boolean remove(Integer id){
        boolean removed =false;
        String sql = "DELETE FROM disco WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
        ) {
            st.setInt(1, id);
            int i = st.executeUpdate();
            if(i>1){
                removed = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return removed;
    }
    
    public  boolean remove(){
        boolean removed =false;
        String sql = "DELETE FROM disco WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
        ) {
            st.setInt(1, this.getId());
            int i = st.executeUpdate();
            if(i>1){
                removed = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return removed;
    }
    
    public boolean save(){
        boolean saved = false;

        String sql = "INSERT INTO disco(nombre, fecha_publicacion, id_artista, foto) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
        ) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, this.getName());
            st.setDate(2, Date.valueOf(this.getReleaseDate()));
            st.setInt(3, this.getArtist().getId());
            st.setString(4, this.getPhoto());
            int i = st.executeUpdate();
            if(i>1){
                saved = true;
            }

            sql = "SElECT nombre, duracion, id_artista, foto FROM disco WHERE nombre = ? ORDER BY id DESC LIMIT 1";
            st = conn.prepareStatement(sql);
            st.setString(1, this.getName());
            ResultSet rs = st.executeQuery();
            if(rs !=null && rs.next()){
                this.setId(rs.getInt("id"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return saved;
    }

}
