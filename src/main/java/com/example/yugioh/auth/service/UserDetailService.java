package com.example.yugioh.auth.service;

import com.example.yugioh.auth.entity.Player;
import com.example.yugioh.auth.repository.PlayerRepository;
import com.example.yugioh.auth.security.UserDetail;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final PlayerRepository playerRepository;


    @Override
    public UserDetail loadUserByUsername(String email) throws UsernameNotFoundException {
        Player player = playerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return new UserDetail(player);
    }
}
