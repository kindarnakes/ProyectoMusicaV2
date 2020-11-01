package org.ciclo.model;

import java.io.Serializable;
import java.util.Set;

public interface IGenre extends Comparable<IGenre>, Serializable { //Angel

    abstract public String getName();
    abstract public Integer getId();
    abstract public Set<ISong> getSongs();


}
