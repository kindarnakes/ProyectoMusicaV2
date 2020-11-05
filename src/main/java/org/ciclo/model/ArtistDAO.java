
package org.ciclo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ArtistDAO extends Artist {
    
    //Querys
    final static String INSERT = "INSERT INTO artista(nombre, nacionalidad, foto) VALUES(?, ?, ?)";
    final static String UPDATE = "UPDATE artista SET nombre = ?, nacionalidad = ?, foto = ? WHERE id = ?";
    final static String DELETE_by_Id = "DELETE FROM artista WHERE id = ?";
    final static String DELETE_by_Name = "DELETE FROM artista WHERE nombre = ?";
    final static String SELECT_All = "SELECT * FROM artista";
    final static String SELECT_by_Id = "SELECT id, nombre, nacionalidad, foto FROM artista WHERE id = ?";
    final static String SELECT_by_Name = "SELECT id, nombre, nacionalidad, foto FROM artista WHERE nombre = ?";
    
    public ArtistDAO(){
        super();
    };
    
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
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return artists;
    }
  
    
      public static List<Artist> List_Artist_By_Id(Integer id) {
        List<Artist> artists = new ArrayList<>();
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(SELECT_by_Id);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Artist a = new Artist();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("nombre"));
                a.setNationality(rs.getString("nacionalidad"));
                a.setPhoto(rs.getString("foto"));
                artists.add(a);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return artists;
    }
      
      
        public static List<Artist> List_Artist_By_Name(String name) {
        List<Artist> artists = new ArrayList<>();
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(SELECT_by_Name);
        ) {
            ps.setInt(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Artist a = new Artist();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("nombre"));
                a.setNationality(rs.getString("nacionalidad"));
                a.setPhoto(rs.getString("foto"));
                artists.add(a);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return artists;
    }
        
        public boolean Insert_Artist(){
        boolean result = false;
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
        ) {
            PreparedStatement ps = c.prepareStatement(INSERT);
            ps.setString(1, this.getName());
            ps.setString(2, this.getFrom());
            ps.setString(3, this.getPhoto());
            int i = ps.executeUpdate();
            if(i>1){
                result = true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
        
      public boolean Update_Artist(){
        boolean result = false;
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(UPDATE);
        ) {
            ps.setString(1, this.getName());
            ps.setString(2, this.getFrom());
            ps.setString(3, this.getPhoto());
            int i = ps.executeUpdate();
            if(i>1){
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return result;
    }
      
       public static  boolean Remove_Artist_by_Id(Integer id){
        boolean result =false;
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(DELETE_by_Id);
        ) {
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if(i>1){
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
       
       public static  boolean Remove_Artist_by_Name(String name){
        boolean result =false;
        try (
                Connection c = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement ps = c.prepareStatement(DELETE_by_Name);
        ) {
            ps.setString(1, name);
            int i = ps.executeUpdate();
            if(i>1){
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
      
}
