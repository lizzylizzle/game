package com.github.lizzylizzle.mmg.ui;


import javax.swing.*;
import java.util.Random;


/**
 * UI component showing our candies
 */
public class viewCandy {

    private JLayeredPane frame;

    private final Random randomNumber = new Random();
    private utilSprite[] mm; // Our CANDIES
    private int mmSpeed = 3;
    private int MAX; // MAX OF CANDIES
    private String theme; // OUR THEME

    /**
     * Constructor
     * @param theme Theme of our ui
     */
    public viewCandy(String theme) {
        this.theme = theme;
    }


    /**
     * Initialize our candies
     * @param frame main panel where we show our available lives
     * @param max Max of candies in our ui
     */
    public void init(JLayeredPane frame, int max) {
        this.frame = frame;
        this.MAX = max;

        mm = new utilSprite[this.MAX];

        for (int i = 0; i < MAX; ++i) {
            int mmWidth = 60;
            int mmHeight = 60;
            this.mm[i] = new utilSprite(theme + "/mm" + (i + 1) + ".png", mmWidth, mmHeight);
            this.mm[i].Speed = 0;
            this.mm[i].notOccupied = true;
            this.mm[i].setSize(mmWidth, mmHeight);
            this.mm[i].setVisible(false);
        }
    }


    /**
     * Give our candy object filled with candies
     * @return all candies
     */
    public utilSprite[] getCandy() {
        return this.mm;
    }


    /**
     * Take random MM, if not already taken then add it to our Game
     * This gives more random effect in when they fall as well (sometimes they are occupied)
     */
    public void show() {

        int item = randomNumber.nextInt(8);

        if (mm[item].notOccupied) {

            Random randomPosition = new Random();

            mm[item].notOccupied = false;
            mm[item].Speed = this.mmSpeed;

            this.frame.add(mm[item], 1);

            mm[item].setVisible(true);
            mm[item].setLocation(randomPosition.nextInt(this.frame.getWidth() - 100), 10);

        }
    }


    /**
     * Let our candies who have been selected in (show) fall down
     * @param sleep Sleeping time between frames
     */
    public void update(long sleep) {

        for (int x = 0; x < MAX; ++x) {

            if (!this.mm[x].notOccupied) {

                long positionY = ((long) this.mm[x].getY() + (long) (this.mm[x].Speed) * sleep / 30L);

                this.mm[x].setLocation(this.mm[x].getX(), (int) positionY);

                if ((int) positionY > frame.getHeight()) {
                    // System.out.println("" + x + " " + (int) positionY + " " + frame.getHeight());
                    mm[x].notOccupied = true;
                    mm[x].Speed = this.mmSpeed;
                }
            }
        }

    }


    /**
     * INCREASE SPEED BUT NOT MORE THEN 10 TIMES FASTER...
     */
    public void moreSpeed() {
        if (this.mmSpeed < 10) {
            ++this.mmSpeed;
        }
    }


    /**
     * Reset all candies after ui over for example
     */
    public void reset() {

        this.mmSpeed = 3;

        for (int i = 0; i < MAX; ++i) {
            this.mm[i].Speed = 0;
            this.mm[i].setVisible(false);
        }

    }

}
