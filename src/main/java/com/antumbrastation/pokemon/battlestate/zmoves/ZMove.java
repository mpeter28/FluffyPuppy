package com.antumbrastation.pokemon.battlestate.zmoves;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.PlayerMove;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.Priority;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;

public class ZMove implements PlayerMove {

    private PokeMove zMove;
    private boolean playerOne;

    public ZMove(PokeMove zMove, boolean playerOne) {
        this.zMove = zMove;
        this.playerOne = playerOne;
    }

    @Override
    public Priority priority() {
        return new Priority(0);
    }

    @Override
    public BattleState use(BattleState currentState, boolean goingFirst) {
        if (playerOne)
            currentState.getPlayerOneLead().setItem(Pokemon.HoldItem.None);
        else
            currentState.getPlayerTwoLead().setItem(Pokemon.HoldItem.None);

        return zMove.use(currentState, goingFirst);
    }

    public PokeMove getzMove() {
        return zMove;
    }

    public void setzMove(PokeMove zMove) {
        this.zMove = zMove;
    }

    @Override
    public String toString() {
        return "ZMove{" +
                "zMove=" + zMove +
                '}';
    }
}
