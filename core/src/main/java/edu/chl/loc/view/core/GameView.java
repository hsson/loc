package edu.chl.loc.view.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.view.characters.CharacterView;
import edu.chl.loc.view.map.GameMapView;

/**
 * Top level class for the view of loc
 * @author Alexander Karlsson
 * @version 0.5.0
 *
 * Revised by Alexander Håkansson
 */
public class GameView implements Screen{

    public static final int GRID_SIZE = 32; // Size in pixels of each cell in grid
    public static final int RES_X = 1280; // Resolution of game in x-axis
    public static final int RES_Y = 720; // Resolution of game in y-axis

    public static final Texture PLAYER_TEXTURE = new Texture(Gdx.files.internal("player-sheet.png"));

    private static SpriteBatch batch = new SpriteBatch();//Will be used by other views
    private GameModel model;
    private IView playerView;
    private IView gameMapView;

    private Viewport viewport;
    private OrthographicCamera camera;

    /**
     * Basic constructor with all necessary values
     * @param model The loc gamemodel
     */
    public GameView(GameModel model){
        this.model = model;
        this.playerView = new CharacterView(GameModel.getPlayer(), PLAYER_TEXTURE);
        this.gameMapView = new GameMapView(this);

        // Setup camera and viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(RES_X, RES_Y, camera);
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
     * @param v A float
     */
    @Override
    public void render(float v) {

        camera.update();

        GameView.batch.begin();
        gameMapView.render();
        playerView.render();
        GameView.batch.end();
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {
        //TODO implement this shit
    }

    @Override
    public void hide() {
        //TODO implement this shit
    }

    @Override
    public void pause() {
        //TODO implement this shit
    }

    @Override
    public void resume() {
        //TODO implement this shit
    }

    @Override
    public void dispose() {
        batch.dispose();
        //TODO dispose playerView and gameMapView, interface IView should have dispose method?
    }
}
