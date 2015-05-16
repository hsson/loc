package edu.chl.loc.minigame.caps.capsmodel;

/**
 * Model class for caps minigame
 * @author Alexander Karlsson
 * @version 1.0
 */
public class capsModel {
    private int level;
    private float cupPosition;
    private float aimPosition;
    private boolean moveAimBackwards;
    private boolean capThrown;

    public capsModel(){
        this.level = 1;
        //cupPosition = ;
        aimPosition = 0;
        moveAimBackwards = false;
        capThrown = false;
    }

}
