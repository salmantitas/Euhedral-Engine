package com.euhedral.engine;

import com.euhedral.engine.*;
import com.euhedral.engine.Button;
import com.euhedral.engine.MenuItem;
import com.euhedral.engine.Panel;
import com.euhedral.game.ActionTag;

import java.awt.*;
import java.util.LinkedList;

public class Menu {

    protected ActionTag action = null;
    private int index = 0;
    private GameState state;
    protected LinkedList<MenuItem> menuItems = new LinkedList<>();

    protected Button[] options;
    protected int MAXBUTTON;

    // Title Variables

    int titleX = Engine.percWidth(9);
    int titleY = Engine.percHeight(20);
    int titleSize = Engine.percWidth(11.5);
    Color titleColor = Color.BLACK;

    int buttonSize = Engine.percWidth(5);
    int leftButtonX = Engine.percWidth(5);
    int midLeftButtonX = Engine.percWidth(38);
    int midButtonX = Engine.percWidth(45);
    int midRightButtonX = Engine.percWidth(50);
    int rightButtonX = Engine.percWidth(80);
    int topButtonY = Engine.percHeight(30);
    int midHeightButtonY = Engine.percHeight(50);
    int lowestButtonY = Engine.percHeight(70);

    public Menu(GameState state) {
        this.state = state;
    }

    public void render(Graphics g) {
        for (MenuItem menuItem: menuItems) {
            menuItem.render(g);
        }

        for (int i = 0; i < MAXBUTTON; i++) {
            Button button = options[i];
            button.render(g);
        }
    }

    /*
     * Selects the button over which the cursor is hovering.
     * Everything else is deselected.
     * */
    public void checkHover(int mx, int my) {
        for (int i = 0; i < MAXBUTTON; i++) {
            Button button = options[i];
            if (button.mouseOverlap(mx, my)) {
                button.select();
            } else button.deselect();
        }
    }

    /*
     * Checks whether the mouse has clicked on a button. If true, the button is activated.
     * */
    public void checkButtonAction(int mx, int my) {
        for (int i = 0; i < MAXBUTTON; i++) {
            Button button = options[i];
            if (button.mouseOverlap(mx, my)) {
                activateButton(button);
                break;
//                if (button instanceof ButtonNav) {
//                    ButtonNav navButton = (ButtonNav) button;
//                    Engine.setState(navButton.getTargetSate());
//                    break;
//                } else {
//                    ButtonAction actButton = (ButtonAction) button;
//                    this.action = actButton.getAction();
//                    break;
//                }
            }
        }
    }

    /*
     * If the selected button is a navigation button, the GameState is changed. Otherwise, the ActionTag is applied.
     * */
    public void activateButton(Button button) {
        if (button instanceof ButtonNav) {
            button.activate();
//            ButtonNav navButton = (ButtonNav) button;
//            Engine.setState(navButton.getTargetSate());
        } else {
            ButtonAction actButton = (ButtonAction) button;
            this.action = actButton.getAction();
        }
    }

    /*
     * Activates the button that is selected
     * */
    public void chooseSelected() {
        activateButton(options[index]);
    }

    /*
     * Changes selected button by keypress
     * */
    public void keyboardSelection(char c) {
        if (c == 'r') {
            options[index].deselect();
            index = (index + 1) % MAXBUTTON;
            options[index].select();
        } else {
            options[index].deselect();
            index = (index - 1);
            if (index < 0) index = MAXBUTTON-1;
            options[index].select();
        }
    }

    public GameState getState() {
        return state;
    }

    public ActionTag getAction() {
        return action;
    }

    /****************
     * UI Functions *
     ****************/

    public void addPanel(Panel panel) {
        menuItems.add(panel);
    }

    public void addPanel(int x, int y, int width, int height, GameState state) {
        menuItems.add(new Panel(x, y, width, height, state));
    }


    public void addPanel(int x, int y, int width, int height, GameState state, float transparency, Color color) {
        menuItems.add(new Panel(x, y, width, height, state, transparency, color));
    }
}
