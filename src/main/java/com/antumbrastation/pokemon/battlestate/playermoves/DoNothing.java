package com.antumbrastation.pokemon.battlestate.playermoves;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.PlayerMove;
import com.antumbrastation.pokemon.battlestate.Priority;

public class DoNothing implements PlayerMove {

    @Override
    public Priority priority() {
        return new Priority(-1);
    }

    @Override
    public BattleState use(BattleState currentState, boolean goesFirst) {
        return currentState;
    }

    @Override
    public String toString() {
        return "DoNothing{}";
    }
}
