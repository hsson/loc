package edu.chl.loc.models.characters.npc;

import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.minigame.MinigameHandler;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.utilities.Position2D;

/**
 * Created by maxim on 15-04-24
 * @author Maxim Goretskyy
 * Revised by Alexander Karlsson
 */
public class MinigameNPC extends AbstractNPC{
    private IMinigame minigame;

    protected MinigameNPC(Position2D pos, Direction dir, int id, String name, Gender gender, IMinigame minigame, Dialog dialog) {
        super(pos, dir, id, name, gender, dialog);
        this.minigame = minigame;
    }

    /**
     * Starts this NPCs minigame
     */
    @Override
    public void doAction() {
        minigame.reset();
        MinigameHandler handler = MinigameHandler.getInstance();
        handler.startMinigame(minigame);
    }

}
