package edu.chl.loc.characters.npc;

import edu.chl.loc.characters.utilities.CharacterUtilities;
import edu.chl.loc.characters.utilities.Direction;
import edu.chl.loc.characters.utilities.Gender;
import edu.chl.loc.items.Inventory;
import edu.chl.loc.utilities.Position2D;

/**
 * Factory class used for creating NPCs
 * @author Alexander Karlsson
 * @version 0.1
 */
public class NPCFactory {
    /**
     * Creates an NPC with a given position, standard direction (north),
     * empty inventory and random other values
     * @return A new NPC object with given values and randomized other values
     */
    public static NPC createNPC(Position2D position){
        Gender gender = CharacterUtilities.generateGender();
        String name = CharacterUtilities.generateName(gender);
        NPC npc = new NPC(position, Direction.NORTH, name, new Inventory(), gender);
        return npc;
    }

    /**
     * Creates an NPC with given values and randomizes name and gender
     * @param position The position of the NPC
     * @param direction The direction of the NPC
     * @param inventory The NPCs inventory
     * @return An NPC with given values and randomized name and gender
     */
    public static NPC createNPC(Position2D position, Direction direction, Inventory inventory){
        Gender gender = CharacterUtilities.generateGender();
        String name = CharacterUtilities.generateName(gender);
        return new NPC(position,direction,name,inventory,gender);
    }

    /**
     * Creates and returns an NPC with all given values
     * @param position The position for the NPC
     * @param direction The NPCs direction
     * @param name The NPCs name
     * @param inventory The NPCs inventory
     * @param gender The gender of the NPC
     * @return A new
     */
    public static NPC createNPC(Position2D position, Direction direction, String name, Inventory inventory, Gender gender){
        return new NPC(position, direction, name, inventory, gender);
    }
}
