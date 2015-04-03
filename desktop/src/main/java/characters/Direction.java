package characters;

import utilities.Position2D;

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
            return new Position2D(0.0,1.0);
        }
    },
    EAST{
        @Override
        public Position2D getDelta(){
            return new Position2D(1.0,0.0);
        }
    },
    SOUTH{
        @Override
        public Position2D getDelta(){
            return new Position2D(0.0,-1.0);
        }
    },
    WEST{
        @Override
        public Position2D getDelta(){
            return new Position2D(-1.0,0.0);
        }
    };
    /*
        Returns a Position2D object with delta values.
        Used to display change(delta) in a direction.
     */
    public abstract Position2D getDelta();

}
