package com.github.lizzylizzle.mmg.ui;

import javax.swing.*;

/**
 * UI component showing our background
 */
public class viewBackground {

    private final utilSprite Background;

    /**
     * Constructor
     * @param theme Theme of our ui
     */
    public viewBackground(String theme) {
        Background = new utilSprite(theme + "/background.png", 800, 600);
        Background.setLocation(0, 0);
    }

    /**
     * Initialize our background
     * @param frame main panel where we show our available lives
     */
    public void init(JLayeredPane frame) {
        frame.add(Background, JLayeredPane.DEFAULT_LAYER);
    }


}

