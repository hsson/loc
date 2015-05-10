package edu.chl.loc.view.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import edu.chl.loc.models.characters.npc.Dialog;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;

/**
 * Created by kritt on 5/9/15.
 */
public class DialogView implements IView {
    private Dialog dialog;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    public DialogView(Dialog dialog){
        this.dialog = dialog;
        this.spriteBatch = GameView.getSpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        //render Frame
        shapeRenderer.setColor(255, 255, 255, 0);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(10, 10, GameView.RES_X - 21, 50);
        shapeRenderer.end();

        shapeRenderer.setColor(0, 0, 0, 0);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(10, 10, GameView.RES_X - 21, 50);
        Gdx.gl20.glLineWidth(5);
        shapeRenderer.end();

        spriteBatch.begin();

        //render text
        font.draw(spriteBatch, dialog.getDialogStrings()[0], 10, 10);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
