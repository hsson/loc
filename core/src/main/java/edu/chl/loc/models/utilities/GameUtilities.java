package edu.chl.loc.models.utilities;

/**@author Maxim Goretskyy
 * Created by maxim on 15-05-03.
 */

/**
 * Class for utilities that concern the game
 */
public class GameUtilities {

    /**
     * Prevent from instantiating
     */
    private GameUtilities(){

    }

    /**
     * Method used to calculate distance between two positions
     * Could be used when calculating distance to Hubben to play a song.
     * @param startPos The position you want to start from
     * @param endPos The position you want to reach
     * @return Distance between two positions applying pythagorean theorem,
     *                                      rounds down to closes integer
     */
    public static double calculateDistance(Position2D startPos, Position2D endPos){
        int distanceY = Math.abs(startPos.getY()-endPos.getY());
        int distanceX = Math.abs(startPos.getX() - endPos.getX());
        //Pythagorean theorem
        return (Math.sqrt(distanceX*distanceX + distanceY * distanceY));
    }
}
