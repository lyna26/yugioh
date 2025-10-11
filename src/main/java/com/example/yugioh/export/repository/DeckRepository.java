package com.example.yugioh.export.repository;

import com.example.yugioh.export.model.CardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DeckRepository  extends JpaRepository<CardDTO, Integer> {
}
