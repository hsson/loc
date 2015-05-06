package edu.chl.loc.models.items;

import edu.chl.loc.models.core.GameModel;

/**
 * Created by Maxim on 15-04-14.
 * @author Maxim Goretskyy
 */
public class ItemScore extends AbstractItem{
    
    private int hec;
    public ItemScore(String beverageName, int hec){
        super(ItemType.USE, beverageName);
        this.hec = hec;
    }

    public ItemScore(ItemType itemType, String beverageName){
        super(itemType, beverageName);
    }

    public ItemScore(ItemScore copyBeer){
        super(copyBeer.getType(), copyBeer.getItemName());
    }

    @Override
    public AbstractItem copy() {
        return new ItemScore(this);
    }

    /**
     * Adds 3 hec to the gamestate
     * @param state
     */
    @Override
    public void use(GameModel state) {
        state.addHec(hec);
        System.out.println("You drank a beer");
    }

}