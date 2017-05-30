package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.SpecialMove;

public class GigavoltHavoc extends SpecialMove {

    public GigavoltHavoc(boolean playerOne) {
        super(playerOne, 175, 1000, 0, Pokemon.PokeType.Electric);
        setName("GigavoltHavoc");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new GigavoltHavoc(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }
}
