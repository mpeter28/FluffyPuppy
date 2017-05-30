package com.antumbrastation.pokemon.battlestate.pokemoves;

import com.antumbrastation.pokemon.battlestate.Pokemon;

public abstract class PhysicalMove extends DamageMove {

    public PhysicalMove(boolean playerOne, int power, int accuracy, int critRate, Pokemon.PokeType type) {
        super(playerOne, power, accuracy, critRate, type);
    }

    @Override
    public int attackStat(Pokemon user, boolean crit) {
        double attack = user.getAttack();

        if (user.getMainStatus() == Pokemon.MainStatus.Burned)
            attack /= 2;

        if (user.getAttackMod() >= 0)
            attack *= (2 + user.getAttackMod()) / 2.0;
        else if (!crit)
            attack *= 2.0 / (2 - user.getAttackMod());

        return (int) attack;
    }

    @Override
    public int defenseStat(Pokemon victim, boolean crit) {
        double defense = victim.getDefense();

        if (victim.getDefenseMod() < 0)
            defense *= 2.0 / (2 - victim.getDefenseMod());
        else if (!crit)
            defense *= (2 + victim.getDefenseMod()) / 2.0;

        return (int) defense;
    }
}