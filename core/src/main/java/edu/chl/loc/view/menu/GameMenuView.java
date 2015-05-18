package edu.chl.loc.view.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.loc.models.menu.GameMenu;
import edu.chl.loc.models.menu.IMenuOption;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;
import edu.chl.loc.view.utilities.RenderUtilities;

import java.util.List;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-18
 */
public class GameMenuView implements IView {


    private final GameMenu gameMenu;

    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    private int textYOffset = 64;

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
        List<IMenuOption> options = gameMenu.getMenuOptions();

        float menuWidth = viewport.getWorldWidth() / 5;
        float menuHeight = textYOffset * options.size();

        float xPos = (viewport.getWorldWidth() / 4) * 3;
        float yPos = (viewport.getWorldHeight() / 5) * 2;

        float textX = xPos + 32;
        float textY = yPos + menuHeight - 32;

        RenderUtilities.renderRectangle(xPos, yPos, menuWidth, menuHeight, shapeRenderer);

        batch.begin();
        font.setColor(Color.BLACK);

        for (IMenuOption option : options) {
            int index = options.indexOf(option);
            font.draw(batch, option.getName(), textX, textY + textYOffset * index);
        }
        batch.end();


        batch.setProjectionMatrix(oldProjMatrix);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        font.dispose();
    }
}
