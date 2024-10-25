package org.spring.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spring.models.Team;
import org.spring.services.serviceImpl.TeamServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamServiceIntegrationTest {

    private TeamServiceImpl teamService;


    @BeforeAll
    public static void init() {
        System.setProperty("puName", "tournamentTest");
    }

    @BeforeEach
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContextTest.xml");
        teamService = (TeamServiceImpl) context.getBean("teamServiceTest");
    }
    @AfterEach
    public void tearDown() {
        // Ensure database is cleaned after each test
        teamService.getAllTeams().forEach(team -> teamService.deleteTeam(team.getId()));
    }


    @Test
    public void testGetAllTeams() {
        Team team = new Team();
        team.setName("Team A");
        team.setRanking(1);

        Team team1 = new Team();
        team1.setName("Team B");
        team1.setRanking(1);

        teamService.addTeam(team);
        teamService.addTeam(team1);


        List<Team> teams = teamService.getAllTeams();
        assertEquals(2, teams.size());
    }


    @Test
    public void testAddAndGetTeam() {
        Team team = new Team();
        team.setName("Team A");
        team.setRanking(1);
        teamService.addTeam(team);

        Team fetchedTeam = teamService.getTeamById(team.getId());

        assertNotNull(fetchedTeam, "Fetched team should not be null");
        assertEquals("Team A", fetchedTeam.getName(), "Team name should match");
    }


    @Test
    public void testUpdateTeam() {
        Team team = new Team();
        team.setName("Team A");
        team.setRanking(1);

        teamService.addTeam(team);

        team.setName("Team A Updated");

        teamService.updateTeam(team);

        Team updatedTeam = teamService.getTeamById(team.getId());
        assertEquals("Team A Updated", updatedTeam.getName());
    }

    @Test
    public void testDeleteTeam() {
        Team team = new Team();
        team.setName("Team A");
        team.setRanking(1);

        teamService.addTeam(team);

        boolean result = teamService.deleteTeam(team.getId());
        assertTrue(result);

        Team deletedTeam = teamService.getTeamById(team.getId());
        assertNull(deletedTeam);
    }
}
