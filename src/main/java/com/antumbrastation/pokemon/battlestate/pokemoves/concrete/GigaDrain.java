package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.SpecialMove;

public class GigaDrain extends SpecialMove {

    public GigaDrain(boolean playerOne) {
        super(playerOne, 75, 100, 0, Pokemon.PokeType.Grass);
        setName("GigaDrain");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new GigaDrain(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {
        user.setCurrentHp(user.getCurrentHp() + damage / 2);

        if (user.getCurrentHp() > user.getMaxHp())
            user.setCurrentHp(user.getMaxHp());
    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }
}
