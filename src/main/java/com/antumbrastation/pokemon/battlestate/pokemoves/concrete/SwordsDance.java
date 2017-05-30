package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.StatusMove;

public class SwordsDance extends StatusMove{

    public SwordsDance(boolean playerOne) {
        super (playerOne, 100, Pokemon.PokeType.Normal);
        setName("SwordsDance");
    }

    @Override
    public BattleState use(BattleState currentState, boolean goingFirst) {
        if (isPlayerOne()) {
            if (currentState.getPlayerOneLead().getCurrentHp() <= 0)
                return currentState;

            currentState.getPlayerOneLead().setAttackMod(
                    currentState.getPlayerOneLead().getAttackMod() + 2);

            if (currentState.getPlayerOneLead().getAttackMod() > 6)
                currentState.getPlayerOneLead().setAttackMod(6);
        } else {
            if (currentState.getPlayerTwoLead().getCurrentHp() <= 0)
                return currentState;

            currentState.getPlayerTwoLead().setAttackMod(
                    currentState.getPlayerTwoLead().getAttackMod() + 2);

            if (currentState.getPlayerTwoLead().getAttackMod() > 6)
                currentState.getPlayerTwoLead().setAttackMod(6);
        }

        return currentState;
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new SwordsDance(true));
    }
}
