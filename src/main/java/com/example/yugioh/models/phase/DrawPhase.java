package com.example.yugioh.models.phase;

import com.example.yugioh.enums.PhaseEnum;

/*
La draw phase correspond à la première phase du tour d’un joueur. Durant cette phase le joueur pour qui c’est le tour pioche la carte du dessus de son deck.

Attention: lors du premier tour du premier joueur (celui qui commence le duel), celui-ci saute sa draw phase, c’est à dire qu’il ne pioche pas de carte.

Si des effets doivent se résoudre à ce moment (c’est à dire qu’il y a marqué durant la draw phase) résolvez les.
 */
public class DrawPhase extends Phase {
    public DrawPhase() {
        super(PhaseEnum.DRAW);
    }

    @Override
    public void play() {
        System.out.println("DRAW PHASE");
        //TODO make the player draw
    }
}
