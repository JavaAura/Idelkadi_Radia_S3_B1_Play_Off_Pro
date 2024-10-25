package org.spring.dao.daoImpl;

import org.spring.dao.TournamentDao;
import org.spring.models.Tournament;
import org.spring.utils.EntityManagerSingleton;

import javax.persistence.EntityManager;

public class TournamentDaoImpl implements TournamentDao {

    private EntityManager entityManager;
    public TournamentDaoImpl() {
        entityManager = EntityManagerSingleton.getEntityManager();
    }

    @Override
    public Double calculateEstimatedDuration(Long tournamentId) {
        Tournament tournament= readTournament(tournamentId);

        int numberOfTeams = tournament.getTeams().size();
        double averageMatchDuration = tournament.getGame().getAvgMatchDuration();
        double breakTime = tournament.getBreakTimeBetweenMatches();

        return (numberOfTeams * averageMatchDuration) + breakTime;
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

}
