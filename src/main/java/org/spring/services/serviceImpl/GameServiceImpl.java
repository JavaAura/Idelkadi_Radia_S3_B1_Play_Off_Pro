package org.spring.services.serviceImpl;

import org.spring.models.Game;
import org.spring.repositories.GameRepository;
import org.spring.services.GameService;
import java.util.List;

public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepo) {
        this.gameRepository = gameRepo;
    }

    @Override
    public boolean addGame(Game game) {
        try {
            gameRepository.save(game);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding game: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateGame(Game game) {
        if (gameRepository.findById(game.getId())!= null) {
            gameRepository.save(game);
            return true;
        } else {
            System.out.println("Game not found with ID: " + game.getId());
            return false;
        }
    }

    @Override
    public boolean deleteGame(Long id) {
        if (gameRepository.findById(id) != null) {
            gameRepository.delete(id);
            return true;
        } else {
            System.out.println("Game not found with ID: " + id);
            return false;
        }
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}
