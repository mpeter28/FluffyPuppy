package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.StatusMove;

public class StealthRock extends StatusMove {

    public StealthRock(boolean playerOne) {
        super(playerOne, 100, Pokemon.PokeType.Rock);
        setName("StealthRock");
    }

    @Override
    public BattleState use(BattleState currentState, boolean goingFirst) {
        if (isPlayerOne()) {
            if (currentState.getPlayerOneLead().getCurrentHp() <= 0)
                return currentState;

            currentState.setStealthRocksForOne(true);
        }
        else {
            if (currentState.getPlayerTwoLead().getCurrentHp() <= 0)
                return currentState;

            currentState.setStealthRocksForTwo(true);
        }

        return currentState;
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new StealthRock(true));
    }
}
