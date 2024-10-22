package org.spring.repositories.repositoryImpl;

import org.spring.models.Team;
import org.spring.repositories.TeamRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class TeamRepositoryImpl implements TeamRepository {

    private EntityManager entityManager;

    public TeamRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean createTeam(Team team) {
        try {
            entityManager.persist(team);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Team readTeam(Long id) {
        try {
            return entityManager.find(Team.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Team> readAllTeams() {
        try {
            return entityManager.createQuery("SELECT t FROM Team t", Team.class).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateTeam(Team team) {
        try {
            entityManager.merge(team);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTeam(Long id) {
        try {
            Team team = entityManager.find(Team.class, id);
            if (team != null) {
                entityManager.remove(team);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
