package org.spring.services;

import org.spring.models.Team;

import java.util.List;

public interface TeamService {
    boolean addTeam(Team team);
    Team getTeamById(Long id);
    List<Team> getAllTeams();
    boolean updateTeam(Team team);
    boolean deleteTeam(Long id);
}
