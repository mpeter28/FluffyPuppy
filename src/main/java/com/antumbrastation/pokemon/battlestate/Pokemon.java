package com.antumbrastation.pokemon.battlestate;

import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

import java.util.Set;
import java.util.stream.Collectors;

public class Pokemon {

    // Team of Pokemon
    private boolean isPlayerOne;

    // Hp
    private int maxHp, currentHp;

    // Pokemon identity
    private PokeSpecies species;
    private PokeType primaryType;
    private PokeType secondaryType;
    private double pokeWeight;

    // Base Stats
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;

    // Moves
    private Set<PokeMove> moves;
    private String firstMove;

    // ability
    private Ability ability;
    private Ability activeAbility;

    // hold item
    private HoldItem item;
    private HoldItem recycleItem;

    // -6 to +6 steps off base stats
    private int attackMod;
    private int defenseMod;
    private int specialAttackMod;
    private int specialDefenseMod;
    private int speedMod;
    private int evasionMod;
    private int accuracyMod;

    // statuses
    private MainStatus mainStatus;
    private int mainStatusTurns;

    private boolean confused;
    private int confusedTurns;

    private boolean encored;
    private int encoredTurns;

    private boolean taunt;
    private int tauntTurns;

    private boolean destinyBond;

    private boolean leechSeed;

    private boolean smackedDown;

    private boolean substitute;
    private int subHealth;

    private boolean trapped;

    private boolean flinch;

    // recharge
    private boolean recharge;
    private Charging charging;

    // protect uses since last failed/non use
    private int protectUses;

    // ditto stuff
    private boolean transformed;
    private Pokemon intoWhat;

    public Pokemon duplicate() {
        Pokemon duplicate = new Pokemon();

        duplicate.setPlayerOne(isPlayerOne);

        duplicate.setMaxHp(maxHp);
        duplicate.setCurrentHp(currentHp);

        duplicate.setSpecies(species);
        duplicate.setPrimaryType(primaryType);
        duplicate.setSecondaryType(secondaryType);
        duplicate.setPokeWeight(pokeWeight);

        duplicate.setAttack(attack);
        duplicate.setDefense(defense);
        duplicate.setSpecialAttack(specialAttack);
        duplicate.setSpecialDefense(specialDefense);
        duplicate.setSpeed(speed);

        duplicate.setMoves(moves.stream().map(PokeMove::duplicate).collect(Collectors.toSet()));
        duplicate.setFirstMove(firstMove);

        duplicate.setAbility(ability);
        duplicate.setActiveAbility(activeAbility);

        duplicate.setItem(item);
        duplicate.setRecycleItem(recycleItem);

        duplicate.setAttackMod(attackMod);
        duplicate.setDefenseMod(defenseMod);
        duplicate.setSpecialAttackMod(specialAttackMod);
        duplicate.setSpecialDefenseMod(specialDefenseMod);
        duplicate.setSpeedMod(speedMod);
        duplicate.setEvasionMod(evasionMod);
        duplicate.setAccuracyMod(accuracyMod);

        duplicate.setMainStatus(mainStatus);
        duplicate.setMainStatusTurns(mainStatusTurns);

        duplicate.setConfused(confused);
        duplicate.setConfusedTurns(confusedTurns);

        duplicate.setEncored(encored);
        duplicate.setEncoredTurns(encoredTurns);

        duplicate.setTaunt(taunt);
        duplicate.setTauntTurns(tauntTurns);

        duplicate.setDestinyBond(destinyBond);

        duplicate.setLeechSeed(leechSeed);

        duplicate.setSmackedDown(smackedDown);

        duplicate.setTrapped(trapped);

        duplicate.setFlinch(flinch);

        duplicate.setSubstitute(substitute);
        duplicate.setSubHealth(subHealth);

        duplicate.setRecharge(recharge);
        duplicate.setCharging(charging);

        duplicate.setProtectUses(protectUses);

        duplicate.setTransformed(transformed);
        duplicate.setIntoWhat(intoWhat);

        return duplicate;
    }

    public enum FieldLocation {
        Normal,
        Air,
        Underground,
        Underwater
    }

    public enum Charging {
        None,
        SolarBeam,
        SkyAttack,
        RazorWind,
        Flying,
        Dig,
        Dive
    }

