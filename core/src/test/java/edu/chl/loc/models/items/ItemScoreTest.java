package edu.chl.loc.models.items;

import edu.chl.loc.models.core.GameModel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Maxim Goretskyy
 * Created by maxim on 15-05-10.
 */
public class ItemScoreTest {

    @Test
    public void testingCopy(){
        ItemScore firstItem = new ItemScore("PrippsBlå", 3);
        AbstractItem copyItem = firstItem.copy();
        Assert.assertTrue("Copy should be itemscore's class", copyItem.getClass() == ItemScore.class);
        ItemScore copyFirstItem = (ItemScore)firstItem.copy();
        Assert.assertTrue("Names should be the same for the item and it's copy",
                copyFirstItem.getItemName().equals(firstItem.getItemName()));

        Assert.assertTrue("Both copy and real item should give same amount of hec",
                copyFirstItem.getHec() == firstItem.getHec());
    }
    @Test
    public void testUsingItemScore(){
        GameModel model = new GameModel();

        ItemScore item = new ItemScore("PrippsBlå", 5);
        item.use(model);

        Assert.assertTrue("Model should have 5 hec", model.getHec() == 5);
    }

    @Test
    public void testingEquals(){
        ItemScore firstItem = new ItemScore("PrippsBlå", 5);
        ItemScore secondItem = new ItemScore("PrippsBlå", 7);
        Assert.assertTrue("Items should be same because they share same name and type", firstItem.equals(secondItem));

        //test letter case-sensitivity
        ItemScore thirdItem = new ItemScore("Prippsblå", 5);
        ItemScore fourthItem = new ItemScore("PrippsBlå", 7);
        Assert.assertFalse("Items shouldn't be same because they have different name", thirdItem.equals(fourthItem));

        //testing different types but same name
        ItemScore fifthItem= new ItemScore("PrippsBlå", 7);
        ItemScore sixthItem = new ItemScore("PrippsBlå", 7);
        fifthItem.setType(ItemType.COLLECT);
        Assert.assertFalse("Items should not be the same, because of different types", fifthItem.equals(sixthItem));

        //testing null
        Assert.assertFalse("An item should not be equal with null", sixthItem.equals(null));

        Assert.assertTrue("An item should be equal itself", sixthItem.equals(sixthItem));


    }
}
