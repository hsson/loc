package edu.chl.loc.models.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-18
 */
public class GameMenu {
    private boolean isOpen = false;

    private final List<IMenuOption> menuOptions;

    private int selectedIndex = 0;

    public GameMenu() {
        menuOptions = new ArrayList<IMenuOption>();
    }

    public void addMenuOption(IMenuOption option) {
        if (!menuOptions.contains(option)) {
            menuOptions.add(option);
        }
    }

    public void openMenu() {
        this.isOpen = true;
    }

    public void closeMenu() {
        this.isOpen = false;
    }

    public void toggleOpen() {
        this.isOpen = !isOpen;
    }

    public boolean isMenuOpen() {
        return this.isOpen;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public List<IMenuOption> getMenuOptions() {
        return this.menuOptions;
    }

    public IMenuOption getSelectedOption() {
        return menuOptions.get(selectedIndex);
    }

    public void incSelection() {
        if (selectedIndex == (menuOptions.size() - 1) || menuOptions.size() == 0) {
            selectedIndex = 0;
        } else {
            selectedIndex++;
        }
    }

    public void decSelection() {
        if (menuOptions.size() == 0) {
            selectedIndex = 0;
        } else  if (selectedIndex == 0) {
            selectedIndex = menuOptions.size() - 1;
        } else {
            selectedIndex--;
        }
    }
}
