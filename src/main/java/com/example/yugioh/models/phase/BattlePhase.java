package com.example.yugioh.models.phase;

import com.example.yugioh.enums.PhaseEnum;
import com.example.yugioh.enums.Position;
import com.example.yugioh.models.card.MonsterCard;
import com.example.yugioh.models.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class BattlePhase extends Phase {
    public BattlePhase(){
        super(PhaseEnum.BATTLE);
    }

    @Override
    public void play() {
        System.out.println("BATTLE PHASE...");
    }

    /**
     * @param targetMonster the monster of the opponent to attack
     * @param attackMonster the duelist monster that will attack
     * @return damage that must be inflicted to a player
     */
    public int  CalculateDamage(MonsterCard targetMonster, MonsterCard attackMonster)
    {
        log.info("Calculating damage...");

        int damage = 0;

        if (targetMonster == null) {
            damage = attackMonster.getAtk();
        }
        else {
            //case opponent monster is in attack position
            if (targetMonster.getPosition() == Position.ATK) {
                damage = attackMonster.getAtk() - targetMonster.getAtk();
            }
            if(targetMonster.getPosition() == Position.DEF) {
                damage = attackMonster.getAtk() - targetMonster.getDef();
            }
        }
        log.info("The damage will be : {}", damage);
        return damage;
    }

    /**
     *  inflicts the damage to the corresponding player
     * @param damage damge lp
     * @param targetPlayer the player who will receive the damage
     */
    public void inflictDamage(int damage, Player targetPlayer){
        log.info("Inflicting damage to {}", targetPlayer.getName());

        int currentLp = targetPlayer.getLp().getCurrentLp().getValue();
        targetPlayer.getLp().getCurrentLp().set(currentLp - Math.abs(damage));
    }
}
