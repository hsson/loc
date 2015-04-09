package edu.chl.loc.items;

import java.util.List;

/**
 * A class to represent items in different places
 *
 * @author Criticalstone
 * @version 1.0.0
 * @since 2015-04-07
 */
public abstract class AbstractItem {

    private ItemType type;

    public AbstractItem(ItemType type){
        this.type = type;
    }

    /**
     * @return The type that has been set to the item
     */
    public ItemType getType(){
        return type;
    }

    /**
     * sets the type to the item
     *
     * @param type the type that should be added
     */
    public void setType(ItemType type){
        this.type = type;
    }

    /**
     * removes a type from the item
     *
     * @param type the type that should be added
     */

    public abstract int getItemID();

}
