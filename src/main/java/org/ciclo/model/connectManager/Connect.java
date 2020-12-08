package org.ciclo.model.connectManager;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connect {

    private static final String PERSISTENCE_UNIT = "MariaDB";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    private static EntityManager manager = null;

    public static EntityManager getManager() {
        try {
            if (manager == null || !manager.isOpen()) {
                manager = emf.createEntityManager();
            }
        }catch (IllegalStateException ex){
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return manager;
    }

}
