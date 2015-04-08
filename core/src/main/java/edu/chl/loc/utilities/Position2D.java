package edu.chl.loc.utilities;

/**
 * Created by Maxim on 15-04-03.
 * @author Maxim
 *
 * Revised by Alexander Håkansson
 *
 * Class representing the position on a 2D-plane.
 */


public class Position2D {
    private double xCoord;
    private double yCoord;

    public Position2D(double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Position2D(){
        this.xCoord = 0;
        this.yCoord = 0;
    }

    public double getX(){
        return xCoord;
    }


    public double getY(){
        return yCoord;
    }

    /**
     * Adds given x- and y-coordinates to this Position2D.
     *
     * returns a new Position2D with the sum of x and y coordinates.
     */
    public Position2D add(double x, double y){
        double tempX = this.getX() + x;
        double tempY = this.getY() + y;
        return new Position2D(tempX, tempY);
    }

    /**
     * Adds given the Position2D to this Position2D.
     *
     * throws IllegalArgumentException if given Position2D == null
     * returns a new Position2D with the sum of x and y coordinates.
     */
    public Position2D add(Position2D position){
        if(position == null){
            throw new IllegalArgumentException("Can only add positions");
        }
        double tempX = this.xCoord + position.getX();
        double tempY = this.yCoord + position.getY();
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

    public String toString(){
        return "Position of X-Coordinate is" + getX() + ", and Position of Y-Coordinate is " + getY();
    }
}
