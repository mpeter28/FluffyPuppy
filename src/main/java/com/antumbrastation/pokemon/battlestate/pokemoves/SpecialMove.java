package com.antumbrastation.pokemon.battlestate.pokemoves;

import com.antumbrastation.pokemon.battlestate.Pokemon;

public abstract class SpecialMove extends DamageMove {

    public SpecialMove(boolean playerOne, int power, int accuracy, int critRate, Pokemon.PokeType type) {
        super(playerOne, power, accuracy, critRate, type);
    }

    @Override
    public int attackStat(Pokemon user, boolean crit) {
        double attack = user.getSpecialAttack();

        if (user.getSpecialAttackMod() >= 0)
            attack *= (2 + user.getSpecialAttackMod()) / 2.0;
        else if (!crit)
            attack *= 2.0 / (2 - user.getSpecialAttackMod());

        if (user.getItem() == Pokemon.HoldItem.ChoiceSpecs)
            attack *= 1.5;

        return (int) attack;
    }

    @Override
    public int defenseStat(Pokemon victim, boolean crit) {
        double defense = victim.getSpecialDefense();

        if (victim.getSpecialDefenseMod() < 0)
            defense *= 2.0 / (2 - victim.getSpecialDefenseMod());
        else if (!crit)
            defense *= (2 + victim.getSpecialDefenseMod()) / 2.0;

        return (int) defense;
    }
}
