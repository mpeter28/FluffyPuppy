package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;


import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PhysicalMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

public class GunkShot extends PhysicalMove{

    public GunkShot(boolean playerOne) {
        super(playerOne, 120, 80, 0, Pokemon.PokeType.Poison);
        setName("GunkShot");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new GunkShot(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {
        int roll = (int) (Math.random() * 10);
        if (roll <= 2 && victim.getMainStatus() == Pokemon.MainStatus.Fine) {
            victim.setMainStatus(Pokemon.MainStatus.Poison);
        }
    }
}
