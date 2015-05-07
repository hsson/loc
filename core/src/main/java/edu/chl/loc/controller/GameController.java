package edu.chl.loc.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.loc.models.characters.Player;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.models.map.GameMap;
import edu.chl.loc.models.map.Layer;
import edu.chl.loc.models.utilities.Position2D;

/**
 * @author Alexander Håkansson
 * @author Maxim Goretskyy
 * @version 0.1.0
 * @since 2015-04-30
 */
public class GameController implements InputProcessor {

    private final GameModel model;
    private Player player;
    private GameMap gameMap; //todo make gamemap static inside gamemodel?

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
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        chooseDirection(keycode);//sets direction of player depends what you click
        switch(keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
            case Input.Keys.DOWN:
            case Input.Keys.UP:
                model.moveCharacter(player.getNextPosition());//sends information about next position to model
                return true;
            //model will decide if it can move
        }
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

    public void chooseDirection(int keycode){
        switch(keycode){
            case  Input.Keys.LEFT:
                player.setDirection(Direction.WEST);
                break;

            case  Input.Keys.RIGHT:
                player.setDirection(Direction.EAST);
                break;

            case  Input.Keys.UP:
                player.setDirection(Direction.NORTH);
                break;

            case  Input.Keys.DOWN:
                player.setDirection(Direction.SOUTH);
                break;
        }
    }

}
