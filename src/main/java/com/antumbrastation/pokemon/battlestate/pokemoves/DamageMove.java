package com.antumbrastation.pokemon.battlestate.pokemoves;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;

public abstract class DamageMove extends PokeMove {

    private int power;
    private int accuracy;
    private int critRate;
    private Pokemon.PokeType type;

    public DamageMove(boolean playerOne, int power, int accuracy, int critRate, Pokemon.PokeType type) {
        super(playerOne);
        this.power = power;
        this.accuracy = accuracy;
        this.critRate = critRate;
        this.type = type;
    }

    @Override
    public BattleState use(BattleState currentState, boolean goingFirst) {
        Pokemon attacker = currentState.getPlayerOneLead();
        Pokemon defender = currentState.getPlayerTwoLead();
        if (!isPlayerOne()) {
            attacker = currentState.getPlayerTwoLead();
            defender = currentState.getPlayerOneLead();
        }

        if (attacker.getFirstMove() == null)
            attacker.setFirstMove(getName());

        if (attacker.getCurrentHp() <= 0)
            return currentState;

        if (attacker.isFlinch())
            return currentState;

        if (attacker.getMainStatus() == Pokemon.MainStatus.Paralysis && (int) (Math.random() * 100) < 25) {
            return currentState;
        }

        if (attacker.getMainStatus() == Pokemon.MainStatus.Frozen && (int) (Math.random() * 100) < 25) {
            attacker.setMainStatus(Pokemon.MainStatus.Fine);
        } else if (attacker.getMainStatus() == Pokemon.MainStatus.Frozen) {
            return currentState;
        }

        if (attacker.getMainStatus() == Pokemon.MainStatus.Sleep) {
            if (attacker.getMainStatusTurns() == 0) {
                attacker.setMainStatusTurns(1);
                return currentState;
            } else if (attacker.getMainStatusTurns() == 1 && (int) (Math.random() * 100) < 33) {
                attacker.setMainStatus(Pokemon.MainStatus.Fine);
                attacker.setMainStatusTurns(2);
            } else if (attacker.getMainStatusTurns() == 2 && (int) (Math.random() * 100) < 50) {
                attacker.setMainStatus(Pokemon.MainStatus.Fine);
                attacker.setMainStatusTurns(3);
            } else if (attacker.getMainStatusTurns() >= 3) {
                attacker.setMainStatus(Pokemon.MainStatus.Fine);
                attacker.setMainStatusTurns(0);
            }
        }

        if ((int) (Math.random() * 100) >= accuracy)
            return currentState;

        boolean crit = (int)(Math.random() * 16) <= critRate;
        double damage = damageAmount(attacker, defender, crit, goingFirst);
        if (crit)
            damage *= 1.5;

        defender.setCurrentHp(defender.getCurrentHp() - (int) damage);
        buff(attacker, currentState, (int) damage);
        debuff(defender, currentState);

        return currentState;
    }

    public int damageAmount(Pokemon user, Pokemon victim, boolean crit, boolean goingFirst) {
        int attack = attackStat(user, crit);
        int defense = defenseStat(victim, crit);

        if (type == Pokemon.PokeType.Ground && victim.getAbility() == Pokemon.Ability.Levitate)
            return 0;

        if (type == Pokemon.PokeType.Water && victim.getAbility() == Pokemon.Ability.WaterAbsorb)
            return 0;

        double damage = 42 * power * ((double) attack / defense) + 2;
        damage /= 50;

        if (type == user.getPrimaryType() || type == user.getSecondaryType())
            damage *= 1.5;

        if (user.getCurrentHp() / (double) user.getMaxHp() <= .333)
            if (type == Pokemon.PokeType.Grass && user.getAbility() == Pokemon.Ability.Overgrow)
                damage *= 1.5;
            else if (type == Pokemon.PokeType.Fire && user.getAbility() == Pokemon.Ability.Blaze)
                damage *= 1.5;

        if (user.getAbility() == Pokemon.Ability.Analytic && !goingFirst)
            damage *= 1.3;

        if (user.getItem() == Pokemon.HoldItem.LifeOrb) {
            damage *= 1.3;

            int recoil = user.getMaxHp() / 10;
            user.setCurrentHp(user.getCurrentHp() - recoil);
        }

        if (type == Pokemon.PokeType.Ground && victim.getItem() == Pokemon.HoldItem.ShucaBerry) {
            damage *= .67;
            victim.setItem(Pokemon.HoldItem.None);
        }

        damage *= typeModifier(type, victim.getPrimaryType());
        damage *= typeModifier(type, victim.getSecondaryType());

        damage *= (int)(Math.random() * 16 + 85) / 100.0;

        return (int) damage;
    }

