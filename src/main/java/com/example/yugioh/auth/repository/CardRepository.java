package com.example.yugioh.auth.repository;

import com.example.yugioh.auth.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByNameContainingIgnoreCase(String name);
    Card findById(int id);
}
