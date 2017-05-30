package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.SpecialMove;

public class HiddenPower extends SpecialMove {

    private Pokemon.PokeType type;

    public HiddenPower(boolean playerOne, Pokemon.PokeType type) {
        super(playerOne, 60, 100, 0, type);
        this.type = type;
        setName("HiddenPower");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new HiddenPower(true, type));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }

    public String toString() {
        return super.toString() + type;
    }
}
