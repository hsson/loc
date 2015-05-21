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
                model.setIsMoving(true);
                model.moveLeft();
                return true;
            case Input.Keys.RIGHT:
                model.setIsMoving(true);
                model.moveRight();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean keyUp(int i) {
        model.setIsMoving(false);
        return true;
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
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
