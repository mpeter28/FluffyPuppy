package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PhysicalMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

public class IronHead extends PhysicalMove {

    public IronHead(boolean playerOne) {
        super(playerOne, 80, 100, 0, Pokemon.PokeType.Steel);
        setName("IronHead");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new IronHead(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {
        int roll = (int) (Math.random() * 10);
        if (roll <= 2) {
            victim.setFlinch(true);
        }
    }
}
