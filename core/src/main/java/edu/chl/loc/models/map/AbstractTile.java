package edu.chl.loc.models.map;

import edu.chl.loc.models.utilities.Position2D;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-24
 */
public abstract class AbstractTile implements ITile {
    private boolean isCollidable = false;
    private final Position2D position;

    public AbstractTile(final Position2D position) {
        this.position = position.copy();
    }

    @Override
    public boolean isCollidable() {
        return this.isCollidable;
    }

    @Override
    public void setIsCollidable(boolean collidable) {
        this.isCollidable = collidable;
    }

    @Override
    public Position2D getPosition() {
        return this.position.copy();
    }
}
