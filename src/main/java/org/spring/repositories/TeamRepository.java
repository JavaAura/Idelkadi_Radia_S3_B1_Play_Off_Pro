package org.spring.repositories;

import org.spring.models.Team;
import java.util.List;

 public interface TeamRepository {
     boolean createTeam(Team team);
     Team readTeam(Long id);
     List<Team> readAllTeams() ;
     boolean updateTeam(Team team) ;
     boolean deleteTeam(Long id) ;
}
