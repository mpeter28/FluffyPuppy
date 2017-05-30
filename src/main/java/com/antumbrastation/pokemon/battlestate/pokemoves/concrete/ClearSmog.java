package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.SpecialMove;

public class ClearSmog extends SpecialMove {

    public ClearSmog(boolean playerOne) {
        super(playerOne, 50, 100, 0, Pokemon.PokeType.Poison);
        setName("ClearSmog");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new ClearSmog(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {
        if (!victim.isSubstitute() && victim.getPrimaryType() != Pokemon.PokeType.Steel &&
                victim.getSecondaryType() != Pokemon.PokeType.Steel) {
            victim.setAttackMod(0);
            victim.setDefenseMod(0);
            victim.setSpecialAttackMod(0);
            victim.setSpecialDefenseMod(0);
            victim.setSpeedMod(0);
            victim.setAccuracyMod(0);
            victim.setEvasionMod(0);
        }
    }
}
