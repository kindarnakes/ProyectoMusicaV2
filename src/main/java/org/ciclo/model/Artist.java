package org.ciclo.model;

import java.util.Set;

/**
 * Artist class
 */

public class Artist implements IArtist {

    /**
     * Database Id
     */
    private Integer _id;
    /**
     * Artist name
     */
    private String _name;
    /**
     * Url to artist photo
     */
    private String _photo;
    /**
     * Artist's nationality
     */
    private String _nationality;
    /**
     * Artist's discs collection
     */
    private Set<IDisc> _Discs;

    /**
     * Constructor
     */
    public Artist() {
    }

    /**
     * Parametrized constructor
     *
     * @param _name
     * @param _photo
     * @param _nationality
     */
    public Artist(String _name, String _photo, String _nationality) {
        this._name = _name;
        this._photo = _photo;
        this._nationality = _nationality;
    }

    /**
     * Id getter
     *
     * @return Artist's id
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
     * @return Artist's name
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
     * Photo getter
     *
     * @return Artist's photo
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
     * Nationality getter
     *
     * @return Artist's nationality
     */
    @Override
    public String getFrom() {
        return _nationality;
    }

    /**
     * Nationality setter
     *
     * @param _nationality Nationality to assign
     */
    public void setNationality(String _nationality) {
        this._nationality = _nationality;
    }

    /**
     * Discs getter
     *
     * @return Artist's discs
     */
    @Override
    public Set<IDisc> getDiscs() {
        return _Discs;
    }

    /**
     * Discs setter
     *
     * @param _Discs Disc to assign
     */
    public void setDiscs(Set<IDisc> _Discs) {
        this._Discs = _Discs;
    }

    /**
     * Add a disc to Artist
     *
     * @param disc Disc to add
     * @return True if add, false if not
     */
    public boolean addDisc(IDisc disc) {
        return this._Discs.add(disc);
    }

    /**
     * Disc to remove of Artist
     *
     * @param disc Disc to remove
     * @return True if remove, false if not
     */
    public boolean removeDisc(IDisc disc) {
        return this._Discs.remove(disc);
    }

    @Override
    public int compareTo(IArtist o) {
        return o.getName().compareTo(this._name);
    }

    @Override
    public int hashCode() {
        return _name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;

        Artist artist = (Artist) o;

        return _id.equals(artist._id);
    }

    @Override
    public String toString() {
        return "Artist{" + "_id=" + _id + ", _name=" + _name + ", _photo=" + _photo + ", _nationality=" + _nationality + ", _Discs=" + _Discs + '}';
    }


}
