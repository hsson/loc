package edu.chl.loc.minigame.caps.capscontroller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.loc.minigame.caps.capsmodel.CapsGameModel;

/**
 * Controller class for the caps minigame
 */
public class CapsGameController implements InputProcessor {
    private CapsGameModel model;

    public CapsGameController(CapsGameModel model){
        this.model = model;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.SPACE && !model.isCapThrown()){
            model.throwCap(3.0f);
            return true;
        }
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
        return keyDown(Input.Keys.SPACE);
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
