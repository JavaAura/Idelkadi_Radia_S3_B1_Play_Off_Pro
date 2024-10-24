package org.spring.repositories.repositoryImpl;

import org.spring.models.Game;
import org.spring.repositories.GameRepository;
import org.spring.utils.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {

    private EntityManager entityManager;

    public GameRepositoryImpl() {
        this.entityManager = EntityManagerSingleton.getInstance().getEntityManager();
    }

    @Override
    public boolean save(Game game) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(game);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error saving game: " + e.getMessage());
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public Game findById(Long id) {
        try {
            return entityManager.find(Game.class, id);
        } catch (Exception e) {
            System.out.println("Error finding game: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Game> findAll() {
        try {
            return entityManager.createQuery("SELECT g FROM Game g", Game.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error retrieving games: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Game game) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(game);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error updating game: " + e.getMessage());
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Game game = entityManager.find(Game.class, id);
            if (game != null) {
                entityManager.remove(game);
                transaction.commit();
                return true;
            }
            transaction.commit();
            return false;
        } catch (Exception e) {
            System.out.println("Error deleting game: " + e.getMessage());
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }
}
