package com.example.yugioh.game;

import com.example.yugioh.auth.entity.Player;
import com.example.yugioh.auth.repository.PlayerRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class RegistrationService {
    private final PlayerRepository playerRepository;
    //private final PasswordEncoder passwordEncoder;

    public Player registerNewPlayer(Player player) {

        if (playerRepository.findByUsername(player.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }
        if (playerRepository.findByEmail(player.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email address already registered.");
        }

        //String hashedPassword = passwordEncoder.encode(player.getPassword());


        // 4. Sauvegarde dans la base de données
        return playerRepository.save(player);
    }


}
