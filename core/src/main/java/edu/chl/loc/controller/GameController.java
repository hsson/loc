package edu.chl.loc.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.loc.models.characters.Player;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.core.GameModel;

/**
 * @author Alexander Håkansson
 * @author Maxim Goretskyy
 * @version 0.1.0
 * @since 2015-04-30
 */
public class GameController implements InputProcessor {

    private final GameModel model;
    private Player player;

    public GameController(GameModel model) {
        this.model = model;
        this.player = model.getPlayer();

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
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
        player.move();
        updateViews();
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

    //Todo update views in this method
    public void updateViews(){

    }
}
