package edu.chl.loc.view.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.loc.models.core.GameModel;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-28
 */
public interface IGameView {
    public SpriteBatch getSpriteBatch();
    public GameModel getGameModel();
}
