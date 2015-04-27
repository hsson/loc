package edu.chl.loc.models.characters.npc;

import edu.chl.loc.models.characters.utilities.CharacterUtilities;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.items.Inventory;
import edu.chl.loc.models.minigames.IMinigame;
import edu.chl.loc.models.utilities.Position2D;

/**
 * Factory class used for creating NPCs
 * @author Alexander Karlsson
 * @version 1.1
 */
public class NPCFactory {
    private static String name = null;
    private static Gender gender = null;
    private static Direction direction = null;
    private static Inventory inventory = null;
    private static IMinigame minigame = null;
    private static Dialog dialog = null;

    /**
     * Sets the name of the NPC currently being built
     * @param name The name of the NPC to create
     */
    public static void setName(String name){
        NPCFactory.name = name;
    }

    /**
     * Sets the gender of the NPC currently being built
     * @param gender The gender of the NPC to create
     */
    public static void setGender(Gender gender){
        NPCFactory.gender = gender;
    }

    /**
     * Sets the direction of the NPC currently being built
     * @param direction The direction of the NPC to create
     */
    public static void setDirection(Direction direction){
        NPCFactory.direction = direction;
    }

    /**
     * Sets the inventory of the NPC being built
     * @param inventory The inventory of the NPC being built
     * @throws CannotSetThisValueException If the current build already has a minigame set a inventory cant be set
     */
    public static void setInventory(Inventory inventory) throws CannotSetThisValueException{
        if(NPCFactory.isMinigameSet()){
            throw new CannotSetThisValueException("Cannot set inventory when minigame is set");
        }else{
            NPCFactory.inventory = inventory;
        }
    }

    /**
     * Sets the minigame of the NPC being built
     * @param minigame The minigame of the NPC being built
     * @throws CannotSetThisValueException If the current build already has an inventory set a minigame cant be set
     */
    public static void setMinigame(IMinigame minigame) throws CannotSetThisValueException{
        if(NPCFactory.isInventorySet()){
            throw new CannotSetThisValueException("Cannot set minigame when inventory is set");
        }else {
            NPCFactory.minigame = minigame;
        }
    }

    /**
     * Sets the dialog of the NPC being built
     * @param dialog The dialog of the NPC to create
     */
    public static void setDialog(Dialog dialog){
        NPCFactory.dialog = dialog;
    }

    /**
     * Checks if the current build has an inventory set
     * @return True if an inventory is set, false otherwise
     */
    public static boolean isInventorySet(){
        return NPCFactory.inventory != null;
    }

    /**
     * Checks if the current build has a minigame set
     * @return True if a minigame is set, false otherwise
     */
    public static boolean isMinigameSet(){
        return NPCFactory.minigame != null;
    }

    /**
     * Resets all the values of the current build
     */
    public static void reset(){
        NPCFactory.name = null;
        NPCFactory.gender = null;
        NPCFactory.direction = null;
        NPCFactory.inventory = null;
        NPCFactory.minigame = null;
    }

    /**
     * Builds and returns the current build using the values from the set methods
     * @param position The position the new NPC should have
     * @return An NPC with values from set methods and given position
     */
    public static AbstractNPC build(Position2D position){
        Gender gender;
        String name;
        Direction direction;
        Inventory inventory;
        IMinigame minigame;
        Dialog dialog;

        if(NPCFactory.gender == null){
            gender = CharacterUtilities.generateGender();
        }else{
            gender = NPCFactory.gender;
        }

        if(NPCFactory.name == null){
            name = CharacterUtilities.generateName(gender);
        }else{
            name = NPCFactory.name;
        }

        if(NPCFactory.direction == null){
            direction = Direction.NORTH;//Standard direction is north
        }else{
            direction = NPCFactory.direction;
        }

        if(NPCFactory.dialog == null){
            dialog = CharacterUtilities.generateDialog();
        }else{
            dialog = NPCFactory.dialog;
        }

        if(isInventorySet()){
            inventory = NPCFactory.inventory;
            NPCFactory.reset();
            return new ItemNPC(position,direction,name,gender,inventory,dialog);
        }

        if(isMinigameSet()){
            minigame = NPCFactory.minigame;
            NPCFactory.reset();
            return new MinigameNPC(position,direction,name,gender,minigame,dialog);
        }

        return new StandardNPC(position,direction,name,gender,dialog);
    }
}
