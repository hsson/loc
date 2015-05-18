package edu.chl.loc.minigame.cortege.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.loc.minigame.cortege.model.CortegeModel;
import edu.chl.loc.minigame.cortege.model.Tool;


/**
 * Created by Maxim on 15-03-28.
 * @author Maxim Goretskyy
 */
public class CortegeView implements Screen {

    private final CortegeModel game;

    private Texture poopImage;
    private Texture toolBoxImage;
    private Texture[] toolsImages;
    private Texture backgroundTexture;


    private Music backgroundMusic;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;


    public CortegeView(CortegeModel game){

        this.game = game;

        //load images
        backgroundTexture = new Texture(Gdx.files.internal("cortege/cortege.png"));
        toolsImages = new Texture[]{new Texture(Gdx.files.internal("cortege/hammer.png")),
                        new Texture(Gdx.files.internal("cortege/plank.png")),
                new Texture(Gdx.files.internal("cortege/saw.png")),
                new Texture(Gdx.files.internal("cortege/screwer.png"))
        };
        toolBoxImage = new Texture(Gdx.files.internal("cortege/toolbox.png"));
        poopImage = new Texture(Gdx.files.internal("cortege/poop.png"));

        //load music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("cortege/CortegeKaterina.mp3"));

        font = new BitmapFont();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 576);

    }

    @Override
    public void render(float v) {

        // tell the camera to update its matrices.
        camera.update();

        game.updatingGame(Gdx.graphics.getDeltaTime());

        batch.begin();
        batch.draw(backgroundTexture, 0, 0); //draw background picture in fullscreen

        font.draw(batch, "Your score is " + game.getScore(), 0, 560); //show score in corner


        batch.draw(toolBoxImage, game.getToolBox().x, game.getToolBox().y); //draw toolbox

        drawTools();


        if(!game.isPlaying()){ //show grade when finished
            font.draw(batch, "Your grade is " + game.getGrade(), 450, 450);
        }

        batch.end();

    }

    private void drawTools(){
        for (Tool tool : game.getToolsList()) {
            batch.draw(decideTexture(tool), tool.x, tool.y); //image of tool
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void show() {
       backgroundMusic.play();

    }

    @Override
    public void hide() {
        backgroundMusic.pause();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        toolBoxImage.dispose();

        poopImage.dispose();
        font.dispose();
        backgroundMusic.dispose();
        for(int i = 0; i<toolsImages.length; i++){
            toolsImages[i].dispose();
        }
        backgroundTexture.dispose();
    }

    public Texture decideTexture(Tool tool){
        switch(tool.getType()){
            case HAMMER:
                return toolsImages[0];
            case PLANK:
                return toolsImages[1];
            case SAW:
                return toolsImages[2];
            case SCREWER:
                return toolsImages[3];
            case POOP:
                return poopImage;
            default:
                return toolsImages[1];//return planks if not one of these types


        }
    }
}
