package edu.chl.loc.view.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.loc.models.map.ItemTile;
import edu.chl.loc.models.utilities.Position2D;
import edu.chl.loc.view.IView;
import edu.chl.loc.view.core.GameView;

/**
 * Created by kevin on 15-05-03.
 * @author Kevin Hoogendijk
 *
 * Revised by Alexander Håkansson
 * Revised by Alexander Karlsson
 * Revised by Maxim Goretskyy
 */
public class ItemView implements IView {
    private ItemTile itemTile;
    private Position2D position;
    private Texture itemTexture;

    /**
     * @param itemTexture the texture that corresponds to the item
     */
    public ItemView(ItemTile itemTile, Texture itemTexture){
        this.itemTile = itemTile;
        this.position = itemTile.getPosition();
        this.itemTexture = itemTexture;
    }

    /**
     * Render the texture of the item at the position of the item
     */
    @Override
    public void render(float delta, SpriteBatch batch) {
        if(itemTile.hasItem()) {
            batch.draw(itemTexture, position.getX() * GameView.GRID_SIZE,
                    position.getY() * GameView.GRID_SIZE);
        }

    }

    @Override
    public void dispose() {
        itemTexture.dispose();
        //SpriteBatch is disposed through gameview
    }
}
