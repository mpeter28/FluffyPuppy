package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;


import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.StatusMove;

public class RockPolish extends StatusMove {

    public RockPolish(boolean playerOne) {
        super(playerOne, 100, Pokemon.PokeType.Rock);
        setName("RockPolish");
    }

    @Override
    public BattleState use(BattleState currentState, boolean goingFirst) {
        if (isPlayerOne()) {
            if (currentState.getPlayerOneLead().getCurrentHp() <= 0)
                return currentState;

            currentState.getPlayerOneLead().setSpeedMod(
                    currentState.getPlayerOneLead().getSpeedMod() + 2);

            if (currentState.getPlayerOneLead().getSpeedMod() > 6)
                currentState.getPlayerOneLead().setSpeedMod(6);
        }
        else {
            if (currentState.getPlayerTwoLead().getCurrentHp() <= 0)
                return currentState;

            currentState.getPlayerTwoLead().setSpeedMod(
                    currentState.getPlayerTwoLead().getSpeedMod() + 2);

            if (currentState.getPlayerTwoLead().getSpeedMod() > 6)
                currentState.getPlayerTwoLead().setSpeedMod(6);
        }

        return currentState;
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new RockPolish(true));
    }
}
