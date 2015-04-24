package edu.chl.loc.map;

import edu.chl.loc.characters.npc.AbstractNPC;
import edu.chl.loc.utilities.Position2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-08
 */
public class GameMap extends AbstractMap {

    private final List<AbstractNPC> npcList =  new ArrayList<AbstractNPC>();

    public GameMap() {
        super();
    }

    /**
     * Add an AbstractNPC to the map if it hasn't already been added.
     *
     * @param npc The AbstractNPC to add.
     */
    public void addNPC(final AbstractNPC npc) {
        if (!npcList.contains(npc)) {
            npcList.add(npc);
        }
    }

    /**
     * Get a list of all the NPCs on the map.
     *
     * @return A list of all NPCs on the map
     */
    public List<AbstractNPC> getAllNPCs() {
        return npcList;
    }

    /**
     * Get the AbstractNPC on the specified position. This only gets the first AbstractNPC
     * and if there happen to be more than one AbstractNPC on the same position
     * this will only get one. If no AbstractNPC can be found on the given position,
     * and exception will be thrown.
     *
     * @param pos The position of the AbstractNPC to get
     * @return The AbstractNPC at the specified position
     * @throws java.lang.IllegalArgumentException If there is not AbstractNPC at the given position
     */
    public AbstractNPC getNPCAtPosition(final Position2D pos) {
        for (AbstractNPC npc : npcList) {
            if (npc.getPosition().equals(pos)) {
                return npc;
            }
        }

        throw new IllegalArgumentException("No AbstractNPC at the specified position");
    }

    /**
     * Get the AbstractNPC on the specified position. This only gets the first AbstractNPC
     * and if there happen to be more than one AbstractNPC on the same position
     * this will only get one. If no AbstractNPC can be found on the given position,
     * and exception will be thrown.
     *
     * @param x The position in the x-axis of the AbstractNPC to get
     * @param y The position in the y-axis of the AbstractNPC to get
     * @return The AbstractNPC at the specified position
     * @throws java.lang.IllegalArgumentException If there is not AbstractNPC at the given position
     */
    public AbstractNPC getNPCAtPosition(int x, int y) {
        return getNPCAtPosition(new Position2D(x, y));
    }

    /**
     * Check if there is any AbstractNPC at the given position on the map.
     *
     * @param pos The position to check for an AbstractNPC
     * @return True if there is any AbstractNPC at the given position, false otherwise
     */
    public boolean npcExisitsAtPosition(final Position2D pos) {
        for (AbstractNPC npc : npcList) {
            if (npc.getPosition().equals(pos)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if there is any AbstractNPC at the given position on the map.
     *
     * @param x The position in the x-axis to check for an AbstractNPC
     * @param y The position in the y-axis to check for an AbstractNPC
     * @return True if there is any AbstractNPC at the given position, false otherwise
     */
    public boolean npcExistsAtPosition(int x, int y) {
        return npcExisitsAtPosition(new Position2D(x, y));
    }
}
