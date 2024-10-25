package org.spring.dao.daoImpl;

import org.spring.dao.TournamentDao;
import org.spring.models.Tournament;
import org.spring.utils.EntityManagerSingleton;

import javax.persistence.EntityManager;

public class TournamentDaoExtension  implements TournamentDao {
    private EntityManager entityManager;

    public TournamentDaoExtension() {
        entityManager = EntityManagerSingleton.getEntityManager();
    }

    @Override
    public Double calculateEstimatedDuration(Long tournamentId) {
        Tournament tournament= readTournament(tournamentId);

        int numberOfTeams = tournament.getTeams().size();
        double averageMatchDuration = tournament.getGame().getAvgMatchDuration();
        double breakTime = tournament.getBreakTimeBetweenMatches();
        double ceremonyTime = tournament.getCeremonyTime() * 2;
        double difficultyMultiplier = tournament.getGame().getDifficulty().getMultiplier();

        return (numberOfTeams * averageMatchDuration * difficultyMultiplier) + breakTime + ceremonyTime;

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
