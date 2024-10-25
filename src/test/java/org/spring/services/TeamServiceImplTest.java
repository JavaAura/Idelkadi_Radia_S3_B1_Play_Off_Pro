package org.spring.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.spring.models.Team;
import org.spring.repositories.TeamRepository;
import org.spring.services.serviceImpl.TeamServiceImpl;

import java.util.Arrays;
import java.util.List;

public class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTeam() {
        Team team = new Team();
        when(teamRepository.createTeam(team)).thenReturn(true);

        boolean result = teamService.addTeam(team);

        assertTrue(result);
        verify(teamRepository, times(1)).createTeam(team);
    }

    @Test
    public void testGetTeamById() {
        Long teamId = 1L;
        Team team = new Team();
        when(teamRepository.readTeam(teamId)).thenReturn(team);

        Team result = teamService.getTeamById(teamId);

        assertEquals(team, result);
        verify(teamRepository, times(1)).readTeam(teamId);
    }

    @Test
    public void testGetAllTeams() {
        Team team1 = new Team();
        Team team2 = new Team();
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamRepository.readAllTeams()).thenReturn(teams);

        List<Team> result = teamService.getAllTeams();

        assertEquals(2, result.size());
        assertEquals(teams, result);
        verify(teamRepository, times(1)).readAllTeams();
    }

    @Test
    public void testUpdateTeam() {
        Team team = new Team();
        when(teamRepository.updateTeam(team)).thenReturn(true);

        boolean result = teamService.updateTeam(team);

        assertTrue(result);
        verify(teamRepository, times(1)).updateTeam(team);
    }

    @Test
    public void testDeleteTeam() {
        Long teamId = 1L;
        when(teamRepository.deleteTeam(teamId)).thenReturn(true);

        boolean result = teamService.deleteTeam(teamId);

        assertTrue(result);
        verify(teamRepository, times(1)).deleteTeam(teamId);
    }
}
