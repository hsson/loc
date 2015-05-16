package edu.chl.loc.minigame.caps.capsmodel;

/**
 * Model class for caps minigame
 * @author Alexander Karlsson
 * @version 1.0
 */
public class CapsGameModel {
    private int level;
    private float cupPosition;
    private float aimPosition;
    private boolean moveAimBackwards;
    private boolean capThrown;

    public CapsGameModel(){
        this.level = 1;
        cupPosition = getNewCupPosition();
        aimPosition = 0;
        moveAimBackwards = false;
        capThrown = false;
    }

    /**
     * Updates the flow of the game based on a time variable
     * @param deltaTime The time passed in seconds since last update
     */
    public void update(float deltaTime){
        if(!capThrown){
            updateAim(deltaTime);
        }
    }

    /**
     * Throws the cap in the current game
     */
    public void throwCap(){
        capThrown = true;
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
     * Gets a random cup position between 0.05 and 0.95
     * @return A new cup position
     */
    private float getNewCupPosition(){
        float position = (float)Math.random();
        position = position * 0.9f;
        position += 0.05f;
        return position;
    }
}
