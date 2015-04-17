package se.alexanderkarlsson.beerchug.beerchugmodel;

import com.badlogic.gdx.utils.TimeUtils;
import se.alexanderkarlsson.beerchug.utilities.NoMoreBeerException;
import se.alexanderkarlsson.beerchug.utilities.ShakeDirection;
import se.alexanderkarlsson.beerchug.utilities.SquirtException;

/**
 * Beerchug model for bottle chugs
 * @author Alexander Karlsson
 * @version 0.1
 */
public class BottleBeerChug {
    private int centilitersRemaining;
    private ShakeDirection lastShake;
    private boolean chugStarted;
    private long timeStarted;

    public BottleBeerChug(){
        centilitersRemaining = 33;
        lastShake = null;
        timeStarted = TimeUtils.nanoTime();
        chugStarted = false;
    }

    /**
     * Starts a chug
     */
    public void startChug(){
        chugStarted = true;
        lastShake = ShakeDirection.LEFT;
    }

    /**
     * Shakes the bottle during a chug and drinks if done properly,
     * otherwise throws exception
     * @param shakeDirection The direction to shake the beer
     * @throws NoMoreBeerException If you try to drink from an empty
     * bottle an exception is thrown
     * @throws SquirtException If you shake the same direction twice
     * an exception is thrown
     */
    public void shake(ShakeDirection shakeDirection) throws NoMoreBeerException, SquirtException{
        if(shakeDirection == lastShake){
            throw new SquirtException();
        }else if(centilitersRemaining <= 0){
            throw new NoMoreBeerException();
        }else{
            lastShake = shakeDirection;
            centilitersRemaining--;
        }

    }
}
