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
import edu.chl.loc.view.utilities.RenderUtilities;

import java.awt.geom.Rectangle2D;

/**
 * @author Kevin Hoogendijk
 * @version 1.1.0
 */
public class DialogView implements IView {
    private Dialog dialog;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    private Rectangle yesNoRect;

    public DialogView(Dialog dialog){
        this();
        this.dialog = dialog;
    }

    public DialogView(){
        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont();
    }

    @Override
    public void render(float delta, SpriteBatch spriteBatch) {
        Viewport viewport =  GameView.getViewport();
        Matrix4 oldProjMatrix = spriteBatch.getProjectionMatrix();
        spriteBatch.setProjectionMatrix(shapeRenderer.getProjectionMatrix());               //Set the projection matrix
                                                            // to the same to be able to use the same coordinate systems
        //render the dialog frame
        RenderUtilities.renderRectangle(10, 10, viewport.getWorldWidth() - 21, viewport.getWorldHeight() / 5, shapeRenderer);

        //if the dialog is active, render it and all its components
        if(dialog.isLastString() && dialog.hasYesOption()){
            yesNoRect  = new Rectangle(viewport.getWorldWidth() - viewport.getWorldWidth()/5, viewport.getWorldHeight()/5, 100, 100);
            RenderUtilities.renderRectangle(yesNoRect.x, yesNoRect.y, yesNoRect.width, yesNoRect.height, shapeRenderer);
            renderPointer();
            renderYesNoText(spriteBatch);
        }
        renderDialogText(spriteBatch);
        spriteBatch.setProjectionMatrix(oldProjMatrix);
    }

    public void renderYesNoText(SpriteBatch spriteBatch){
        spriteBatch.begin();
        font.setColor(Color.BLUE);
        font.draw(spriteBatch, "YES", yesNoRect.getX() + yesNoRect.getWidth() / 2, yesNoRect.getY() + yesNoRect.getHeight() * 0.6f);
        font.draw(spriteBatch, "NO", yesNoRect.getX() + yesNoRect.getWidth() / 2, yesNoRect.getY() + yesNoRect.getHeight() * 0.3f);
        spriteBatch.end();
    }

    public void renderPointer(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.triangle(
                yesNoRect.getX() + yesNoRect.getWidth() / 2 - yesNoRect.getWidth() / 3, 5 + yesNoRect.getY() + yesNoRect.getHeight() * (dialog.getOptionSelected() ? 0.6f : 0.3f),
                10 + yesNoRect.getX() + yesNoRect.getWidth() / 2 - yesNoRect.getWidth() / 3, yesNoRect.getY() + yesNoRect.getHeight() * (dialog.getOptionSelected() ? 0.6f : 0.3f),
                yesNoRect.getX() + yesNoRect.getWidth() / 2 - yesNoRect.getWidth() / 3, -5 + yesNoRect.getY() + yesNoRect.getHeight() * (dialog.getOptionSelected() ? 0.6f : 0.3f));
        shapeRenderer.end();
    }

    public void renderDialogText(SpriteBatch spriteBatch){
        spriteBatch.begin();
        font.setColor(Color.BLACK);
        font.draw(spriteBatch, dialog.getCurrentString(), 40, 100);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        font.dispose();
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }
}
