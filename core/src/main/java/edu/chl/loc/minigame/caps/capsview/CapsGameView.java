package edu.chl.loc.minigame.caps.capsview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.loc.minigame.caps.capsmodel.CapsGameModel;

/**
 * View class for caps minigame
 * @author Alexander Karlsson
 * @version 1.0
 */
public class CapsGameView implements Screen {
    private CapsGameModel model;
    private Texture crossHair;
    private Texture beerCup;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private static final int SCREEN_WIDTH = 1024;
    private static final int SCREEN_HEIGHT = 576;
    private static final int BEER_CUP_Y_POS = 20;

    public CapsGameView(CapsGameModel model){
        this.model = model;

        //Instantiate textures
        this.crossHair = new Texture(Gdx.files.internal("caps/aim.png"));
        this.beerCup = new Texture(Gdx.files.internal("caps/beerCup.png"));

        //Instantiate spritebatch
        this.batch = new SpriteBatch();

        //Instantiate and set up camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        model.update(Gdx.graphics.getDeltaTime());

        batch.begin();
        drawCup();
        drawCrossHair();
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private void drawCup(){
        //Calculate x value
        float xValue = (camera.viewportWidth * model.getCupPosition()) - beerCup.getWidth()/2;
        batch.draw(beerCup, xValue, BEER_CUP_Y_POS);
    }

    private void drawCrossHair(){
        //Calculate x value
        float xValue = (camera.viewportWidth * model.getAimPosition()) - crossHair.getWidth()/2;
        //Middle of crosshair should be on top of the beercup
        float yValue = (float)BEER_CUP_Y_POS + beerCup.getHeight() - crossHair.getHeight()/2;

        batch.draw(crossHair, xValue, yValue);
    }
}
