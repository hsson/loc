package edu.chl.loc.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.loc.models.characters.Player;
import edu.chl.loc.models.characters.npc.AbstractNPC;
import edu.chl.loc.models.characters.npc.Dialog;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.core.IGameModel;
import edu.chl.loc.models.core.StatsWindow;
import edu.chl.loc.models.map.GameMap;
import edu.chl.loc.models.menu.GameMenu;
import edu.chl.loc.view.music.Playlist;

/**
 * @author Alexander Håkansson
 * @author Maxim Goretskyy
 * @author Kevin Hoogendijk
 * @version 0.1.0
 * @since 2015-04-30
 */
public class GameController implements InputProcessor {

    private final IGameModel model;
    private Player player;
    private GameMap gameMap;
    private static final String[] NOTHING_TO_INTERACT_WITH_STRING = {"Är du go i huvvet eller?", "Här finns det inget du kan prata med"};
    private static final Dialog NOTHING_TO_INTERACT_WITH_DIALOG = new Dialog(NOTHING_TO_INTERACT_WITH_STRING, false);

    /**
     * Constructs a standard controller for a Loc game model
     * @param model The model you want to control
     */
    public GameController(IGameModel model) {
        this.model = model;
        this.player = model.getPlayer();
        this.gameMap = model.getGameMap();
    }

    /**
     * Updates the Loc game model based on keyboard inputs
     * @param keycode The key pressed
     * @return True if the input was processed, false if not
     */
    @Override
    public boolean keyDown(int keycode) {// assuming smooth movement will be here?

        if (keycode == Input.Keys.E) {
            Playlist.getInstance().next();
        }

        if (keycode == Input.Keys.F) {
            boolean fullscreen = Gdx.graphics.isFullscreen();
            Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width,
                    Gdx.graphics.getDesktopDisplayMode().height, !fullscreen);
        }

        if(model.getGameMenu().isMenuOpen()) {
            return handleMenu(keycode);
        } else if (model.isDialogActive()) {
            return handleDialog(keycode);
        } else if (model.isStatsActive()) {
            return handleStats(keycode);
        } else {
            return handleCharacter(keycode);
        }
    }


    @Override
    public boolean keyUp(int keycode) {
        return false;//Not used
    }

    @Override
    public boolean keyTyped(char character) {
        return false;//Not used
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;//Not used
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;//Not used
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;//Not used
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;//Not used
    }

    @Override
    public boolean scrolled(int amount) {
        return false;//Not used
    }

    /**
     * Processes the inputs while a dialog is active
     * @param keycode The key pressed
     * @return Whether or not the key press was handled
     */
    public boolean handleDialog(int keycode) {
        Dialog dialog = model.getActiveDialog();
        if(dialog.isLastString()){
            switch (keycode) {
                case Input.Keys.SPACE:
                case Input.Keys.ENTER:
                    model.setIsDialogActive(false);
                    dialog.resetDialog();
                    if(dialog.getOptionSelected()){
                        try {
                            AbstractNPC npc = gameMap.getNPCAtPosition(player.getNextPosition());
                            if(model.getActiveDialog().equals(npc.getDialog()))
                                npc.doAction();
                        }catch(IllegalArgumentException e){
                            //Do nothing if no npc is present
                        }
                    }
                    return true;
                case Input.Keys.UP:
                    dialog.setOptionSelected(true);
                    return true;
                case Input.Keys.DOWN:
                    dialog.setOptionSelected(false);
                    return true;
                default:
                    return false;
            }
        } else {
            switch (keycode) {
                case Input.Keys.SPACE:
                case Input.Keys.ENTER:
                    dialog.setNextString();
                    return true;
                default:
                    return false;
            }
        }
    }

    /**
     * Handles input while the mune is active
     * @param keycode The key pressed
     * @return Whether or not the input was processed
     */
    private boolean handleMenu(int keycode) {
        GameMenu menu = model.getGameMenu();
        switch (keycode) {
            case Input.Keys.UP:
                menu.decSelection();
                return true;
            case Input.Keys.DOWN:
                menu.incSelection();
                return true;
            case Input.Keys.ENTER:
                menu.getSelectedOption().choose();
                return true;
            case Input.Keys.ESCAPE:
                menu.toggleOpen();
                return true;
            default:
                return false;
        }
    }

    /**
     * Processes the inputs while the stats view is active
     * @param keycode The key pressed
     * @return Whether or not the key press was handled
     */
    public boolean handleStats(int keycode){
        StatsWindow statsWindow = model.getStatsWindow();
        switch (keycode){
            case Input.Keys.ESCAPE:
            case Input.Keys.ENTER:
            case Input.Keys.SPACE:
                model.setIsStatsActive(false);
                return true;
            case Input.Keys.W:
            case Input.Keys.UP:
                statsWindow.scrollUp();
                return true;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                statsWindow.scrollDown();
                return true;
            default:
                return false;
        }
    }

    /**
     * Processes the inputs and moves the character
     * @param keycode The key pressed
     * @return Whether or not the key press was handled
     */
    public boolean handleCharacter(int keycode){
        GameMenu menu = model.getGameMenu();
        chooseDirection(keycode);
        switch(keycode) {
            case Input.Keys.A:
            case Input.Keys.D:
            case Input.Keys.S:
            case Input.Keys.W:
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
            case Input.Keys.DOWN:
            case Input.Keys.UP:
                model.moveCharacter(player.getNextPosition());//sends information about next position to model
                return true;
            case Input.Keys.SPACE:
            case Input.Keys.ENTER:
                try{
                    AbstractNPC npc = gameMap.getNPCAtPosition(player.getNextPosition());
                    npc.setDirection(player.getDirection().getOpposite());
                    if(!model.isDialogActive()) {
                        model.setActiveDialog(npc.getDialog());
                        model.setActiveSpeakerName(npc.getName());
                        model.setIsDialogActive(true);
                    }
                }catch(IllegalArgumentException e){
                    model.setActiveDialog(NOTHING_TO_INTERACT_WITH_DIALOG);
                    model.setActiveSpeakerName("");
                    model.setIsDialogActive(true);
                }
                return true;
            case Input.Keys.ESCAPE:
                menu.toggleOpen();
                return true;
            default:
                return false;
        }
    }

    private void addTimesTurned() {
        Double timesTurned = (Double) model.getStats().getPlayerStat("Antal svängningar");
        if (timesTurned == null) {
            timesTurned = 1.0;
        } else {
            timesTurned++;
        }
        model.addPlayerStat("Antal svängningar", timesTurned);
    }

    /**
     * Gives the character his proper direction based on key press
     * @param keycode The key pressed
     */
    public void chooseDirection(int keycode){
        switch(keycode){
            case  Input.Keys.A:
            case  Input.Keys.LEFT:
                if (player.getDirection() != Direction.WEST){ addTimesTurned(); }
                player.setDirection(Direction.WEST);
                break;
            case  Input.Keys.D:
            case  Input.Keys.RIGHT:
                if(player.getDirection()!=Direction.EAST){addTimesTurned(); }
                player.setDirection(Direction.EAST);
                break;
            case  Input.Keys.W:
            case  Input.Keys.UP:
                if(player.getDirection()!=Direction.NORTH){ addTimesTurned(); }
                player.setDirection(Direction.NORTH);
                break;
            case  Input.Keys.S:
            case  Input.Keys.DOWN:
                if(player.getDirection()!=Direction.SOUTH){ addTimesTurned(); }
                player.setDirection(Direction.SOUTH);
                break;
            default:
                //Do nothing if invalid key is entered
                break;
        }
    }
}
