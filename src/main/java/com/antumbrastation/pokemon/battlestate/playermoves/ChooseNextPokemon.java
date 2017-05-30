package com.antumbrastation.pokemon.battlestate.playermoves;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.PlayerMove;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.Priority;

public class ChooseNextPokemon implements PlayerMove {

    private int nextPokemonIndex;
    private boolean isPlayerOne;

    public ChooseNextPokemon(int nextPokemonIndex, boolean isPlayerOne) {
        this.nextPokemonIndex = nextPokemonIndex;
        this.isPlayerOne = isPlayerOne;
    }

    @Override
    public Priority priority() {
        return new Priority(0);
    }

    @Override
    public BattleState use(BattleState nextState, boolean goesFirst) {
        if (isPlayerOne) {
            Pokemon nextLead = nextState.getPlayerOneTeam().get(nextPokemonIndex);

            if (nextLead.getAbility() == Pokemon.Ability.SandStream) {
                nextState.setWeather(BattleState.WeatherType.Sandstorm);
                nextState.setWeatherTurns(5);
            }

            nextState.getPlayerOneTeam().remove(nextPokemonIndex);
            nextState.setPlayerOneLead(nextLead);
        } else {
            Pokemon nextLead = nextState.getPlayerTwoTeam().get(nextPokemonIndex);

            if (nextLead.getAbility() == Pokemon.Ability.SandStream) {
                nextState.setWeather(BattleState.WeatherType.Sandstorm);
                nextState.setWeatherTurns(5);
            }

            nextState.getPlayerTwoTeam().remove(nextPokemonIndex);
            nextState.setPlayerTwoLead(nextLead);
        }

        return nextState;
    }

    @Override
    public String toString() {
        return "ChooseNextPokemon{" +
                "nextPokemonIndex=" + nextPokemonIndex +
                ", isPlayerOne=" + isPlayerOne +
                '}';
    }
}
