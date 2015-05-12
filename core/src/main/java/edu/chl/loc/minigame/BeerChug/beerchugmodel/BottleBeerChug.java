package edu.chl.loc.minigame.BeerChug.beerchugmodel;


import edu.chl.loc.minigame.BeerChug.utilities.ShakeDirection;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Beerchug model for bottle chugs
 * @author Alexander Karlsson
 * @version 1.3.3.7
 */
public class BottleBeerChug {
    private float centilitersRemaining;
    private ShakeDirection lastShake;
    private float timeElapsed;
    private boolean finished;
    private boolean squirted;
    private boolean chugStarted;
    private boolean firstShakeDone;
    private boolean countDownHasBegun;
    private String disqualifiedReason;
    private float countDown;
    private boolean countingDown;
    private float endCountDown;
    private PropertyChangeSupport pcs;

    private static final long STARTING_CENTILITRES = 33l;
    private static final float COUNTDOWN_LENGTH = 2.0f;
    private static final float END_COUNTDOWN_LENGTH = 2.0f;

    /**
     * Basic constructor which starts and creates a basic 33cl beerchug
     */
    public BottleBeerChug(){
        centilitersRemaining = STARTING_CENTILITRES;
        lastShake = null;
        finished = false;
        timeElapsed = 0;
        endCountDown = 0;
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Starts a chug
     */
    public void startChug(){
        chugStarted = true;
    }

    /**
     * Ends a chug
     */
    public void endChug(){
        if(centilitersRemaining != 0){
            squirted = true;
            disqualifiedReason = "Spill";
        }else if(!squirted && !finished){
            finished = true;
        }
    }

    /**
     * Shakes the bottle for the first time, does
     * nothing if first shake is already done
     */
    public void firstShake(){
        if(!firstShakeDone) {
            if (chugStarted()) {
                lastShake = ShakeDirection.LEFT;
                firstShakeDone = true;
            } else {
                squirted = true;
                disqualifiedReason = "Tjuvstart";
            }
        }
    }

    /**
     * Shakes the bottle during a chug and drinks if done properly,
     * otherwise throws exception
     * @param shakeDirection The direction to shake the beer
     */
    public void shake(ShakeDirection shakeDirection){
        if(!chugStarted() || (lastShake == shakeDirection && centilitersRemaining!=0) || !firstShakeDone) {
            squirted = true;
            disqualifiedReason = "Spill";
        }else if(!squirted && centilitersRemaining!=0){
            centilitersRemaining--;
        }
        lastShake = shakeDirection;
    }

    public boolean isSquirted(){
        return squirted;
    }

    /**
     * Tells you if the chug is finished
     * @return True if the chug is finished, false
     * otherwise
     */
    public boolean isFinished(){
        return finished;
    }

    /**
     * Checks if a chug has started
     * @return False if the countdown is ongoing, false otherwise
     */
    public boolean chugStarted(){
        return chugStarted;
    }

    /**
     * Checks if the first shake has been done
     * @return True if the first shake is done
     */
    public boolean isFirstShakeDone(){
        return firstShakeDone;
    }

    /**
     * Checks which shake was done last
     * @return The last shakes direction
     */
    public ShakeDirection getLastShake(){
        return lastShake;
    }

    /**
     * Returns the percentage left of the bottle
     * @return The percentage of remaining liquid
     */
    public float drinkRemaining(){
        return(float)centilitersRemaining/STARTING_CENTILITRES;
    }

    public String getDisqualifiedReason(){
        if(!squirted){
            return null;
        }else{
            return disqualifiedReason;
        }
    }

    /**
     * Returns how long the current chug has been going on
     * or the final time of the chug in seconds
     * @return The time elapsed if the chug is still ongoing
     * or the final time if the chug is finished
     */
    public float timeElapsed(){
        return timeElapsed;
    }

    /**
     * Updates the progress in the beerchug
     * @param delta The time to add in seconds
     */
    public void update(float delta){
        if (chugStarted && !finished) {
            timeElapsed += delta;
        }
        if(countingDown) {
            countDown -= delta;
            if (countDown < 0) {
                startChug();
            }
            if(countDown < -1){
                countingDown = false;
            }
        }
        if(finished || squirted){
            endCountDown += delta;
        }
        if(endCountDown > END_COUNTDOWN_LENGTH){
            pcs.firePropertyChange("gameFinished",null,null);
        }
    }

    /**
     * Starts the countdown, should be called before startchug. The countdown will count
     * from it's length to minus 1 second before stopping
     */
    public void startCountdown(){
        countDown = COUNTDOWN_LENGTH;
        countDownHasBegun = true;
        countingDown = true;
    }

    /**
     * Checks if the countdown is ongoing
     * @return If the countdown is ongoing
     */
    public boolean isCountingDown(){
        return countingDown;
    }

    /**
     * Checks if the countdown has once been started
     * @return
     */
    public boolean countDownHasBegun(){
        return countDownHasBegun;
    }

    /**
     * Returns the current countdown
     * @return Current countdown
     */
    public float getCountDown(){
        return countDown;
    }

    /**
     * Returns the correct grade based on your chugtime
     * @return U if disqualified or time higher than 7 seconds, 5 if
     * the time is lower than 3 seconds, 4 if the time is lower than
     * 4 seconds and 3 if the time is lower than 7 seconds.
     */
    public char getGrade(){
        if(finished && !squirted) {
            if (timeElapsed() < 3) {
                return '5';
            } else if (timeElapsed() < 4) {
                return '4';
            } else if (timeElapsed() < 7) {
                return '3';
            }
        }
        return 'U';
    }

    /**
     * Adds a listener to this BeerChug, will be notified when the game
     * is over
     * @param listener The listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }
}
