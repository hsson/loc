package edu.chl.loc.view.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.loc.models.core.GameModel;

/**
 * Top level class for the view of loc
 * @author Alexander Karlsson
 * @version 0.1
 */
public class GameView{
    private static SpriteBatch batch = new SpriteBatch();//Will be used by other views
    private GameModel model;
    private IView playerView;
    private IView gameMapView;

    /**
     * Basic constructor with all necessary values
     * @param model The loc gamemodel
     * @param playerView The view representing the player
     * @param gameMapView The view representing the map with associated NPCs and items
     */
    public GameView(GameModel model, IView playerView, IView gameMapView){
        this.model = model;
        this.playerView = playerView;
        this.gameMapView = gameMapView;
    }

    /**
     * Gives access to the spritebatch so other view classes render methods
     * can share spritebatch with GameView
     * @return The spritebatch
     */
    public static SpriteBatch getSpriteBatch() {
        return GameView.batch;
    }

    /**
     * Returns the gamemodel this view represents
     * @return The gamemodel
     */
    public GameModel getGameModel() {
        return this.model;
    }

    /**
     * Renders the world represented in the gamemodel this view represents
     */
    public void render() {
        GameView.batch.begin();
        gameMapView.render();
        playerView.render();
        GameView.batch.end();
    }
}
