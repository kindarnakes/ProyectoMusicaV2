package org.ciclo.model;

import java.io.Serializable;
import java.util.Set;

public interface IArtist extends Comparable<IArtist>, Serializable { //Agustin

    Integer getId();

    String getName();

    String getFrom();

    String getPhoto();

    Set<IDisc> getDiscs(); //Lazy
}
