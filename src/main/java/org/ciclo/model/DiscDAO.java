package org.ciclo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class DiscDAO extends Disc {
    /**
     * Constructor
     */
    public DiscDAO() {

        super();
    }

    /**
     * Parametrized constructor
     *
     * @param d Disc to update
     */

    public DiscDAO(Disc d) {
        this.setArtist(d.getArtist());
        this.setId(d.getId());
        this.setName(d.getName());
        this.setPhoto(d.getPhoto());
        this.setReleaseDate(d.getReleaseDate());
        this.setSongs(d.getSongs());

    }

    /**
     * Constructor
     *
     * @param id of the Disc
     */

    public DiscDAO(Integer id) {
        this(DiscDAO.listById(id));
    }

    /**
     * List all disc
     *
     * @return All discs
     */

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
                for (int i = 0; i < artists.size() && !find; i++) {
                    if (artists.get(i).getId() == rs.getInt("id_artista")) {
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

    /**
     * List all disc by id
     *
     * @param id unique for all the discs
     * @return the disc with that id
     */

    public static Disc listById(Integer id) {
        Disc disc = new Disc();
        String sql = "SELECT id, nombre, fecha_publicacion, foto, id_artista FROM disco WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Artist art = ArtistDAO.List_Artist_By_Id(rs.getInt("id_artista"));
                Disc aux = new Disc();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setReleaseDate(rs.getDate("fecha_publicacion").toLocalDate());
                aux.setPhoto(rs.getNString("foto"));
                aux.setArtist(art);
                disc = aux;
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return disc;
    }

    /**
     * List all disc with that name
     *
     * @param name of the discs
     * @return the discs with that name
     */


    public static List<Disc> listByName(String name) {
        List<Disc> disc = new ArrayList<>();
        String sql = "SELECT d.id, d.nombre, d.foto, d.fecha_publicacion, a.id, a.nombre, a.foto, a.nacionalidad " +
                "FROM disco AS d LEFT JOIN artista AS a ON a.id=d.id_artista " +
                "WHERE d.nombre = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs != null && rs.next()) {
                Artist artist = new Artist();
                Disc aux = new Disc();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setReleaseDate(rs.getDate("fecha_publicacion").toLocalDate());
                artist.setId(rs.getInt("a.id"));
                artist.setName(rs.getString("a.nombre"));
                artist.setNationality(rs.getString("a.nacionalidad"));
                aux.setArtist(artist);
                disc.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return disc;
    }

    /**
     * Update disc
     *
     * @return true if the disc has been updated, false if not
     */

    public boolean update() {
        boolean update = false;
        String sql = "UPDATE disco SET nombre = ?, fecha_publicacion = ?, id_artista = ?, foto = ? WHERE id = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setString(1, this.getName());
            st.setDate(2, Date.valueOf(this.getReleaseDate()));
            st.setInt(3, this.getArtist().getId());
            st.setString(4, this.getPhoto());
            st.setInt(5, this.getId());

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
     * Remove the disc with that id
     *
     * @param id unique for all the discs
     * @return true if the artist has been removed, false if not
     */

    public static boolean remove(Integer id) {
        boolean removed = false;
        String sql = "DELETE FROM disco WHERE id = ?";
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

    /**
     * Remove the disc
     *
     * @return true if the disc has been removed, false if not
     */

    public boolean remove() {
        boolean removed = false;
        String sql = "DELETE FROM disco WHERE id = ?";
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

    /**
     * Save and insert a disc
     *
     * @return true if the disc has been inserted, false if not
     */

    public boolean save() {
        boolean saved = false;

        String sql = "INSERT INTO disco(nombre, fecha_publicacion, id_artista, foto) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect()
        ) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, this.getName());
            st.setDate(2, Date.valueOf(this.getReleaseDate()));
            st.setInt(3, this.getArtist().getId());
            st.setString(4, this.getPhoto());
            int i = st.executeUpdate();
            if (i > 0) {
                saved = true;
            }

            sql = "SElECT id FROM disco WHERE nombre = ? ORDER BY id DESC LIMIT 1";
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
     * List  the disc with that artist
     *
     * @param artist of that disc
     * @return Discs with that artist
     */

    public static List<Disc> searchByAuthor(Artist artist) {
        Integer id_artist = artist.getId();
        List<Disc> discs = new ArrayList<>();

        String sql = "SELECT id, nombre, fecha_publicacion, foto FROM disco WHERE id_artista = ?";
        try (
                Connection conn = org.ciclo.model.connect.Connection.getConnect();
                PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, id_artist);
            ResultSet rs = st.executeQuery();
            while (rs != null && rs.next()) {

                Disc aux = new Disc();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("nombre"));
                aux.setReleaseDate(rs.getDate("fecha_publicacion").toLocalDate());
                aux.setArtist(artist); //Artist Author to artist disc
                discs.add(aux);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return discs;

    }

    /**
     * Load all the song by disc
     *
     * @return true if the song has been loaded, false if not
     */

    public boolean loadSongs() {
        boolean loaded = false;
        this.setSongs(new TreeSet<>(SongDAO.searchByDisc(this)));
        if (!this.getSongs().isEmpty()) {
            loaded = true;
        }
        return loaded;
    }

}
