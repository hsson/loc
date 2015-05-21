package edu.chl.loc.minigame.caps.capsview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
    private Texture background;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private long lastThrowTime;
    private boolean renderThrow;
    private boolean increasingDrunkness;
    private boolean blurry;
    private Music backgroundMusic;

    private static final int SCREEN_WIDTH = 1024;
    private static final int SCREEN_HEIGHT = 576;
    private static final int BEER_CUP_Y_POS = 20;
    private static final float CAP_FLIGHT_TIME = 1.5f;
    private static final float GRAVITATIONAL_CONSTANT = 9.82f;
    private static final float THROWING_ANGLE = 45.0f*((float)Math.PI/180f);
    private static final int LEVEL_TEXT_X_POS = 475;
    private static final int LEVEL_TEXT_Y_POS = 550;

    public CapsGameView(CapsGameModel model){
        this.model = model;

        //Instantiate textures
        this.crossHair = new Texture(Gdx.files.internal("caps/aim.png"));
        this.beerCup = new Texture(Gdx.files.internal("caps/beerCup.png"));
        this.cap = new Texture(Gdx.files.internal("caps/cap.png"));
        this.background = new Texture(Gdx.files.internal("caps/background.png"));

        //Instantiate spritebatch and font
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        //Instantiate and set up camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,SCREEN_WIDTH,SCREEN_HEIGHT);

        //Instantiate music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("caps/background.mp3"));
        backgroundMusic.setLooping(true);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(model.getLevel()>=3) {
            drunkenShaking(Gdx.graphics.getDeltaTime(),model.getLevel()>=5);
        }

        if(model.getLevel()>=7 && !blurry){
            setDrunkBlur(true);
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        model.update(Gdx.graphics.getDeltaTime());
        if(renderThrow && !model.isCapThrown()){
            renderThrow = false;
        }

        batch.begin();
        batch.draw(background,0,0,camera.viewportWidth,camera.viewportHeight);
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
        backgroundMusic.play();
    }

    @Override
    public void hide() {
        backgroundMusic.stop();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        crossHair.dispose();
        beerCup.dispose();
        cap.dispose();
        background.dispose();
        font.dispose();
    }

    /**
     * Draws the cup
     */
    private void drawCup(){
        //Calculate x value
        float xValue = (camera.viewportWidth * model.getCupPosition()) - (camera.viewportWidth/10)/2;
        batch.draw(beerCup, xValue, BEER_CUP_Y_POS, camera.viewportWidth/10, beerCup.getHeight());
    }

    /**
     * Draws the crosshair
     */
    private void drawCrossHair(){
        //Calculate x value
        float xValue = (camera.viewportWidth * model.getAimPosition()) - (float)crossHair.getWidth()/2.0f;
        //Middle of crosshair should be on top of the beercup
        float yValue = getThrowHeight() - (float)crossHair.getHeight()/2.0f;

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

        batch.draw(cap, (float)xValue-(float)cap.getWidth()/2.0f, (float)yValue-(float)cap.getHeight()/2.0f);

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
                font.setColor(color);
                font.draw(batch,"Betyg: " + model.getGrade(),LEVEL_TEXT_X_POS,LEVEL_TEXT_Y_POS-60);
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

    /**
     * Shakes and optionally rotates the screen, how much shake is applied depends on the level
     * @param deltaTime Time passed since last render
     * @param rotate True if the screen should rotate, false otherwise
     */
    private void drunkenShaking(float deltaTime, boolean rotate){
        if(increasingDrunkness){
            camera.zoom -= deltaTime/10;
            if(rotate) {
                camera.rotate(deltaTime * model.getLevel());
            }
            if(camera.zoom < 0.9){
                increasingDrunkness = false;
            }
        }else{
            camera.zoom += deltaTime/10;
            if(rotate) {
                camera.rotate(-deltaTime * model.getLevel());
            }
            if(camera.zoom > 1.0){
                increasingDrunkness = true;
            }
        }
    }

    /**
     * Blurs the screen or unblurs the screen
     * @param value Blurs the screen if true, false otherwise
     */
    private void setDrunkBlur(boolean value){
        blurry = value;
        if(value){
            background = new Texture(Gdx.files.internal("caps/backgroundDrunk.png"));
            beerCup = new Texture(Gdx.files.internal("caps/beerCupDrunk.png"));
            crossHair = new Texture(Gdx.files.internal("caps/aimDrunk.png"));
            cap = new Texture(Gdx.files.internal("caps/capDrunk.png"));
        }else{
            background = new Texture(Gdx.files.internal("caps/background.png"));
            beerCup = new Texture(Gdx.files.internal("caps/beerCup.png"));
            crossHair = new Texture(Gdx.files.internal("caps/aim.png"));
            cap = new Texture(Gdx.files.internal("caps/capDrunk.png"));
        }
    }
}
