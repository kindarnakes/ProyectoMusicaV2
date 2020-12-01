package org.ciclo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="lista_reproduccion")
public class Playlist implements IPlaylists {
    /**
     * Name of Playlist
     */
    @Column(name="nombre")
    private String name;
    /**
     * Database Id
     */
    @Id
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
    @Column(name="id_creador")
    private IUser creator;
    /**
     * Susbcribers of Playlist
     */
    private Set<IUser> susbcribers;
    /**
     * Songs of Playlist
     */
    private Set<ISong> songs;


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
     * @param comments
     */
    public Playlist(String name, int id, String description, IUser creator, Set<IUser> susbcribers, Set<ISong> songs,
                    Set<IComments> comments) {
        super();
        this.name = name;
        this.id = id;
        this.description = description;
        this.creator = creator;
        //this.susbcribers = susbcribers;
        //this.songs = songs;
        //this.comments = comments;
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

    public void setCreator(IUser creator) {
        this.creator = creator;
    }

    /**
     * Susbcribers setter
     *
     * @param susbcribers Susbcribers to assign
     */

   public void setSusbcribers(Set<IUser> susbcribers) {
        this.susbcribers = susbcribers;
    }

    /**
     * Songs setter
     *
     * @param songs Songs to assign
     */

    public void setSongs(Set<ISong> songs) {
        this.songs = songs;
    }


    /**
     * Name getter
     *
     * @return Name of Playlist
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    /**
     * Id getter
     *
     * @return Id of Playlist
     */

    @Override
    public Integer getId() {
        // TODO Auto-generated method stub
        return id;
    }

    /**
     * Description getter
     *
     * @return Description of Playlist
     */

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return description;
    }

    /**
     * Creator getter
     *
     * @return Creator of Playlist
     */

    @Override
    public IUser getCreator() {
        // TODO Auto-generated method stub
        return creator;
    }

    /**
     * Suscribers getter
     *
     * @return Suscribers of Playlist
     */

    @Override
    public Set<IUser> getSusbcribers() {
        // TODO Auto-generated method stub
        return susbcribers;
    }

    /**
     * Songs getter
     *
     * @return Songs of Playlist
     */

    @Override
    public Set<ISong> getSongs() {
        // TODO Auto-generated method stub
        return songs;
    }


    @Override
    public int compareTo(IPlaylists o) {

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
