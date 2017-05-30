package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.SpecialMove;

public class HydroPump extends SpecialMove {

    public HydroPump(boolean playerOne) {
        super(playerOne, 110, 80, 0, Pokemon.PokeType.Water);
        setName("HydroPump");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new HydroPump(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {
        if (victim.getAbility() == Pokemon.Ability.WaterAbsorb) {
            int healing = victim.getMaxHp() / 4;

            victim.setCurrentHp(victim.getCurrentHp() + healing);

            if (victim.getCurrentHp() > victim.getMaxHp())
                victim.setCurrentHp(victim.getMaxHp());
        }

    }
}
