package com.antumbrastation.pokemon;

import com.antumbrastation.pokemon.prediction.MoveType;
import com.antumbrastation.pokemon.prediction.RollMoveLot;
import com.antumbrastation.pokemon.prediction.TemplateSet;

import java.util.LinkedList;
import java.util.List;

public class Arcanine {

    public static void main(String[] args) {
        List<MoveType> knownMoves = new LinkedList<>();
        //knownMoves.add(MoveType.Stab);

        List<TemplateSet> suggestedSets = new LinkedList<>();
        suggestedSets.add(new TemplateSet(MoveType.StabSpecial, MoveType.StatBoost, MoveType.Status, MoveType.Protection));
        suggestedSets.add(new TemplateSet(MoveType.StabSpecial, MoveType.StatBoost, MoveType.Status, MoveType.Phazing));
        suggestedSets.add(new TemplateSet(MoveType.StabSpecial, MoveType.StatBoost, MoveType.Status, MoveType.Healing));
        suggestedSets.add(new TemplateSet(MoveType.StabPhysical, MoveType.Coverage, MoveType.Priority, MoveType.Protection));

        for (int i = 0; i < 20; i++)
            System.out.println(
                    RollMoveLot.rollMoves(suggestedSets, knownMoves)
            );
    }
}
