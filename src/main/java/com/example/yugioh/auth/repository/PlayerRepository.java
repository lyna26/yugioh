package com.example.yugioh.auth.repository;

import com.example.yugioh.auth.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository  extends JpaRepository<Player, Integer> {
    Optional<Player> findByEmail(String email);
    Optional<Player> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
