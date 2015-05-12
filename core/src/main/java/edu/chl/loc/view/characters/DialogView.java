package edu.chl.loc.view.characters;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.loc.models.characters.npc.Dialog;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;

/**
 * @author Kevin Hoogendijk
 * @version 1.0.0
 */
public class DialogView implements IView {
    private Dialog dialog;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    public DialogView(Dialog dialog){
        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont();
        this.dialog = dialog;
    }

    @Override
    public void render(float delta, SpriteBatch spriteBatch) {
        Camera camera = GameView.getCamera();
        Viewport viewport = GameView.getViewport();
        Vector2 viewportOrigo = new Vector2(camera.position.x - viewport.getWorldWidth()/2,
                camera.position.y + viewport.getWorldHeight()/2);

        //render Frame
        shapeRenderer.setColor(255, 255, 255, 0);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(10, 10, Gdx.graphics.getWidth() - 21, Gdx.graphics.getHeight()/5);
        shapeRenderer.end();

        shapeRenderer.setColor(0, 0, 0, 0);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(10, 10, Gdx.graphics.getWidth() - 21, Gdx.graphics.getHeight() / 5);
        Gdx.gl20.glLineWidth(5);
        shapeRenderer.end();

        if(dialog.isLastString() && dialog.hasYesOption()){
            shapeRenderer.setColor(255, 255, 255, 0);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(Gdx.graphics.getWidth() - 121, 71, 100, 100);
            shapeRenderer.end();

            shapeRenderer.setColor(0, 0, 0, 0);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(Gdx.graphics.getWidth() - 121, 71, 100, 100);
            Gdx.gl20.glLineWidth(5);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.triangle(Gdx.graphics.getWidth() - 100, 150 - (dialog.getOptionSelected()?0:1)*40,
                                    Gdx.graphics.getWidth() - 100, 140 - (dialog.getOptionSelected()?0:1)*40,
                                    Gdx.graphics.getWidth() - 95, 145 - (dialog.getOptionSelected()?0:1)*40);
            shapeRenderer.end();

            spriteBatch.begin();
            font.setColor(Color.BLUE);
            font.draw(spriteBatch, "YES", Gdx.graphics.getWidth()- 80,
                    Gdx.graphics.getHeight() - 150);
            font.draw(spriteBatch, "NO", Gdx.graphics.getWidth()- 80,
                                         Gdx.graphics.getHeight() - 110);
            spriteBatch.end();
        }

        //render text
        spriteBatch.begin();
        font.setColor(Color.BLACK);
        font.draw(spriteBatch, dialog.getCurrentString(), viewportOrigo.x + 40, viewportOrigo.y - viewport.getWorldHeight() + 100);
        spriteBatch.end();


    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
