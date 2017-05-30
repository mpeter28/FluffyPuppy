package com.antumbrastation.pokemon.battlestate.pokemoves.concrete;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.DamageMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

public class Psyshock extends DamageMove{

    public Psyshock(boolean playerOne) {
        super (playerOne, 80, 100, 0, Pokemon.PokeType.Psychic);
        setName("Psyshock");
    }

    @Override
    public PokeMove duplicate() {
        return duplicate(new Psyshock(true));
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
        double defense = victim.getDefense();

        if (victim.getDefenseMod() < 0)
            defense *= 2.0 / (2 - victim.getDefenseMod());
        else if (!crit)
            defense *= (2 + victim.getDefenseMod()) / 2.0;

        return (int) defense;
    }

    @Override
    public void buff(Pokemon user, BattleState state, int damage) {

    }

    @Override
    public void debuff(Pokemon victim, BattleState state) {

    }
}
