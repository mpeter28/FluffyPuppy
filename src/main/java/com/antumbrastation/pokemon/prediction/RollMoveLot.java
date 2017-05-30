package com.antumbrastation.pokemon.prediction;

import java.io.Serializable;
import java.util.*;

public class RollMoveLot implements Serializable {

    public static TemplateSet rollMoves(List<TemplateSet> suggestedSets, List<MoveType> knownMovesInitial) {
        TemplateSet rolled = new TemplateSet(MoveType.None, MoveType.None, MoveType.None, MoveType.None);
        List<MoveType> knownMoves = new LinkedList<>(knownMovesInitial);

        //move one
        if (knownMoves.size() == 0) {
            Map<MoveType, Integer> counts = countMoveTypes(suggestedSets, knownMoves);
            MoveType chosen = chooseMove(counts);

            knownMoves.add(chosen);
            rolled.setMoveOne(chosen);
        } else {
            rolled.setMoveOne(knownMoves.get(0));
        }

        //move two
        if (knownMoves.size() == 1) {
            Map<MoveType, Integer> counts = countMoveTypes(suggestedSets, knownMoves);
            MoveType chosen = chooseMove(counts);

            knownMoves.add(chosen);
            rolled.setMoveTwo(chosen);
        } else {
            rolled.setMoveTwo(knownMoves.get(1));
        }

        //move three
        if (knownMoves.size() == 2) {
            Map<MoveType, Integer> counts = countMoveTypes(suggestedSets, knownMoves);
            MoveType chosen = chooseMove(counts);

            knownMoves.add(chosen);
            rolled.setMoveThree(chosen);
        } else {
            rolled.setMoveThree(knownMoves.get(2));
        }

        //move four
        if (knownMoves.size() == 3) {
            Map<MoveType, Integer> counts = countMoveTypes(suggestedSets, knownMoves);
            MoveType chosen = chooseMove(counts);

            knownMoves.add(chosen);
            rolled.setMoveFour(chosen);
        } else {
            rolled.setMoveFour(knownMoves.get(3));
        }

        return rolled;
    }

    public static MoveType chooseMove(Map<MoveType, Integer> counts) {
        int lots =  counts.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)
                .mapToInt(Map.Entry::getValue)
                .sum();
        int roll = (int) (Math.random() * lots);

        for (Map.Entry<MoveType, Integer> move : counts.entrySet()) {
            roll -= move.getValue();
            if (roll < 0)
                return move.getKey();
        }
        return MoveType.None;
    }

    public static Map<MoveType, Integer> countMoveTypes(List<TemplateSet> suggestedSets, List<MoveType> knownMoves) {
        Map<MoveType, Integer> totalCounts = zeroCounts();

        for (TemplateSet suggested : suggestedSets) {
            Map<MoveType, Integer> setCounts = countMoveTypes(suggested, knownMoves);

            totalCounts.put(MoveType.StabSpecial, totalCounts.get(MoveType.StabSpecial) + setCounts.get(MoveType.StabSpecial));
            totalCounts.put(MoveType.StabPhysical, totalCounts.get(MoveType.StabPhysical) + setCounts.get(MoveType.StabPhysical));
            totalCounts.put(MoveType.Coverage, totalCounts.get(MoveType.Coverage) + setCounts.get(MoveType.Coverage));
            totalCounts.put(MoveType.Priority, totalCounts.get(MoveType.Priority) + setCounts.get(MoveType.Priority));
            totalCounts.put(MoveType.Status, totalCounts.get(MoveType.Status) + setCounts.get(MoveType.Status));
            totalCounts.put(MoveType.Healing, totalCounts.get(MoveType.Healing) + setCounts.get(MoveType.Healing));
            totalCounts.put(MoveType.Screens, totalCounts.get(MoveType.Screens) + setCounts.get(MoveType.Screens));
            totalCounts.put(MoveType.Hazards, totalCounts.get(MoveType.Hazards) + setCounts.get(MoveType.Hazards));
            totalCounts.put(MoveType.HazardRemoval, totalCounts.get(MoveType.HazardRemoval) + setCounts.get(MoveType.HazardRemoval));
            totalCounts.put(MoveType.Room, totalCounts.get(MoveType.Room) + setCounts.get(MoveType.Room));
            totalCounts.put(MoveType.StatBoost, totalCounts.get(MoveType.StatBoost) + setCounts.get(MoveType.StatBoost));
            totalCounts.put(MoveType.Phazing, totalCounts.get(MoveType.Phazing) + setCounts.get(MoveType.Phazing));
            totalCounts.put(MoveType.Protection, totalCounts.get(MoveType.Protection) + setCounts.get(MoveType.Protection));
            totalCounts.put(MoveType.Momentum, totalCounts.get(MoveType.Momentum) + setCounts.get(MoveType.Momentum));
            totalCounts.put(MoveType.Nuke, totalCounts.get(MoveType.Nuke) + setCounts.get(MoveType.Nuke));
            totalCounts.put(MoveType.Trapping, totalCounts.get(MoveType.Trapping) + setCounts.get(MoveType.Trapping));
            totalCounts.put(MoveType.Other, totalCounts.get(MoveType.Other) + setCounts.get(MoveType.Other));
        }

        for (MoveType type : totalCounts.keySet()) {
            int weight = totalCounts.get(type);
            totalCounts.put(type, weight * weight);
        }

        return totalCounts;
    }

    public static Map<MoveType, Integer> countMoveTypes(TemplateSet set, List<MoveType> knownMoves) {
        Map<MoveType, Integer> counts = countMoveTypes(set);

        for (MoveType type: knownMoves) {
            counts.put(type, counts.get(type) - 1);
            if (counts.get(type) < 0)
                counts.put(type, 0);
        }

        return counts;
    }

    public static Map<MoveType, Integer> countMoveTypes(TemplateSet set) {
        Map<MoveType, Integer> counts = zeroCounts();

        counts.put(set.getMoveOne(), counts.get(set.getMoveOne()) + 1);
        counts.put(set.getMoveTwo(), counts.get(set.getMoveTwo()) + 1);
        counts.put(set.getMoveThree(), counts.get(set.getMoveThree()) + 1);
        counts.put(set.getMoveFour(), counts.get(set.getMoveFour()) + 1);

        return counts;
    }

    public static Map<MoveType, Integer> zeroCounts() {
        Map<MoveType, Integer> counts = new HashMap<>();

        counts.put(MoveType.StabSpecial, 0);
        counts.put(MoveType.StabPhysical, 0);
        counts.put(MoveType.Coverage, 0);
        counts.put(MoveType.Priority, 0);
        counts.put(MoveType.Status, 0);
        counts.put(MoveType.Healing, 0);
        counts.put(MoveType.Screens, 0);
        counts.put(MoveType.Hazards, 0);
        counts.put(MoveType.HazardRemoval, 0);
        counts.put(MoveType.Room, 0);
        counts.put(MoveType.StatBoost, 0);
        counts.put(MoveType.Phazing, 0);
        counts.put(MoveType.Protection, 0);
        counts.put(MoveType.Momentum, 0);
        counts.put(MoveType.Nuke, 0);
        counts.put(MoveType.Trapping, 0);
        counts.put(MoveType.Other, 0);
        counts.put(MoveType.None, 0);

        return counts;
    }

}
