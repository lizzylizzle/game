package com.github.lizzylizzle.mmg.ui;

import javax.swing.*;

/**
 * UI component showing our cloud which also moves
 */
public class viewCloud {

    private JLayeredPane frame;
    private final utilSprite Cloud;


    /**
     * Constructor
     * @param theme Theme of our ui
     */
    public viewCloud(String theme) {
        Cloud = new utilSprite(theme + "/cloud.png", 180, 110);
        Cloud.Speed = 1;
        Cloud.setLocation(0, 0);
    }


    /**
     * Initialize our lives
     * @param frame main panel where we show our available lives
     */
    public void init(JLayeredPane frame) {
        this.frame = frame;
        // SHOW OUR CLOUD ON THE GAME LAYER
        this.frame.add(Cloud, 0);
    }


    /**
     * Move our cloud from left to right and back when it reaches end of screen
     * @param sleep Sleeping time between frames
     */
    public void update(long sleep) {

        // MOVE CLOUD WHILE STAYING ON SAME Y COORDINATE (0)
        Cloud.setLocation(Cloud.getX() + (int)((long)Cloud.Speed * sleep) / 30, 0);

        if (Cloud.getX() + Cloud.getWidth() > this.frame.getWidth() - 30 || Cloud.getX() < 0) {
            // END OF SCREEN NOW MOVE OTHER SIDE (- * + = - (left), - * - = + (right))
            Cloud.Speed *= -1;
        }

    }

}
