package com.example.yugioh.auth.controller;

import com.example.yugioh.auth.dto.deck.DeckCollectionDTO;
import com.example.yugioh.auth.entity.Player;
import com.example.yugioh.auth.service.DeckCollectionService;
import com.example.yugioh.auth.service.LoginService;
import com.example.yugioh.auth.service.UserDetailService;
import com.example.yugioh.request.DeckCollectionCreationRequest;
import com.example.yugioh.request.DeckCollectionRenameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/decks")
public class DeckCollectionController {
    private final DeckCollectionService deckService;
    private final LoginService loginService;
    private final UserDetailService playerService;

    @GetMapping
    public List<DeckCollectionDTO> getDeckCollections(@CookieValue("jwt") String jwt){
        String email = loginService.getEmailFromToken(jwt);
        Player player = playerService.loadUserByUsername(email).getPlayer();
        return  deckService.getDeckCollections(player);
    }

   @PostMapping("/add")
    public void createDeckCollection(@RequestBody final DeckCollectionCreationRequest deckCreationRequest, @CookieValue("jwt") String jwt) {
        String email = loginService.getEmailFromToken(jwt);
        Player player = playerService.loadUserByUsername(email).getPlayer();
        deckService.createDeckCollection(deckCreationRequest.getName(), player);
    }

    @DeleteMapping("delete/{id}")
    public void deleteDeckCollection(@PathVariable int id, @CookieValue("jwt") String jwt) {
        String email = loginService.getEmailFromToken(jwt);
        Player player = playerService.loadUserByUsername(email).getPlayer();
        deckService.deleteDeckCollection(id, player);
    }

    @PatchMapping("/rename")
    public void rename(
                       @RequestBody DeckCollectionRenameRequest request,
                       @CookieValue("jwt") String jwt){
        String email = loginService.getEmailFromToken(jwt);
        Player player = playerService.loadUserByUsername(email).getPlayer();
        deckService.rename(request.getId(), request.getName(), player);
    }
}

