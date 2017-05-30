package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.SpecialMove;

public class DracoMeteor extends SpecialMove {

    public DracoMeteor(boolean playerOne) {
        super(playerOne, 130, 90, 0, Pokemon.PokeType.Dragon);
        setName("DracoMeteor");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new DracoMeteor(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {
        user.setSpecialAttackMod(user.getSpecialAttackMod() - 2);

        if(user.getSpecialAttackMod() < -6)
            user.setSpecialAttackMod(-6);
    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }
}
