package edu.chl.loc.models.core;

import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.models.characters.Player;
import edu.chl.loc.models.characters.npc.Dialog;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.items.AbstractItem;
import edu.chl.loc.models.map.*;
import edu.chl.loc.models.menu.ExitOption;
import edu.chl.loc.models.menu.GameMenu;
import edu.chl.loc.models.menu.StatsOption;
import edu.chl.loc.models.utilities.Position2D;

import java.util.Date;
import java.util.Map;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-12
 * revised by Maxim Goretskyy
 * Revised by Kevin Hoogendijk
 */
public class GameModel implements IGameWonListener {

    // Player default values
    public static final Position2D STARTING_POS = new Position2D(0, 0);
    public static final String PLAYER_DEFAULT_NAME = "Emil";
    public static final Gender PLAYER_DEFAULT_GENDER = Gender.MALE;
    public static final long STARTTIME = new Date().getTime();

    private static final String[] GAME_WON_TEXT = {"Du vann spelet... Hurra..."};
    private static final Dialog GAME_WON_DIALOG = new Dialog(GAME_WON_TEXT, false);

    private static Player player = new Player(STARTING_POS,
            Direction.NORTH,
            PLAYER_DEFAULT_NAME,
            PLAYER_DEFAULT_GENDER);

    private GameMap gameMap;
    private Stats stats;
    private StatsWindow statsWindow;

    private Dialog activeDialog;
    private boolean isDialogActive;
    private boolean isStatsActive;
    private String activeSpeakerName = "";

    private final GameMenu gameMenu;

    public GameModel() {
        this.gameMap = new GameMap();
        this.stats = new Stats();
        this.stats.addGameWonListener(this);
        this.statsWindow = new StatsWindow(stats);

        gameMenu = new GameMenu();
        setupGameMenu();
    }

    private void setupGameMenu() {
        gameMenu.addMenuOption(new StatsOption(this));
        gameMenu.addMenuOption(new ExitOption());
    }

    public GameMenu getGameMenu() {
        return this.gameMenu;
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

    public Object getPlayerStat(String key){
        return stats.getPlayerStat(key);
    }

    public Map<String, Object> getAllPlayerStats(){
        return stats.getPlayerStats();
    }

    public Stats getStats(){
        return this.stats;
    }

    public StatsWindow getStatsWindow() {
        return this.statsWindow;
    }

    public void addMinigameStat(IMinigame minigame){
        stats.addMinigameScore(minigame);
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
        if(isDialogActive){
            Integer timesSpoken = (Integer) stats.getPlayerStat("Antal samtal");
            if (timesSpoken == null) {
                timesSpoken = 1;
            } else {
                timesSpoken++;
            }
            stats.addPlayerStat("Antal samtal", timesSpoken);
        }
        this.isDialogActive = isDialogActive;
    }

    public boolean isStatsActive() {
        return isStatsActive;
    }

    public void setIsStatsActive(boolean isStatsActive) {
        stats.addPlayerStat("Sekunder spelat", (int) ((new Date().getTime() - STARTTIME) / 1000));
        this.isStatsActive = isStatsActive;
    }

    public String getActiveSpeakerName() {
        return activeSpeakerName;
    }

    public void setActiveSpeakerName(String activeSpeakerName) {
        this.activeSpeakerName = activeSpeakerName;
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
            incStepsTaken();
            // Pickup item if one exists
            pickupItem(itemLayer, nextPos);
        }
    }

    private void incStepsTaken() {

        Integer stepsTaken = (Integer) stats.getPlayerStat("Antal steg");
        if (stepsTaken == null) {
            stepsTaken = 1;
        } else {
            stepsTaken++;
        }

        stats.addPlayerStat("Antal steg", stepsTaken);
    }

    public void pickupItem(ILayer itemLayer, Position2D nextPlayerPos) {
        if (gameMap.layerExists(itemLayer) && gameMap.tileExists(itemLayer, nextPlayerPos)) {

            ITile tempTile = gameMap.getTile(itemLayer, nextPlayerPos);

            if (tempTile.hasItem()) { //todo discuss to use instanceof later or getClass, will need when we have minigameTile
                ItemTile itemTile = (ItemTile) tempTile; //safe to convert because only itemTile have items
                doItemAction(itemTile);
                stats.addPlayerStat("Plockade föremål", 1.0);
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
                case QUEST:
                    // Add item to player's inventory
                    player.getInventory().addItem(item);
                    break;
            }
        } catch (EmptyTileException e) {
            // Do nothing
        }
    }

    @Override
    public void gameWon() {
        setActiveSpeakerName("SPELET");
        setActiveDialog(GAME_WON_DIALOG);
        setIsDialogActive(true);
    }
}
