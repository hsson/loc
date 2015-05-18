package edu.chl.loc.minigame.caps.capsview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
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
    private Texture cap;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private long lastThrowTime;
    private boolean renderThrow;

    private static final int SCREEN_WIDTH = 1024;
    private static final int SCREEN_HEIGHT = 576;
    private static final int BEER_CUP_Y_POS = 20;
    private static final float CAP_FLIGHT_TIME = 1.5f;
    private static final float GRAVITATIONAL_CONSTANT = 9.82f;
    private static final float THROWING_ANGLE = 45.0f*((float)Math.PI/180f);
    private static final int LEVEL_TEXT_X_POS = 475;
    private static final int LEVEL_TEXT_Y_POS = 300;

    public CapsGameView(CapsGameModel model){
        this.model = model;

        //Instantiate textures
        this.crossHair = new Texture(Gdx.files.internal("caps/aim.png"));
        this.beerCup = new Texture(Gdx.files.internal("caps/beerCup.png"));
        this.cap = new Texture(Gdx.files.internal("caps/cap.png"));

        //Instantiate spritebatch and font
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

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
        if(renderThrow && !model.isCapThrown()){
            renderThrow = false;
        }

        batch.begin();
        drawCup();
        drawLevel();
        if(model.isCapThrown()){
            if(!renderThrow){
                this.lastThrowTime = TimeUtils.millis();
                renderThrow = true;
            }
            float timePassed = TimeUtils.millis() - lastThrowTime;
            timePassed = timePassed/1000f;//Convert to seconds
            drawCapThrow(timePassed, model.getAimPosition());
        }else{
            drawCrossHair();
        }
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

    /**
     * Draws the cup
     */
    private void drawCup(){
        //Calculate x value
        float xValue = (camera.viewportWidth * model.getCupPosition()) - beerCup.getWidth()/2;
        batch.draw(beerCup, xValue, BEER_CUP_Y_POS);
    }

    /**
     * Draws the crosshair
     */
    private void drawCrossHair(){
        //Calculate x value
        float xValue = (camera.viewportWidth * model.getAimPosition()) - crossHair.getWidth()/2;
        //Middle of crosshair should be on top of the beercup
        float yValue = getThrowHeight() - crossHair.getHeight()/2;

        batch.draw(crossHair, xValue, yValue);
    }

    /**
     * Draws the games current level
     */
    private void drawLevel(){
        String textToDraw = "Nivå: " + model.getLevel();
        font.draw(batch, textToDraw, LEVEL_TEXT_X_POS, LEVEL_TEXT_Y_POS);
    }

    /**
     * Draws a cap in the air, the cap is always thrown with a 45 degree
     * angle with varying velocity. The cap will move for 1.5 seconds and
     * the speed in the air is not physically realistic. No compensation
     * for air resistance or any other forces apart from gravity are made.
     * The trajectory would be realistic in a time warping vaccum in Sweden.
     * @param timePassed The time passed since the cap was thrown
     * @param distance The total distance the cap should travel in
     *                 screen width percent
     */
    private void drawCapThrow(float timePassed, float distance){
        double velocity = Math.sqrt(distance * GRAVITATIONAL_CONSTANT);
        double xValue;

        if(timePassed < CAP_FLIGHT_TIME) {
            xValue = distance * (timePassed / CAP_FLIGHT_TIME);
        }else{
            xValue = distance;
        }

        //This is physics
        double yValue = (xValue*Math.tan(THROWING_ANGLE)-
                ((GRAVITATIONAL_CONSTANT*xValue*xValue)/
                        (2.0*(velocity*Math.cos(THROWING_ANGLE))*
                                (velocity*Math.cos(THROWING_ANGLE)))));

        xValue *= SCREEN_WIDTH;
        yValue *= SCREEN_HEIGHT;
        yValue += getThrowHeight();

        batch.draw(cap, (float)xValue-cap.getWidth()/2, (float)yValue-cap.getHeight()/2);

        if(timePassed >= CAP_FLIGHT_TIME){
            drawHitOrMiss();
        }
    }

    /**
     * Draws if the throw was a hit or miss, if a throw has not been made does nothing.
     */
    public void drawHitOrMiss(){
        Color color = font.getColor();
        if(model.isCapThrown()){
            if(model.wasHit()){
                font.setColor(Color.GREEN);
                font.draw(batch,"Träff",LEVEL_TEXT_X_POS,LEVEL_TEXT_Y_POS-30);
            }else{
                font.setColor(Color.RED);
                font.draw(batch,"Miss",LEVEL_TEXT_X_POS,LEVEL_TEXT_Y_POS-30);
            }
        }
        font.setColor(color);
    }

    /**
     * Gets the height that the cap will be thrown from
     * @return The height the cap will be thrown from
     */
    private float getThrowHeight(){
        return BEER_CUP_Y_POS + beerCup.getHeight();
    }
}
