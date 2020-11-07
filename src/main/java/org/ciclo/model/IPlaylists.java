package org.ciclo.model;

import java.io.Serializable;
import java.util.Set;

public interface IPlaylists extends Comparable<IPlaylists>, Serializable { //Santos

    String getName();

    Integer getId();

    String getDescription();

    IUser getCreator();

    Set<IUser> getSusbcribers(); //Lazy

    Set<ISong> getSongs(); //Lazy

    Set<IComments> getComments(); //Lazy
}
