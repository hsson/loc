package se.alexanderkarlsson.beerchug.beerchugview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.alexanderkarlsson.beerchug.beerchugcontroller.BottleBeerChugController;
import se.alexanderkarlsson.beerchug.beerchugmodel.BottleBeerChug;
import se.alexanderkarlsson.beerchug.utilities.Converter;
import se.alexanderkarlsson.beerchug.utilities.ShakeDirection;

/**
 * View representing a bottle beerchug
 */
public class BottleBeerChugView implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BottleBeerChug model;
    private BottleBeerChugController controller;
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

    public BottleBeerChugView(BottleBeerChug model) {
        batch = new SpriteBatch();

        //Create camera with correct resolution
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1024,576);

        //Create the images for the player
        standingPlayer = new Texture(Gdx.files.internal("drinker.png"));
        shakingLeftPlayer = new Texture(Gdx.files.internal("drinkerLeft.png"));
        shakingRightPlayer = new Texture(Gdx.files.internal("drinkerRight.png"));

        //Create the images for the keys
        leftKey = new Texture(Gdx.files.internal("leftKey.gif"));
        rightKey = new Texture(Gdx.files.internal("rightKey.gif"));
        spaceKey = new Texture(Gdx.files.internal("spaceKey.gif"));

        //Create the table images
        beerTable = new Texture(Gdx.files.internal("beerTable.png"));
        table = new Texture(Gdx.files.internal("table.png"));

        //Create the background image
        background = new Texture(Gdx.files.internal("background.png"));

        //Instantiate model and connected controller
        this.model = model;
        controller = new BottleBeerChugController(model);

        //Create font
        font = new BitmapFont();

        //Instantiate and start background music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        startSound = Gdx.audio.newSound(Gdx.files.internal("start.wav"));
        startSoundPlayed = false;

        //Instatiate explosion
        kaboom = new ParticleEffect();
        kaboom.load(Gdx.files.internal("Kaboom.effect"), Gdx.files.internal(""));
        kaboom.getEmitters().first().setPosition(512, 165);
        hasBlown = false;

        explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
    }

    /**
     * Renders appropriate texture for the player
     */
    private void drawPlayer(){
        if (model.getLastShake() == null || model.isFinished()) {
            batch.draw(standingPlayer, (1024 / 2) - standingPlayer.getWidth() / 2, 40);
        } else if (model.getLastShake() == ShakeDirection.LEFT) {
            batch.draw(shakingLeftPlayer, (1024 / 2) - shakingLeftPlayer.getWidth() / 2, 40);
        } else if (model.getLastShake() == ShakeDirection.RIGHT) {
            batch.draw(shakingRightPlayer, (1024 / 2) - shakingRightPlayer.getWidth() / 2, 40);
        }
    }

    /**
     * Renders appropriate key
     */
    private void drawNextKey(){
        if ((model.getLastShake() == null || (model.drinkRemaining() == 0 && !model.isFinished())) && model.timeElapsed()>0) {
            batch.draw(spaceKey, (1024/2) - spaceKey.getWidth()/2,250);
        } else if (model.getLastShake() == ShakeDirection.LEFT && model.drinkRemaining()!=0) {
            batch.draw(rightKey, (1024/2) + (spaceKey.getWidth()/2),250);
        } else if (model.getLastShake() == ShakeDirection.RIGHT && model.drinkRemaining()!=0) {
            batch.draw(leftKey, (1024/2) - ((spaceKey.getWidth()/2)+leftKey.getWidth()),250);
        }
    }

    private void drawTable(){
        if(!model.isFirstShakeDone() || model.isFinished()){
            batch.draw(beerTable,(1024/2)-(beerTable.getWidth()/2),10);
        }else{
            batch.draw(table,(1024/2)-(table.getWidth()/2),10);
        }
    }

    private void drawTimeElapsed(){
        if (model.timeElapsed() > 0) {
            font.draw(batch, Float.toString(Converter.nanoToSeconds(model.timeElapsed())), 0, 560);
            if (model.timeElapsed() < 1000000000) {
                font.draw(batch, "HÄFV!!!!", 470, 350);
            }
        }else if(model.timeElapsed() > -1000000000){
            font.draw(batch, "Färdiga", 470, 350);
        }else{
            font.draw(batch, "Klara", 470, 350);
        }

    }

    private void drawGrade(){
        font.draw(batch, "Betyg: " + model.getGrade(), 470, 350);
    }

    private void drawDQReason(){
        font.draw(batch, model.getDisqualifiedReason(), 470, 400);
        if(!hasBlown){
            kaboom.start();
            hasBlown = true;
            explosionSound.play();
        }
        kaboom.update(Gdx.graphics.getDeltaTime());
        kaboom.draw(batch);
    }

    private void drawDrinkRemaining(){
        font.draw(batch, Converter.percentToString(model.drinkRemaining()), 0, 500);
    }

    @Override
    public void render (float v) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        controller.update();

        if(model.timeElapsed()>0 && !startSoundPlayed){
            startSound.play();
            startSoundPlayed = true;
        }

        batch.begin();

        batch.draw(background, 0, 0);//Draw background
        drawPlayer();
        drawNextKey();
        drawTable();
        drawTimeElapsed();
        //Draw grade when finished
        if(model.isFinished()){
            drawGrade();
        }
        //Draw potential DQ message
        if(model.isSquirted()){
            drawDQReason();
        }
        drawDrinkRemaining();

        //Restart if enter is presses (Only used for testing, will be removed)
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            model = new BottleBeerChug();
            controller = new BottleBeerChugController(model);
            hasBlown = false;
            kaboom.reset();
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

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
