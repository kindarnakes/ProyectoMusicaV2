package org.ciclo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public interface IDisc extends Comparable<IDisc>, Serializable { //Agustin

    Integer getId();

    String getName();

    LocalDate getReleaseDate();

    String getPhoto();

    IArtist getArtist();

    Set<ISong> getSongs(); //Eagle

}
