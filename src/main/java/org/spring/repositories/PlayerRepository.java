package org.spring.repositories;

import org.spring.models.Player;

import java.util.List;

public interface PlayerRepository {
    boolean save(Player player);

    boolean update(Player player);

    boolean delete(Long id);

    Player findById(Long id);

    List<Player> findAll();
}
