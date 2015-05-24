package edu.chl.loc.models.menu;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-18
 */
public class ExitOption implements IMenuOption {
    @Override
    public void choose() {
        System.out.println("Closing game.");
        System.exit(0);
    }

    @Override
    public String getName() {
        return "Avsluta";
    }
}