    public enum MainStatus {
        Fine,
        Poison,
        Paralysis,
        Sleep,
        Rest,
        Frozen,
        BadPoison,
        Burned
    }

    public enum Ability {
        Off,
        Blaze,
        SandStream,
        Regenerator,
        Analytic,
        Overgrow,
        Justified,
        WaterAbsorb,
        Levitate
    }

    public enum HoldItem {
        None,
        Leftovers,
        LifeOrb,
        BlackSludge,
        ChoiceSpecs,
        ElectriumZ,
        ShucaBerry,
        DecidiumZ,
        Other
    }

    public enum PokeType {
        Normal,
        Fire,
        Water,
        Electric,
        Grass,
        Ice,
        Fighting,
        Poison,
        Ground,
        Flying,
        Psychic,
        Bug,
        Rock,
        Ghost,
        Dragon,
        Dark,
        Steel,
        Fairy,
        None
    }

    public enum PokeSpecies {
        Pikachu,
        Infernape,
        Hippowdon,
        Amoonguss,
        Starmie,
        Decidueye,
        Cobalion,
        Volcanion,
        Latias
    }

    // getters and setters


    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    public void setPlayerOne(boolean playerOne) {
        isPlayerOne = playerOne;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public PokeSpecies getSpecies() {
        return species;
    }

    public void setSpecies(PokeSpecies species) {
        this.species = species;
    }

    public PokeType getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(PokeType primaryType) {
        this.primaryType = primaryType;
    }

    public PokeType getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(PokeType secondaryType) {
        this.secondaryType = secondaryType;
    }

    public double getPokeWeight() {
        return pokeWeight;
    }

    public void setPokeWeight(double pokeWeight) {
        this.pokeWeight = pokeWeight;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        return mainStatus == MainStatus.Paralysis ? speed / 2 : speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Set<PokeMove> getMoves() {
        return moves;
    }

    public void setMoves(Set<PokeMove> moves) {
        this.moves = moves;
    }

    public String getFirstMove() {
        return firstMove;
    }

    public void setFirstMove(String firstMove) {
        this.firstMove = firstMove;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Ability getActiveAbility() {
        return activeAbility;
    }

    public void setActiveAbility(Ability activeAbility) {
        this.activeAbility = activeAbility;
    }

    public HoldItem getItem() {
        return item;
    }

    public void setItem(HoldItem item) {
        this.item = item;
    }

    public HoldItem getRecycleItem() {
        return recycleItem;
    }

    public void setRecycleItem(HoldItem recycleItem) {
        this.recycleItem = recycleItem;
    }

    public int getAttackMod() {
        return attackMod;
    }

    public void setAttackMod(int attackMod) {
        this.attackMod = attackMod;
    }

    public int getDefenseMod() {
        return defenseMod;
    }

    public void setDefenseMod(int defenseMod) {
        this.defenseMod = defenseMod;
    }

    public int getSpecialAttackMod() {
        return specialAttackMod;
    }

    public void setSpecialAttackMod(int specialAttackMod) {
        this.specialAttackMod = specialAttackMod;
    }

    public int getSpecialDefenseMod() {
        return specialDefenseMod;
    }

    public void setSpecialDefenseMod(int specialDefenseMod) {
        this.specialDefenseMod = specialDefenseMod;
    }

    public int getSpeedMod() {
        return speedMod;
    }

    public void setSpeedMod(int speedMod) {
        this.speedMod = speedMod;
    }

    public int getEvasionMod() {
        return evasionMod;
    }

    public void setEvasionMod(int evasionMod) {
        this.evasionMod = evasionMod;
    }

    public int getAccuracyMod() {
        return accuracyMod;
    }

    public void setAccuracyMod(int accuracyMod) {
        this.accuracyMod = accuracyMod;
    }

    public MainStatus getMainStatus() {
        return mainStatus;
    }

    public void setMainStatus(MainStatus mainStatus) {
        this.mainStatus = mainStatus;
    }

    public int getMainStatusTurns() {
        return mainStatusTurns;
    }

    public void setMainStatusTurns(int mainStatusTurns) {
        this.mainStatusTurns = mainStatusTurns;
    }

    public boolean isConfused() {
        return confused;
    }

    public void setConfused(boolean confused) {
        this.confused = confused;
    }

    public int getConfusedTurns() {
        return confusedTurns;
    }

    public void setConfusedTurns(int confusedTurns) {
        this.confusedTurns = confusedTurns;
    }

    public boolean isEncored() {
        return encored;
    }

    public void setEncored(boolean encored) {
        this.encored = encored;
    }

    public int getEncoredTurns() {
        return encoredTurns;
    }

    public void setEncoredTurns(int encoredTurns) {
        this.encoredTurns = encoredTurns;
    }

    public boolean isTaunt() {
        return taunt;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public int getTauntTurns() {
        return tauntTurns;
    }

    public void setTauntTurns(int tauntTurns) {
        this.tauntTurns = tauntTurns;
    }

    public boolean isDestinyBond() {
        return destinyBond;
    }

    public void setDestinyBond(boolean destinyBond) {
        this.destinyBond = destinyBond;
    }

    public boolean isLeechSeed() {
        return leechSeed;
    }

    public void setLeechSeed(boolean leechSeed) {
        this.leechSeed = leechSeed;
    }

    public boolean isSmackedDown() {
        return smackedDown;
    }

    public void setSmackedDown(boolean smackedDown) {
        this.smackedDown = smackedDown;
    }

    public boolean isSubstitute() {
        return substitute;
    }

    public void setSubstitute(boolean substitute) {
        this.substitute = substitute;
    }

    public int getSubHealth() {
        return subHealth;
    }

    public void setSubHealth(int subHealth) {
        this.subHealth = subHealth;
    }

    public boolean isRecharge() {
        return recharge;
    }

    public void setRecharge(boolean recharge) {
        this.recharge = recharge;
    }

    public Charging getCharging() {
        return charging;
    }

    public void setCharging(Charging charging) {
        this.charging = charging;
    }

    public int getProtectUses() {
        return protectUses;
    }

    public void setProtectUses(int protectUses) {
        this.protectUses = protectUses;
    }

    public boolean isTransformed() {
        return transformed;
    }

    public void setTransformed(boolean transformed) {
        this.transformed = transformed;
    }

    public Pokemon getIntoWhat() {
        return intoWhat;
    }

    public void setIntoWhat(Pokemon intoWhat) {
        this.intoWhat = intoWhat;
    }

    public boolean isTrapped() {
        return trapped;
    }

    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }

    public boolean isFlinch() {
        return flinch;
    }

    public void setFlinch(boolean flinch) {
        this.flinch = flinch;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "isPlayerOne=" + isPlayerOne +
                ", maxHp=" + maxHp +
                ", currentHp=" + currentHp +
                ", species=" + species +
                ", primaryType=" + primaryType +
                ", secondaryType=" + secondaryType +
                ", pokeWeight=" + pokeWeight +
                ", attack=" + attack +
                ", defense=" + defense +
                ", specialAttack=" + specialAttack +
                ", specialDefense=" + specialDefense +
                ", speed=" + speed +
                ", moves=" + moves +
                ", firstMove='" + firstMove + '\'' +
                ", ability=" + ability +
                ", activeAbility=" + activeAbility +
                ", item=" + item +
                ", recycleItem=" + recycleItem +
                ", attackMod=" + attackMod +
                ", defenseMod=" + defenseMod +
                ", specialAttackMod=" + specialAttackMod +
                ", specialDefenseMod=" + specialDefenseMod +
                ", speedMod=" + speedMod +
                ", evasionMod=" + evasionMod +
                ", accuracyMod=" + accuracyMod +
                ", mainStatus=" + mainStatus +
                ", mainStatusTurns=" + mainStatusTurns +
                ", confused=" + confused +
                ", confusedTurns=" + confusedTurns +
                ", encored=" + encored +
                ", encoredTurns=" + encoredTurns +
                ", taunt=" + taunt +
                ", tauntTurns=" + tauntTurns +
                ", destinyBond=" + destinyBond +
                ", leechSeed=" + leechSeed +
                ", smackedDown=" + smackedDown +
                ", substitute=" + substitute +
                ", subHealth=" + subHealth +
                ", trapped=" + trapped +
                ", flinch=" + flinch +
                ", recharge=" + recharge +
                ", charging=" + charging +
                ", protectUses=" + protectUses +
                ", transformed=" + transformed +
                ", intoWhat=" + intoWhat +
                '}';
    }
}
