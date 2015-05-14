package edu.chl.loc.minigame.cortege.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.loc.minigame.cortege.model.CortegeModel;

/**
 * Created by maxim on 15-05-12.
 * @author Maxim Goretskyy
 */
public class CortegeController implements InputProcessor{

    private final CortegeModel model;
    public CortegeController(CortegeModel model){
        this.model = model;
    }
    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.LEFT:
                model.moveLeft();
                break;
            case Input.Keys.RIGHT:
                model.moveRight();
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        if(model.isPlaying()) { // only move according to mouse when game is still playing
            int tempX = i - 64 / 2;
            if (tempX >= 0 && tempX <= 1024 - 64)
                model.getToolBox().x = i - 64 / 2;
        }
        return true;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
