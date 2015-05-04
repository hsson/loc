package edu.chl.loc.view.map;

import com.badlogic.gdx.graphics.Texture;
import edu.chl.loc.models.characters.npc.AbstractNPC;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.view.characters.CharacterView;
import edu.chl.loc.view.characters.NPCTextureFactory;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alexander Håkansson
 * @version 0.3.0
 * @since 2015-04-30
 * Revised by Alexander Karlsson
 */
public class GameMapView implements IView {

    private final GameView gameView;
    private final GameModel gameModel;

    private Set<CharacterView> npcViews;

    public GameMapView(final GameView gameView) {
        this.gameView = gameView;
        this.gameModel = gameView.getGameModel();

        // TODO: Create CharacterViews and ItemViews based on something so they can be rendered
        createNPCViews();
    }

    /**
     * Gets all NPC from the model and creates views for them.
     */
    private void createNPCViews() {
        npcViews = new HashSet<CharacterView>();

        for (AbstractNPC npc : gameModel.getGameMap().getAllNPCs()) {
            Texture npcTexture = NPCTextureFactory.build(npc);
            npcViews.add(new CharacterView(npc, npcTexture));
        }
    }

    @Override
    public void render() {

        // Render NPCs
        for (CharacterView view : npcViews) {
            view.render();
        }
    }

    @Override
    public void dispose() {
        for(CharacterView view : npcViews){
            view.dispose();
        }
    }
}
