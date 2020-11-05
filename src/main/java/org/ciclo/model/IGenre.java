package org.ciclo.model;

import java.io.Serializable;
import java.util.Set;

public interface IGenre extends Comparable<IGenre>, Serializable { //Angel

    String getName();

    Integer getId();

    Set<ISong> getSongs();


}
