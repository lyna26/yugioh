package com.example.yugioh.auth.service;

import com.example.yugioh.auth.dto.RegisterRequest;
import com.example.yugioh.auth.exception.UserAlreadyExistsException;
import com.example.yugioh.auth.entity.Player;
import com.example.yugioh.auth.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegisterService {
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        final String email = request.email();
        final String username = request.username();

        if (playerRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email is already in use.");
        }


        if (playerRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username is already in use.");
        }

        Player player = new Player(username, email, passwordEncoder.encode(request.password()));

        playerRepository.save(player);
    }
}

