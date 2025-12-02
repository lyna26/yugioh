package com.example.yugioh.auth.service;

import com.example.yugioh.auth.entity.Player;
import com.example.yugioh.auth.repository.PlayerRepository;
import com.example.yugioh.auth.utility.JwtUtility;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LoginService {
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtility jwtService;


    public String authenticate(String email, String password) {
        Player player = playerRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(password, player.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtService.generateToken(player);
    }

    public String getEmailFromToken(String token) {
        return jwtService.extractEmail(token); // ou extractEmail selon ton JwtUtility
    }
}
