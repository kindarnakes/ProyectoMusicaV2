package org.ciclo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IComments extends Comparable<IComments>, Serializable { //No incluida scrum 1

    abstract public IUser getUser();
    abstract public IPlaylists getReproductionList();
    abstract public LocalDateTime getInstant();
    abstract public String getComment();

}
