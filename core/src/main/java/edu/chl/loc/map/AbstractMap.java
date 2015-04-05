package edu.chl.loc.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A general representation of maps.
 *
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-05
 */
public abstract class AbstractMap {

    private final int sizeX;
    private final int sizeY;

    private final int nbrOfLayers;

    private Map<Integer, List<ITile>> mapTiles = new HashMap<Integer, List<ITile>>();

    public AbstractMap(final int sizeX, final int sizeY, final int nbrOfLayers) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.nbrOfLayers = nbrOfLayers;
    }

    /**
     * Returns the size of the map in the x-axis.
     *
     * @return Map size in x-axis
     */
    public final int getSizeX() {
        return this.sizeX;
    }

    /**
     * Returns the size of the map in the y-axis.
     *
     * @return Map size in y-axis
     */
    public final int getSizeY() {
        return this.sizeY;
    }

    /**
     * Returns the total amount of layers on the map.
     *
     * @return Total amount of layers
     */
    public final int getNbrOfLayers() {
        return this.nbrOfLayers;
    }

    /**
     * Checks if the tile on the specified layer and coordinates is collidable
     * or not. If the tile can't be found, an exception is thrown.
     *
     * @param layer The layer on which to check
     * @param x The x coordinate of the tile to check
     * @param y THe y coordinate of the tile to check
     * @throws java.lang.IllegalArgumentException If the specified tile and layer doesn't exist
     * @return Returns true if the specified tile is collidable, false otherwise
     */
    public boolean isCollidable(int layer, int x, int y) {
        if (!mapTiles.containsKey(layer)) {
            throw new IllegalArgumentException("The layer specified does not exist");
        }

        for (ITile tile : mapTiles.get(layer)) {
            if (tile.getX() == x && tile.getY() == y) {
                return tile.isCollidable();
            }
        }

        throw new IllegalArgumentException("The tile specified at x=" + x +" and y=" + y + ", does not exist");
    }

    /**
     * Gets the tile on the specified layer and coordinates.
     * If the tile can't be found, an exception is thrown.
     *
     * @param layer The layer on which to get
     * @param x The x coordinate of the tile to get
     * @param y THe y coordinate of the tile to get
     * @throws java.lang.IllegalArgumentException If the specified tile and layer doesn't exist
     * @return Returns the specified tile
     */
    public ITile getTile(int layer, int x, int y) {
        if (!mapTiles.containsKey(layer)) {
            throw new IllegalArgumentException("The layer specified does not exist");
        }

        for (ITile tile : mapTiles.get(layer)) {
            if (tile.getX() == x && tile.getY() == y) {
                return tile;
            }
        }

        throw new IllegalArgumentException("The tile specified at x=" + x +" and y=" + y + ", does not exist");
    }
}
