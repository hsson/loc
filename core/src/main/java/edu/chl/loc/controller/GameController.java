package edu.chl.loc.controller;

import com.badlogic.gdx.InputProcessor;
import edu.chl.loc.models.core.GameModel;

/**
 * @author Alexander Håkansson
 * @version 0.1.0
 * @since 2015-04-30
 */
public class GameController implements InputProcessor {

    private final GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
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
}
