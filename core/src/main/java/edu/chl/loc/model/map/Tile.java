package edu.chl.loc.model.map;

import edu.chl.loc.model.utilities.Position2D;

/**
 * A class representing tiles on the map, this tile has no special ability
 *
 * Revised by Alexander Håkansson
 *
 * @author Kevin Hoogendijk
 * @version 2.0.0
 * @since 2015-04-07
 */
public class Tile extends AbstractTile{

    /**
     * Creates a new tile with the specified coordinates and sets isCollidable
     * the tile is by default not collidable
     *
     * @param position Theposition of the Tile
     * @param isCollidable if the tile is collidable or not
     */
    public Tile(Position2D position, boolean isCollidable){
        super(position);
        this.setIsCollidable(isCollidable);
    }

    /**
     * Creates a new tile with the specified coordinates
     * the tile is by default not collidable
     *
     * @param position The position of the Tile
     */
    public Tile(Position2D position){
        super(position);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasItem() {
        return false;
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
        return this.getPosition().equals(other.getPosition());
    }

    @Override
    public int hashCode(){
        return 104761 + getPosition().hashCode() * 13;
    }
}
