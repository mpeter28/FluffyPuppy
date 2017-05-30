package com.antumbrastation.pokemon.battlestate.playermoves;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.PlayerMove;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.Priority;

public class SwitchPokemon implements PlayerMove {

    private int switchToIndex;
    private boolean isPlayerOne;

    public SwitchPokemon(int switchToIndex, boolean isPlayerOne) {
        this.switchToIndex = switchToIndex;
        this.isPlayerOne = isPlayerOne;
    }

    @Override
    public Priority priority() {
        return new Priority(6, false, true);
    }

    @Override
    public BattleState use(BattleState nextState, boolean goesFirst) {
        if (isPlayerOne) {
            Pokemon currentLead = nextState.getPlayerOneLead();
            Pokemon nextLead = nextState.getPlayerOneTeam().get(switchToIndex);
            nextLead.setMainStatusTurns(0);

            if (currentLead.getAbility() == Pokemon.Ability.Regenerator) {

                int healing = currentLead.getMaxHp() / 3;
                currentLead.setCurrentHp(currentLead.getCurrentHp() + healing);

                if (currentLead.getCurrentHp() > currentLead.getMaxHp())
                    currentLead.setCurrentHp(currentLead.getMaxHp());
            }

            currentLead.setFirstMove(null);

            if (nextLead.getAbility() == Pokemon.Ability.SandStream) {
                nextState.setWeather(BattleState.WeatherType.Sandstorm);
                nextState.setWeatherTurns(5);
            }

            nextState.getPlayerOneTeam().remove(switchToIndex);
            nextState.setPlayerOneLead(nextLead);
            nextState.getPlayerOneTeam().add(currentLead);
        } else {
            Pokemon currentLead = nextState.getPlayerTwoLead();
            Pokemon nextLead = nextState.getPlayerTwoTeam().get(switchToIndex);
            nextLead.setMainStatusTurns(0);

            if (currentLead.getAbility() == Pokemon.Ability.Regenerator) {

                int healing = currentLead.getMaxHp() / 3;
                currentLead.setCurrentHp(currentLead.getCurrentHp() + healing);

                if (currentLead.getCurrentHp() > currentLead.getMaxHp())
                    currentLead.setCurrentHp(currentLead.getMaxHp());
            }

            currentLead.setFirstMove(null);

            if (nextLead.getAbility() == Pokemon.Ability.SandStream) {
                nextState.setWeather(BattleState.WeatherType.Sandstorm);
                nextState.setWeatherTurns(5);
            }

            nextState.getPlayerTwoTeam().remove(switchToIndex);
            nextState.setPlayerTwoLead(nextLead);
            nextState.getPlayerTwoTeam().add(currentLead);
        }

        return nextState;
    }

    @Override
    public String toString() {
        return "SwitchPokemon{" +
                "switchToIndex=" + switchToIndex +
                ", isPlayerOne=" + isPlayerOne +
                '}';
    }
}
