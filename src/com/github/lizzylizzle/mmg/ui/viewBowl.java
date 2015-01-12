package com.github.lizzylizzle.mmg.ui;

import javax.swing.*;
import com.github.lizzylizzle.mmg.View;

/**
 * UI component showing our catcher
 */
// public class viewBowl extends View {
public class viewBowl {

    private JLayeredPane frame;

    private final utilSprite Bowl;
    private int bowlSteps = 0;


    /**
     * Constructor
     * @param theme Theme of our ui
     */
    public viewBowl(String theme) {
        Bowl = new utilSprite(theme + "/catcher.png", 180, 77, 3, 1);
    }

    /**
     * Initialize our catcher
     * @param frame main panel where we show our available lives
     */
    public void init(JLayeredPane frame) {

        this.frame = frame;
        Bowl.Speed = 0;
        Bowl.setSize(60, 100);
        this.frame.add(Bowl, 1);
        Bowl.setLocation((this.frame.getWidth() - Bowl.getWidth() - 30)/2, this.frame.getHeight() - 10 - Bowl.getHeight());

        Bowl.setFocusable(true);
        Bowl.grabFocus();
    }


    /**
     * Get our catcher so we can see it's position
     * @return Bowl our catcher
     */
    public utilSprite getBowl() {
        return this.Bowl;
    }


    /**
     * Catcher icon
     * @param icon 0/2 FROM SPRITE SHOWING LEFT/RIGHT
     * @param dx SHOWING DIRECTION
     */
    public void move(int icon, int dx) {
        Bowl.moveIcon(icon);
        Bowl.Speed = 4 * dx;
        this.bowlSteps = 0;
    }


    /**
     * Update position of our catcher if not moved then set speed slowly to 0
     * @param sleep Sleeping time between frames
     */
    public void update(long sleep) {

        Bowl.grabFocus();

        // OUR CATCHER CAN MOVE FROM LEFT END TO RIGHT END AND BACKWARDS
        long catcherPosition = (long)Bowl.getX();
        // DIRECTION LEFT (X)
        int directionX = (int)(catcherPosition + sleep * (long)this.Bowl.Speed / 10L);

        if (directionX > this.frame.getWidth()) {
            directionX = 0;
        }
        if (directionX < 0) {
            directionX = this.frame.getWidth();
        }

        this.Bowl.setLocation(directionX, Bowl.getY());

        if (Math.abs((long)directionX - catcherPosition) > 0L) {

            ++this.bowlSteps;

            // DON'T MOVE FURTHER IF USER DIDN'T CLICK/HOLD
            if (this.bowlSteps >= 5) {
                this.Bowl.Speed = 0;
            }

        }
    }


    /**
     * Gain more speed for our catcher
     */
    public void moreSpeed() {
        ++this.Bowl.Speed;
    }


}