    public double typeModifier(Pokemon.PokeType attack, Pokemon.PokeType defense) {
        if (defense == null)
            return 1;

        switch (attack) {
            case Normal:
                switch (defense) {
                    case Rock:
                        return 0.5;
                    case Steel:
                        return 0.5;
                    case Ghost:
                        return 0;
                }
                break;
            case Fire:
                switch (defense) {
                    case Grass:
                        return 2;
                    case Ice:
                        return 2;
                    case Bug:
                        return 2;
                    case Steel:
                        return 2;
                    case Fire:
                        return 0.5;
                    case Water:
                        return 0.5;
                    case Rock:
                        return 0.5;
                    case Dragon:
                        return 0.5;
                }
                break;
            case Water:
                switch (defense) {
                    case Fire:
                        return 2;
                    case Ground:
                        return 2;
                    case Rock:
                        return 2;
                    case Water:
                        return 0.5;
                    case Grass:
                        return 0.5;
                    case Dragon:
                        return 0.5;
                }
                break;
            case Electric:
                switch (defense) {
                    case Water:
                        return 2;
                    case Flying:
                        return 2;
                    case Ground:
                        return 0;
                    case Electric:
                        return 0.5;
                    case Grass:
                        return 0.5;
                    case Dragon:
                        return 0.5;
                }
                break;
            case Grass:
                switch (defense) {
                    case Water:
                        return 2;
                    case Ground:
                        return 2;
                    case Rock:
                        return 2;
                    case Fire:
                        return 0.5;
                    case Grass:
                        return 0.5;
                    case Poison:
                        return 0.5;
                    case Flying:
                        return 0.5;
                    case Bug:
                        return 0.5;
                    case Dragon:
                        return 0.5;
                    case Steel:
                        return 0.5;
                }
                break;
            case Ice:
                switch (defense) {
                    case Grass:
                        return 2;
                    case Ground:
                        return 2;
                    case Flying:
                        return 2;
                    case Dragon:
                        return 2;
                    case Fire:
                        return 0.5;
                    case Water:
                        return 0.5;
                    case Ice:
                        return 0.5;
                    case Steel:
                        return 0.5;
                }
                break;
            case Fighting:
                switch (defense) {
                    case Normal:
                        return 2;
                    case Ice:
                        return 2;
                    case Rock:
                        return 2;
                    case Dark:
                        return 2;
                    case Steel:
                        return 2;
                    case Poison:
                        return 0.5;
                    case Flying:
                        return 0.5;
                    case Psychic:
                        return 0.5;
                    case Bug:
                        return 0.5;
                    case Fairy:
                        return 0.5;
                    case Ghost:
                        return 0;
                }
                break;
            case Poison:
                switch (defense) {
                    case Grass:
                        return 2;
                    case Fairy:
                        return 2;
                    case Poison:
                        return 0.5;
                    case Ground:
                        return 0.5;
                    case Rock:
                        return 0.5;
                    case Ghost:
                        return 0.5;
                }
                break;
            case Ground:
                switch (defense) {
                    case Fire:
                        return 2;
                    case Electric:
                        return 2;
                    case Poison:
                        return 2;
                    case Rock:
                        return 2;
                    case Steel:
                        return 2;
                    case Grass:
                        return 0.5;
                    case Flying:
                        return 0;
                    case Bug:
                        return 0.5;
                }
                break;
            case Flying:
                switch (defense) {
                    case Grass:
                        return 2;
                    case Fighting:
                        return 2;
                    case Bug:
                        return 2;
                    case Electric:
                        return 0.5;
                    case Rock:
                        return 0.5;
                    case Steel:
                        return 0.5;
                }
                break;
            case Psychic:
                switch (defense) {
                    case Fighting:
                        return 2;
                    case Poison:
                        return 2;
                    case Psychic:
                        return 0.5;
                    case Steel:
                        return 0.5;
                    case Dark:
                        return 0;
                }
                break;
            case Bug:
                switch (defense) {
                    case Grass:
                        return 2;
                    case Psychic:
                        return 2;
                    case Dark:
                        return 2;
                    case Fire:
                        return 0.5;
                    case Fighting:
                        return 0.5;
                    case Poison:
                        return 0.5;
                    case Flying:
                        return 0.5;
                    case Ghost:
                        return 0.5;
                    case Steel:
                        return 0.5;
                    case Fairy:
                        return 0.5;
                }
                break;
            case Rock:
                switch (defense) {
                    case Fire:
                        return 2;
                    case Ice:
                        return 2;
                    case Flying:
                        return 2;
                    case Bug:
                        return 2;
                    case Fighting:
                        return 0.5;
                    case Ground:
                        return 0.5;
                    case Steel:
                        return 0.5;
                }
                break;
            case Ghost:
                switch (defense) {
                    case Psychic:
                        return 2;
                    case Ghost:
                        return 2;
                    case Dark:
                        return 0.5;
                    case Normal:
                        return 0;
                }
                break;
            case Dragon:
                switch (defense) {
                    case Dragon:
                        return 2;
                    case Steel:
                        return 0.5;
                    case Fairy:
                        return 0;
                }
                break;
            case Dark:
                switch (defense) {
                    case Psychic:
                        return 2;
                    case Ghost:
                        return 2;
                    case Fighting:
                        return 0.5;
                    case Dark:
                        return 0.5;
                    case Fairy:
                        return 0.5;
                }
                break;
            case Steel:
                switch (defense) {
                    case Ice:
                        return 2;
                    case Rock:
                        return 2;
                    case Fairy:
                        return 2;
                    case Fire:
                        return 0.5;
                    case Water:
                        return 0.5;
                    case Electric:
                        return 0.5;
                    case Steel:
                        return 0.5;
                }
                break;
            case Fairy:
                switch (defense) {
                    case Fighting:
                        return 2;
                    case Dragon:
                        return 2;
                    case Dark:
                        return 2;
                    case Fire:
                        return 0.5;
                    case Poison:
                        return 0.5;
                    case Steel:
                        return 0.5;
                }
                break;
        }

        return 1.0;
    }

    @Override
    public Pokemon.PokeType getType() {
        return type;
    }

    public abstract int attackStat(Pokemon user, boolean crit);
    public abstract int defenseStat(Pokemon victim, boolean crit);

    public abstract void buff(Pokemon user, BattleState state, int damage);
    public abstract void debuff(Pokemon victim, BattleState state);

}
