package org.ciclo.model;

import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@org.hibernate.annotations.NamedQuery(
        name = "getAllPlaylist",
        query = "FROM Playlist",
        timeout = 1
)
@org.hibernate.annotations.NamedQuery(
        name = "getNamed",
        query = "FROM Playlist WHERE name = :name",
        timeout = 1
)
@org.hibernate.annotations.NamedQuery(
        name = "getBySong",
        query = "SELECT DISTINCT p FROM Playlist p JOIN p.songs Song WHERE Song.id = :id",
        timeout = 1
)
@org.hibernate.annotations.NamedQuery(
        name = "getByUser",
        query = "SELECT DISTINCT p FROM Playlist p JOIN p.creator User WHERE User.id = :id",
        timeout = 1
)

@org.hibernate.annotations.NamedQuery(
        name = "getBySubscriber",
        query = "SELECT DISTINCT p FROM Playlist p JOIN p.susbcribers User WHERE User.id = :id",
        timeout = 1
)
@Entity
@Table(name = "lista_reproduccion")
public class Playlist implements Serializable, Comparable<Playlist> {
    /**
     * Name of Playlist
     */
    @Column(name = "nombre")
    private String name;
    /**
     * Database Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * Description of Playlist
     */
    @Column(name = "descripcion")
    private String description;
    /**
     * Creator of Playlist
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_creador")
    private User creator;
    /**
     * Susbcribers of Playlist
     */
    @ManyToMany(mappedBy = "_subscribed")
    private Set<User> susbcribers;
    /**
     * Songs of Playlist
     */

    @ManyToMany(mappedBy = "list")
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
     * @param description
     * @param creator
     * @param susbcribers
     * @param songs
     */
    public Playlist(String name, String description, User creator, Set<User> susbcribers, Set<Song> songs) {
        super();
        this.name = name;
        this.description = description;
        this.setCreator(creator);
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
        if ( creator!= null &&  !creator.getCreated().contains(this)) {
            creator.create(this);
        }
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
        if(this.creator == null){
            this.creator = new User("anonimo", "anonimo", "anonimo");
        }
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

    /**
     * Add a subscription to playlist
     *
     * @param u User to subscribe
     * @return True if subscribe, false if not
     */
    public boolean subscribe(User u) {
        if(!u.getSubscribed().contains(this)){
            u.subscribe(this);
        }
        return this.susbcribers.add(u);
    }

    /**
     * Remove a subscription to playlist
     *
     * @param u User to unsubscribe
     * @return True if unsubscribe, false if not
     */
    public boolean unsubscribe(User u) {
        if(u.getSubscribed().contains(this)){
            u.unsubscribe(this);
        }
        return this.susbcribers.remove(u);
    }


    /**
     * Add a song to playlist
     *
     * @param s Song to add
     * @return True if add, false if not
     */
    public boolean addSong(Song s) {
        if(!s.getLists().contains(this)){
            s.getLists().add(this);
        }
        return this.songs.add(s);
    }

    /**
     * Remove a song to playlist
     *
     * @param s Song to unsubscribe
     * @return True if remove, false if not
     */
    public boolean removeSong(Song s) {
        if(s.getLists().contains(this)){
            s.getLists().remove(this);
        }
        return this.susbcribers.remove(s);
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
