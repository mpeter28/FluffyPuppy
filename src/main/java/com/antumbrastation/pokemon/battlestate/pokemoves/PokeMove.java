package com.antumbrastation.pokemon.battlestate.pokemoves;

import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.Priority;
import com.antumbrastation.pokemon.battlestate.PlayerMove;

public abstract class PokeMove implements PlayerMove {

    private boolean isPlayerOne;

    private String name;
    private Pokemon.PokeType type;

    private int currentPP;
    private int maxPP;

    private boolean disabled;
    private Priority priority;
    private boolean attacking;

    public PokeMove(boolean isPlayerOne) {
        this.isPlayerOne = isPlayerOne;
        priority = new Priority(0);
    }

    @Override
    public Priority priority() {
        return priority;
    }

    public abstract PokeMove duplicate();

    public PokeMove duplicate(PokeMove correctType) {
        correctType.setPlayerOne(isPlayerOne);

        correctType.setName(name);
        correctType.setType(type);
        correctType.setCurrentPP(currentPP);
        correctType.setMaxPP(maxPP);
        correctType.setDisabled(disabled);
        correctType.setPriority(priority.duplicate());
        correctType.setAttacking(attacking);

        return correctType;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pokemon.PokeType getType() {
        return type;
    }

    public void setType(Pokemon.PokeType type) {
        this.type = type;
    }

    public int getCurrentPP() {
        return currentPP;
    }

    public void setCurrentPP(int currentPP) {
        this.currentPP = currentPP;
    }

    public int getMaxPP() {
        return maxPP;
    }

    public void setMaxPP(int maxPP) {
        this.maxPP = maxPP;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    public void setPlayerOne(boolean playerOne) {
        isPlayerOne = playerOne;
    }

    public String toString() {
        return name;
    }
}