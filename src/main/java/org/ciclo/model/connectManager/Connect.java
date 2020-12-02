package org.ciclo.model.connectManager;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connect {

    private static final EntityManagerFactory emf =  Persistence.createEntityManagerFactory("aplicacion");
    private static EntityManager manager = null;

    public static EntityManager getManager(){
        if(manager == null || !manager.isOpen()){
            manager = emf.createEntityManager();
        }
        return manager;
    }

}
