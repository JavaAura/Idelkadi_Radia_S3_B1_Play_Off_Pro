package org.spring.services.serviceImpl;

import org.spring.models.Player;
import org.spring.services.PlayerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PlayerServiceImpl  implements PlayerService {
    private EntityManager entityManager;

    public PlayerServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void ajouterJoueur(Player joueur) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(joueur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
