package org.ciclo.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends User {
	/**
     * Constructor
     */
    public UserDAO() {
        super();
    }


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
    public UserDAO(Integer id){
        this(UserDAO.listById(id));
    }
    
    
    public static List<User> listAll() {
        List<User> user = new ArrayList<>();
        String sql = "SELECT id, nombre, foto, correo FROM usuario";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
        		ResultSet rs = st.executeQuery();
        ) {
        	
         
            while (rs != null && rs.next()) {
                User aux = new User();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setPhoto(rs.getString("foto"));
                aux.setEmail(rs.getString("correo"));
                
           
               
                
                user.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    public static User listById(Integer id) {
        User user=new User();
        String sql = "SELECT id, nombre, foto, correo FROM usuario WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
                User aux = new User();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setEmail(rs.getString("correo"));
                aux.setPhoto(rs.getNString("foto"));
                
                user=aux;
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    public static List<User> listByName(String name) {
        List<User> user = new ArrayList<>();
        String sql = "SELECT id, nombre, foto, correo FROM usuario WHERE nombre = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
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
    public static User listByEmail(String email) {
        User user=new User();
        String sql = "SELECT id, nombre, foto, correo FROM usuario WHERE email = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
        ) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            
                User aux = new User();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setEmail(rs.getString("correo"));
                aux.setPhoto(rs.getNString("foto"));
                
                user=aux;
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    public boolean save(){
        boolean saved = false;

        String sql = "INSERT INTO user(nombre, foto, correo) VALUES (?, ?, ?)";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
        ) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, this.getName());
            st.setString(2, this.getPhoto());
            st.setString(3, this.getEmail());
           
            int i = st.executeUpdate();
            if(i>1){
                saved = true;
            }

            sql = "SElECT id FROM usuario WHERE email=?";
            st = conn.prepareStatement(sql);
            st.setString(1, this.getEmail());
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
    
    
    public boolean update(){
        boolean update =false;
        String sql = "UPDATE usuario SET nombre = ?, foto = ?, correo = ?, FROM usuario WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
        ) {
            st.setString(1, this.getName());
            st.setString(2, this.getPhoto());
            st.setString(3, this.getEmail());
           
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
        String sql = "DELETE FROM usuario WHERE id = ?";
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
    public static  boolean remove(String email){
        boolean removed =false;
        String sql = "DELETE FROM usuario WHERE email = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql);
        ) {
            st.setString(1, email);
            int i = st.executeUpdate();
            if(i>1){
                removed = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return removed;
    }

	

}
