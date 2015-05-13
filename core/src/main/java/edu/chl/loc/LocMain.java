package edu.chl.loc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import edu.chl.loc.controller.GameController;
import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.minigame.IMinigameHandlerListener;
import edu.chl.loc.minigame.MinigameHandler;
import edu.chl.loc.models.characters.npc.Dialog;
import edu.chl.loc.models.characters.npc.InvalidIdException;
import edu.chl.loc.models.characters.npc.NPCFactory;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.models.items.ItemScore;
import edu.chl.loc.models.map.ILayer;
import edu.chl.loc.models.map.ItemTile;
import edu.chl.loc.models.map.Layer;
import edu.chl.loc.models.map.Tile;
import edu.chl.loc.models.utilities.Position2D;
import edu.chl.loc.utilities.FileUtilities;
import edu.chl.loc.view.core.GameView;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author Alexander Håkansson
 * Revised by Alexander Karlsson
 */
public class LocMain extends Game implements IMinigameHandlerListener {
	private float elapsed;
    private float delta;

    private GameModel model;
    private GameController controller;
    private GameView view;

	@Override
	public void create () {
        model = new GameModel();
        setupGameMap();
        createNPCsFromFile();
        controller = new GameController(model);
        view = new GameView(model);
        MinigameHandler handler = MinigameHandler.getInstance();
        handler.registerListener(this);
        Gdx.input.setInputProcessor(controller);



        setScreen(view);
    }

    private void setupGameMap() {
        TiledMap johanneberg = new TmxMapLoader().load(Gdx.files.internal("maps/johanneberg.tmx").path());

        for (MapLayer mapLayer : johanneberg.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer) mapLayer;
            ILayer layer = new Layer(tiledLayer.getName());

            model.getGameMap().addLayer(layer);

            for (int y = 0; y < tiledLayer.getHeight(); y++) {
                for (int x = 0; x < tiledLayer.getWidth(); x++) {
                    boolean collision = false;
                    TiledMapTile mapTile;
                    if (tiledLayer.getCell(x, y) != null && (mapTile = tiledLayer.getCell(x,y).getTile()) != null) {

                        if (mapLayer.getName().equals("items")) {
                            setupItem(layer, mapTile, new Position2D(x, y));
                            continue;
                        } else if (mapTile.getProperties().containsKey("collision")) {
                            String property = (String) mapTile.getProperties().get("collision");
                            collision = property.equals("true");
                        }
                        model.getGameMap().addTile(layer, new Tile(new Position2D(x, y), collision));
                    }
                }
            }
        }
    }

    private void setupItem(ILayer layer, TiledMapTile tile, Position2D position) {
        MapProperties properties = tile.getProperties();

        if (properties != null && properties.containsKey("type")) {
            if (properties.get("type").equals("beverage")) {
                String name = (String) properties.get("name");
                Integer score = Integer.parseInt((String) properties.get("score"));
                model.getGameMap().addTile(layer, new ItemTile(new ItemScore(name, score), position));
            }
        }
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
        List<List<String>> NPCList = null;
        try {
            NPCList = FileUtilities.readFile("NPCs.loc");
        } catch (FileNotFoundException e) {
            System.err.println("Could not start game, non existent file");
            System.exit(1);

        }
        for(List<String> NPCProperty: NPCList){
			int id = Integer.parseInt(NPCProperty.get(0));
            NPCFactory.setId(id);
			try{
				Dialog dialog = new Dialog(id, "Dialogs.loc");
				NPCFactory.setDialog(dialog);
			}catch(InvalidIdException e){
				//NPCFactory automatically generates a random dialog if none is specified in the file
			}catch(FileNotFoundException ex){
                //NPCFactory automatically generates a random dialog if none is specified in the file
            }
            NPCFactory.setName(NPCProperty.get(1));
            NPCFactory.setGender(Gender.valueOf(NPCProperty.get(2)));
            position = new Position2D(Integer.parseInt(NPCProperty.get(3)),
                                      Integer.parseInt(NPCProperty.get(4)));
            if(id>=2000 && id<=2999){
                try{
                    NPCFactory.setMinigame(FileUtilities.idToMinigame(id));
                }catch(IllegalArgumentException e){
                    //If no minigame matches the id no minigame will be set
                }
            }
            //TODO: create Inventory with items that are specified
            //TODO: check the position of the NPC before creating it
            //TODO: call this method somewhere before the game is rendered
            model.getGameMap().addNPC(NPCFactory.build(position));
        }
    }

    @Override
    public void minigameFinished() {
        Gdx.input.setInputProcessor(controller);
        setScreen(this.view);
    }

    @Override
    public void startMinigame(IMinigame minigame) {
        InputProcessor controller = minigame.getController();
        Screen view = minigame.getView();
        Gdx.input.setInputProcessor(controller);
        setScreen(view);
    }
}