package com.example.yugioh.auth.controller;

import com.example.yugioh.auth.dto.LoginRequest;
import com.example.yugioh.auth.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?>  login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        String email = loginRequest.email();
        String password = loginRequest.password();

        try{
            String jwtToken = loginService.authenticate(email, password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            Cookie cookie = new Cookie("jwt", jwtToken);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.ok(Map.of("token", jwtToken));
        }
        catch (IllegalArgumentException exception){
                return ResponseEntity.status(UNAUTHORIZED)
                        .body(Map.of("message", "Invalid email or password"));

        }
    }
}
