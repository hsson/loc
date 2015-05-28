package edu.chl.loc.model.core;

import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.model.characters.Player;
import edu.chl.loc.model.characters.npc.Dialog;
import edu.chl.loc.model.map.GameMap;
import edu.chl.loc.model.menu.GameMenu;
import edu.chl.loc.model.utilities.Position2D;

/**
 * @author Maxim Goretskyy
 * revised by Kevin Hoogendijk
 */
public interface IGameModel {
    Player getPlayer();
    GameMap getGameMap();
    void addPlayerStat(String key, Object value);
    GameMenu getGameMenu();
    boolean isDialogActive();
    boolean isStatsActive();
    Dialog getActiveDialog();
    void setIsDialogActive(boolean isDialogActive);
    StatsWindow getStatsWindow();
    void setIsStatsActive(boolean isStatsActive);
    void moveCharacter(Position2D nextPos);
    void setActiveDialog(Dialog dialog);
    void setActiveSpeakerName(String activeSpeakerName);
    Stats getStats();
    double getHec();
    String getActiveSpeakerName();
    void addHec(double hec);
    void setGameMap(GameMap map);
    void addMinigameStat(IMinigame minigame);


}
