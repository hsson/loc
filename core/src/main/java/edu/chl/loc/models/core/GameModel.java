package edu.chl.loc.models.core;

import edu.chl.loc.models.characters.Player;
import edu.chl.loc.models.characters.npc.AbstractNPC;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.items.AbstractItem;
import edu.chl.loc.models.map.*;
import edu.chl.loc.models.utilities.Position2D;
import edu.chl.loc.models.utilities.Stats;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-12
 * revised by Maxim Goretskyy
 */
public class GameModel {

    // The starting position of the player
    public static final Position2D STARTING_POS = new Position2D(0, 0);


    private static Player player = new Player(STARTING_POS, Direction.NORTH, "Emil", Gender.MALE);
    private GameMap gameMap;

    private Stats stats;

    public GameModel() {
        gameMap = new GameMap();
        stats = new Stats();
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

    public void addPlayerStat(String key, double value){
        stats.addPlayerStat(key, value);
    }

    public double getPlayerStat(String key){
        return stats.getPlayerStat(key);
    }

    public void addHec(double amount){
        stats.addHec(amount);
    }

    public double getHec(){
        return stats.getHec();
    }

    /**
     * Moves character to the given position,
     * performs checks whether it's okay to walk there, and if player is standing on
     * a item/minigame tile, if so then it will perform appropriate actions
     *
     * @param nextPos Position you want the character to move to
     */
    public void moveCharacter(Position2D nextPos) {

        // Player will be on the ground layer
        ILayer groundLayer = new Layer("ground");

        if (!gameMap.isCollidable(groundLayer, nextPos)){
            player.move();
        }

        //If player stands on an item, do item's action
        ITile tempTile = getGameMap().getTile(groundLayer, player.getPosition());
        if (tempTile.hasItem()){ //todo discuss to use instanceof later or getClass, will need when we have minigameTile
            ItemTile itemTile = (ItemTile)tempTile; //safe to convert because only itemTile have items
            doItemAction(itemTile);

        }//if tile doesn't have an item do something else, check for minigame
    }

    /**
     * Picks up an item from this itemTile and performs an appropriate action of the item
     * @param itemTile you want to pick an item from
     */

    private void doItemAction(ItemTile itemTile){

        AbstractItem item;

        try {
            item = itemTile.takeItem();

            switch(item.getType()){
                case USE:
                    item.use(this);
                    break;
                case COLLECT:
                    // Add item to player's inventory
                    player.getInventory().addItem(item);
                    break;
                case QUEST:
                    // Add item to player's inventory
                    player.getInventory().addItem(item);
                    break;
            }
        } catch (EmptyTileException e) {
            // Do nothing
        }
    }
}
