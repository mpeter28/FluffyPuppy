package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.SpecialMove;

public class GrassKnot extends SpecialMove {

    public GrassKnot(boolean playerOne) {
        super(playerOne, 0, 100, 0, Pokemon.PokeType.Grass);
        setName("GrassKnot");
    }

    @Override
    public int damageAmount(Pokemon user, Pokemon victim, boolean crit, boolean wentFirst) {
        int attack = attackStat(user, crit);
        int defense = defenseStat(victim, crit);
        Pokemon.PokeType type = super.getType();
        int power;

        if (victim.getPokeWeight() <= 9.9)
            power = 20;
        else if (victim.getPokeWeight() <= 24.9)
            power = 40;
        else if (victim.getPokeWeight() <= 49.9)
            power = 60;
        else if (victim.getPokeWeight() <= 99.9)
            power = 80;
        else if (victim.getPokeWeight() <= 199.9)
            power = 100;
        else
            power = 120;

        double damage = 42 * power * ((double) attack / defense) + 2;
        damage /= 50;

        if (type == user.getPrimaryType() || type == user.getSecondaryType())
            damage *= 1.5;

        if (user.getItem() == Pokemon.HoldItem.LifeOrb) {
            damage *= 1.3;

            int recoil = user.getMaxHp() / 10;
            user.setCurrentHp(user.getCurrentHp() - recoil);
        }

        damage *= typeModifier(type, victim.getPrimaryType());
        damage *= typeModifier(type, victim.getSecondaryType());

        damage *= (int) (Math.random() * 16 + 85) / 100.0;

        return (int) damage;
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new GrassKnot(true));
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }
}
