package edu.chl.loc.view.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import edu.chl.loc.models.items.AbstractItem;

import java.util.Locale;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-06
 */
public class ItemTextureFactory {

    private static final Texture DEFAULT_ITEM_TEXTURE = new Texture(Gdx.files.internal("items/pripps.png"));

    private ItemTextureFactory() {
        // No construction allowed
    }

    public static Texture build(AbstractItem item) {

        if (item.getItemName().toLowerCase(Locale.ENGLISH).equals("pripps blå")) {
            return new Texture(Gdx.files.internal("items/pripps.png"));
        }

        return DEFAULT_ITEM_TEXTURE;
    }
}
