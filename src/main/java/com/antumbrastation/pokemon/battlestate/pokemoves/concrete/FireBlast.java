package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.SpecialMove;

public class FireBlast extends SpecialMove {

    public FireBlast(boolean playerOne) {
        super(playerOne, 110, 85, 0, Pokemon.PokeType.Fire);
        setName("FireBlast");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new FireBlast(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {
        int roll = (int) (Math.random() * 10);
        if (roll == 0 && victim.getMainStatus() == Pokemon.MainStatus.Fine) {
            victim.setMainStatus(Pokemon.MainStatus.Burned);
        }
    }
}
