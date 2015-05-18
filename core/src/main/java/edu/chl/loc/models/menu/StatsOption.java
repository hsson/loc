package edu.chl.loc.models.menu;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-18
 */
public class StatsOption implements IMenuOption {
    @Override
    public void choose() {
        // TODO: Open stats here
        System.out.println("Opens stats");
    }

    @Override
    public String getName() {
        return "Statistics";
    }
}
