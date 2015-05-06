package edu.chl.loc.view.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.loc.models.items.AbstractItem;
import edu.chl.loc.models.map.ItemTile;
import edu.chl.loc.models.utilities.Position2D;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;

/**
 * Created by kevin on 15-05-03.
 * @author Kevin Hoogendijk
 *
 * Revised by Alexander Håkansson
 * Revised by Alexander Karlsson
 */
public class ItemView implements IView {
    private ItemTile itemTile;
    private AbstractItem absItem;
    private Position2D position;
    private Texture itemTexture;
    private SpriteBatch spriteBatch;

    /**
     * @param itemTexture the texture that corresponds to the item
     */
    public ItemView(ItemTile itemTile, Texture itemTexture){
        this.absItem = itemTile.getItem();
        this.position = itemTile.getPosition();
        this.itemTexture = itemTexture;
        this.spriteBatch = GameView.getSpriteBatch();
    }

    /**
     * Render the texture of the item at the position of the item
     */
    @Override
    public void render(float delta) {
        if(itemTile.hasItem()) {
            spriteBatch.draw(itemTexture, position.getX() * GameView.GRID_SIZE,
                    position.getY() * GameView.GRID_SIZE);
        }
    }

    @Override
    public void dispose() {
        itemTexture.dispose();
        //SpriteBatch is disposed through gameview
    }
}
