package edu.chl.loc.minigame;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import edu.chl.loc.models.characters.npc.ItemNPC;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.models.items.AbstractItem;

/**@author Maxim Goretskyy
 * Created by maxim on 15-05-03.
 * Prototype/ideas for Cortege minigame
 */
public class MinigameCortege implements IMinigame{
    /**
     * Will have to start this game in another screen, generate a new map
     * because items will spawn differently and need item tile at different positions
     * Temporary solution to have different items on different layers
     * and compare players position with items position on another layer
     *
     * Start with just 2 items to show it works
     *
     *
     * ItemNPC will have an inv with items, and he will want the same items.
     *
     */

    private ItemNPC npc;

    public MinigameCortege(ItemNPC npc){//will have to make so MinigameNPC
                                        //which starts the minigame, also creates
                                        //ItemNPC with inventory
        this.npc = npc;
    }



    /**
     * Will compare this item with the item that NPC wants
     *
     * @param item you want to compare with NPC
     * @return true if its an item that NPC wants.
     */
    public boolean isCorrectItem(AbstractItem item){
        return this.npc.getInventory().hasItem(item);
    }

    /**
     * When NPC asks you to bring tools, you will attempt giving
     * tools with this method to the inventory
     * @param item you want to place inside cortegeInventory
     */
    public void giveItemToNPC(AbstractItem item){
        if(isCorrectItem(item)){
            //give item to npc (remove item from players inv)
            //remove item from npc
        }
    }

    /**
     *
     * @return true if all items have been found
     */
    public boolean areAllItemsFound(){
        //if npc's inventory is empty it means you gave him everything he needs, therefore found all
        return npc.getInventory().isEmpty();
    }

    /**
     * when you finish the game you should get something
     * @param model the gamemodels state you want to change
     */
    public void giveReward(GameModel model){
        if(areAllItemsFound()){
            //give some reward, achievement or something
        }
    }


    @Override
    public Screen getView() {
        return null;
    }

    @Override
    public InputProcessor getController() {
        return null;
    }

    @Override
    public void setListener(IMinigameListener listener) {

    }

    @Override
    public char getGrade() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
