package com.github.lizzylizzle.mmg;

import com.github.lizzylizzle.mmg.ui.*;

import javax.swing.*;
import java.awt.*;

/**
 * Our main Game view holding all UI components
 */
public class View {

    private final JFrame frame;
    // private final JButton button;
    private final JLayeredPane layer;


    // PUBLIC ON VIEW (for example view.myCandy can be used in controller)
    public viewCandy myViewCandy;
    public viewBowl myBowl;
    public viewScore myViewScore;
    public viewLives myViewLives;
    private viewCloud myViewCloud;


    /**
     * Create basic frame with a layered panel (easy to add elements and change position)
     */
    public View() {

        frame = new JFrame();
        frame.setTitle("M&M World by Lizzylizzle");
        frame.setPreferredSize(new Dimension(618, 525));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.WHITE);

        layer = new JLayeredPane();

        frame.add(layer, BorderLayout.CENTER);
        layer.setBounds(0, 0, 600, 480);

        // http://docs.oracle.com/javase/tutorial/uiswing/layout/index.html
        frame.getContentPane().setLayout(new BorderLayout());
        // frame.getContentPane().setLayout(new GridLayout(0,1));

        // button = new JButton("START GAME");
        // frame.getContentPane().add(button, BorderLayout.SOUTH);


        frame.pack();
        frame.setVisible(true);

    }


    /**
     * Our main ui view has different UI components
     * @param lives The total of lives available
     * @param max Max items of candies (make sure image exist)
     * @param theme Our ui theme (graphics)
     */
    void ui(int lives, int max, String theme) {

        viewBackground myViewBackground = new viewBackground(theme);

        myViewCandy = new viewCandy(theme);
        myBowl = new viewBowl(theme);
        myViewScore = new viewScore();
        myViewLives = new viewLives(theme);
        myViewCloud = new viewCloud(theme);

        // GUI ELEMENTS
        myViewBackground.init(layer);
        myViewCandy.init(layer, max);
        myViewCloud.init(layer);
        myViewScore.init(layer);
        myBowl.init(layer);
        myViewLives.init(layer, lives);

    }


    /**
     * Game over. Setup new modal to start again
     */
    void empty() {
        // layer.removeAll();

        // SHOW START BUTTON AGAIN
        // button.setVisible(true);
        // frame.setSize(600, 530);
        frame.repaint();
    }

    /**
     * In our ui loop we update all objects which can move/animate
     * @param sleep time in between loop
     */
    void update(long sleep) {
        myViewCloud.update(sleep);
        myViewCandy.update(sleep);
        myBowl.update(sleep);
    }


    /**
     * Get our screen height
     * @return height of current screen
     */
    public int getScreenHeight() {
        return this.frame.getHeight();
    }


    /**
     * Get our button
     * @return button instance

    public JButton getButton(){
        return button;
    }
     */
}

