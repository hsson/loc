package edu.chl.loc.map;

/**
 * @author Alexander Håkansson, Kevin Hoogendijk
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
     * Sets collision of the tile
     *
     * @param isCollidable
     */
    public void setIsCollidable(boolean isCollidable);

    /**
     * returns if the tile has an item (is an itemTile)
     * necessary since we have to be able to check on every if it has an item
     *
     * @return True if the tile has an item (is an itemTile)
     */
    public boolean hasItem();

    /**
     * Get the x position of the tile
     *
     * @return The position in the x-axis
     */
    public double getX();

    /**
     * Get the y position of the tile
     *
     * @return The position in the y-axis
     */
    public double getY();

}
