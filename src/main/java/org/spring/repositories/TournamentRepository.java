package org.spring.repositories;

import org.spring.models.Tournament;

import java.util.List;

public interface TournamentRepository {
    Long createTournament(Tournament tournament);

    Tournament readTournament(Long id);

    List<Tournament> readAllTournaments();

    boolean updateTournament(Tournament tournament);

    boolean deleteTournament(Long id);
}
