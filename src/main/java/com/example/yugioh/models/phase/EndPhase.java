package com.example.yugioh.models.phase;

import com.example.yugioh.enums.PhaseEnum;

/*La end phase est la fin du tour, certains effets vont se résoudre durant cette phase. Vous pouvez aussi activer des cartes magie rapide / pièges.

        Enfin si vous avez plus de 6 cartes dans votre main, vous devez en défaussez jusqu’à en avoir plus que 6.

 */
public class EndPhase extends Phase {

    public EndPhase() {
        super(PhaseEnum.END);
    }

    @Override
    public void play() {
        System.out.println("EndPhase...");
    }
}
