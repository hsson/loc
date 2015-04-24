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
     * Creates an AbstractNPC with a given position, standard direction (north),
     * empty inventory and random other values
     * @return A new AbstractNPC object with given values and randomized other values
     */
    public static AbstractNPC createNPC(Position2D position){
        Gender gender = CharacterUtilities.generateGender();
        String name = CharacterUtilities.generateName(gender);
        AbstractNPC npc = new AbstractNPC(position, Direction.NORTH, name, new Inventory(), gender);
        return npc;
    }

    /**
     * Creates an AbstractNPC with given values and randomizes name and gender
     * @param position The position of the AbstractNPC
     * @param direction The direction of the AbstractNPC
     * @param inventory The NPCs inventory
     * @return An AbstractNPC with given values and randomized name and gender
     */
    public static AbstractNPC createNPC(Position2D position, Direction direction, Inventory inventory){
        Gender gender = CharacterUtilities.generateGender();
        String name = CharacterUtilities.generateName(gender);
        return new AbstractNPC(position,direction,name,inventory,gender);
    }

    /**
     * Creates and returns an AbstractNPC with all given values
     * @param position The position for the AbstractNPC
     * @param direction The NPCs direction
     * @param name The NPCs name
     * @param inventory The NPCs inventory
     * @param gender The gender of the AbstractNPC
     * @return A new
     */
    public static AbstractNPC createNPC(Position2D position, Direction direction, String name, Inventory inventory, Gender gender){
        return new AbstractNPC(position, direction, name, inventory, gender);
    }
}
