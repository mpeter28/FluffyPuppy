package com.antumbrastation.pokemon.mcts;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.PlayerMove;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DecisionNode2 {

    private BattleState parent;
    private PlayerMove leftMove;
    private PlayerMove rightMove;

    private BattleState gameState;
    private boolean terminal;
    private double stateValue;

    private boolean expanded;

    private double wins;
    private int visits;
    private int[] leftVisits;
    private int[] rightVisits;
    private double[] leftValues;
    private double[] rightValues;

    private List<PlayerMove> leftOptions;
    private List<PlayerMove> rightOptions;

    private List[][] children;

    public DecisionNode2(BattleState rootState) {
        this.gameState = rootState;
    }

    public DecisionNode2(BattleState parent, PlayerMove leftMove, PlayerMove rightMove) {
        this.parent = parent;
        this.leftMove = leftMove;
        this.rightMove = rightMove;
    }

    public double selectDownwards() {
        if (gameState == null) {
            gameState = parent.resolveMoves(leftMove, rightMove);

            if (gameState.done()) {
                expanded = true;
                terminal = true;
                stateValue = gameState.playerOneWin();
            }
        }

        if (terminal) {
            return stateValue;
        }

        if (!expanded) {
            expand();
            expanded = true;

            Pair<Integer, Integer> chosenPlay = new ImmutablePair<>(
                    choosePlay(visits, leftVisits, leftValues, leftOptions.size()),
                    choosePlay(visits, rightVisits, rightValues, rightOptions.size())
            );
            List<DecisionNode2> possibleResults = children[chosenPlay.getLeft()][chosenPlay.getRight()];
            if (possibleResults == null) {
                possibleResults = new LinkedList<>();
                children[chosenPlay.getLeft()][chosenPlay.getRight()] = possibleResults;

                for (int i = 0; i < 8; i++)
                    possibleResults.add(new DecisionNode2(gameState,
                            leftOptions.get(chosenPlay.getLeft()),
                            rightOptions.get(chosenPlay.getRight())));
            }
            DecisionNode2 child = possibleResults.get((int) (Math.random() * 8));
            double result = child.playout();

            wins += result;
            visits++;
            leftVisits[chosenPlay.getLeft()]++;
            rightVisits[chosenPlay.getRight()]++;
            leftValues[chosenPlay.getLeft()] += result;
            rightValues[chosenPlay.getRight()] += 1 - result;

            return result;
        } else {
            Pair<Integer, Integer> chosenPlay = new ImmutablePair<>(
                    choosePlay(visits, leftVisits, leftValues, leftOptions.size()),
                    choosePlay(visits, rightVisits, rightValues, rightOptions.size())
            );
            List<DecisionNode2> possibleResults = children[chosenPlay.getLeft()][chosenPlay.getRight()];
            if (possibleResults == null) {
                possibleResults = new LinkedList<>();
                children[chosenPlay.getLeft()][chosenPlay.getRight()] = possibleResults;

                for (int i = 0; i < 8; i++)
                    possibleResults.add(new DecisionNode2(gameState,
                            leftOptions.get(chosenPlay.getLeft()),
                            rightOptions.get(chosenPlay.getRight())));
            }
            DecisionNode2 child = possibleResults.get((int) (Math.random() * 8));
            double result = child.selectDownwards();

            wins += result;
            visits++;
            leftVisits[chosenPlay.getLeft()]++;
            rightVisits[chosenPlay.getRight()]++;
            leftValues[chosenPlay.getLeft()] += result;
            rightValues[chosenPlay.getRight()] += 1 - result;

            return result;
        }
    }

    private int choosePlay(int allVisits, int[] visits, double[] values, int moveCount) {
        int bestMove = -1;
        double bestValuation = -1;

        for (int move = 0; move < moveCount; move++) {
            double valuation = Double.MAX_VALUE;
            if (allVisits > 0 && visits[move] > 0) {
                valuation = (values[move] / visits[move]) + 1.1 * Math.sqrt(Math.log(allVisits) / visits[move]);
            }

            if (valuation > bestValuation) {
                bestMove = move;
                bestValuation = valuation;
            }
        }

        return bestMove;
    }

    private void expand() {
        leftOptions = gameState.playerOneMoves();
        rightOptions = gameState.playerTwoMoves();
        children = new List[leftOptions.size()][rightOptions.size()];
        leftVisits = new int[leftOptions.size()];
        leftValues = new double[leftOptions.size()];
        rightVisits = new int[rightOptions.size()];
        rightValues = new double[rightOptions.size()];
    }

    public double playout() {
        if (gameState == null) {
            gameState = parent.resolveMoves(leftMove, rightMove);

            if (gameState.done()) {
                expanded = true;
                terminal = true;
                stateValue = gameState.playerOneWin();
            }
        }

        BattleState state = gameState;
        int turn = 0;
        while (!state.done() && turn < 1000) {
            turn++;
            List<PlayerMove> leftOptions = state.playerOneMoves();
            List<PlayerMove> rightOptions = state.playerTwoMoves();

            PlayerMove leftMove = leftOptions.get((int) (Math.random() * leftOptions.size()));
            PlayerMove rightMove = rightOptions.get((int) (Math.random() * rightOptions.size()));

            state = state.resolveMoves(leftMove, rightMove);
        }

        return state.playerOneWin();
    }

    public double playoutVerbose() {
        BattleState state = gameState;
        int turn = 0;
        while (!state.done() && turn < 100) {
            List<PlayerMove> leftOptions = state.playerOneMoves();
            List<PlayerMove> rightOptions = state.playerTwoMoves();

            PlayerMove leftMove = leftOptions.get((int) (Math.random() * leftOptions.size()));
            PlayerMove rightMove = rightOptions.get((int) (Math.random() * rightOptions.size()));

            System.out.println(++turn + " " + leftMove.toString() + " " + rightMove.toString());
            state = state.resolveMoves(leftMove, rightMove);
            System.out.println(turn + " " + state.getPlayerOneLead().getSpecies() + " v " + state.getPlayerTwoLead().getSpecies());
            System.out.println(turn + " HP: " + state.getPlayerOneLead().getCurrentHp() + " HP: " + state.getPlayerTwoLead().getCurrentHp());
        }

        return state.playerOneWin();
    }

    @Override
    public String toString() {
        return "DecisionNode{" +
                "visits=" + visits +
                ", leftVisits=" + Arrays.toString(leftVisits) +
                ", rightVisits=" + Arrays.toString(rightVisits) +
                ", leftValues=" + Arrays.toString(leftValues) +
                ", rightValues=" + Arrays.toString(rightValues) +
                '}';
    }

    public PlayerMove chosenPlayerOneMove() {
        int bestMove = -1;
        int mostVisits = -1;

        for (int move = 0; move < leftOptions.size(); move++) {
            if (leftVisits[move] > mostVisits) {
                mostVisits = leftVisits[move];
                bestMove = move;
            }
        }

        return leftOptions.get(bestMove);
    }

    public PlayerMove chosenPlayerTwoMove() {
        int bestMove = -1;
        double bestValue = -1;

        for (int move = 0; move < rightOptions.size(); move++) {
            double valuation = Double.MIN_VALUE;
            if (rightVisits[move] > 0) {
                valuation = (rightValues[move] / rightVisits[move]);
            }

            if (valuation > bestValue) {
                bestValue = valuation;
                bestMove = move;
            }
        }

        return rightOptions.get(bestMove);
    }

    public double getWins() {
        return wins;
    }

    public int getVisits() {
        return visits;
    }

    public int[] getLeftVisits() {
        return leftVisits;
    }

    public int[] getRightVisits() {
        return rightVisits;
    }

    public double[] getLeftValues() {
        return leftValues;
    }

    public double[] getRightValues() {
        return rightValues;
    }

    public double[] leftResults() {
        double[] results = new double[leftOptions.size()];
        for (int i = 0; i < leftOptions.size(); i++) {
            results[i] = leftValues[i] / leftVisits[i];
        }

        return results;
    }

    public double[] rightResults() {
        double[] results = new double[rightOptions.size()];
        for (int i = 0; i < rightOptions.size(); i++) {
            results[i] = rightValues[i] / rightVisits[i];
        }

        return results;
    }
}
