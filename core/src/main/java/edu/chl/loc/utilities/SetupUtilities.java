package edu.chl.loc.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import edu.chl.loc.models.characters.npc.AbstractNPC;
import edu.chl.loc.models.characters.npc.Dialog;
import edu.chl.loc.models.characters.npc.InvalidIdException;
import edu.chl.loc.models.characters.npc.NPCFactory;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.items.ItemScore;
import edu.chl.loc.models.map.*;
import edu.chl.loc.models.menu.GameMenu;
import edu.chl.loc.models.utilities.Position2D;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Hoogendijk
 * @version 1.0.0
 * @since 2015-05-21
 */
public class SetupUtilities {

    private SetupUtilities(){

    }

    /**
     * Creates all the NPCs from the textfile NPCs.loc and adds each
     * of the to the gamemap
     * @return List of all the NPCs that are created
     */
    public static List<AbstractNPC> createNPCsFromFile(){
        Position2D position;
        List<AbstractNPC> builtNpcs = new ArrayList<AbstractNPC>();
        List<List<String>> npcList = null;
        try {
            npcList = FileUtilities.readFile("NPCs.loc");
        } catch (FileNotFoundException e) {
            System.err.println("Could not start game, non existent file");
            System.exit(1);
        }
        for(List<String> npcProperty: npcList){
            int id = Integer.parseInt(npcProperty.get(0));
            NPCFactory.setId(id);
            try{
                Dialog dialog = new Dialog(id, "Dialogs.loc");
                NPCFactory.setDialog(dialog);
            }catch(InvalidIdException e){
                //NPCFactory automatically generates a random dialog if none is specified in the file
            }catch(FileNotFoundException ex){
                //NPCFactory automatically generates a random dialog if none is specified in the file
            }
            NPCFactory.setName(npcProperty.get(1));
            NPCFactory.setGender(Gender.valueOf(npcProperty.get(2)));
            position = new Position2D(Integer.parseInt(npcProperty.get(3)),
                    Integer.parseInt(npcProperty.get(4)));
            if(id>=2000 && id<=2999){
                try{
                    NPCFactory.setMinigame(FileUtilities.idToMinigame(id));
                }catch(IllegalArgumentException e){
                    //If no minigame matches the id no minigame will be set
                }
            }
            NPCFactory.setDirection(Direction.valueOf(npcProperty.get(5)));
            builtNpcs.add(NPCFactory.build(position));
        }
        return builtNpcs;
    }

    public static GameMap setupGameMap(String pathToTmxFile) {
        TiledMap johanneberg = new TmxMapLoader().load(Gdx.files.internal(pathToTmxFile).path());
        GameMap map = new GameMap();

        for (MapLayer mapLayer : johanneberg.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer) mapLayer;
            ILayer layer = new Layer(tiledLayer.getName());

            map.addLayer(layer);

            for (int y = 0; y < tiledLayer.getHeight(); y++) {
                for (int x = 0; x < tiledLayer.getWidth(); x++) {
                    boolean collision = false;
                    TiledMapTile mapTile;
                    if (tiledLayer.getCell(x, y) != null && (mapTile = tiledLayer.getCell(x,y).getTile()) != null) {

                        if (mapLayer.getName().equals("items")) {
                            setupItem(layer, mapTile, new Position2D(x, y), map);
                            continue;
                        } else if (mapTile.getProperties().containsKey("collision")) {
                            String property = (String) mapTile.getProperties().get("collision");
                            collision = property.equals("true");
                        }
                        map.addTile(layer, new Tile(new Position2D(x, y), collision));
                    }
                }
            }

        }

        return map;
    }

    public static void setupItem(ILayer layer, TiledMapTile tile, Position2D position, GameMap map) {
        MapProperties properties = tile.getProperties();

        if (properties != null && properties.containsKey("type")) {
            if (properties.get("type").equals("beverage")) {
                String name = (String) properties.get("name");
                Integer score = Integer.parseInt((String) properties.get("score"));
                map.addTile(layer, new ItemTile(new ItemScore(name, score), position));
            }
        }
    }


}
