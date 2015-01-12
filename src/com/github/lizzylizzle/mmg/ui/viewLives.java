package com.github.lizzylizzle.mmg.ui;


import javax.swing.*;

/**
 * UI component for our lives
 */
public class viewLives {

    private utilSprite[] gameLife;
    private int maxLives = 1;

    private final String theme;
    private JLayeredPane frame;

    /**
     * Constructor
     * @param theme Theme of our ui
     */
    public viewLives(String theme) {
        this.theme = theme;
    }


    /**
     * Initialize our lives
     * @param frame main panel where we show our available lives
     * @param lives available lives
     */
    public void init(JLayeredPane frame, int lives) {

        this.maxLives = lives;
        this.frame = frame;

        /**
         * SPRITE X + 2, ONE FOR BEING ARRAY (SO 0 COUNTS AS WELL), AND ONE FOR POSSIBLE EXTRA LIFE
         */
        gameLife = new utilSprite[this.maxLives + 2];

    }


    /**
     * Create all lives available
     * @param lives Number of lives
     */
    public void setLives(int lives) {
        for (int i = 0; i <= lives; ++i) {
            addToFrame(i);
        }
    }


    /**
     * Lost a live
     * @param item Number of our array which represents a live
     */
    public void remove(int item) {
        --this.maxLives;
        gameLife[item].setVisible(false);
    }


    /**
     *  Create a free extra live
     */
    public void extraLive() {
        addToFrame(this.maxLives + 1);
    }


    /**
     * Paint our available live to the main panel
     * @param itemNumber Number of our array holding all live image objects
     */
    private void addToFrame(int itemNumber) {
        gameLife[itemNumber] = new utilSprite(theme + "/score.png", 40, 40);
        gameLife[itemNumber].setSize(40, 40);
        this.frame.add(gameLife[itemNumber], 1);
        // itemNumber 0 means MOST RIGHT CORNER
        // SO THIS WILL BE GONE LAST (removed from left to right (2-1-0, three lives)
        gameLife[itemNumber].setLocation(frame.getWidth() - ((1 + itemNumber) * (gameLife[itemNumber].getWidth() + 5)) - 5, 10);
        gameLife[itemNumber].setVisible(true);
    }
}

