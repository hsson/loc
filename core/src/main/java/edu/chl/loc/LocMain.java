package edu.chl.loc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import edu.chl.loc.controller.GameController;
import edu.chl.loc.models.core.GameModel;
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

        setScreen(view);
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
