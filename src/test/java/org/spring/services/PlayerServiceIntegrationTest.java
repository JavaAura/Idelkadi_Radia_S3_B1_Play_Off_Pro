package org.spring.services;

import org.junit.jupiter.api.*;
import org.spring.models.Player;
import org.spring.services.serviceImpl.PlayerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerServiceIntegrationTest {

    private PlayerServiceImpl playerService;

    @BeforeAll
    public static void init() {
        System.setProperty("puName", "tournamentTest");
    }

    @BeforeEach
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContextTest.xml");
        playerService = (PlayerServiceImpl) context.getBean("playerServiceTest");

        playerService.getAllPlayers().forEach(player -> playerService.deletePlayer(player.getId()));
    }

    @AfterEach
    public void tearDown() {
        playerService.getAllPlayers().forEach(player -> playerService.deletePlayer(player.getId()));
    }

    @Test
    public void testGetAllPlayers() {
        Player player1 = new Player();
        player1.setPseudo("Player One");
        player1.setAge(16);

        Player player2 = new Player();
        player2.setPseudo("Player Two");
        player2.setAge(2);

        playerService.addPlayer(player1);
        playerService.addPlayer(player2);

        List<Player> players = playerService.getAllPlayers();
        assertEquals(2, players.size(), "The number of players should be 2");
    }

    @Test
    public void testAddAndGetPlayer() {
        Player player = new Player();
        player.setPseudo("Player One");
        player.setAge(1);

        playerService.addPlayer(player);
        Player fetchedPlayer = playerService.getPlayerById(player.getId());

        assertNotNull(fetchedPlayer, "Fetched player should not be null");
        assertEquals("Player One", fetchedPlayer.getPseudo(), "Player name should match");
    }

    @Test
    public void testUpdatePlayer() {
        Player player = new Player();
        player.setPseudo("Player One");
        player.setAge(18);

        playerService.addPlayer(player);

        player.setPseudo("Player One Updated");
        playerService.updatePlayer(player);

        Player updatedPlayer = playerService.getPlayerById(player.getId());
        assertEquals("Player One Updated", updatedPlayer.getPseudo(), "Player name should be updated");
    }

    @Test
    public void testDeletePlayer() {
        Player player = new Player();
        player.setPseudo("Player One");
        player.setAge(19);

        playerService.addPlayer(player);

        boolean result = playerService.deletePlayer(player.getId());
        assertTrue(result, "Delete operation should return true");

        Player deletedPlayer = playerService.getPlayerById(player.getId());
        assertNull(deletedPlayer, "Deleted player should be null");
    }
}
