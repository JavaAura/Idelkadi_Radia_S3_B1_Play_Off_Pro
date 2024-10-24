package org.spring.services;

import org.spring.models.Tournament;

import java.util.List;

 public interface TournamentService {

     Long createTournament(Tournament tournament);

     Tournament readTournament(Long id);

     List<Tournament> readAllTournaments();

     boolean updateTournament(Tournament tournament);

     boolean deleteTournament(Long id);

     Double getEstimatedDuration(Long tournamentId);
}

    