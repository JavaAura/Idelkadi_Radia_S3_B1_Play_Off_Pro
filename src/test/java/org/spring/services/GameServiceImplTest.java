package org.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.spring.models.Game;
import org.spring.repositories.GameRepository;
import org.spring.services.serviceImpl.GameServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameServiceImplTest {

    @Mock
    private GameRepository gameRepository;
    @InjectMocks
    private GameServiceImpl gameService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddGame_Success() {
        Game game = new Game();
        when(gameRepository.save(game)).thenReturn(true);

        boolean result = gameService.addGame(game);

        assertTrue(result);
        verify(gameRepository).save(game);
    }

    @Test
    public void testUpdateGame_Success() {
        Game game = new Game();
        game.setId(1L);
        when(gameRepository.findById(1L)).thenReturn(game);
        when(gameRepository.update(game)).thenReturn(true);

        boolean result = gameService.updateGame(game);

        assertTrue(result);
        verify(gameRepository).save(game);
    }

    @Test
    public void testUpdateGame_Failure() {
        Game game = new Game();
        game.setId(1L);
        when(gameRepository.findById(1L)).thenReturn(null);

        boolean result = gameService.updateGame(game);

        assertFalse(result);
        verify(gameRepository, never()).save(game);
    }

    @Test
    public void testDeleteGame_Success() {
        when(gameRepository.findById(1L)).thenReturn(new Game());
        when(gameRepository.delete(1L)).thenReturn(true);

        boolean result = gameService.deleteGame(1L);

        assertTrue(result);
        verify(gameRepository).delete(1L);
    }

    @Test
    public void testDeleteGame_Failure() {
        when(gameRepository.findById(1L)).thenReturn(null);

        boolean result = gameService.deleteGame(1L);

        assertFalse(result);
        verify(gameRepository, never()).delete(1L);
    }

    @Test
    public void testGetGameById() {
        Game game = new Game();
        game.setId(1L);
        when(gameRepository.findById(1L)).thenReturn(game);

        Game result = gameService.getGameById(1L);

        assertEquals(game, result);
        verify(gameRepository).findById(1L);
    }

    @Test
    public void testGetAllGames() {
        List<Game> games = Collections.singletonList(new Game());
        when(gameRepository.findAll()).thenReturn(games);

        List<Game> result = gameService.getAllGames();

        assertEquals(games, result);
        verify(gameRepository).findAll();
    }
}
