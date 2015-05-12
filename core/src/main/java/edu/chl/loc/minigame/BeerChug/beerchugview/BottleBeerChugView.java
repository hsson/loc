package edu.chl.loc.minigame.BeerChug.beerchugview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import edu.chl.loc.minigame.BeerChug.beerchugmodel.BottleBeerChug;
import edu.chl.loc.minigame.BeerChug.utilities.Converter;
import edu.chl.loc.minigame.BeerChug.utilities.ShakeDirection;

import java.text.DecimalFormat;

/**
 * View representing a bottle beerchug
 * @author Alexander Karlsson
 * @version 1.3.3.7
 */
public class BottleBeerChugView implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BottleBeerChug model;
    private Music backgroundMusic;
    private Sound startSound;
    private boolean startSoundPlayed;
    private Texture standingPlayer;
    private Texture shakingLeftPlayer;
    private Texture shakingRightPlayer;
    private Texture leftKey;
    private Texture rightKey;
    private Texture spaceKey;
    private Texture background;
    private Texture table;
    private Texture beerTable;
    private BitmapFont font;
    private boolean hasBlown;
    private ParticleEffect kaboom;
    private Sound explosionSound;

    private static final int SCREEN_WIDTH = 1024;
    private static final int SCREEN_HEIGHT = 576;
    private static final int EXPLOTION_X_POS = 512;
    private static final int EXPLOTION_Y_POS = 165;
    private static final int MIDDLE_OF_SCREEN_WIDTH = SCREEN_WIDTH / 2;
    private static final int PLAYER_Y_POS = 40;
    private static final int NEXT_KEY_Y_POS = 250;
    private static final int READY_TEXT_X_POS = 425;
    private static final int READY_TEXT_Y_POS = 350;
    private static final int TABLE_Y_POS = 10;
    private static final int TIME_X_POS = 480;
    private static final int TIME_Y_POS = 400;
    private static final int GRADE_X_POS = 470;
    private static final int GRADE_Y_POS = 350;
    private static final int DQREASON_X_POS = 470;
    private static final int DQREASON_Y_POS = 380;
    private static final int DRINK_REMAINING_TEXT_X_POS = 0;
    private static final int DRINK_REMAINING_TEXT_Y_POS = 500;
    private static final int COUNTDOWN_X_POS = 470;
    private static final int COUNTDOWN_Y_POS = 350;
    private static final int BACKGROUND_X_POS = 0;
    private static final int BACKGROUND_Y_POS = 0;

    public BottleBeerChugView(BottleBeerChug model) {
        batch = new SpriteBatch();

        //Create camera with correct resolution
        camera = new OrthographicCamera();
        camera.setToOrtho(false,SCREEN_WIDTH,SCREEN_HEIGHT);

        //Create the images for the player
        standingPlayer = new Texture(Gdx.files.internal("beerChug/drinker.png"));
        shakingLeftPlayer = new Texture(Gdx.files.internal("beerChug/drinkerLeft.png"));
        shakingRightPlayer = new Texture(Gdx.files.internal("beerChug/drinkerRight.png"));

        //Create the images for the keys
        leftKey = new Texture(Gdx.files.internal("beerChug/leftKey.gif"));
        rightKey = new Texture(Gdx.files.internal("beerChug/rightKey.gif"));
        spaceKey = new Texture(Gdx.files.internal("beerChug/spaceKey.gif"));

        //Create the table images
        beerTable = new Texture(Gdx.files.internal("beerChug/beerTable.png"));
        table = new Texture(Gdx.files.internal("beerChug/table.png"));

        //Create the background image
        background = new Texture(Gdx.files.internal("beerChug/background.png"));

        //Instantiate model and connected controller
        this.model = model;

        //Create font
        font = new BitmapFont();

        //Instantiate and start background music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("beerChug/background.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        startSound = Gdx.audio.newSound(Gdx.files.internal("beerChug/start.wav"));
        startSoundPlayed = false;

        //Instatiate explosion
        kaboom = new ParticleEffect();
        kaboom.load(Gdx.files.internal("beerChug/Kaboom.effect"), Gdx.files.internal("beerChug/"));
        kaboom.getEmitters().first().setPosition(EXPLOTION_X_POS, EXPLOTION_Y_POS);
        hasBlown = false;

        explosionSound = Gdx.audio.newSound(Gdx.files.internal("beerChug/explosion.wav"));
    }

    /**
     * Renders appropriate texture for the player
     */
    private void drawPlayer(){
        if (model.getLastShake() == null || model.isFinished()) {
            batch.draw(standingPlayer, MIDDLE_OF_SCREEN_WIDTH - (standingPlayer.getWidth() / 2), PLAYER_Y_POS);
        } else if (model.getLastShake() == ShakeDirection.LEFT) {
            batch.draw(shakingLeftPlayer, MIDDLE_OF_SCREEN_WIDTH - (shakingLeftPlayer.getWidth() / 2), PLAYER_Y_POS);
        } else if (model.getLastShake() == ShakeDirection.RIGHT) {
            batch.draw(shakingRightPlayer, MIDDLE_OF_SCREEN_WIDTH - (shakingRightPlayer.getWidth() / 2), PLAYER_Y_POS);
        }
    }

    /**
     * Renders appropriate key
     */
    private void drawNextKey(){
        if (((model.getLastShake() == null || (model.drinkRemaining() == 0 && !model.isFinished())) && model.timeElapsed()>0) || !model.countDownHasBegun()) {
            batch.draw(spaceKey, MIDDLE_OF_SCREEN_WIDTH - (spaceKey.getWidth()/2),NEXT_KEY_Y_POS);
            if(!model.countDownHasBegun()){
                font.draw(batch, "Tryck space när du är redo", READY_TEXT_X_POS, READY_TEXT_Y_POS);
            }
        } else if (model.getLastShake() == ShakeDirection.LEFT && model.drinkRemaining()!=0) {
            batch.draw(rightKey, MIDDLE_OF_SCREEN_WIDTH + (spaceKey.getWidth()/2),NEXT_KEY_Y_POS);
        } else if (model.getLastShake() == ShakeDirection.RIGHT && model.drinkRemaining()!=0) {
            batch.draw(leftKey, MIDDLE_OF_SCREEN_WIDTH - ((spaceKey.getWidth()/2)+leftKey.getWidth()),NEXT_KEY_Y_POS);
        }
    }

    /**
     * Renders a table with or without a beer on it
     */
    private void drawTable(){
        if(!model.isFirstShakeDone() || model.isFinished()){
            batch.draw(beerTable,MIDDLE_OF_SCREEN_WIDTH-(beerTable.getWidth()/2),TABLE_Y_POS);
        }else{
            batch.draw(table,MIDDLE_OF_SCREEN_WIDTH-(table.getWidth()/2),TABLE_Y_POS);
        }
    }

    /**
     * Renders the time elapsed
     */
    private void drawTimeElapsed(){
        DecimalFormat df = new DecimalFormat("0.00");
        String timeElapsed = df.format(model.timeElapsed());
        font.draw(batch, timeElapsed, TIME_X_POS, TIME_Y_POS);
    }

    /**
     * Renders the grade of the chug
     */
    private void drawGrade(){
        font.draw(batch, "Betyg: " + model.getGrade(), GRADE_X_POS, GRADE_Y_POS);
    }

    /**
     * Renders the reason for DQ, if any and renders explosion
     * effect with sound
     */
    private void drawDQReason(){
        if(model.isSquirted()) {
            font.draw(batch, model.getDisqualifiedReason(), DQREASON_X_POS, DQREASON_Y_POS);
        }
        if(!hasBlown){
            kaboom.start();
            hasBlown = true;
            explosionSound.play();
        }
        kaboom.update(Gdx.graphics.getDeltaTime());
        kaboom.draw(batch);
    }

    /**
     * Renders the amount of drink remaining
     */
    private void drawDrinkRemaining(){
        font.draw(batch, Converter.percentToString(model.drinkRemaining()), DRINK_REMAINING_TEXT_X_POS, DRINK_REMAINING_TEXT_Y_POS);
    }

    /**
     * Renders a countdown if the model has one ongoing
     */
    private void drawCountdown(){
        if(model.isCountingDown()){
            if(model.getCountDown() > 1){
                font.draw(batch, "Klara", COUNTDOWN_X_POS, COUNTDOWN_Y_POS);
            }else if(model.getCountDown() > 0){
                font.draw(batch, "Färdiga", COUNTDOWN_X_POS, COUNTDOWN_Y_POS);
            }else{
                font.draw(batch, "HÄFV!!!", COUNTDOWN_X_POS, COUNTDOWN_Y_POS);
            }
        }
    }



    @Override
    public void render (float v) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        model.update(Gdx.graphics.getDeltaTime());

        if(model.timeElapsed()>0 && !startSoundPlayed){
            startSound.play();
            startSoundPlayed = true;
        }

        batch.begin();

        batch.draw(background, BACKGROUND_X_POS, BACKGROUND_Y_POS);//Draw background
        drawPlayer();
        drawNextKey();
        drawTable();
        drawDrinkRemaining();

        //Draw the time if the chug is ongoing
        if(model.chugStarted()) {
            drawTimeElapsed();
        }

        //Draw the countdown if it is ongoing
        if(model.isCountingDown()){
            drawCountdown();
        }

        //Draw grade when finished
        if(model.isFinished()){
            drawGrade();
        }

        //Draw potential DQ message
        if(model.isSquirted()){
            drawDQReason();
        }

        batch.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        Gdx.graphics.setContinuousRendering(false);
    }

    @Override
    public void resume() {
        Gdx.graphics.setContinuousRendering(true);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundMusic.dispose();
        startSound.dispose();
        standingPlayer.dispose();
        shakingLeftPlayer.dispose();
        shakingRightPlayer.dispose();
        leftKey.dispose();
        rightKey.dispose();
        spaceKey.dispose();
        background.dispose();
        table.dispose();
        beerTable.dispose();
        font.dispose();
        kaboom.dispose();
        explosionSound.dispose();
    }
}
