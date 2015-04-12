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

    private final Player player;
    private GameMap gameMap;

    public GameState() {
        player = new Player(STARTING_POS);
        gameMap = new GameMap();
    }

    /**
     * Get the player of the game.
     *
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Get the game map containing all the tiles and NPCs.
     *
     * @return The game map
     */
    public GameMap getGameMap() {
        return this.gameMap;
    }
}
