package com.euhedral.game;

import com.euhedral.engine.*;
import com.euhedral.engine.Button;
import com.euhedral.engine.Menu;
import com.euhedral.engine.MenuItem;
import com.euhedral.engine.Panel;

import java.awt.*;
import java.util.ArrayList;

public class UIHandler {
    private Menu currentMenu;
    private ArrayList<Menu> menus;
    ActionTag action = null;

    /************************
    * Common game variables *
    *************************/

    Menu nullMenu = new Menu(GameState.Quit);

    /******************
     * User variables *
     ******************/


    public UIHandler() {

        /***************
         * Engine Code *
         ***************/

        menus = new ArrayList<>();

        /*************
         * Game Code *
         *************/

//        MenuMain mainMenu = new MenuMain();
//        menus.add(mainMenu);
//        currentMenu = mainMenu;

    }

//    public void update() {
//
//    }

    /*
     * Renders the current menu
     * */
    public void render(Graphics g) {
        currentMenu.render(g);

        // Debug/Console
        drawState(g);
        if (VariableManager.isConsole())
            drawCommand(g);
    }

    public void checkHover(int mx, int my) {
        currentMenu.checkHover(mx, my);
    }

    public void checkButtonAction(int mx, int my) {
        currentMenu.checkButtonAction(mx, my);
        action = currentMenu.getAction();
    }

    public void chooseSelected() {
        currentMenu.chooseSelected();
    }

    /*
     * Changes the menu to match the current gameState. If no matching menu is found, it's set to an empty menu.
     * */
    private void findNewCurrent(GameState state) {
        boolean menuChanged = false;
//        currentMenu = nullMenu;

        for (Menu menu: menus) {
            if (menu.getState() == state) {
                currentMenu = menu;
                menuChanged = true;
                break;
            }
        }

        if (!menuChanged)
            currentMenu = nullMenu;
    }

    public void keyboardSelection(char c) {
        currentMenu.keyboardSelection(c);
    }

    public ActionTag getAction() {
        return action;
    }

    public void endAction() {
        action = null;
    }

    public void updateState(GameState state) {
        findNewCurrent(state);
    }

    /***************************
     * Render Helper Functions *
     ***************************/

    /*******************
     * Debug Functions *
     *******************/

    public void drawState(Graphics g) {
        g.setFont(new Font("arial", 1, Utility.percWidth(1.5)));
        g.setColor(Color.WHITE);
        g.drawString("State: " + Engine.currentState, Utility.percWidth(85), Utility.percHeight(8));
    }

    public void drawCommand(Graphics g) {
        g.setFont(new Font("arial", 1, Utility.percWidth(1.5)));
        g.setColor(Color.WHITE);
        g.drawString("Command: " + GameController.cmd, Utility.percWidth(25), Utility.percHeight(45));
    }
}
