package com.example.yugioh.auth.controller;

import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.service.Cardservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cardSearcher")
public class CardSearcherController {
    private final Cardservice cardservice;

    @GetMapping
    List<CardDTO> searchCardByName(@RequestParam("cardName") String cardSearchRequest){
        return cardservice.findByName(cardSearchRequest);
    }
}
