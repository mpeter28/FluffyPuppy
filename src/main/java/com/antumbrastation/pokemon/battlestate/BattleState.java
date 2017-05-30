package com.antumbrastation.pokemon.battlestate;

import com.antumbrastation.pokemon.battlestate.playermoves.ChooseNextPokemon;
import com.antumbrastation.pokemon.battlestate.playermoves.DoNothing;
import com.antumbrastation.pokemon.battlestate.playermoves.SwitchPokemon;
import com.antumbrastation.pokemon.battlestate.pokemoves.PokeMove;
import com.antumbrastation.pokemon.battlestate.pokemoves.concrete.GigavoltHavoc;
import com.antumbrastation.pokemon.battlestate.pokemoves.concrete.Struggle;
import com.antumbrastation.pokemon.battlestate.zmoves.ZMove;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BattleState {

    private boolean battleTurn;

    // roster
    private Pokemon playerOneLead;
    private List<Pokemon> playerOneTeam;
    private Pokemon playerTwoLead;
    private List<Pokemon> playerTwoTeam;

    // weather and terrain
    private WeatherType weather;
    private int weatherTurns;
    private TerrainType terrain;
    private int terrainTurns;

    // hazards
    private int spikesForOne;
    private int spikesForTwo;
    private boolean stealthRocksForOne;
    private boolean stealthRocksForTwo;
    private int toxicSpikesForOne;
    private int toxicSpikesForTwo;
    private boolean stickyWebForOne;
    private boolean stickyWebForTwo;

    // rooms
    private RoomType roomType;
    private int roomTurns;

    // screens
    private int playerOneLightScreen;
    private int playerOneReflect;
    private int playerTwoLightScreen;
    private int playerTwoReflect;
    private int playerOneAuroraVeil;
    private int playerTwoAuroraVeil;

    public List<PlayerMove> playerOneMoves() {
        List<PlayerMove> validMoves = new LinkedList<>();

        if (battleTurn) {
            if (playerOneLead.getItem() == Pokemon.HoldItem.ElectriumZ)
                validMoves.add(new ZMove(new GigavoltHavoc(true), true));

            if (playerOneLead.getFirstMove() == null) {
                for (PokeMove move : playerOneLead.getMoves()) {
                    validMoves.add(move);
                }
            } else if (playerOneLead.getItem() == Pokemon.HoldItem.ChoiceSpecs) {
                for (PokeMove move : playerOneLead.getMoves()) {
                    if (move.getName().equals(playerOneLead.getFirstMove()))
                        validMoves.add(move);
                }
            } else {
                for (PokeMove move : playerOneLead.getMoves()) {
                    validMoves.add(move);
                }
            }

            if (validMoves.size() == 0)
                validMoves.add(new Struggle(true));


            if (!playerOneLead.isTrapped())
                for (int index = 0; index < playerOneTeam.size(); index++) {
                    if (playerOneTeam.get(index).getCurrentHp() > 0)
                        validMoves.add(new SwitchPokemon(index, true));
                }
        } else {
            if (playerOneLead == null || playerOneLead.getCurrentHp() <= 0) {
                for (int index = 0; index < playerOneTeam.size(); index++) {
                    if (playerOneTeam.get(index).getCurrentHp() > 0)
                        validMoves.add(new ChooseNextPokemon(index, true));
                }
            } else {
                validMoves.add(new DoNothing());
            }
        }

        return validMoves;
    }

    public List<PlayerMove> playerTwoMoves() {
        List<PlayerMove> validMoves = new LinkedList<>();

        if (battleTurn) {
            if (playerTwoLead.getItem() == Pokemon.HoldItem.ElectriumZ)
                validMoves.add(new ZMove(new GigavoltHavoc(false), false));

            if (playerTwoLead.getFirstMove() == null) {
                for (PokeMove move : playerTwoLead.getMoves()) {
                    validMoves.add(move);
                }
            } else if (playerTwoLead.getItem() == Pokemon.HoldItem.ChoiceSpecs) {
                for (PokeMove move : playerTwoLead.getMoves()) {
                    if (move.getName().equals(playerTwoLead.getFirstMove()))
                        validMoves.add(move);
                }
            } else {
                for (PokeMove move : playerTwoLead.getMoves()) {
                    validMoves.add(move);
                }
            }

            if (validMoves.size() == 0)
                validMoves.add(new Struggle(false));


            if (!playerTwoLead.isTrapped())
                for (int index = 0; index < playerTwoTeam.size(); index++) {
                    if (playerTwoTeam.get(index).getCurrentHp() > 0)
                        validMoves.add(new SwitchPokemon(index, false));
                }
        } else {
            if (playerTwoLead == null || playerTwoLead.getCurrentHp() <= 0) {
                for (int index = 0; index < playerTwoTeam.size(); index++) {
                    if (playerTwoTeam.get(index).getCurrentHp() > 0)
                        validMoves.add(new ChooseNextPokemon(index, false));
                }
            } else {
                validMoves.add(new DoNothing());
            }
        }

        return validMoves;
    }

    public BattleState resolveMoves(PlayerMove playerOne, PlayerMove playerTwo) {
        Priority priorityOne = playerOne.priority();
        Priority priorityTwo = playerTwo.priority();

        BattleState nextState = duplicate();

        if (priorityOne.compareTo(priorityTwo) > 0) {
            playerOne.use(nextState, true);
            playerTwo.use(nextState, false);
        } else if (priorityOne.compareTo(priorityTwo) < 0) {
            playerTwo.use(nextState, true);
            playerOne.use(nextState, false);
        } else if (battleTurn) {
            if (playerOneLead.getSpeed() > playerTwoLead.getSpeed()) {
                playerOne.use(nextState, true);
                playerTwo.use(nextState, false);
            } else if (playerOneLead.getSpeed() < playerTwoLead.getSpeed()) {
                playerTwo.use(nextState, true);
                playerOne.use(nextState, false);
            } else {
                int roll = (int) (Math.random() * 2);
                if (roll == 0) {
                    playerOne.use(nextState, true);
                    playerTwo.use(nextState, false);
                } else {
                    playerTwo.use(nextState, true);
                    playerOne.use(nextState, false);
                }
            }
        } else {
            int roll = (int) (Math.random() * 2);
            if (roll == 0) {
                playerOne.use(nextState, true);
                playerTwo.use(nextState, false);
            } else {
                playerTwo.use(nextState, true);
                playerOne.use(nextState, false);
            }
        }

        if (battleTurn) {
            nextState.endOfCombatTurn();
        }

        if (!battleTurn)
            nextState.setBattleTurn(true);
        else
            nextState.setBattleTurn(nextState.getPlayerOneLead().getCurrentHp() > 0
                    && nextState.getPlayerTwoLead().getCurrentHp() > 0);

        return nextState;
    }

    public void endOfCombatTurn() {
        playerOneLead.setFlinch(false);
        playerTwoLead.setFlinch(false);

        if (playerOneLead.getItem() == Pokemon.HoldItem.Leftovers ||
                playerOneLead.getItem() == Pokemon.HoldItem.BlackSludge) {
            int healing = playerOneLead.getMaxHp() / 16;

            playerOneLead.setCurrentHp(playerOneLead.getCurrentHp() + healing);

            if (playerOneLead.getCurrentHp() > playerOneLead.getMaxHp())
                playerOneLead.setCurrentHp(playerOneLead.getMaxHp());
        }

        if (playerTwoLead.getItem() == Pokemon.HoldItem.Leftovers ||
                playerTwoLead.getItem() == Pokemon.HoldItem.BlackSludge) {

            int healing = playerTwoLead.getMaxHp() / 16;

            playerTwoLead.setCurrentHp(playerTwoLead.getCurrentHp() + healing);

            if (playerTwoLead.getCurrentHp() > playerTwoLead.getMaxHp())
                playerTwoLead.setCurrentHp(playerTwoLead.getMaxHp());
        }

        if (playerOneLead.getMainStatus() == Pokemon.MainStatus.Burned)
            playerOneLead.setCurrentHp(playerOneLead.getCurrentHp() - playerOneLead.getMaxHp() / 16);
        else if (playerOneLead.getMainStatus() == Pokemon.MainStatus.Poison)
            playerOneLead.setCurrentHp(playerOneLead.getCurrentHp() - playerOneLead.getMaxHp() / 8);
        else if (playerOneLead.getMainStatus() == Pokemon.MainStatus.BadPoison) {
            playerOneLead.setMainStatusTurns(playerOneLead.getMainStatusTurns() + 1);

            int damage = playerOneLead.getMaxHp() / 16;
            damage *= playerOneLead.getMainStatusTurns();

            playerOneLead.setCurrentHp(playerOneLead.getCurrentHp() - damage);
        }

        if (playerTwoLead.getMainStatus() == Pokemon.MainStatus.Burned)
            playerTwoLead.setCurrentHp(playerTwoLead.getCurrentHp() - playerTwoLead.getMaxHp() / 16);
        else if (playerTwoLead.getMainStatus() == Pokemon.MainStatus.Poison)
            playerTwoLead.setCurrentHp(playerTwoLead.getCurrentHp() - playerTwoLead.getMaxHp() / 8);
        else if (playerTwoLead.getMainStatus() == Pokemon.MainStatus.BadPoison) {
            playerTwoLead.setMainStatusTurns(playerTwoLead.getMainStatusTurns() + 1);

            int damage = playerTwoLead.getMaxHp() / 16;
            damage *= playerTwoLead.getMainStatusTurns();

            playerTwoLead.setCurrentHp(playerTwoLead.getCurrentHp() - damage);
        }

        if (weather == WeatherType.Sandstorm) {
            if (playerOneLead.getPrimaryType() != Pokemon.PokeType.Ground &&
                    playerOneLead.getPrimaryType() != Pokemon.PokeType.Steel &&
                    playerOneLead.getSecondaryType() != Pokemon.PokeType.Ground &&
                    playerOneLead.getSecondaryType() != Pokemon.PokeType.Steel) {

                int damage = playerOneLead.getMaxHp() / 16;
                playerOneLead.setCurrentHp(playerOneLead.getCurrentHp() - damage);
            }

            if (playerTwoLead.getPrimaryType() != Pokemon.PokeType.Ground &&
                    playerTwoLead.getPrimaryType() != Pokemon.PokeType.Steel &&
                    playerTwoLead.getSecondaryType() != Pokemon.PokeType.Ground &&
                    playerTwoLead.getSecondaryType() != Pokemon.PokeType.Steel) {

                int damage = playerTwoLead.getMaxHp() / 16;
                playerTwoLead.setCurrentHp(playerTwoLead.getCurrentHp() - damage);
            }

            weatherTurns--;

            if (weatherTurns == 0)
                weather = WeatherType.None;
        }
    }

    //Checks if players have usable Pokemon
    public boolean playerOneHasPokemon() {
        return (playerOneLead != null && playerOneLead.getCurrentHp() > 0) || playerOneTeam.size() > 0;
    }

    public boolean playerTwoHasPokemon() {
        return (playerTwoLead != null && playerTwoLead.getCurrentHp() > 0) || playerTwoTeam.size() > 0;
    }

    public boolean done() {
        return !playerOneHasPokemon() || !playerTwoHasPokemon();
    }

    public double playerOneWin() {
        boolean playerOneIn = playerOneHasPokemon();
        boolean playerTwoIn = playerTwoHasPokemon();

        if (!playerOneIn && !playerTwoIn)
            return 0.5;
        else if (!playerTwoIn)
            return 1;
        else
            return 0;
    }

    // cloning
    public BattleState duplicate() {
        BattleState duplicate = new BattleState();

        duplicate.setBattleTurn(battleTurn);

        duplicate.setPlayerOneLead(playerOneLead == null ? null : playerOneLead.duplicate());
        duplicate.setPlayerOneTeam(playerOneTeam.stream()
                .map(Pokemon::duplicate).collect(Collectors.toList()));
        duplicate.setPlayerTwoLead(playerTwoLead == null ? null : playerTwoLead.duplicate());
        duplicate.setPlayerTwoTeam(playerTwoTeam.stream()
                .map(Pokemon::duplicate).collect(Collectors.toList()));

        duplicate.setWeather(weather);
        duplicate.setWeatherTurns(weatherTurns);
        duplicate.setTerrain(terrain);
        duplicate.setTerrainTurns(terrainTurns);

        duplicate.setSpikesForOne(spikesForOne);
        duplicate.setSpikesForTwo(spikesForTwo);
        duplicate.setStealthRocksForOne(stealthRocksForOne);
        duplicate.setStealthRocksForTwo(stealthRocksForTwo);
        duplicate.setToxicSpikesForOne(toxicSpikesForOne);
        duplicate.setToxicSpikesForTwo(toxicSpikesForTwo);
        duplicate.setStickyWebForOne(stickyWebForOne);
        duplicate.setStickyWebForTwo(stickyWebForTwo);

        duplicate.setRoomType(roomType);
        duplicate.setRoomTurns(roomTurns);

        duplicate.setPlayerOneLightScreen(playerOneLightScreen);
        duplicate.setPlayerOneReflect(playerOneReflect);
        duplicate.setPlayerTwoLightScreen(playerTwoLightScreen);
        duplicate.setPlayerTwoReflect(playerTwoReflect);
        duplicate.setPlayerOneAuroraVeil(playerOneAuroraVeil);
        duplicate.setPlayerTwoAuroraVeil(playerTwoAuroraVeil);

        return duplicate;
    }

    public enum WeatherType {
        Rain,
        Sun,
        Hail,
        Sandstorm,
        None
    }

    public enum TerrainType {
        Misty,
        Psychic,
        Grassy,
        Electric,
        None
    }

    public enum RoomType {
        None,
        TrickRoom
    }

    // fuck ton of getters and setters
    public boolean isBattleTurn() {
        return battleTurn;
    }

    public void setBattleTurn(boolean battleTurn) {
        this.battleTurn = battleTurn;
    }

    public Pokemon getPlayerOneLead() {
        return playerOneLead;
    }

    public void setPlayerOneLead(Pokemon playerOneLead) {
        this.playerOneLead = playerOneLead;
    }

    public List<Pokemon> getPlayerOneTeam() {
        return playerOneTeam;
    }

    public void setPlayerOneTeam(List<Pokemon> playerOneTeam) {
        this.playerOneTeam = playerOneTeam;
    }

    public Pokemon getPlayerTwoLead() {
        return playerTwoLead;
    }

    public void setPlayerTwoLead(Pokemon playerTwoLead) {
        this.playerTwoLead = playerTwoLead;
    }

    public List<Pokemon> getPlayerTwoTeam() {
        return playerTwoTeam;
    }

    public void setPlayerTwoTeam(List<Pokemon> playerTwoTeam) {
        this.playerTwoTeam = playerTwoTeam;
    }

    public WeatherType getWeather() {
        return weather;
    }

    public void setWeather(WeatherType weather) {
        this.weather = weather;
    }

    public int getWeatherTurns() {
        return weatherTurns;
    }

    public void setWeatherTurns(int weatherTurns) {
        this.weatherTurns = weatherTurns;
    }

    public TerrainType getTerrain() {
        return terrain;
    }

    public void setTerrain(TerrainType terrain) {
        this.terrain = terrain;
    }

    public int getTerrainTurns() {
        return terrainTurns;
    }

    public void setTerrainTurns(int terrainTurns) {
        this.terrainTurns = terrainTurns;
    }

    public int getSpikesForOne() {
        return spikesForOne;
    }

    public void setSpikesForOne(int spikesForOne) {
        this.spikesForOne = spikesForOne;
    }

    public int getSpikesForTwo() {
        return spikesForTwo;
    }

    public void setSpikesForTwo(int spikesForTwo) {
        this.spikesForTwo = spikesForTwo;
    }

    public boolean isStealthRocksForOne() {
        return stealthRocksForOne;
    }

    public void setStealthRocksForOne(boolean stealthRocksForOne) {
        this.stealthRocksForOne = stealthRocksForOne;
    }

    public boolean isStealthRocksForTwo() {
        return stealthRocksForTwo;
    }

    public void setStealthRocksForTwo(boolean stealthRocksForTwo) {
        this.stealthRocksForTwo = stealthRocksForTwo;
    }

    public int getToxicSpikesForOne() {
        return toxicSpikesForOne;
    }

    public void setToxicSpikesForOne(int toxicSpikesForOne) {
        this.toxicSpikesForOne = toxicSpikesForOne;
    }

    public int getToxicSpikesForTwo() {
        return toxicSpikesForTwo;
    }

    public void setToxicSpikesForTwo(int toxicSpikesForTwo) {
        this.toxicSpikesForTwo = toxicSpikesForTwo;
    }

    public boolean isStickyWebForOne() {
        return stickyWebForOne;
    }

    public void setStickyWebForOne(boolean stickyWebForOne) {
        this.stickyWebForOne = stickyWebForOne;
    }

    public boolean isStickyWebForTwo() {
        return stickyWebForTwo;
    }

    public void setStickyWebForTwo(boolean stickyWebForTwo) {
        this.stickyWebForTwo = stickyWebForTwo;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getRoomTurns() {
        return roomTurns;
    }

    public void setRoomTurns(int roomTurns) {
        this.roomTurns = roomTurns;
    }

    public int getPlayerOneLightScreen() {
        return playerOneLightScreen;
    }

    public void setPlayerOneLightScreen(int playerOneLightScreen) {
        this.playerOneLightScreen = playerOneLightScreen;
    }

    public int getPlayerOneReflect() {
        return playerOneReflect;
    }

    public void setPlayerOneReflect(int playerOneReflect) {
        this.playerOneReflect = playerOneReflect;
    }

    public int getPlayerTwoLightScreen() {
        return playerTwoLightScreen;
    }

    public void setPlayerTwoLightScreen(int playerTwoLightScreen) {
        this.playerTwoLightScreen = playerTwoLightScreen;
    }

    public int getPlayerTwoReflect() {
        return playerTwoReflect;
    }

    public void setPlayerTwoReflect(int playerTwoReflect) {
        this.playerTwoReflect = playerTwoReflect;
    }

    public int getPlayerOneAuroraVeil() {
        return playerOneAuroraVeil;
    }

    public void setPlayerOneAuroraVeil(int playerOneAuroraVeil) {
        this.playerOneAuroraVeil = playerOneAuroraVeil;
    }

    public int getPlayerTwoAuroraVeil() {
        return playerTwoAuroraVeil;
    }

    public void setPlayerTwoAuroraVeil(int playerTwoAuroraVeil) {
        this.playerTwoAuroraVeil = playerTwoAuroraVeil;
    }

    @Override
    public String toString() {
        return "BattleState{" +
                "battleTurn=" + battleTurn +
                ", playerOneLead=" + playerOneLead +
                ", playerOneTeam=" + playerOneTeam +
                ", playerTwoLead=" + playerTwoLead +
                ", playerTwoTeam=" + playerTwoTeam +
                ", weather=" + weather +
                ", weatherTurns=" + weatherTurns +
                ", terrain=" + terrain +
                ", terrainTurns=" + terrainTurns +
                ", spikesForOne=" + spikesForOne +
                ", spikesForTwo=" + spikesForTwo +
                ", stealthRocksForOne=" + stealthRocksForOne +
                ", stealthRocksForTwo=" + stealthRocksForTwo +
                ", toxicSpikesForOne=" + toxicSpikesForOne +
                ", toxicSpikesForTwo=" + toxicSpikesForTwo +
                ", stickyWebForOne=" + stickyWebForOne +
                ", stickyWebForTwo=" + stickyWebForTwo +
                ", roomType=" + roomType +
                ", roomTurns=" + roomTurns +
                ", playerOneLightScreen=" + playerOneLightScreen +
                ", playerOneReflect=" + playerOneReflect +
                ", playerTwoLightScreen=" + playerTwoLightScreen +
                ", playerTwoReflect=" + playerTwoReflect +
                ", playerOneAuroraVeil=" + playerOneAuroraVeil +
                ", playerTwoAuroraVeil=" + playerTwoAuroraVeil +
                '}';
    }
}
