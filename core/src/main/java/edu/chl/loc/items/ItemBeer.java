package edu.chl.loc.items;

/**
 * Created by Maxim on 15-04-14.
 * @author Maxim Goretskyy
 */
public class ItemBeer extends AbstractItem{

    public ItemBeer(String beerName){
        super(ItemType.USE, beerName);

    }

    public ItemBeer(ItemType itemType, String beerName){
        super(itemType, beerName);
    }

    public ItemBeer(ItemBeer copyBeer){
        super(copyBeer.getType(), copyBeer.getItemName());
    }

    @Override
    public AbstractItem copy() {
        return new ItemBeer(this);
    }

    /**
     * Do something with the beer
     * //todo Finish use method for beer.
     */
    public void use(){
        System.out.println("You drank a beer");

    }

}