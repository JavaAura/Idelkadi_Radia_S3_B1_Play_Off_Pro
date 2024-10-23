package org.spring.repositories.repositoryImpl;

import org.spring.models.Player;
import org.spring.repositories.PlayerRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepository {

    private final EntityManager entityManager;

    public PlayerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Player player) {
        try {
            entityManager.persist(player);
            return true;
        } catch (Exception e) {
            System.out.println("Error saving player: " + e.getMessage());
            e.printStackTrace();
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
        try {
            entityManager.merge(player);
            return true;
        } catch (Exception e) {
            System.out.println("Error updating player: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Player player = entityManager.find(Player.class, id);
            if (player != null) {
                entityManager.remove(player);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error deleting player: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
