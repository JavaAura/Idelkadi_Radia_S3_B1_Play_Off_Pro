package org.spring.services;

import org.spring.models.Tournament;

import java.util.List;

 public interface TournamentService {

     boolean createTournament(Tournament tournament);

     Tournament readTournament(Long id);

     List<Tournament> readAllTournaments();

     boolean updateTournament(Tournament tournament);

     boolean deleteTournament(Long id);
}

    