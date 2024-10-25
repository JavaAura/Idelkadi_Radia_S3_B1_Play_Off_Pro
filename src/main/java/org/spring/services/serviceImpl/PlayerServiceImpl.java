package org.spring.services.serviceImpl;

import org.spring.models.Player;
import org.spring.repositories.PlayerRepository;
import org.spring.services.PlayerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PlayerServiceImpl  implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    public boolean addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public boolean updatePlayer(Player player) {
        return playerRepository.update(player);
    }

    public boolean deletePlayer(Long id) {
        return playerRepository.delete(id);
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

}
