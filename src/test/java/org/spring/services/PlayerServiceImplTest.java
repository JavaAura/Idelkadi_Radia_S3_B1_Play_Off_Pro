package org.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.spring.models.Player;
import org.spring.repositories.PlayerRepository;
import org.spring.services.serviceImpl.PlayerServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerServiceImplTest {

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPlayer_Success() {
        Player player = new Player();
        when(playerRepository.save(player)).thenReturn(true);

        boolean result = playerService.addPlayer(player);

        assertTrue(result);
        verify(playerRepository).save(player);
    }

    @Test
    public void testAddPlayer_Failure() {
        Player player = new Player();
        when(playerRepository.save(player)).thenReturn(false);

        boolean result = playerService.addPlayer(player);

        assertFalse(result);
        verify(playerRepository).save(player);
    }

    @Test
    public void testUpdatePlayer_Success() {
        Player player = new Player();
        player.setId(1L);
        when(playerRepository.findById(1L)).thenReturn(player);
        when(playerRepository.update(player)).thenReturn(true);

        boolean result = playerService.updatePlayer(player);

        assertTrue(result);
        verify(playerRepository).update(player);
    }


    @Test
    public void testDeletePlayer() {
        Long playerId = 1L;

        playerService.deletePlayer(playerId);

        verify(playerRepository, times(1)).delete(playerId);
    }



    @Test
    public void testGetPlayerById() {
        Player player = new Player();
        player.setId(1L);
        when(playerRepository.findById(1L)).thenReturn(player);

        Player result = playerService.getPlayerById(1L);

        assertEquals(player, result);
        verify(playerRepository).findById(1L);
    }

    @Test
    public void testGetAllPlayers() {
        List<Player> players = Collections.singletonList(new Player());
        when(playerRepository.findAll()).thenReturn(players);

        List<Player> result = playerService.getAllPlayers();

        assertEquals(players, result);
        verify(playerRepository).findAll();
    }
}
