package org.ciclo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Person using the system
 */
@Entity
@Table(name = "usuario")
public class User implements Serializable {

    /**
     * Database Id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer _id;
    /**
     * User nickname
     */
    @Column(name = "nombre")
    private String _name;
    /**
     * Url to user photo
     */
    @Column(name = "foto", columnDefinition="TEXT")
    private String _photo;
    /**
     * User's email
     */
    @Column(name = "correo")
    private String _email;
    /**
     * Playlists created by user
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "creator")
    private Set<Playlist> _created;
    /**
     * Playlists to which the user is subscribed
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "suscripcion",
            joinColumns = { @JoinColumn(name = "id_usuario") },
            inverseJoinColumns = { @JoinColumn(name = "id_lista") }
    )
    private Set<Playlist> _subscribed;

    /**
     * Constructor
     */
    public User() {
    }

    /**
     * Parametrized constructor
     *
     * @param _name  User's name
     * @param _photo Url to user's photo
     * @param _email User's email
     */
    public User(String _name, String _photo, String _email) {
        this._name = _name;
        this._photo = _photo;
        this._email = _email;
    }


    /**
     * Id getter
     *
     * @return User's id
     */
    public Integer getId() {
        return this._id;
    }


    /**
     * Name getter
     *
     * @return User's name
     */
    public String getName() {
        return this._name;
    }

    /**
     * Photo getter
     *
     * @return Url to user's photo
     */
    public String getPhoto() {
        return this._photo;
    }


    /**
     * Email getter
     *
     * @return User's email
     */
    public String getEmail() {
        return this._email;
    }

    /**
     * Created getter
     *
     * @return Set of playlist created
     */
    public Set<Playlist> getCreated() {
        return this._created;
    }


    /**
     * Subscribed getter
     *
     * @return Set of playlist subscribed
     */
    public Set<Playlist> getSubscribed() {
        return this._subscribed;
    }


    /**
     * Id setter
     *
     * @param _id Id to assign
     */
    public void setId(Integer _id) {
        this._id = _id;
    }

    /**
     * Name setter
     *
     * @param _name Name to assign
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * Photo setter
     *
     * @param _photo Url to user's photo to assign
     */
    public void setPhoto(String _photo) {
        this._photo = _photo;
    }

    /**
     * Email setter
     *
     * @param _email Email to assign
     */
    public void setEmail(String _email) {
        this._email = _email;
    }

    /**
     * Created setter
     *
     * @param _created Set of playlist to assign
     */
    public void setCreated(Set<Playlist> _created) {
        this._created = _created;
    }

    /**
     * Subscribed setter
     *
     * @param _subscribed Set of playlist to assign
     */
    public void setSuscribed(Set<Playlist> _subscribed) {
        this._subscribed = _subscribed;
    }


    /**
     * Add a user created playlist
     *
     * @param rp playlist to add
     * @return True if add, false if not
     */
    public boolean create(Playlist rp) {
        return this._created.add(rp);
    }

    /**
     * Add a subscription to playlist
     *
     * @param rp playlist to subscribe
     * @return True if subscribe, false if not
     */
    public boolean subscribe(Playlist rp) {
        return this._subscribed.add(rp);
    }

    /**
     * Remove a subscription to playlist
     *
     * @param rp playlist to unsubscribe
     * @return True if unsubscribe, false if not
     */
    public boolean unsubscribe(Playlist rp) {
        return this._subscribed.remove(rp);
    }


    public int compareTo(User o) {
        return o.getEmail().compareTo(this._email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return _email.equals(user._email);
    }

    @Override
    public int hashCode() {
        return _email.hashCode();
    }
}
