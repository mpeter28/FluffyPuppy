package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;


import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.StatusMove;

public class Spore extends StatusMove {

    public Spore(boolean playerOne) {
        super (playerOne, 100, Pokemon.PokeType.Grass);
        setName("Spore");
    }

    @Override
    public BattleState use(BattleState currentState, boolean goingFirst) {
        if ((int) (Math.random() * 100) >= super.getAccuracy())
            return currentState;

        if (isPlayerOne()) {
            if (currentState.getPlayerOneLead().getCurrentHp() <= 0)
                return currentState;

            Pokemon.PokeType type1 = currentState.getPlayerTwoLead().getPrimaryType();
            Pokemon.PokeType type2 = currentState.getPlayerTwoLead().getSecondaryType();

            if (type1 == Pokemon.PokeType.Grass || type2 == Pokemon.PokeType.Grass)
                return currentState;

            if (currentState.getPlayerTwoLead().getMainStatus() != Pokemon.MainStatus.Fine)
                return currentState;

            currentState.getPlayerTwoLead().setMainStatus(Pokemon.MainStatus.Sleep);
            currentState.getPlayerTwoLead().setMainStatusTurns(0);
        } else {
            if (currentState.getPlayerTwoLead().getCurrentHp() <= 0)
                return currentState;

            Pokemon.PokeType type1 = currentState.getPlayerOneLead().getPrimaryType();
            Pokemon.PokeType type2 = currentState.getPlayerOneLead().getSecondaryType();

            if (type1 == Pokemon.PokeType.Grass || type2 == Pokemon.PokeType.Grass)
                return currentState;

            if (currentState.getPlayerOneLead().getMainStatus() != Pokemon.MainStatus.Fine)
                return currentState;

            currentState.getPlayerOneLead().setMainStatus(Pokemon.MainStatus.Sleep);
            currentState.getPlayerOneLead().setMainStatusTurns(0);
        }

        return currentState;
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new Spore(true));
    }
}
