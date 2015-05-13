package edu.chl.loc.view.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * @author Kevin Hoogendijk
 * @version 1.0.0
 * @since 2015-05-13
 */
public class RenderUtilities {

    //prevent from instantiating
    private RenderUtilities(){

    }

    public static void renderRectangle(float x, float y, float width, float height, ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(255,255,255,0);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        shapeRenderer.setColor(0, 0, 0, 0);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(x, y, width, height);
        Gdx.gl20.glLineWidth(5);
        shapeRenderer.end();
    }
}
