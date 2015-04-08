package edu.chl.loc.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for handling an inventory of items
 * @author Alexander Karlsson
 * @version 1.0
 */
public class Inventory implements Cloneable{
    private Map<AbstractItem, Integer> inventory;

    /**
     * Basic constructor, creates an empty inventory
     */
    public Inventory(){
        inventory = new HashMap<AbstractItem, Integer>();
    }

    /**
     * Creates an inventory from a map with items mapped to the amount of each item
     * in the map
     * @param map The map to create the inventory from
     */
    public Inventory(Map<AbstractItem, Integer> map){
        inventory = new HashMap(map);
    }

    /**
     * Creates an inventory with one of each item in a list
     * @param itemList The list from which to get the items from
     */
    public Inventory(List<AbstractItem> itemList){
        inventory = new HashMap<AbstractItem, Integer>();
        for(AbstractItem item:itemList) {
            inventory.put(item, 1);
        }
    }

    /**
     * Checks if an item exists in the inventory
     * @param item The item to check
     * @return True if the item exists in the inventory, otherwise false
     */
    public boolean hasItem(AbstractItem item){
        return inventory.containsKey(item);
    }

    /**
     * Returns how many instances of an item exists in the inventory
     * @param item The item to check
     * @return The amount of given items
     */
    public int getItemAmount(AbstractItem item){
        if(inventory.get(item)!=null) {
            return inventory.get(item);
        }else{
            return 0;
        }
    }

    /**
     * Adds one given item to the inventory
     * @param item The item to add
     */
    public void addItem(AbstractItem item){
        if(inventory.containsKey(item)){//Check if item already exists in inventory
            int amount = inventory.get(item);
            amount++;
            inventory.replace(item, amount);//Adds one to the value if the item already exists in inventory
        }else{
            inventory.put(item,1);//Place in inventory
        }
    }

    /**
     * Removes an item from the inventory, does nothing if the item given
     * is not in the inventory
     * @param item The item to remove
     */
    public void removeItem(AbstractItem item){
        if(inventory.containsKey(item)){
            int amount = inventory.get(item);
            if(amount<=1){
                inventory.remove(item);
            }else{
                amount--;
                inventory.replace(item,amount);
            }
        }
    }

    /**
     * Removes all items in an inventory
     */
    public void clear(){
        inventory.clear();
    }

    /**
     * Returns a map view of the inventory
     * @return The inventory as a map
     */
    public Map<AbstractItem, Integer> toMap(){
        return inventory;
    }

    /**
     * Compares two inventories for equality
     * @param o The object to compare
     * @return True if the inventories are equal, else false
     */
    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }else if(o==null){
            return false;
        }else if(!this.getClass().equals(o.getClass())){
            return false;
        }else{
            Inventory otherInventory = (Inventory) o;
            Map<AbstractItem, Integer> otherMap = otherInventory.toMap();
            return inventory.equals(otherMap);
        }
    }

    /**
     * Returns the hash code value for this inventory
     * @return The hash code for this inventory
     */
    @Override
    public int hashCode(){
        return inventory.hashCode();
    }

    //TO-DO correct implementation of clone method
    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        return null;
    }
}
