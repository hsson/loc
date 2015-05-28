package edu.chl.loc.model.utilities;

/**
 * Created by Maxim on 15-04-03.
 * @author Maxim
 *
 * Revised by Alexander Håkansson
 *
 * Class representing the position on a 2D-plane.
 */


public class Position2D {
    private int xCoord;
    private int yCoord;

    /**
     * Creates a position2D object with given coordinates
     * @param xCoord X coordinate you want this position to have
     * @param yCoord Y coordinate you want this position to have
     */
    public Position2D(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * Creates a position2D with default coordinates being (0,0)
     */
    public Position2D(){
        this.xCoord = 0;
        this.yCoord = 0;
    }

    /**
     * Constructor used for copying, refer to copy() method.
     * @param copyPos Position2D you want to copy
     */
    public Position2D(Position2D copyPos){
        this.xCoord = copyPos.getX();
        this.yCoord = copyPos.getY();
    }

    /**
     *
     * @return X coordinate of this position
     */
    public int getX(){
        return xCoord;
    }

    /**
     *
     * @return Y coordinate of this position
     */
    public int getY(){
        return yCoord;
    }
    /**
     * Adds given the Position2D to this Position2D.
     *
     * @throws IllegalArgumentException if given Position2D == null
     * @return a new Position2D with the sum of x and y coordinates.
     */
    public Position2D add(Position2D position){
        if(position == null){
            throw new IllegalArgumentException("Can only add positions");
        }
        int tempX = this.xCoord + position.getX();
        int tempY = this.yCoord + position.getY();
        return new Position2D(tempX, tempY);
    }

    /**
     * Check if the specified object is equal to this Position2D. The
     * equality if determined by the x and y coordinates of the Position2D.
     *
     * @param o The object to compare
     * @return True if the two position share the same x and y coordinates
     */
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

        Position2D other = (Position2D) o;
        return this.xCoord == other.xCoord && this.yCoord == other.yCoord;
    }

    @Override
    public int hashCode(){
        return 104759 + xCoord * 17 + yCoord *47;
    }

    public String toString(){
        return "Position of X-Coordinate is" + getX() + ", and Position of Y-Coordinate is " + getY();
    }

    /**
     * Copy method to clone a current Position2D
     * @return Position2D with same values
     */
    public Position2D copy(){
        return new Position2D(this);
    }
}
