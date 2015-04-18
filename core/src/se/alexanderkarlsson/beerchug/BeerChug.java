package se.alexanderkarlsson.beerchug;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.alexanderkarlsson.beerchug.beerchugcontroller.BottleBeerChugController;
import se.alexanderkarlsson.beerchug.beerchugmodel.BottleBeerChug;
import se.alexanderkarlsson.beerchug.utilities.ShakeDirection;

public class BeerChug extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BottleBeerChug model;
	private BottleBeerChugController controller;
	private Music backgroundMusic;
	private Texture standingPlayer;
	private Texture shakingLeftPlayer;
	private Texture shakingRightPlayer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		//Create camera with correct resolution
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1024,576);

		//Create the images for the player
		standingPlayer = new Texture(Gdx.files.internal("drinker.png"));
		shakingLeftPlayer = new Texture(Gdx.files.internal("drinkerLeft.png"));
		shakingRightPlayer = new Texture(Gdx.files.internal("drinkerRight.png"));

		model = new BottleBeerChug();
		controller = new BottleBeerChugController(model);

		//Instantiate and start background music
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			controller.keyPressed(Input.Keys.SPACE);
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
			controller.keyPressed(Input.Keys.LEFT);
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
			controller.keyPressed(Input.Keys.RIGHT);
		}
		batch.begin();
		if(model.getLastShake() == null){
			batch.draw(standingPlayer, 0, 0);
		}else if(model.getLastShake() == ShakeDirection.LEFT){
			batch.draw(shakingLeftPlayer, 0, 0);
		}else if(model.getLastShake() == ShakeDirection.RIGHT){
			batch.draw(shakingRightPlayer, 0, 0);
		}
		batch.end();
	}
}
