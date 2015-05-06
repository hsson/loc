package edu.chl.loc.models.characters;

import edu.chl.loc.models.characters.npc.Dialog;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the dialog class
 * @author Alexander Karlsson
 * @version 0.1
 */
public class DialogTest {

    @Test
    public void basicCreationTest(){
        String[] messages = {"Test1","Test2"};
        boolean yesOption = false;
        Dialog dialog = new Dialog(messages,yesOption);
        Assert.assertArrayEquals("The array used should equal the array in the dialog",dialog.getDialogStrings(),messages);
        Assert.assertFalse("Boolean value should match input argument", dialog.hasYesOption());
        messages[0] = "Test3";
        Assert.assertNotEquals("Changes in argument array should not affect dialog",messages[0],dialog.getDialogStrings()[0]);
    }
}
