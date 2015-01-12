package com.github.lizzylizzle.mmg.ui;

import javax.swing.*;
import java.awt.*;

/**
 * UI component for Score and High score
 */
public class viewScore {

    private final JLabel KeepScore = new JLabel("");
    private final JLabel KeepHighScore = new JLabel("");

    /**
     * Constructor. Setup our labels
     */
    public viewScore() {
        // TODO: A THEME CAN HAVE DIFFERENT COLOUR
        Color scoreColor = new Color(255, 255, 255);

        KeepScore.setSize(100, 34);
        KeepScore.setLocation(10, 10);
        KeepScore.setFont(new Font("Verdana", Font.BOLD, 32));

        KeepScore.setForeground(scoreColor);

        KeepHighScore.setSize(100, 14);
        KeepHighScore.setLocation(12, 46);
        KeepHighScore.setFont(new Font("Verdana", Font.BOLD, 12));
        KeepHighScore.setForeground(scoreColor);

    }


    /**
     * Add our view to our panel
     * @param frame The main panel were we add this view to
     */
    public void init(JLayeredPane frame) {
        frame.add(KeepScore, 1);
        frame.add(KeepHighScore, 1);
    }


    /**
     * Set score in our display upper left corner
     * @param Score The score
     */
    public void setScore(int Score) {
        KeepScore.setText("" + Score);
    }


    /**
     * Set high score during session
     * @param Score The high score
     */
    public void setHighScore(int Score) {
        KeepHighScore.setText("" + Score);
    }

}
