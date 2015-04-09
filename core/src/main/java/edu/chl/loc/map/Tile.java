package edu.chl.loc.map;

import edu.chl.loc.utilities.Position2D;
import javafx.geometry.Pos;

/**
 * A class representing tiles on the map, this tile has no special ability
 *
 * Revised by Alexander Håkansson
 *
 * @author Kevin Hoogendijk
 * @version 1.0.0
 * @since 2015-04-07
 */
public class Tile implements ITile{

    private boolean isCollidable = false;
    private Position2D pos;

    /**
     * Creates a new tile with the specified coordinates and sets isCollidable
     * the tile is by default not collidable
     *
     * @param x the x coordiante of the tile
     * @param y the y coordinate of the tile
     * @param isCollidable if the tile is collidable or not
     */
    public Tile(double x, double y, boolean isCollidable){
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
    public Tile(double x, double y){
        pos.add(x, y);
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
    public double getX() {
        return this.pos.getX();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getY() {
        return this.pos.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        Tile other = (Tile) o;
        return this.pos.getX() == other.pos.getX() && this.pos.getY() == other.pos.getY();
    }
}
