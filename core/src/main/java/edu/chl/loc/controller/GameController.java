package edu.chl.loc.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.loc.models.characters.Player;
import edu.chl.loc.models.characters.npc.AbstractNPC;
import edu.chl.loc.models.characters.npc.Dialog;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.models.map.GameMap;
import edu.chl.loc.models.map.Layer;
import edu.chl.loc.models.menu.GameMenu;
import edu.chl.loc.models.utilities.Position2D;

/**
 * @author Alexander Håkansson
 * @author Maxim Goretskyy
 * @author Kevin Hoogendijk
 * @version 0.1.0
 * @since 2015-04-30
 */
public class GameController implements InputProcessor {

    private final GameModel model;
    private Player player;
    private GameMap gameMap; //todo make gamemap static inside gamemodel?
    private static final String[] NOTHING_TO_INTERACT_WITH_STRING = {"Sorry but there is nothing here to interact with", "Or you are just stupid"};
    private static final Dialog NOTHING_TO_INTERACT_WITH_DIALOG = new Dialog(NOTHING_TO_INTERACT_WITH_STRING, false);

    /**
     *
     * @param model The model you want to control
     */
    public GameController(GameModel model) {
        this.model = model;
        this.player = model.getPlayer();
        this.gameMap = model.getGameMap();
    }

    @Override
    public boolean keyDown(int keycode) {// assuming smooth movement will be here?
        handleMenu(keycode);
        if (!model.getGameMenu().isMenuOpen()) {
            if (model.isDialogActive()) {
                handleDialog(keycode);
            } else {
                moveCharacter(keycode);
            }
        }
        return true;
    }


    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void handleDialog(int keycode) {
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
                            npc.doAction();
                        }catch(IllegalArgumentException e){
                            //Do nothing if no npc is present
                        }
                    }
                    break;
                case Input.Keys.UP:
                    dialog.setOptionSelected(true);
                    break;
                case Input.Keys.DOWN:
                    dialog.setOptionSelected(false);
                    break;
            }
        } else {
            switch (keycode) {
                case Input.Keys.SPACE:
                case Input.Keys.ENTER:
                    dialog.setNextString();
                    break;
            }
        }
    }

    private void handleMenu(int keycode) {
        GameMenu menu = model.getGameMenu();
        if (keycode == Input.Keys.ESCAPE) {
            menu.toggleOpen();
        } else if (menu.isMenuOpen()) {
            switch (keycode) {
                case Input.Keys.UP:
                    menu.decSelection();
                    break;
                case Input.Keys.DOWN:
                    menu.incSelection();
                    break;
                case Input.Keys.ENTER:
                    menu.getSelectedOption().choose();
                    break;
            }
        }
    }

    public void moveCharacter(int keycode){
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
                break;
            case Input.Keys.SPACE:
            case Input.Keys.ENTER:
                try{
                    AbstractNPC npc = gameMap.getNPCAtPosition(player.getNextPosition());
                    npc.setDirection(player.getDirection().getOpposite());
                    model.setActiveDialog(npc.getDialog());
                    model.setIsDialogActive(true);
                }catch(IllegalArgumentException e){
                    model.setActiveDialog(NOTHING_TO_INTERACT_WITH_DIALOG);
                    model.setIsDialogActive(true);
                }

                break;
        }
    }

    public void chooseDirection(int keycode){
        switch(keycode){
            case  Input.Keys.A:
            case  Input.Keys.LEFT:
                player.setDirection(Direction.WEST);
                break;
            case  Input.Keys.D:
            case  Input.Keys.RIGHT:
                player.setDirection(Direction.EAST);
                break;
            case  Input.Keys.W:
            case  Input.Keys.UP:
                player.setDirection(Direction.NORTH);
                break;
            case  Input.Keys.S:
            case  Input.Keys.DOWN:
                player.setDirection(Direction.SOUTH);
                break;
        }
    }
}
