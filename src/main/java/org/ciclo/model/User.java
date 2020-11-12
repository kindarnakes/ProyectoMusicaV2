package org.ciclo.model;

import java.util.Set;

/**
 * Person using the system
 */
public class User implements IUser {

    /**
     * Database Id
     */
    private Integer _id;
    /**
     * User nickname
     */
    private String _name;
    /**
     * Url to user photo
     */
    private String _photo;
    /**
     * User's email
     */
    private String _email;
    /**
     * Playlists created by user
     */
    private Set<IPlaylists> _created;
    /**
     * Playlists to which the user is subscribed
     */
    private Set<IPlaylists> _subscribed;
    /**
     * User's comments
     */
    private Set<IComments> _comments;
    /**
     * User's reproductions of a songs
     */
    private Set<IReproduction> _reproductions;

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
    public Set<IPlaylists> getCreated() {
        return this._created;
    }


    /**
     * Subscribed getter
     *
     * @return Set of playlist subscribed
     */
    public Set<IPlaylists> getSubscribed() {
        return this._subscribed;
    }


    /**
     * Comments getter
     *
     * @return Set of user's comments
     */
    public Set<IComments> getComments() {
        return this._comments;
    }


    /**
     * Reproductions getter
     *
     * @return Set of user's reproductions
     */
    public Set<IReproduction> getReproductions() {
        return this._reproductions;
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
    public void setCreated(Set<IPlaylists> _created) {
        this._created = _created;
    }

    /**
     * Subscribed setter
     *
     * @param _subscribed Set of playlist to assign
     */
    public void setSuscribed(Set<IPlaylists> _subscribed) {
        this._subscribed = _subscribed;
    }

    /**
     * Comments setter
     *
     * @param _comments Set of comments to assign
     */
    public void setComments(Set<IComments> _comments) {
        this._comments = _comments;
    }

    /**
     * Reproductions setter
     *
     * @param _reproductions Set of reproductions to assign
     */
    public void setReproductions(Set<IReproduction> _reproductions) {
        this._reproductions = _reproductions;
    }

    /**
     * Add a user created playlist
     *
     * @param rp playlist to add
     * @return True if add, false if not
     */
    public boolean create(IPlaylists rp) {
        return this._created.add(rp);
    }

    /**
     * Add a subscription to playlist
     *
     * @param rp playlist to subscribe
     * @return True if subscribe, false if not
     */
    public boolean subscribe(IPlaylists rp) {
        return this._subscribed.add(rp);
    }

    /**
     * Remove a subscription to playlist
     *
     * @param rp playlist to unsubscribe
     * @return True if unsubscribe, false if not
     */
    public boolean unsubscribe(IPlaylists rp) {
        return this._subscribed.remove(rp);
    }

    /**
     * Assign a comment
     *
     * @param comment comment to add
     * @return True if add, false if not
     */
    public boolean comment(IComments comment) {
        return this._comments.add(comment);
    }

    /**
     * Remove a comment
     *
     * @param comment comment to remove
     * @return True if remove, false if not
     */
    public boolean uncomment(IComments comment) {
        return this._comments.remove(comment);
    }

    /**
     * Assign a reproduction
     *
     * @param reproduction reproduction to add
     * @return True if add, false if not
     */
    public boolean reproduce(IReproduction reproduction) {
        return this._reproductions.add(reproduction);
    }

    @Override
    public int compareTo(IUser o) {
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
