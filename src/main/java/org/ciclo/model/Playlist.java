package org.ciclo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="lista_reproduccion")
public class Playlist implements Serializable {
    /**
     * Name of Playlist
     */
    @Column(name="nombre")
    private String name;
    /**
     * Database Id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * Description of Playlist
     */
    @Column(name="descripcion")
    private String description;
    /**
     * Creator of Playlist
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="id_creador")
    private User creator;
    /**
     * Susbcribers of Playlist
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "suscripcion",
            joinColumns = { @JoinColumn(name = "id_lista") },
            inverseJoinColumns = { @JoinColumn(name = "id_usuario") }
    )
    private Set<User> susbcribers;
    /**
     * Songs of Playlist
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "lista_cancion",
            joinColumns = { @JoinColumn(name = "id_lista") },
            inverseJoinColumns = { @JoinColumn(name = "id_cancion") }
    )
    private Set<Song> songs;


    /**
     * Constructor
     */
    public Playlist() {
        super();
    }

    /**
     * Parametrized Constructor
     *
     * @param name
     * @param id
     * @param description
     * @param creator
     * @param susbcribers
     * @param songs
     */
    public Playlist(String name, int id, String description, User creator, Set<User> susbcribers, Set<Song> songs) {
        super();
        this.name = name;
        this.id = id;
        this.description = description;
        this.creator = creator;
        this.susbcribers = susbcribers;
        this.songs = songs;
    }


    /**
     * Name setter
     *
     * @param name Name to assign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Id setter
     *
     * @param id Id to assign
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Desciption setter
     *
     * @param desciption Desciption to assign
     */

    public void setDescription(String desciption) {
        this.description = desciption;
    }

    /**
     * Creator setter
     *
     * @param creator Creator to assign
     */

    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * Susbcribers setter
     *
     * @param susbcribers Susbcribers to assign
     */

   public void setSusbcribers(Set<User> susbcribers) {
        this.susbcribers = susbcribers;
    }

    /**
     * Songs setter
     *
     * @param songs Songs to assign
     */

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }


    /**
     * Name getter
     *
     * @return Name of Playlist
     */
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    /**
     * Id getter
     *
     * @return Id of Playlist
     */

    public Integer getId() {
        // TODO Auto-generated method stub
        return id;
    }

    /**
     * Description getter
     *
     * @return Description of Playlist
     */

    public String getDescription() {
        // TODO Auto-generated method stub
        return description;
    }

    /**
     * Creator getter
     *
     * @return Creator of Playlist
     */

    public User getCreator() {
        // TODO Auto-generated method stub
        return creator;
    }

    /**
     * Suscribers getter
     *
     * @return Suscribers of Playlist
     */

    public Set<User> getSusbcribers() {
        // TODO Auto-generated method stub
        return susbcribers;
    }

    /**
     * Songs getter
     *
     * @return Songs of Playlist
     */

    public Set<Song> getSongs() {
        // TODO Auto-generated method stub
        return songs;
    }

    public int compareTo(Playlist o) {

        return this.name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Playlist)) return false;

        Playlist playlist = (Playlist) obj;

        return this.id == playlist.id;


    }


}
