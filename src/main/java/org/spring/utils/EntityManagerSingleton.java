package org.spring.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {

    private static EntityManagerSingleton instance;
    private static  EntityManager entityManager;

    private EntityManagerSingleton() {
        String persistenceName = System.getProperty("puName", "tournament");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        this.entityManager = emf.createEntityManager();
    }

    public static EntityManagerSingleton getInstance() {
        if (instance == null) {
            synchronized (EntityManagerSingleton.class) {
                if (instance == null) {
                    instance = new EntityManagerSingleton();
                }
            }
        }
        return instance;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}