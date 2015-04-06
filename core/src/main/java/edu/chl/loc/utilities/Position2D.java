package edu.chl.loc.utilities;

/**
 * Created by Maxim on 15-04-03.
 * @author Maxim
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

    /*adds given x- and y-coordinates to this Position2D.
     * returns a new Position2D with the sum of x and y coordinates.
     *
     */
    public Position2D add(double x, double y){
        double tempX = this.getX() + x;
        double tempY = this.getY() + y;
        return new Position2D(tempX, tempY);
    }

    /*adds given the Position2D to this Position2D.
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

    public String toString(){
        return "Position of X-Coordinate is" + getX() + ", and Position of Y-Coordinate is " + getY();
    }
}
