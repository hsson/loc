package edu.chl.loc.map;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-05
 */
public interface ITile {

    /**
     * Returns whether it is possible to collide with the tile or not.
     *
     * @return True if the tile is collidable, false otherwise
     */
    public boolean isCollidable();

    /**
     * Get the x position of the tile
     *
     * @return The position in the x-axis
     */
    public int getX();

    /**
     * Get the y position of the tile
     *
     * @return The position in the y-axis
     */
    public int getY();

}
