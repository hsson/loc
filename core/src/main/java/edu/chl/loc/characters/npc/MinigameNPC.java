package edu.chl.loc.characters.npc;

import edu.chl.loc.characters.utilities.Direction;
import edu.chl.loc.characters.utilities.Gender;
import edu.chl.loc.items.Inventory;
import edu.chl.loc.minigames.IMinigame;
import edu.chl.loc.utilities.Position2D;

/**
 * Created by maxim on 15-04-24
 * @author Maxim Goretskyy
 */
public class MinigameNPC extends AbstractNPC{
    private IMinigame minigame;

    protected MinigameNPC(Position2D pos, Direction dir, String name, Gender gender, IMinigame minigame, Dialog dialog) {
        super(pos, dir, name, gender, dialog);
        this.minigame = minigame;
    }


    @Override
    protected void doAction() {
        //method to start the minigame
        //Todo method for minigames and minigames
    }

}
