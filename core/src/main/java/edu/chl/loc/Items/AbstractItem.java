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

    private List<ItemType> types;

    /**
     * @return All the types that has been set to the item
     */
    public List<ItemType> getTypes(){
        return types;
    }

    /**
     * Adds a type to the item
     *
     * @param type the type that should be added
     */
    public void addType(ItemType type){
        types.add(type);
    }

    /**
     * removes a type from the item
     *
     * @param type the type that should be added
     */
    public void removeType(ItemType type){
        types.remove(type);
    }

    public abstract int getItemID();

}
