package org.ciclo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IReproduction extends Comparable<IReproduction>, Serializable { //No incluida Scrum 1

    IUser getUser();

    ISong getSong();

    LocalDateTime getInstant();

}
