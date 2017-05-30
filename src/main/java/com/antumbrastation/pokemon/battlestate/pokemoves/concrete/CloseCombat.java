package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PhysicalMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

public class CloseCombat extends PhysicalMove {

    public CloseCombat(boolean playerOne) {
        super(playerOne, 120, 100, 0, Pokemon.PokeType.Fighting);
        setName("CloseCombat");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new CloseCombat(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {
        user.setDefenseMod(user.getDefenseMod() - 1);
        user.setSpecialDefenseMod(user.getSpecialDefenseMod() - 1);

        if(user.getDefenseMod() < -6)
            user.setDefenseMod(-6);

        if(user.getSpecialDefenseMod() < -6)
            user.setSpecialDefenseMod(-6);
    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }
}
