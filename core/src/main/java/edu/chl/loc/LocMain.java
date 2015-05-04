package edu.chl.loc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import edu.chl.loc.controller.GameController;
import edu.chl.loc.models.characters.npc.NPCFactory;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.models.items.Inventory;
import edu.chl.loc.models.map.GameMap;
import edu.chl.loc.models.utilities.Position2D;
import edu.chl.loc.view.core.GameView;
import javafx.geometry.Pos;

import java.util.Iterator;
import java.util.List;

/**
 * @author Alexander Håkansson
 */
public class LocMain extends Game {
	private float elapsed;
    private float delta;

    private GameModel model;
    private GameController controller;
    private GameView view;

	@Override
	public void create () {
        model = new GameModel();
        controller = new GameController(model);
        view = new GameView(model);

        Gdx.input.setInputProcessor(controller);

        setScreen(view);
    }

	@Override
	public void resize (int width, int height) {
	    getScreen().resize(width, height);
    }

	@Override
	public void render () {
        delta = Gdx.graphics.getDeltaTime();
		elapsed += delta;
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        getScreen().render(delta);
	}

	@Override
	public void pause () {
        getScreen().pause();
	}

	@Override
	public void resume () {
        getScreen().resume();
	}

	@Override
	public void dispose () {
        getScreen().dispose();
	}

    /**
     * Creates all the NPCs from the textfile NPCs.loc and adds each
     * of the to the gamemap
     */
    private void createNPCsFromFile(){
        Position2D position;
        List<List<String>> NPCList = FileUtilities.readFile("NPCs.loc");
        for(List<String> NPCProperty: NPCList){
            NPCFactory.setId(Integer.parseInt(NPCProperty.get(0)));
            NPCFactory.setName(NPCProperty.get(1));
            NPCFactory.setGender(Gender.valueOf(NPCProperty.get(2)));
            position = new Position2D(Integer.parseInt(NPCProperty.get(3)),
                                      Integer.parseInt(NPCProperty.get(4)));
            //TODO: create Inventory with items that are specified
            //TODO: check the position of the NPC before creating it
            //TODO: call this method somewhere before the game is rendered
            model.getGameMap().addNPC(NPCFactory.build(position));
        }
    }
}
