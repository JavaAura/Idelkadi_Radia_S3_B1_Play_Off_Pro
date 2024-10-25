package org.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.spring.dao.TournamentDao;
import org.spring.models.Tournament;
import org.spring.repositories.TournamentRepository;
import org.spring.services.serviceImpl.TournamentServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TournamentServiceImplTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private TournamentDao tournamentDao;

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTournament() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);

        when(tournamentRepository.createTournament(tournament)).thenReturn(tournament.getId());

        Long createdId = tournamentService.createTournament(tournament);
        assertEquals(tournament.getId(), createdId);
        verify(tournamentRepository).createTournament(tournament);
    }

    @Test
    void testReadTournament() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);

        when(tournamentRepository.readTournament(1L)).thenReturn(tournament);

        Tournament foundTournament = tournamentService.readTournament(1L);
        assertNotNull(foundTournament);
        assertEquals(tournament.getId(), foundTournament.getId());
        verify(tournamentRepository).readTournament(1L);
    }

    @Test
    void testReadAllTournaments() {
        Tournament tournament1 = new Tournament();
        tournament1.setId(1L);
        Tournament tournament2 = new Tournament();
        tournament2.setId(2L);

        List<Tournament> tournaments = Arrays.asList(tournament1, tournament2);
        when(tournamentRepository.readAllTournaments()).thenReturn(tournaments);

        List<Tournament> allTournaments = tournamentService.readAllTournaments();
        assertEquals(2, allTournaments.size());
        verify(tournamentRepository).readAllTournaments();
    }

    @Test
    void testUpdateTournament() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);

        when(tournamentRepository.updateTournament(tournament)).thenReturn(true);

        boolean updated = tournamentService.updateTournament(tournament);
        assertTrue(updated);
        verify(tournamentRepository).updateTournament(tournament);
    }

    @Test
    void testDeleteTournament() {
        when(tournamentRepository.deleteTournament(1L)).thenReturn(true);

        boolean deleted = tournamentService.deleteTournament(1L);
        assertTrue(deleted);
        verify(tournamentRepository).deleteTournament(1L);
    }
}
