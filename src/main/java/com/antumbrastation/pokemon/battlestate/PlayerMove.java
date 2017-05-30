package com.antumbrastation.pokemon.battlestate;

public interface PlayerMove {

    Priority priority();

    BattleState use(BattleState currentState, boolean goingFirst);

}
