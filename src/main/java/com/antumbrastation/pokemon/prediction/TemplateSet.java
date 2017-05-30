package com.antumbrastation.pokemon.prediction;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TemplateSet implements Serializable {

    private MoveType moveOne;
    private MoveType moveTwo;
    private MoveType moveThree;
    private MoveType moveFour;

    public TemplateSet(MoveType moveOne, MoveType moveTwo, MoveType moveThree, MoveType moveFour) {
        this.moveOne = moveOne;
        this.moveTwo = moveTwo;
        this.moveThree = moveThree;
        this.moveFour = moveFour;
    }

    public MoveType getMoveOne() {
        return moveOne;
    }

    public void setMoveOne(MoveType moveOne) {
        this.moveOne = moveOne;
    }

    public MoveType getMoveTwo() {
        return moveTwo;
    }

    public void setMoveTwo(MoveType moveTwo) {
        this.moveTwo = moveTwo;
    }

    public MoveType getMoveThree() {
        return moveThree;
    }

    public void setMoveThree(MoveType moveThree) {
        this.moveThree = moveThree;
    }

    public MoveType getMoveFour() {
        return moveFour;
    }

    public void setMoveFour(MoveType moveFour) {
        this.moveFour = moveFour;
    }

    @Override
    public String toString() {
        List<MoveType> moves = new LinkedList<>();
        moves.add(moveOne);
        moves.add(moveTwo);
        moves.add(moveThree);
        moves.add(moveFour);

        Collections.sort(moves);

        return moves.toString();
    }
}
