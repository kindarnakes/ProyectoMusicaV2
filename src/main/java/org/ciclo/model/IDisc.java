package org.ciclo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public interface IDisc extends Comparable<IDisc>, Serializable { //Agustin

    abstract public Integer getId();
    abstract public String getName();
    abstract public LocalDate getReleaseDate();
    abstract public String getPhoto();
    abstract public IArtist getArtist();
    abstract public Set<ISong> getSongs(); //Eagle

}
