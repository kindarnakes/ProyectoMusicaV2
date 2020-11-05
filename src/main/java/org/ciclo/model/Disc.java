package org.ciclo.model;

import java.time.LocalDate;
import java.util.Set;

/**
 * Disc class
 */
public class Disc implements IDisc {
    /**
     * Database Id
     */
    private Integer _id;
    /**
     * Disc name
     */
    private String _name;
    /**
     * Disc releaseDate
     */
    private LocalDate _ReleaseDate;
    /**
     * Url to disc photo
     */
    private String _photo;
    /**
     * Disc artist
     */
    private IArtist _artist;
    /**
     * Disc's songs collection
     */
    private Set<ISong> _songs;

    /**
     * Constructor
     */
    public Disc() {
    }

    /**
     * Parametrized constructor
     *
     * @param _name
     * @param _ReleaseDate
     * @param _photo
     * @param _artist
     * @param _songs
     */
    public Disc(String _name, LocalDate _ReleaseDate, String _photo, IArtist _artist, Set<ISong> _songs) {
        this._name = _name;
        this._ReleaseDate = _ReleaseDate;
        this._photo = _photo;
        this._artist = _artist;
        this._songs = _songs;
    }

    /**
     * Id getter
     *
     * @return Disc's id
     */
    @Override
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
     * Name getter
     *
     * @return Disc's name
     */
    @Override
    public String getName() {
        return _name;
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
     * ReleaseDate getter
     *
     * @return Disc's ReleaseDate
     */
    @Override
    public LocalDate getReleaseDate() {
        return _ReleaseDate;
    }

    /**
     * ReleaseDate setter
     *
     * @param _ReleaseDate ReleaseDate to assign
     */
    public void setReleaseDate(LocalDate _ReleaseDate) {
        this._ReleaseDate = _ReleaseDate;
    }

    /**
     * Photo getter
     *
     * @return Disc's photo
     */
    @Override
    public String getPhoto() {
        return _photo;
    }

    /**
     * Photo setter
     *
     * @param _photo Photo to assign
     */
    public void setPhoto(String _photo) {
        this._photo = _photo;
    }

    /**
     * Artist getter
     *
     * @return Disc's artist
     */
    @Override
    public IArtist getArtist() {
        return _artist;
    }

    /**
     * Artist setter
     *
     * @param _artist Artist to assign
     */
    public void setArtist(IArtist _artist) {
        this._artist = _artist;
    }

    /**
     * Songs getter
     *
     * @return Disc's songs
     */
    @Override
    public Set<ISong> getSongs() {
        return _songs;
    }

    /**
     * Songs setter
     *
     * @param _songs Songs to assign
     */
    public void setSongs(Set<ISong> _songs) {
        this._songs = _songs;
    }

    /**
     * Add a song to Disc
     *
     * @param song Song to add
     * @return True if add, false if not
     */
    public boolean addSong(ISong song) {
        return this._songs.add(song);
    }

    /**
     * Song to remove of Disc
     *
     * @param song Song to remove
     * @return True if remove, false if not
     */
    public boolean removeSong(ISong song) {
        return this._songs.remove(song);
    }

    @Override
    public int compareTo(IDisc o) {
        return o.getName().compareTo(this._name);
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disc)) return false;

        Disc disc = (Disc) o;

        return _id.equals(disc._id);
    }

    @Override
    public String toString() {
        return "Disc{" + "_id=" + _id + ", _name=" + _name + ", _ReleaseDate=" + _ReleaseDate + ", _photo=" + _photo + ", _artist=" + _artist + ", _songs=" + _songs + '}';
    }


}
