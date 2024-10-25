package org.spring.services.serviceImpl;

import org.spring.models.Team;
import org.spring.repositories.TeamRepository;
import org.spring.services.TeamService;
import java.util.List;

public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean addTeam(Team team) {
        return teamRepository.createTeam(team);
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.readTeam(id);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.readAllTeams();
    }

    @Override
    public boolean updateTeam(Team team) {
        return teamRepository.updateTeam(team);
    }

    @Override
    public boolean deleteTeam(Long id) {
        return teamRepository.deleteTeam(id);
    }
}
