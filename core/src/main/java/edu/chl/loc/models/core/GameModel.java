package edu.chl.loc.models.core;

import edu.chl.loc.models.characters.Player;
import edu.chl.loc.models.characters.npc.AbstractNPC;
import edu.chl.loc.models.characters.npc.Dialog;
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

    // Player default values
    public static final Position2D STARTING_POS = new Position2D(0, 0);
    public static final String PLAYER_DEFAULT_NAME = "Emil";
    public static final Gender PLAYER_DEFAULT_GENDER = Gender.MALE;

    private static Player player = new Player(STARTING_POS,
            Direction.NORTH,
            PLAYER_DEFAULT_NAME,
            PLAYER_DEFAULT_GENDER);

    private GameMap gameMap;
    private Stats stats;

    private Dialog activeDialog;
    private boolean isDialogActive;

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

    public void setActiveDialog(Dialog dialog){
        this.activeDialog = dialog;
    }

    public Dialog getActiveDialog(){
        return this.activeDialog;
    }

    public boolean isDialogActive(){
        return this.isDialogActive;
    }

    public void setIsDialogActive(boolean isDialogActive){
        this.isDialogActive = isDialogActive;
    }

    /**
     * Moves character to the given position,
     * performs checks whether it's okay to walk there, and if player is standing on
     * a item/minigame tile, if so then it will perform appropriate actions
     *
     * @param nextPos Position you want the character to move to
     */
    public void moveCharacter(Position2D nextPos) {

        ILayer collisionLayer = null;
        ILayer groundLayer = null;
        ILayer itemLayer = null;

        for (ILayer layer : gameMap.getLayers()) {
            if (layer.getName().equals("ground")) {
                groundLayer = layer;
            } else if (layer.getName().equals("collision")) {
                collisionLayer = layer;
            } else if (layer.getName().equals("items")) {
                itemLayer = layer;
            }
        }

        if (shouldPlayerMove(collisionLayer, groundLayer, nextPos)) {
            player.move();
            // Pickup item if one exists
            pickupItem(itemLayer, nextPos);
        }
    }

    public void pickupItem(ILayer itemLayer, Position2D nextPlayerPos) {
        if (gameMap.layerExists(itemLayer) && gameMap.tileExists(itemLayer, nextPlayerPos)) {

            ITile tempTile = gameMap.getTile(itemLayer, nextPlayerPos);

            if (tempTile.hasItem()) { //todo discuss to use instanceof later or getClass, will need when we have minigameTile
                ItemTile itemTile = (ItemTile) tempTile; //safe to convert because only itemTile have items
                doItemAction(itemTile);
            }
        }
    }

    public boolean shouldPlayerMove(ILayer collisionLayer, ILayer groundLayer, Position2D playerNextPos) {
        // Check collision with map
        if (gameMap.layerExists(collisionLayer) &&
                !(gameMap.tileExists(collisionLayer, playerNextPos) &&
                        gameMap.isCollidable(collisionLayer, playerNextPos))) {
            // Check so player don't move outside map or collide with NPC
            if (gameMap.tileExists(groundLayer, playerNextPos) && !gameMap.npcExisitsAtPosition(playerNextPos)) {
                // Safe to move
                return true;
            }
        }
        // Player can't move
        return false;
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
