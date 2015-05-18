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
        System.out.println(stats.getKeySet().size());;
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        Matrix4 oldProjMatrix = batch.getProjectionMatrix();
        batch.setProjectionMatrix(shapeRenderer.getProjectionMatrix());
        Viewport viewport = GameView.getViewport();
        Rectangle frame = new Rectangle(10, viewport.getWorldHeight() - 10, viewport.getWorldWidth() * 0.4f, -viewport.getWorldHeight() * 0.7f);

        RenderUtilities.renderRectangle(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight(), shapeRenderer);

        String[] keySet = stats.getKeySet().toArray(new String[0]);
        int scrllIndx = statsWindow.getScrollIndex();

        batch.begin();
        for(int i = scrllIndx; i < scrllIndx + (stats.getKeySet().size() < 5 ? stats.getKeySet().size() : 5); i++){
            font.draw(batch, keySet[i] + ":", frame.getX() + frame.getWidth()/7, frame.getY() + frame.getHeight()/7 + i*(frame.getHeight()/7));
            font.draw(batch, Double.toString(stats.getPlayerStat(keySet[i])), frame.getX() + 3 * (frame.getWidth()/7), frame.getY()  + frame.getHeight()/7 + i*(frame.getHeight()/7));
        }
        batch.end();

        batch.setProjectionMatrix(oldProjMatrix);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
