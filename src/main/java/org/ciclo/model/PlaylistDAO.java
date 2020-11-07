
package org.ciclo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.ciclo.model.ArtistDAO.SELECT_All;


public class PlaylistDAO extends Playlist {
    
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
                p.setDesciption(rs.getString("descripcion"));
                
                boolean find = false;
                int index = 0;
                for (int i = 0; i < users.size() && !find; i++) {
                    if (users.get(i).getId() == rs.getInt("id_creador")) {
                        find = true;
                        index = i;
                    }
                }
                p.setCreator(users.get(index));
                playlist.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return playlist;
    }
      
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
                aux.setDesciption(rs.getNString("descripcion"));
                aux.setCreator(u);
                p = aux;
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
         
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
                aux.setName(rs.getString("descripcion"));
                boolean find = false;
                int index = 0;
                for (int i = 0; i < users.size() && !find; i++) {
                    if (users.get(i).getId() == rs.getInt("id_artista")) {
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
         
        public boolean update() {
        boolean update = false;
        String sql = "UPDATE lista_reproduccion SET nombre = ?, descripccion = ?, id_creador = ? WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, this.getName());
            st.setString(2, this.getDesciption());
            st.setInt(3, this.getCreator().getId());          
            st.setInt(5, this.getId());

            int i = st.executeUpdate();
            if (i > 1) {
                update = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return update;
    }
         
      public static boolean remove(Integer id) {
        boolean removed = false;
        String sql = "DELETE FROM lista_reproduccion WHERE id = ?";
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
        String sql = "DELETE FROM lista_reproduccion WHERE id = ?";
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
       
        public boolean save() {
        boolean saved = false;

        String sql = "INSERT INTO lista_reproduccion(nombre, descripcion, id_creador) VALUES (?, ?, ?)";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect()
        ) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, this.getName());
            st.setString(1, this.getDesciption());
            st.setInt(3, this.getCreator().getId());
            int i = st.executeUpdate();
            if (i > 1) {
                saved = true;
            }

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
}
