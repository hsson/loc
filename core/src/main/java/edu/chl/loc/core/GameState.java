package edu.chl.loc.core;

import edu.chl.loc.characters.Player;
import edu.chl.loc.map.GameMap;
import edu.chl.loc.utilities.Position2D;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-12
 */
public class GameState {

    // The starting position of the player
    public static final Position2D STARTING_POS = new Position2D(0, 0);

    private static final Player player = new Player(STARTING_POS);
    private GameMap gameMap;

    //TODO: come up with a better idea to save score
    private double hec;

    public GameState() {
        gameMap = new GameMap();
        hec = 0;
    }

    /**
     * Get the player of the game.
     *
     * @return The player
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * Get the game map containing all the tiles and NPCs.
     *
     * @return The game map
     */
    public GameMap getGameMap() {
        return this.gameMap;
    }

    public void addHec(double amount){
        this.hec += amount;
    }

    public double getHec(){
        return this.hec;
    }
}
