package edu.chl.loc.models.menu;

import edu.chl.loc.models.core.IGameModel;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-18
 */
public class StatsOption implements IMenuOption {

    private final IGameModel model;

    public StatsOption(IGameModel model) {
        this.model = model;
    }

    @Override
    public void choose() {
        model.setIsStatsActive(true);
        model.getGameMenu().closeMenu();
        System.out.println("Opens stats");
    }

    @Override
    public String getName() {
        return "Statistik";
    }
}
