package org.spring.dao;

import org.spring.models.Tournament;

public interface TournamentDao {
     Double calculateEstimatedDuration(Long tournamentId);
      Tournament readTournament(Long id);
}
