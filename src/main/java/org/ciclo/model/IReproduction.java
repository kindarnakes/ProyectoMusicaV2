package org.ciclo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IReproduction extends Comparable<IReproduction>, Serializable { //Santos

    abstract public IUser getUser();
    abstract public ISong getSong();
    abstract public LocalDateTime getInstant();

}
