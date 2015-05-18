package edu.chl.loc.minigame;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

/**
 * @author Alexander Håkansson
 */
public interface IMinigame {
    public Screen getView();
    public InputProcessor getController();
    public void setListener(IMinigameListener listener);
    public double getScore();
    public void reset();
    public String getName();
}
