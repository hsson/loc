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
}
