package org.ciclo.model;

import java.io.Serializable;
import java.util.Set;

public interface IPlaylists extends  Comparable<IPlaylists>, Serializable { //Santos

    abstract public String getName();
    abstract public Integer getId();
    abstract public String getDesciption();
    abstract public IUser getCreator();
    abstract public Set<IUser> getSusbcribers(); //Lazy
    abstract public Set<ISong> getSongs(); //Lazy
    abstract public Set<IComments> getComments(); //Lazy
}
