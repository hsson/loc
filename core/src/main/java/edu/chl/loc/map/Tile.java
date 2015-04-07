package edu.chl.loc.map;

/**
 * A class representing tiles on the map, this tile has no special ability
 *
 * @author Kevin Hoogendijk
 * @version 1.0.0
 * @since 2015-04-07
 */
public class Tile implements ITile{

    private boolean isCollidable = false;
    private int x;
    private int y;

    /**
     * Creates a new tile with the specified coordinates and sets isCollidable
     * the tile is by default not collidable
     *
     * @param x the x coordiante of the tile
     * @param y the y coordinate of the tile
     * @param isCollidable if the tile is collidable or not
     */
    public Tile(int x, int y, boolean isCollidable){
        this(x, y);
        this.isCollidable = isCollidable;
    }

    /**
     * Creates a new tile with the specified coordinates
     * the tile is by default not collidable
     *
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     */
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCollidable() {
        return this.isCollidable;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setIsCollidable(boolean isCollidable) {
        this.isCollidable = isCollidable;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasItem() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getY() {
        return this.y;
    }
}
