package edu.chl.loc.map;

import edu.chl.loc.characters.NPC;
import edu.chl.loc.utilities.Position2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-08
 */
public class GameMap extends AbstractMap {

    private final List<NPC> npcList =  new ArrayList<NPC>();

    public GameMap() {
        super();
    }

    /**
     * Add an NPC to the map if it hasn't already been added.
     *
     * @param npc The NPC to add.
     */
    public void addNPC(final NPC npc) {
        if (!npcList.contains(npc)) {
            npcList.add(npc);
        }
    }

    /**
     * Get a list of all the NPCs on the map.
     *
     * @return A list of all NPCs on the map
     */
    public List<NPC> getAllNPCs() {
        return npcList;
    }

    /**
     * Get the NPC on the specified position. This only gets the first NPC
     * and if there happen to be more than one NPC on the same position
     * this will only get one. If no NPC can be found on the given position,
     * and exception will be thrown.
     *
     * @param pos The position of the NPC to get
     * @return The NPC at the specified position
     * @throws java.lang.IllegalArgumentException If there is not NPC at the given position
     */
    public NPC getNPCAtPosition(final Position2D pos) {
        for (NPC npc : npcList) {
            if (npc.getPosition().equals(pos)) {
                return npc;
            }
        }

        throw new IllegalArgumentException("No NPC at the specified position");
    }

    /**
     * Get the NPC on the specified position. This only gets the first NPC
     * and if there happen to be more than one NPC on the same position
     * this will only get one. If no NPC can be found on the given position,
     * and exception will be thrown.
     *
     * @param x The position in the x-axis of the NPC to get
     * @param y The position in the y-axis of the NPC to get
     * @return The NPC at the specified position
     * @throws java.lang.IllegalArgumentException If there is not NPC at the given position
     */
    public NPC getNPCAtPosition(double x, double y) {
        return getNPCAtPosition(new Position2D(x, y));
    }

    /**
     * Check if there is any NPC at the given position on the map.
     *
     * @param pos The position to check for an NPC
     * @return True if there is any NPC at the given position, false otherwise
     */
    public boolean npcExisitsAtPosition(final Position2D pos) {
        for (NPC npc : npcList) {
            if (npc.getPosition().equals(pos)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if there is any NPC at the given position on the map.
     *
     * @param x The position in the x-axis to check for an NPC
     * @param y The position in the y-axis to check for an NPC
     * @return True if there is any NPC at the given position, false otherwise
     */
    public boolean npcExistsAtPosition(double x, double y) {
        return npcExisitsAtPosition(new Position2D(x, y));
    }
}
