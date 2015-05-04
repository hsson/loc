package edu.chl.loc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import edu.chl.loc.controller.GameController;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.models.map.ILayer;
import edu.chl.loc.models.map.Layer;
import edu.chl.loc.models.map.Tile;
import edu.chl.loc.models.utilities.Position2D;
import edu.chl.loc.view.core.GameView;

/**
 * @author Alexander Håkansson
 */
public class LocMain extends Game {
	private float elapsed;
    private float delta;

    private GameModel model;
    private GameController controller;
    private GameView view;

	@Override
	public void create () {
        model = new GameModel();
        controller = new GameController(model);
        view = new GameView(model);

        Gdx.input.setInputProcessor(controller);

        setupGameMap();

        setScreen(view);
    }

    private void setupGameMap() {
        TiledMap johanneberg = new TmxMapLoader().load(Gdx.files.internal("maps/johanneberg.tmx").path());

        for (MapLayer mapLayer : johanneberg.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer) mapLayer;
            ILayer layer = new Layer(tiledLayer.getName());

            model.getGameMap().addLayer(layer);

            for (int y = 0; y < tiledLayer.getHeight(); y++) {
                for (int x = 0; x < tiledLayer.getWidth(); x++) {
                    boolean collision = false;
                    if (tiledLayer.getCell(x, y) != null) {
                        TiledMapTile mapTile = tiledLayer.getCell(x, y).getTile();
                        if (mapTile != null && mapTile.getProperties().containsKey("collision")) {
                            collision = mapTile.getProperties().get("collision").equals("true");
                        }
                    }
                    model.getGameMap().addTile(layer, new Tile(new Position2D(x, y), collision));
                }
            }
        }
    }

	@Override
	public void resize (int width, int height) {
	    getScreen().resize(width, height);
    }

	@Override
	public void render () {
        delta = Gdx.graphics.getDeltaTime();
		elapsed += delta;
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
}
