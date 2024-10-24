package org.spring.repositories;

import org.spring.models.Game;

import java.util.List;

public interface GameRepository {
    boolean save(Game game);
    Game findById(Long id);
    List<Game> findAll();
    boolean update(Game game);
    boolean delete(Long id);
}
