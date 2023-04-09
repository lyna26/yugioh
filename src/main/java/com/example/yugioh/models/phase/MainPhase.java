package com.example.yugioh.models.phase;

import com.example.yugioh.enums.PhaseEnum;

/*
    C’est la phase de jeu principale.

    Vous allez pouvoir effectuez les actions suivantes au cours de cette phase:

        Faire une invocation normale / poser un monstre
        Changer la position de combat d’un ou plusieurs monstres
            Remarques: Chaque monstre ne peut changer de position de combat qu’une fois par tour.
        Activer des cartes magies
        Activer des cartes pièges
        Activer des effets de monstre
*/

public class MainPhase extends Phase {
    public MainPhase(PhaseEnum phaseName) {
        super(phaseName);
    }

    @Override
    public void play() {
        System.out.println(this.getPhaseName().toString());
    }
}
