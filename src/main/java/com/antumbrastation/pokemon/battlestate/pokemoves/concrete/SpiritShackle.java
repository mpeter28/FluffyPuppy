package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PhysicalMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

public class SpiritShackle extends PhysicalMove{

    public SpiritShackle(boolean playerOne) {
        super(playerOne, 80, 100, 0, Pokemon.PokeType.Ghost);
        setName("SpiritShackle");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new SpiritShackle(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {
        if (victim.getPrimaryType() != Pokemon.PokeType.Ghost &&
                victim.getSecondaryType() != Pokemon.PokeType.Ghost)
                    victim.setTrapped(true);

    }
}
