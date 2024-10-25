package org.spring.repositories.repositoryImpl;

import org.spring.models.Tournament;
import org.spring.repositories.TournamentRepository;
import org.spring.utils.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TournamentRepositoryImpl implements TournamentRepository {

    private EntityManager entityManager;

    public TournamentRepositoryImpl() {
        this.entityManager = EntityManagerSingleton.getInstance().getEntityManager();
    }

    @Override
    public Long createTournament(Tournament tournament) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(tournament.getGame());
            entityManager.persist(tournament);
            entityManager.flush();
            transaction.commit();
            return tournament.getId();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Tournament readTournament(Long id) {
        try {
            return entityManager.find(Tournament.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Tournament> readAllTournaments() {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            List<Tournament> tournaments = entityManager.createQuery("SELECT t FROM Tournament t", Tournament.class).getResultList();
            transaction.commit();
            return tournaments;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateTournament(Tournament tournament) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(tournament);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTournament(Long id) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Tournament tournament = entityManager.find(Tournament.class, id);
            if (tournament != null) {
                entityManager.remove(tournament);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
