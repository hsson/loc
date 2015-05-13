package edu.chl.loc.view.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.loc.models.characters.npc.Dialog;
import edu.chl.loc.models.utilities.Position2D;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;

import java.awt.geom.Rectangle2D;

/**
 * @author Kevin Hoogendijk
 * @version 1.0.0
 */
public class DialogView implements IView {
    private Dialog dialog;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    private Rectangle yesNoRect;
    private Rectangle yesNoText;

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
            yesNoRect = new Rectangle(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/7, Gdx.graphics.getHeight()/7, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/5);
            yesNoText = new Rectangle(camera.position.x - viewport.getViewportWidth() / 2 + viewport.getViewportWidth() - viewport.getViewportWidth() / 7,
                                      camera.position.y - viewport.getViewportHeight() / 2 + viewport.getViewportHeight()*(2/7),
                                      viewport.getViewportWidth()/10,
                                      viewport.getViewportHeight()/10);

            //shapeRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());

            shapeRenderer.setColor(255, 255, 255, 0);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(yesNoRect.x, yesNoRect.y, yesNoRect.width, yesNoRect.height);
            shapeRenderer.end();

            shapeRenderer.setColor(0, 0, 0, 0);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(yesNoRect.x, yesNoRect.y, yesNoRect.width, yesNoRect.height);
            Gdx.gl20.glLineWidth(5);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.triangle(
                    yesNoRect.getX() + yesNoRect.getWidth() / 2 - yesNoRect.getWidth()/3, 5 + yesNoRect.getY() + yesNoRect.getHeight() * (dialog.getOptionSelected() ?  0.6f : 0.3f),
                    10 + yesNoRect.getX() + yesNoRect.getWidth() / 2 - yesNoRect.getWidth()/3, yesNoRect.getY() + yesNoRect.getHeight() * (dialog.getOptionSelected() ?  0.6f : 0.3f),
                    yesNoRect.getX() + yesNoRect.getWidth() / 2 - yesNoRect.getWidth()/3, -5 + yesNoRect.getY() + yesNoRect.getHeight() * (dialog.getOptionSelected() ?  0.6f : 0.3f));
            shapeRenderer.end();

            Matrix4 oldProjMatrix = spriteBatch.getProjectionMatrix();
            spriteBatch.setProjectionMatrix(shapeRenderer.getProjectionMatrix());
            spriteBatch.begin();
            font.setColor(Color.BLUE);
            font.draw(spriteBatch, "YES", yesNoRect.getX() + yesNoRect.getWidth() / 2, yesNoRect.getY() + yesNoRect.getHeight() * 0.6f);
            font.draw(spriteBatch, "NO", yesNoRect.getX() + yesNoRect.getWidth()/2, yesNoRect.getY() + yesNoRect.getHeight() * 0.3f);
            spriteBatch.end();
            spriteBatch.setProjectionMatrix(oldProjMatrix);
        }

        //render text
        Matrix4 oldProjMatrix = spriteBatch.getProjectionMatrix();
        spriteBatch.setProjectionMatrix(shapeRenderer.getProjectionMatrix());
        spriteBatch.begin();
        font.setColor(Color.BLACK);
        font.draw(spriteBatch, dialog.getCurrentString(), 40, 100);
        spriteBatch.end();
        spriteBatch.setProjectionMatrix(oldProjMatrix);


    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
