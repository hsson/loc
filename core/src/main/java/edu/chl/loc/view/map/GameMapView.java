package edu.chl.loc.view.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.loc.models.characters.npc.AbstractNPC;
import edu.chl.loc.models.items.AbstractItem;
import edu.chl.loc.models.map.GameMap;
import edu.chl.loc.models.map.ILayer;
import edu.chl.loc.models.map.ItemTile;
import edu.chl.loc.models.map.Layer;
import edu.chl.loc.view.IView;
import edu.chl.loc.view.characters.CharacterView;
import edu.chl.loc.view.characters.NPCTextureFactory;
import edu.chl.loc.view.items.ItemTextureFactory;
import edu.chl.loc.view.items.ItemView;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alexander Håkansson
 * @version 0.3.0
 * @since 2015-04-30
 * Revised by Alexander Karlsson
 */
public class GameMapView implements IView {
    private Set<CharacterView> npcViews;
    private Set<ItemView> itemViews;
    private GameMap gameMap;

    public GameMapView(GameMap gameMap) {
        this.gameMap = gameMap;
        createNPCViews();
        createItemViews();
    }

    /**
     * Gets all NPC from the model and creates views for them.
     */
    private void createNPCViews() {
        npcViews = new HashSet<CharacterView>();

        for (AbstractNPC npc : gameMap.getAllNPCs()) {
            Texture npcTexture = NPCTextureFactory.build(npc);
            npcViews.add(new CharacterView(npc, npcTexture));
        }
    }

    private void createItemViews() {
        itemViews = new HashSet<ItemView>();
        ILayer itemLayer = new Layer("items");

        if (gameMap.layerExists(itemLayer)) {
            for (ItemTile itemTile : gameMap.getItemTiles(new Layer("items"))) {
                if (itemTile.hasItem()) {
                    AbstractItem item = itemTile.getItem();
                    Texture texture = ItemTextureFactory.build(item);
                    itemViews.add(new ItemView(itemTile, texture));
                }
            }
        }
    }

    @Override
    public void render(float delta, SpriteBatch batch) {

        // Render NPCs
        for (CharacterView view : npcViews) {
            view.render(delta, batch);
        }

        // Render items
        for (ItemView view : itemViews) {
            view.render(delta, batch);
        }
    }

    @Override
    public void dispose() {
        for(CharacterView view : npcViews){
            view.dispose();
        }

        for (ItemView view : itemViews) {
            view.dispose();
        }
    }
}
