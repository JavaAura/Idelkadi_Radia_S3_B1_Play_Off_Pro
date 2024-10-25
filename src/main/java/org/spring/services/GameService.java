package org.spring.services;

import org.spring.models.Game;

import java.util.List;

public interface GameService {

    boolean addGame(Game game);

    boolean updateGame(Game game);

    boolean deleteGame(Long id);

    Game getGameById(Long id);

    List<Game> getAllGames();
}
