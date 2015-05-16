package edu.chl.loc.minigame.caps.capsmodel;

import edu.chl.loc.minigame.caps.capsmodel.utilities.CapNotThrownException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Model class for caps minigame
 * @author Alexander Karlsson
 * @version 1.0
 */
public class CapsGameModel {
    private int level;
    private float cupPosition;
    private float aimPosition;
    private float nextStageCountdown;
    private boolean moveAimBackwards;
    private boolean capThrown;
    private PropertyChangeSupport pcs;

    public CapsGameModel(){
        this.level = 1;
        cupPosition = getNewCupPosition();
        aimPosition = 0;
        moveAimBackwards = false;
        capThrown = false;
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Updates the flow of the game based on a time variable
     * @param deltaTime The time passed in seconds since last update
     */
    public void update(float deltaTime){
        if(!capThrown){
            updateAim(deltaTime);
        }else{
            updateNextStageCountdown(deltaTime);
            if(nextStageCountdown < 0){
                if(wasHit()){
                    goToNextLevel();
                }else{
                    endGame();
                }
            }
        }
    }

    /**
     * Throws the cap in the current game and starts a countdown for ending
     * the game or moving to the next level depending on if the throw was a
     * hit or miss
     * @param waitTime The time in seconds to wait before proceeding to
     *                 the next level or end the game
     */
    public void throwCap(float waitTime){
        capThrown = true;
        nextStageCountdown = waitTime;
    }

    /**
     * Checks if a thrown cap hit or missed the cup, a throw is considered
     * hit if the position of the aim equals the one of the cup +- 0.05
     * @return True if the throw hit the cup, false otherwise
     */
    public boolean wasHit(){
        if(!capThrown){
            throw new CapNotThrownException();
        }
        return aimPosition > cupPosition - 0.05f && aimPosition < cupPosition +0.05f;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

    /**
     * Updates the countdown time
     * @param deltaTime The time passed in seconds since last update
     */
    private void updateNextStageCountdown(float deltaTime){
        nextStageCountdown -= deltaTime;
    }

    /**
     * Updates the position of the aim based on a time variable.
     * The aim will move between 0.0 and 1.0 and the speed depends
     * on the level. The aim will move forward until it reaches
     * 1.0 and will then return.
     * @param deltaTime The time passed in seconds
     */
    private void updateAim(float deltaTime){
        if(!moveAimBackwards){
            if(aimPosition + deltaTime * (float)level > 1.0f){
                aimPosition = 1.0f;
                moveAimBackwards = true;
            }else {
                aimPosition += deltaTime * (float) level;
            }
        }else{
            if(aimPosition - deltaTime * (float)level < 0f){
                aimPosition=0;
                moveAimBackwards = false;
            }else {
                aimPosition -= deltaTime * (float) level;
            }
        }
    }

    /**
     * Takes the game to the next level
     */
    private void goToNextLevel(){
        level = level++;
        cupPosition = getNewCupPosition();
        aimPosition = 0;
        capThrown = false;
    }

    /**
     * Gets a random cup position between 0.05 and 0.95
     * @return A new cup position
     */
    private float getNewCupPosition(){
        float position = (float)Math.random();
        position = position * 0.9f;
        position += 0.05f;
        return position;
    }

    /**
     * Ends the game
     */
    private void endGame(){
        pcs.firePropertyChange("gameFinished",null,null);
    }
}
