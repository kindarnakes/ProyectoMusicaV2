package org.ciclo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IComments extends Comparable<IComments>, Serializable { //Santos

    abstract public IUser getUser();
    abstract public IPlaylists getReproductionList();
    abstract public LocalDateTime getInstant();
    abstract public String getComment();

}
