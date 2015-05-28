package edu.chl.loc.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Alexander Håkansson
 * @version 1.1.1
 * @since 2015-04-28
 * Revised by Alexander Karlsson
 */
public interface IView {
    public void render(float delta, SpriteBatch batch);
    public void dispose();
}
