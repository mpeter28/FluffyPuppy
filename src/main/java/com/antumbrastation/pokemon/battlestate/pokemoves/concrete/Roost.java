package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.StatusMove;

public class Roost extends StatusMove {

    public Roost(boolean playerOne) {
        super(playerOne, 100, Pokemon.PokeType.Flying);
        setName("Roost");
    }

    @Override
    public BattleState use(BattleState currentState, boolean goingFirst) {
        if (isPlayerOne()) {
            if (currentState.getPlayerOneLead().getCurrentHp() <= 0)
                return currentState;

            int healing = currentState.getPlayerOneLead().getMaxHp() / 2;

            currentState.getPlayerOneLead().setCurrentHp(
                    currentState.getPlayerOneLead().getCurrentHp() + healing);

            if (currentState.getPlayerOneLead().getCurrentHp() > currentState.getPlayerOneLead().getMaxHp())
                currentState.getPlayerOneLead().setCurrentHp(currentState.getPlayerOneLead().getMaxHp());
        }
        else {
            if (currentState.getPlayerTwoLead().getCurrentHp() <= 0)
                return currentState;

            int healing = currentState.getPlayerTwoLead().getMaxHp() / 2;

            currentState.getPlayerTwoLead().setCurrentHp(
                    currentState.getPlayerTwoLead().getCurrentHp() + healing);

            if (currentState.getPlayerTwoLead().getCurrentHp() > currentState.getPlayerTwoLead().getMaxHp())
                currentState.getPlayerTwoLead().setCurrentHp(currentState.getPlayerTwoLead().getMaxHp());
        }

        return currentState;
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new Roost(true));
    }
}
