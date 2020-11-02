package org.ciclo.model;

import java.io.Serializable;
import java.util.Set;

public interface ISong extends Comparable<ISong>, Serializable { //Agustin

    abstract public Integer getId();
    abstract public String getName();
    abstract public Integer getDuration();
    abstract public IGenre getGenre();
    abstract public Set<IPlaylists> getLists(); //Lazy
    abstract public IDisc getDisc();
    abstract public Set<IReproduction> getReproductions(); //Lazy

}
