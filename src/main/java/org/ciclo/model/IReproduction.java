package org.ciclo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IReproduction extends Comparable<IReproduction>, Serializable { //No incluida Scrum 1

    abstract public IUser getUser();
    abstract public ISong getSong();
    abstract public LocalDateTime getInstant();

}
