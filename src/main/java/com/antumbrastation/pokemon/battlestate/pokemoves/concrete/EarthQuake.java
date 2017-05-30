package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PhysicalMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

public class EarthQuake extends PhysicalMove {

    public EarthQuake(boolean playerOne) {
        super(playerOne, 100, 100, 0, Pokemon.PokeType.Ground);
        setName("Earthquake");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new EarthQuake(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }
}
