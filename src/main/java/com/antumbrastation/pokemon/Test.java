package com.antumbrastation.pokemon;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.concrete.FireBlast;
import com.antumbrastation.pokemon.battlestate.pokemoves.concrete.HiddenPower;
import com.antumbrastation.pokemon.battlestate.pokemoves.concrete.SludgeBomb;
import com.antumbrastation.pokemon.battlestate.pokemoves.concrete.SteamEruption;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        PokeMove testMove = new FireBlast(false);

        PokeMove newMove = testMove.duplicate();

        System.out.println(newMove.getName());
        System.out.println(newMove.getType());
        System.out.println(newMove.isPlayerOne());

        Pokemon volcanion = new Pokemon();
        Set<PokeMove> moves = new HashSet<>();

        moves.add(new FireBlast(false));
        moves.add(new SteamEruption(false));
        moves.add(new SludgeBomb(false));
        moves.add(new HiddenPower(false, Pokemon.PokeType.Electric));


        volcanion.setSpecies(Pokemon.PokeSpecies.Volcanion);
        volcanion.setMoves(moves);
        volcanion.setPlayerOne(false);
        volcanion.setItem(Pokemon.HoldItem.ChoiceSpecs);
        volcanion.setAbility(Pokemon.Ability.WaterAbsorb);

        System.out.println(volcanion.getSpecies() + " @ " + volcanion.getItem());
        System.out.println(volcanion.getAbility());
        System.out.println(volcanion.getMoves());

        Pokemon clone = volcanion.duplicate();

        System.out.println(clone.getSpecies() + " @ " + clone.getItem());
        System.out.println(clone.getAbility());
        System.out.println(clone.getMoves());
        System.out.println(clone.isPlayerOne());

    }

}
