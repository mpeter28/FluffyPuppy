package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.SpecialMove;

public class IceBeam extends SpecialMove {

    public IceBeam(boolean playerOne) {
        super(playerOne, 90, 100, 0, Pokemon.PokeType.Ice);
        setName("IceBeam");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new IceBeam(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {
        int roll = (int) (Math.random() * 10);
        if (roll == 0 && victim.getMainStatus() == Pokemon.MainStatus.Fine) {
            victim.setMainStatus(Pokemon.MainStatus.Frozen);
        }
    }
}
