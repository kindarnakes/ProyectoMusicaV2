package org.ciclo.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "cancion")
public class Song implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * Database Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;
    /**
     * Name of song
     */
    @Column(name = "nombre")
    private String name;
    /**
     * Duration of song
     */
    @Column(name = "duracion")
    private int duration;

    /**
     * Song playlist
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "lista_cancion",
            joinColumns = {@JoinColumn(name = "id_cancion")},
            inverseJoinColumns = {@JoinColumn(name = "id_lista")}
    )
    private Set<Playlist> list;
    /**
     * Song of disc
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_disco")
    private Disc disc;

    /**
     * Constructor
     */
    public Song() {
        super();
    }


    /**
     * Parametrized constructor
     *
     * @param name
     * @param duration
     * @param list
     * @param disc
     */
    public Song(String name, int duration, Set<Playlist> list, Disc disc
    ) {
        super();
        this.name = name;
        this.duration = duration;
        this.list = list;
        this.disc = disc;

    }


    /**
     * Parametrized constructor
     *
     * @param name
     * @param duration
     * @param disc
     */
    public Song(String name, int duration, Disc disc) {
        super();
        this.name = name;
        this.duration = duration;
        this.disc = disc;
    }


    /**
     * PlayList setter
     *
     * @param list Playlist to assign
     */
    public void setList(Set<Playlist> list) {
        this.list = list;
    }

    /**
     * Id setter
     *
     * @param id Id to assign
     */

    public void setId(int id) {
        Id = id;
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
     * Duration setter
     *
     * @param duration Duration to assign
     */

    public void setDuration(int duration) {
        this.duration = duration;
    }


    /**
     * Disc setter
     *
     * @param disc Disc to assign
     */

    public void setDisc(Disc disc) {

     this.disc=disc;
       /* if(disc!=null && !disc.getSongs().contains(this)){
            disc.addSong(this);
        }

        */
    }


    /**
     * Id getter
     *
     * @return Id of song
     */
    public Integer getId() {

        return Id;
    }

    /**
     * Name getter
     *
     * @return Name of song
     */

    public String getName() {

        return name;
    }

    /**
     * Duration getter
     *
     * @return Duration of song
     */

    public Integer getDuration() {
        // TODO Auto-generated method stub
        return duration;
    }


    /**
     * Playlist getter
     *
     * @return Playlist of song
     */


    public Set<Playlist> getLists() {
        // TODO Auto-generated method stub
        return list;
    }

    /**
     * Disc getter
     *
     * @return Disc of song
     */

    public Disc getDisc() {
        // TODO Auto-generated method stub
        return disc;
    }

    public int compareTo(Song o) {

        return this.name.compareTo(o.getName());
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Song)) return false;

        Song song = (Song) obj;

        return this.Id == song.Id && this.name.equals(song.name);


    }

    @Override
    public String toString() {
        return name + " : " + getDisc().getArtist().getName();
    }
}
