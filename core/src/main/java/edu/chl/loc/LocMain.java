package edu.chl.loc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import edu.chl.loc.controller.GameController;
import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.minigame.IMinigameHandlerListener;
import edu.chl.loc.minigame.MinigameHandler;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.models.core.IGameModel;
import edu.chl.loc.models.items.ItemScore;
import edu.chl.loc.models.map.ILayer;
import edu.chl.loc.models.map.ItemTile;
import edu.chl.loc.models.map.Layer;
import edu.chl.loc.models.map.Tile;
import edu.chl.loc.models.utilities.Position2D;
import edu.chl.loc.utilities.SetupUtilities;
import edu.chl.loc.view.core.GameView;

import java.util.Set;

/**
 * @author Alexander Håkansson
 * Revised by Alexander Karlsson
 */
public class LocMain extends Game implements IMinigameHandlerListener {
    private float delta;

    private IGameModel model;
    private GameController controller;
    private GameView view;

	@Override
	public void create () {
        model = new GameModel();
        model.setGameMap(SetupUtilities.setupGameMap("maps/johanneberg.tmx"));
        createNPCs();
        controller = new GameController(model);
        view = new GameView(model);
        MinigameHandler handler = MinigameHandler.getInstance();
        handler.registerListener(this);
        Gdx.input.setInputProcessor(controller);



        setScreen(view);
    }

	@Override
	public void resize (int width, int height) {
	    getScreen().resize(width, height);
    }

	@Override
	public void render () {
        delta = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        getScreen().render(delta);
	}

	@Override
	public void pause () {
        getScreen().pause();
	}

	@Override
	public void resume () {
        getScreen().resume();
	}

	@Override
	public void dispose () {
        getScreen().dispose();
	}

    /**
     * Creates all the NPCs from the textfile NPCs.loc and adds each
     * of the to the gamemap
     */
    private void createNPCs(){
        model.getGameMap().addNPC(SetupUtilities.createNPCsFromFile());
    }

    @Override
    public void minigameFinished(IMinigame minigame) {
        model.addMinigameStat(minigame);
        Screen screen = getScreen();
        Gdx.input.setInputProcessor(controller);
        setScreen(this.view);
        screen.dispose();
    }

    @Override
    public void startMinigame(IMinigame minigame) {
        InputProcessor controller = minigame.getController();
        Screen view = minigame.getView();
        Gdx.input.setInputProcessor(controller);
        setScreen(view);
    }
}