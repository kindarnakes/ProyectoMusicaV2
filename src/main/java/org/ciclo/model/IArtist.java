package org.ciclo.model;

import java.io.Serializable;
import java.util.Set;

public interface IArtist extends Comparable<IArtist>, Serializable { //Agustin

    abstract public Integer getId();
    abstract public String getName();
    abstract public String getFrom();
    abstract public String getPhoto();
    abstract public Set<IDisc> getDiscs(); //Lazy
}
