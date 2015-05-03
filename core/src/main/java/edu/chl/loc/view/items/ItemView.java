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
 */
public class ItemView implements IView {
    private ItemTile itemTile;
    private AbstractItem absItem;
    private Position2D position;
    private Texture itemTexture;
    private SpriteBatch spriteBatch;

    /**
     *
     * @param absItem the item you want to render
     * @param itemTexture the texture that corresponds to the item
     * @param position the position at where you want to render it
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
    public void render() {
        spriteBatch.draw(itemTexture, position.getX() * GameView.GRID_SIZE,
                position.getY() * GameView.GRID_SIZE);
    }
}
