package edu.chl.loc.minigame.BeerChug.beerchugcontroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.loc.minigame.BeerChug.beerchugmodel.BottleBeerChug;
import edu.chl.loc.minigame.BeerChug.utilities.ShakeDirection;

/**
 * Controller to be used with the BottleBeerChug class
 * @author Alexander Karlsson
 * @version 1.1
 */
public class BottleBeerChugController implements InputProcessor{
    private BottleBeerChug model;
    private boolean countdownStarted = false;

    public BottleBeerChugController(BottleBeerChug model){
        this.model = model;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            if(!countdownStarted){
                model.startCountdown();
                countdownStarted = true;
            }else if(!model.isFirstShakeDone()) {
                model.firstShake();
            }else{
                model.endChug();
            }
        } else if (keycode == Input.Keys.LEFT) {
            model.shake(ShakeDirection.LEFT);
        } else if (keycode == Input.Keys.RIGHT) {
            model.shake(ShakeDirection.RIGHT);
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
}
