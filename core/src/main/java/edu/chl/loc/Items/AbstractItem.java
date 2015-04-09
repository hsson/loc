package edu.chl.loc.items;

import java.util.List;

/**
 * A class to represent items in different places
 *
 * @author Criticalstone
 * @version 1.0.0
 * @since 2015-04-07
 * revised by Maxim Goretskyy
 */
public abstract class AbstractItem{

    private ItemType type;
    private int itemID;

    public AbstractItem(ItemType type, int itemID){
        this.type = type;
        this.itemID = itemID;
    }

    public AbstractItem(AbstractItem item){
        this.type = item.getType();
        this.itemID = item.getItemID();

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

    /**
     *
     * @return itemID of this item
     */
    public int getItemID(){
        return itemID;
    }

    /**
     * Sets a given itemID for this item
     * @param itemID itemID you want to set for this item
     */

    public void setItemID(int itemID){
        this.itemID = itemID;
    }

    /**
     * This method works as clone method, but clone method is worthless and should not be overriden or used, ever.
     * @return A copy of abstractItem
     */
    public abstract AbstractItem copy();
}
