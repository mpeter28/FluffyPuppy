package com.antumbrastation.pokemon.battlestate.pokemoves;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;

public abstract class StatusMove extends PokeMove {

    private int accuracy;
    private Pokemon.PokeType type;

    public StatusMove(boolean playerOne, int accuracy, Pokemon.PokeType type) {
        super(playerOne);
        this.accuracy = accuracy;
        this.type = type;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    @Override
    public Pokemon.PokeType getType() {
        return type;
    }

    @Override
    public void setType(Pokemon.PokeType type) {
        this.type = type;
    }
}
