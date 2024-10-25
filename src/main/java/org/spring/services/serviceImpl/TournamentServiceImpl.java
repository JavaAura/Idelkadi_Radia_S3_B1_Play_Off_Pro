package org.spring.services.serviceImpl;

import org.spring.dao.TournamentDao;
import org.spring.dao.daoImpl.TournamentDaoExtension;
import org.spring.models.Tournament;
import org.spring.repositories.TournamentRepository;
import org.spring.services.TournamentService;

import java.util.List;

public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentDao tournamentDao;
    public TournamentServiceImpl(TournamentRepository tournamentRepository , TournamentDao tournamentDao) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentDao = tournamentDao;
    }
    @Override
    public Long createTournament(Tournament tournament) {
        return tournamentRepository.createTournament(tournament);
    }

    @Override
    public Tournament readTournament(Long id) {
        return tournamentRepository.readTournament(id);
    }

    @Override
    public List<Tournament> readAllTournaments() {
        return tournamentRepository.readAllTournaments();
    }

    @Override
    public boolean updateTournament(Tournament tournament) {
        return tournamentRepository.updateTournament(tournament);
    }

    @Override
    public boolean deleteTournament(Long id) {
        return tournamentRepository.deleteTournament(id);
    }

    @Override
    public Double getEstimatedDuration(Long tournamentId) {
        return tournamentDao.calculateEstimatedDuration(tournamentId);
    }
}
