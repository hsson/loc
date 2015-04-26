package edu.chl.loc.models.items;

import edu.chl.loc.models.core.GameState;

/**
 * Created by Maxim on 15-04-14.
 * @author Maxim Goretskyy
 */
public class ItemBeverage extends AbstractItem{

    public ItemBeverage(String beverageName){
        super(ItemType.USE, beverageName);
    }

    public ItemBeverage(ItemType itemType, String beverageName){
        super(itemType, beverageName);
    }

    public ItemBeverage(ItemBeverage copyBeer){
        super(copyBeer.getType(), copyBeer.getItemName());
    }

    @Override
    public AbstractItem copy() {
        return new ItemBeverage(this);
    }

    /**
     * Adds 3 hec to the gamestate
     * @param state
     */
    @Override
    public void use(GameState state) {
        state.addHec(3);
        System.out.println("You drank a beer");
    }

}