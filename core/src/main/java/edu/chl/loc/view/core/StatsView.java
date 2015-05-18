package edu.chl.loc.view.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.loc.models.core.Stats;
import edu.chl.loc.models.core.StatsWindow;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;
import edu.chl.loc.view.utilities.RenderUtilities;

/**
 * @author Kevin Hoogendijk
 * @version 1.0.1
 * @since 2015-05-13
 */
public class StatsView implements IView{
    private Stats stats;
    private StatsWindow statsWindow;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    public StatsView(StatsWindow statsWindow){
        this.statsWindow = statsWindow;
        this.stats = statsWindow.getStats();
        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        Matrix4 oldProjMatrix = batch.getProjectionMatrix();
        batch.setProjectionMatrix(shapeRenderer.getProjectionMatrix());
        Viewport viewport = GameView.getViewport();
        Rectangle frame = new Rectangle(10, viewport.getWorldHeight() - 10, viewport.getWorldWidth() * 0.4f, -viewport.getWorldHeight() * 0.7f);
        String[] activeLabels = new String[5];
        String[] activeValues = new String[5];

        RenderUtilities.renderRectangle(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight(), shapeRenderer);

        String[] keySet = stats.getKeySet().toArray(new String[0]);
        int scrllIndx = statsWindow.getScrollIndex();

        for(int i = scrllIndx; i < keySet.length && i < 5+scrllIndx; i++){
            activeLabels[i-scrllIndx] = keySet[i];
            activeValues[i-scrllIndx] = Double.toString(stats.getPlayerStat(keySet[i]));
        }

        batch.begin();
        for(int i = 0; i < keySet.length && i < 5; i++) {
            font.draw(batch, activeLabels[i], frame.getX() + frame.getWidth() / 7, frame.getY() + frame.getHeight() / 7 + i * (frame.getHeight() / 7));
            font.draw(batch, activeValues[i], frame.getX() + 3 * (frame.getWidth() / 7), frame.getY() + frame.getHeight() / 7 + i * (frame.getHeight() / 7));
        }
        batch.end();

        batch.setProjectionMatrix(oldProjMatrix);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
