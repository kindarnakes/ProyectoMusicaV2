package org.ciclo.model;


import java.io.Serializable;
import java.util.Set;

public interface IUser extends Comparable<IUser>, Serializable { //Angel

    Integer getId();

    String getName();

    String getPhoto();

    String getEmail();

    Set<IPlaylists> getCreated();  //Lazy

    Set<IPlaylists> getSubscribed(); //Lazy

    Set<IComments> getComments(); //Lazy

    Set<IReproduction> getReproductions(); //Lazy
}
