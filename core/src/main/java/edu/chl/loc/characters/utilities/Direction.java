package edu.chl.loc.characters.utilities;

import edu.chl.loc.utilities.Position2D;

/**
 * Created by Maxim on 15-04-03.
 * @author Maxim
 *
 * Enum to represent possible directions
 */
public enum Direction {

    NORTH{
        @Override
        public Position2D getDelta(){
            return new Position2D(0,1);
        }
        @Override
    public Direction getOpposite(){
            return SOUTH;
        }
    },
    EAST{
        @Override
        public Position2D getDelta(){
            return new Position2D(1, 0);
        }
        @Override
        public Direction getOpposite(){
            return WEST;
        }
    },
    SOUTH{
        @Override
        public Position2D getDelta(){
            return new Position2D(0, -1);
        }
        @Override
        public Direction getOpposite(){
            return NORTH;
        }
    },
    WEST{
        @Override
        public Position2D getDelta(){
            return new Position2D(-1, 0);
        }
        @Override
        public Direction getOpposite(){
            return EAST;
        }
    };
    /**

        Used to display change(delta) in a direction.
        @return Position2D object with delta values.
     */
    public abstract Position2D getDelta();

    /**
     *
     * @return opposite direction of current direction
     */
    public abstract Direction getOpposite();
}
