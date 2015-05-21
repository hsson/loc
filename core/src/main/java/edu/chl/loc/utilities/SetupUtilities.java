package edu.chl.loc.utilities;

import edu.chl.loc.models.characters.npc.AbstractNPC;
import edu.chl.loc.models.characters.npc.Dialog;
import edu.chl.loc.models.characters.npc.InvalidIdException;
import edu.chl.loc.models.characters.npc.NPCFactory;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
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


}
