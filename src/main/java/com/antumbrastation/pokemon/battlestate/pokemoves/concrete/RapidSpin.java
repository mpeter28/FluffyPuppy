package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PhysicalMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

public class RapidSpin extends PhysicalMove {

    public RapidSpin(boolean playerOne) {
        super(playerOne, 20, 100, 0, Pokemon.PokeType.Normal);
        setName("RapidSpin");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new RapidSpin(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {
        if (user.isPlayerOne()) {
            if (state.getPlayerTwoLead().getPrimaryType() != Pokemon.PokeType.Ghost &&
                    state.getPlayerTwoLead().getSecondaryType() != Pokemon.PokeType.Ghost) {
                state.setSpikesForTwo(0);
                state.setToxicSpikesForTwo(0);
                state.setStealthRocksForTwo(false);
                state.setStickyWebForTwo(false);
            }
        } else {
            if (state.getPlayerOneLead().getPrimaryType() != Pokemon.PokeType.Ghost &&
                    state.getPlayerOneLead().getSecondaryType() != Pokemon.PokeType.Ghost) {
                state.setSpikesForOne(0);
                state.setToxicSpikesForOne(0);
                state.setStealthRocksForOne(false);
                state.setStickyWebForOne(false);
            }
        }
    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }
}
