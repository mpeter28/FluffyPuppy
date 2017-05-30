package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PhysicalMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

public class Struggle extends PhysicalMove {

    public Struggle(boolean playerOne) {
        super(playerOne, 50, 100, 0, Pokemon.PokeType.Normal);
        setName("Struggle");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new Struggle(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {
        user.setCurrentHp(user.getCurrentHp() - user.getMaxHp() / 4);
    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }
}
