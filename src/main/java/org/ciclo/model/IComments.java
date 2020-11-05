package org.ciclo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IComments extends Comparable<IComments>, Serializable { //No incluida scrum 1

    IUser getUser();

    IPlaylists getReproductionList();

    LocalDateTime getInstant();

    String getComment();

}
