package org.ciclo.model;

import java.io.Serializable;
import java.util.Set;

public interface ISong extends Comparable<ISong>, Serializable { //Santos

    Integer getId();

    String getName();

    Integer getDuration();

    IGenre getGenre();

    Set<IPlaylists> getLists(); //Lazy

    IDisc getDisc();

    Set<IReproduction> getReproductions(); //Lazy

}
