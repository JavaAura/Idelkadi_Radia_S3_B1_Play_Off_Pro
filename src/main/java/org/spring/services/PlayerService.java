package org.spring.services;

import org.spring.models.Player;

import java.util.List;

public interface  PlayerService {

    public boolean addPlayer(Player player);

    public boolean updatePlayer(Player player) ;

    public boolean deletePlayer(Long id);

    public Player getPlayerById(Long id) ;

    public List<Player> getAllPlayers();

}
