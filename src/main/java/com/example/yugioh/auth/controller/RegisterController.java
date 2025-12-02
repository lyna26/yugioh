package com.example.yugioh.auth.controller;

import com.example.yugioh.auth.dto.RegisterRequest;
import com.example.yugioh.auth.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
            registerService.register(registerRequest);
            return ResponseEntity.ok("Registration successful");
    }
}
