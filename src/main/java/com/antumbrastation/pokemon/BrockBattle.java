package com.antumbrastation.pokemon;

import com.antumbrastation.pokemon.battlestate.BattleState;
import com.antumbrastation.pokemon.battlestate.PlayerMove;
import com.antumbrastation.pokemon.battlestate.Pokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.concrete.*;
import com.antumbrastation.pokemon.mcts.DecisionNode;
import com.antumbrastation.pokemon.mcts.DecisionNode2;

import java.util.*;

public class BrockBattle {

    public static void main(String[] args) {
        BattleState startState = new BattleState();
        startState.setBattleTurn(false);

        // Field stuff
        startState.setRoomType(BattleState.RoomType.None);
        startState.setWeather(BattleState.WeatherType.None);
        startState.setTerrain(BattleState.TerrainType.None);

        // Brock Team
        List<Pokemon> brockTeam = new LinkedList<>();

        Pokemon infernape = new Pokemon();
        brockTeam.add(infernape);
        infernape.setPlayerOne(true);
        infernape.setSpecies(Pokemon.PokeSpecies.Infernape);
        infernape.setPrimaryType(Pokemon.PokeType.Fire);
        infernape.setSecondaryType(Pokemon.PokeType.Fighting);
        infernape.setAbility(Pokemon.Ability.Blaze);
        infernape.setItem(Pokemon.HoldItem.LifeOrb);
        infernape.setMainStatus(Pokemon.MainStatus.Fine);
        infernape.setPokeWeight(55.0);

        Set<PokeMove> infernapeMoves = new HashSet<>();
        infernapeMoves.add(new FireBlast(true));
        infernapeMoves.add(new CloseCombat(true));
        infernapeMoves.add(new GrassKnot(true));
        infernapeMoves.add(new GunkShot(true));
        infernape.setMoves(infernapeMoves);

        infernape.setMaxHp(293);
        infernape.setCurrentHp(293);
        infernape.setAttack(286);
        infernape.setDefense(178);
        infernape.setSpecialAttack(266);
        infernape.setSpecialDefense(160);
        infernape.setSpeed(346);

        Pokemon hippowdon = new Pokemon();
        brockTeam.add(hippowdon);
        hippowdon.setPlayerOne(true);
        hippowdon.setSpecies(Pokemon.PokeSpecies.Hippowdon);
        hippowdon.setPrimaryType(Pokemon.PokeType.Ground);
        hippowdon.setSecondaryType(Pokemon.PokeType.None);
        hippowdon.setAbility(Pokemon.Ability.SandStream);
        hippowdon.setItem(Pokemon.HoldItem.Leftovers);
        hippowdon.setMainStatus(Pokemon.MainStatus.Fine);
        hippowdon.setPokeWeight(300);

        Set<PokeMove> hippowdonMoves = new HashSet<>();
        hippowdonMoves.add(new StealthRock(true));
        hippowdonMoves.add(new EarthQuake(true));
        hippowdonMoves.add(new SlackOff(true));
        hippowdonMoves.add(new Toxic(true));
        hippowdon.setMoves(hippowdonMoves);

        hippowdon.setMaxHp(420);
        hippowdon.setCurrentHp(420);
        hippowdon.setAttack(260);
        hippowdon.setDefense(321);
        hippowdon.setSpecialAttack(154);
        hippowdon.setSpecialDefense(224);
        hippowdon.setSpeed(130);

        Pokemon amoongus = new Pokemon();
        brockTeam.add(amoongus);
        amoongus.setPlayerOne(true);
        amoongus.setSpecies(Pokemon.PokeSpecies.Amoonguss);
        amoongus.setPrimaryType(Pokemon.PokeType.Grass);
        amoongus.setSecondaryType(Pokemon.PokeType.Poison);
        amoongus.setAbility(Pokemon.Ability.Regenerator);
        amoongus.setItem(Pokemon.HoldItem.BlackSludge);
        amoongus.setMainStatus(Pokemon.MainStatus.Fine);

        Set<PokeMove> amoongusMoves = new HashSet<>();
        amoongusMoves.add(new Spore(true));
        amoongusMoves.add(new GigaDrain(true));
        amoongusMoves.add(new HiddenPower(true, Pokemon.PokeType.Fire));
        amoongusMoves.add(new ClearSmog(true));
        amoongus.setMoves(amoongusMoves);

        amoongus.setMaxHp(432);
        amoongus.setCurrentHp(432);
        amoongus.setAttack(184);
        amoongus.setDefense(222);
        amoongus.setSpecialAttack(205);
        amoongus.setSpecialDefense(235);
        amoongus.setSpeed(95);

        Pokemon starmie = new Pokemon();
        brockTeam.add(starmie);
        starmie.setPlayerOne(true);
        starmie.setSpecies(Pokemon.PokeSpecies.Starmie);
        starmie.setPrimaryType(Pokemon.PokeType.Water);
        starmie.setSecondaryType(Pokemon.PokeType.Psychic);
        starmie.setAbility(Pokemon.Ability.Analytic);
        starmie.setItem(Pokemon.HoldItem.LifeOrb);
        starmie.setMainStatus(Pokemon.MainStatus.Fine);

        Set<PokeMove> starmieMoves = new HashSet<>();
        starmieMoves.add(new HydroPump(true));
        starmieMoves.add(new IceBeam(true));
        starmieMoves.add(new ThunderBolt(true));
        starmieMoves.add(new RapidSpin(true));
        starmie.setMoves(starmieMoves);

        starmie.setMaxHp(261);
        starmie.setCurrentHp(261);
        starmie.setAttack(167);
        starmie.setDefense(207);
        starmie.setSpecialAttack(299);
        starmie.setSpecialDefense(206);
        starmie.setSpeed(361);

        startState.setPlayerOneTeam(brockTeam);

        // AI Team
        List<Pokemon> aiTeam = new LinkedList<>();

        Pokemon decidueye = new Pokemon();
        aiTeam.add(decidueye);
        decidueye.setSpecies(Pokemon.PokeSpecies.Decidueye);
        decidueye.setPrimaryType(Pokemon.PokeType.Grass);
        decidueye.setSecondaryType(Pokemon.PokeType.Ghost);
        decidueye.setAbility(Pokemon.Ability.Overgrow);
        decidueye.setItem(Pokemon.HoldItem.Leftovers);
        decidueye.setMainStatus(Pokemon.MainStatus.Fine);
        decidueye.setPokeWeight(36.6);

        Set<PokeMove> decidueyeMoves = new HashSet<>();
        decidueyeMoves.add(new SwordsDance(false));
        decidueyeMoves.add(new SpiritShackle(false));
        decidueyeMoves.add(new LeafBlade(false));
        decidueyeMoves.add(new Roost(false));
        decidueye.setMoves(decidueyeMoves);

        decidueye.setMaxHp(297);
        decidueye.setCurrentHp(297);
        decidueye.setAttack(313);
        decidueye.setDefense(186);
        decidueye.setSpecialAttack(313);
        decidueye.setSpecialDefense(186);
        decidueye.setSpeed(262);

        Pokemon cobalion = new Pokemon();
        aiTeam.add(cobalion);
        cobalion.setSpecies(Pokemon.PokeSpecies.Cobalion);
        cobalion.setPrimaryType(Pokemon.PokeType.Steel);
        cobalion.setSecondaryType(Pokemon.PokeType.Fighting);
        cobalion.setAbility(Pokemon.Ability.Justified);
        cobalion.setItem(Pokemon.HoldItem.ShucaBerry);
        cobalion.setMainStatus(Pokemon.MainStatus.Fine);
        cobalion.setPokeWeight(250);

        Set<PokeMove> cobalionMoves = new HashSet<>();
        cobalionMoves.add(new SwordsDance(false));
        cobalionMoves.add(new RockPolish(false));
        cobalionMoves.add(new IronHead(false));
        cobalionMoves.add(new CloseCombat(false));
        cobalion.setMoves(cobalionMoves);

        cobalion.setMaxHp(323);
        cobalion.setCurrentHp(323);
        cobalion.setAttack(297);
        cobalion.setDefense(294);
        cobalion.setSpecialAttack(194);
        cobalion.setSpecialDefense(181);
        cobalion.setSpeed(346);

        Pokemon volcanion = new Pokemon();
        aiTeam.add(volcanion);
        volcanion.setSpecies(Pokemon.PokeSpecies.Volcanion);
        volcanion.setPrimaryType(Pokemon.PokeType.Fire);
        volcanion.setSecondaryType(Pokemon.PokeType.Water);
        volcanion.setAbility(Pokemon.Ability.WaterAbsorb);
        volcanion.setItem(Pokemon.HoldItem.ChoiceSpecs);
        volcanion.setMainStatus(Pokemon.MainStatus.Fine);
        volcanion.setPokeWeight(195);

        Set<PokeMove> volcanionMoves = new HashSet<>();
        volcanionMoves.add(new SteamEruption(false));
        volcanionMoves.add(new FireBlast(false));
        volcanionMoves.add(new SludgeBomb(false));
        volcanionMoves.add(new HiddenPower(false, Pokemon.PokeType.Electric));
        volcanion.setMoves(volcanionMoves);

        volcanion.setMaxHp(301);
        volcanion.setCurrentHp(301);
        volcanion.setAttack(230);
        volcanion.setDefense(276);
        volcanion.setSpecialAttack(393);
        volcanion.setSpecialDefense(217);
        volcanion.setSpeed(239);

        Pokemon latias = new Pokemon();
        aiTeam.add(latias);
        latias.setSpecies(Pokemon.PokeSpecies.Latias);
        latias.setPrimaryType(Pokemon.PokeType.Psychic);
        latias.setSecondaryType(Pokemon.PokeType.Dragon);
        latias.setAbility(Pokemon.Ability.Levitate);
        latias.setItem(Pokemon.HoldItem.ElectriumZ);
        latias.setMainStatus(Pokemon.MainStatus.Fine);
        latias.setPokeWeight(40);

        Set<PokeMove> latiasMoves = new HashSet<>();
        latiasMoves.add(new Psyshock(false));
        latiasMoves.add(new DracoMeteor(false));
        latiasMoves.add(new ThunderBolt(false));
        latiasMoves.add(new IceBeam(false));
        latias.setMoves(latiasMoves);

        latias.setMaxHp(301);
        latias.setCurrentHp(301);
        latias.setAttack(148);
        latias.setDefense(216);
        latias.setSpecialAttack(319);
        latias.setSpecialDefense(296);
        latias.setSpeed(350);

        startState.setPlayerTwoTeam(aiTeam);

        // PLAY!

        Scanner scanner = new Scanner(System.in);
        BattleState state = startState;
        int turn = 0;

        while (!state.done()) {
            System.out.println("\n========== Turn " + ++turn + " ==========\n");
            System.out.println(state.toString().replaceAll(
                    "Pokemon\\{isPlayerOne=true", "" + (char) 27 + "[32mPokemon{isPlayerOne=true" + (char) 27 + "[39m"
            ).replaceAll("Pokemon\\{isPlayerOne=false", "" + (char) 27 + "[31mPokemon{isPlayerOne=false" + (char) 27 + "[39m")
                    .replaceAll("currentHp", "" + (char) 27 + "[36mcurrentHp" + (char) 27 + "[39m")
                    .replaceAll("mainStatus", "" + (char) 27 + "[33mmainStatus" + (char) 27 + "[39m")
                    .replaceAll("species", "" + (char) 27 + "[35mspecies" + (char) 27 + "[39m")
                    + "\n\n");

            List<PlayerMove> moves = state.playerOneMoves();
            for (int i = 0; i < moves.size(); i++) {
                System.out.println("\t[" + i + "] " + moves.get(i).toString() + "\n");
            }
            System.out.print("\t> ");

            int choice = scanner.nextInt();
            PlayerMove humanChosenMove = moves.get(choice);

            // AI
            PlayerMove computerChosenMove;
            if (state.playerTwoMoves().size() <= 1) {
                computerChosenMove = state.playerTwoMoves().get(0);
            } else {
                DecisionNode2 root = new DecisionNode2(state);
                for (int i = 0; i < 40000; i++) {
                    root.selectDownwards();
                }
                computerChosenMove = root.chosenPlayerTwoMove();
                System.out.print("\n\tHuman Results : ");
                for (double visits : root.leftResults())
                    System.out.print(visits + " ");
                System.out.print("\n\tFluffy Puppy Results : ");
                for (double visits : root.rightResults())
                    System.out.print(visits + " ");

                System.out.println("\n\tPre move win chance : " + (root.getWins() / root.getVisits()));
            }
            System.out.println("\t" + (char) 27 + "[33mFluffyPuppy chose " + computerChosenMove.toString() + (char) 27 + "[39m");

            state = state.resolveMoves(humanChosenMove, computerChosenMove);
        }
    }
}
