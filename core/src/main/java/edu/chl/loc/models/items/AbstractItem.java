package edu.chl.loc.models.items;


import edu.chl.loc.models.core.GameModel;

/**
 * A class to represent items in different places
 *
 * @author Criticalstone
 * @version 1.0.0
 * @since 2015-04-07
 * revised by Maxim Goretskyy, Alexander Håkansson
 */
public abstract class AbstractItem{

    private ItemType type;
    private String itemName;

    public AbstractItem(ItemType type, String itemName){
        this.type = type;
        this.itemName = itemName;
    }

    public AbstractItem(AbstractItem item){
        this.type = item.getType();
        this.itemName = item.getItemName();

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
     *
     * @return itemName of this item
     */
    public String getItemName(){
        return itemName;
    }

    /**
     * Sets a given itemName for this item
     * @param itemName itemName you want to set for this item
     */

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    /**
     * This method works as clone method, but clone method is worthless and should not be overriden or used, ever.
     * @return A copy of abstractItem
     */
    public abstract AbstractItem copy();

    /**
     * If the item is usable this method should be implemented
     * Whatever the item is supposed to do happens in this method
     * It has access to the whole game
     */
    public abstract void use(GameModel state);

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(o == null){
            return false;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }else{
            AbstractItem otherAbs = (AbstractItem)o;
            return otherAbs.getType().equals(this.getType()) &&
                    otherAbs.getItemName().equals(this.getItemName());
        }
    }
    @Override
    public int hashCode(){
        return 97*89*getItemName().hashCode() * getType().hashCode();
    }
}
