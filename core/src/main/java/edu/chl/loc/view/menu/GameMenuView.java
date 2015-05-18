package edu.chl.loc.view.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.loc.models.menu.GameMenu;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;
import edu.chl.loc.view.utilities.RenderUtilities;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-18
 */
public class GameMenuView implements IView {


    private final GameMenu gameMenu;

    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    public GameMenuView(GameMenu menu) {
        this.gameMenu = menu;
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
    }


    @Override
    public void render(float delta, SpriteBatch batch) {
        Matrix4 oldProjMatrix = batch.getProjectionMatrix();
        batch.setProjectionMatrix(shapeRenderer.getProjectionMatrix());
        Viewport viewport = GameView.getViewport();

        int menuWidth = viewport.getViewportWidth() / 5;
        int menuHeight = viewport.getViewportHeight() / 2;

        int xPos = (viewport.getViewportWidth() / 6) * 4;
        int yPos = (viewport.getViewportHeight() / 6) * 5;

        RenderUtilities.renderRectangle(xPos, yPos, menuWidth, menuHeight, shapeRenderer);

        batch.setProjectionMatrix(oldProjMatrix);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        font.dispose();
    }
}
