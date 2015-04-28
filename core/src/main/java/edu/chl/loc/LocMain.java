package edu.chl.loc;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LocMain implements ApplicationListener {
	SpriteBatch batch;
	float elapsed;

	@Override
	public void create () {
		batch = new SpriteBatch();
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
