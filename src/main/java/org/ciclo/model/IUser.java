package org.ciclo.model;


import java.io.Serializable;
import java.util.Set;

public interface IUser extends Comparable<IUser>, Serializable { //Angel

    abstract public Integer getId();
    abstract public String getName();
    abstract public String getPhoto();
    abstract public String getEmail();
    abstract public Set<IPlaylists> getCrated();  //Lazy
    abstract public Set<IPlaylists> getSubscribed(); //Lazy
    abstract public Set<IComments> getComments(); //Lazy
    abstract public Set<IReproduction> getReproductions(); //Lazy
}
