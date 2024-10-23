package org.spring.repositories.repositoryImpl;

import org.spring.models.Player;
import org.spring.repositories.PlayerRepository;
import org.spring.utils.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepository {

    private EntityManager entityManager;

    public PlayerRepositoryImpl() {
        this.entityManager = EntityManagerSingleton.getInstance().getEntityManager();
    }

    @Override
    public boolean save(Player player) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(player);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error saving player: " + e.getMessage());
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public Player findById(Long id) {
        try {
            return entityManager.find(Player.class, id);
        } catch (Exception e) {
            System.out.println("Error finding player: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Player> findAll() {
        try {
            return entityManager.createQuery("SELECT p FROM Player p", Player.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error retrieving players: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Player player) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(player);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error updating player: " + e.getMessage());
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
            Player player = entityManager.find(Player.class, id);
            if (player != null) {
                entityManager.remove(player);
                transaction.commit();
                return true;
            }
            transaction.commit();
            return false;
        } catch (Exception e) {
            System.out.println("Error deleting player: " + e.getMessage());
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }
}
