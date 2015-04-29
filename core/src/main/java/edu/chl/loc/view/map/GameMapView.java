package edu.chl.loc.view.map;

import edu.chl.loc.models.characters.npc.AbstractNPC;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.view.characters.CharacterView;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Alexander Håkansson
 * @version 0.1.0
 * @since 2015-04-30
 */
public class GameMapView implements IView {

    private final GameView gameView;
    private final GameModel gameModel;

    private Set<CharacterView> npcViews = new HashSet<CharacterView>();
    private List<AbstractNPC> npcModels;

    public GameMapView(final GameView gameView) {
        this.gameView = gameView;
        this.gameModel = gameView.getGameModel();

        // TODO: Create CharacterViews and ItemViews based on something so they can be rendered
    }

    @Override
    public void render() {

    }
}
