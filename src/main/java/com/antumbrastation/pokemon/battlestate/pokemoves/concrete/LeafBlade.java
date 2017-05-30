package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PhysicalMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

public class LeafBlade extends PhysicalMove {

    public LeafBlade(boolean playerOne) {
        super(playerOne, 90, 100, 1, Pokemon.PokeType.Grass);
        setName("LeafBlade");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new LeafBlade(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }
}
