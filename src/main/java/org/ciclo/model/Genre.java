package org.ciclo.model;

import java.util.Set;

/**
 * Represents a music genre
 */
public class Genre implements IGenre {

    /**
     * name of genre
     */
    private String _name;
    /**
     * Database id
     */
    private Integer _id;
    /**
     * Songs that belong to a genre
     */
    private Set<ISong> _songs;

    /**
     * Constructor
     */
    public Genre() {
    }

    /**
     * Parametrized constructor
     *
     * @param _name  Name to assign
     * @param _songs Songs to assign
     */
    public Genre(String _name, Set<ISong> _songs) {
        this._name = _name;
        this._songs = _songs;
    }

    /**
     * Name getter
     *
     * @return Name of genre
     */
    public String getName() {
        return _name;
    }

    /**
     * Name Setter
     *
     * @param _name name to assign
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * Id getter
     *
     * @return Id of genre
     */
    public Integer getId() {
        return _id;
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
     * Songs getter
     *
     * @return Songs of a genre
     */
    public Set<ISong> getSongs() {
        return _songs;
    }

    /**
     * Songs setter
     *
     * @param _songs Set of songs belong to a genre
     */
    public void setSongs(Set<ISong> _songs) {
        this._songs = _songs;
    }


    /**
     * Add a song in a genre
     *
     * @param song Song to add
     * @return True if add, false if not
     */
    public boolean addSong(ISong song) {
        return this._songs.add(song);
    }

    /**
     * Song to remove in a genre
     *
     * @param song Song to remove
     * @return True if remove, false if not
     */
    public boolean removeSong(ISong song) {
        return this._songs.remove(song);
    }


    @Override
    public int compareTo(IGenre o) {
        return o.getName().compareTo(this._name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;

        Genre genre = (Genre) o;

        return _name.equals(genre._name);
    }

    @Override
    public int hashCode() {
        return _name.hashCode();
    }
}
