package com.github.lizzylizzle.mmg.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.InputStream;

// import java.net.URL;
// import java.io.File;

/**
 * Sprite utility extending jLabel to make images/sprites
 */
public class utilSprite extends JLabel {

    /**
     * We move our sprites with animations which we can alter our speed on
     */
    public int Speed = 0;
    /**
     * For some sprites we want to be able to know if they are busy instead of asking for X,Y position (first is faster)
     */
    public boolean notOccupied = true;
    // docs.oracle.com/javase/7/docs/api/javax/swing/ImageIcon.html
    private ImageIcon[] spriteIcon;

    /**
     * Have link to our static resources
     */
    private final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();


    /**
     * Show correct image frame from our sprite. Max is given when making instance
     * @param itemNumber The frame where the sprite is in (0-1-2...)
     */
    public void moveIcon(int itemNumber) {
        this.setIcon(this.spriteIcon[itemNumber]);
    }


    /**
     * Making an image with correct ourput scale
     * @param strURL path to our static image (should be placed in static/img)
     * @param width output width of image
     * @param height output height of image
     */
    public utilSprite(String strURL, int width, int height) {
        try {

            // BufferedImage e = ImageIO.read(new File(strImg)); // String strURL
            // BufferedImage e = ImageIO.read(strURL); // Url strURL

            InputStream input = classLoader.getResourceAsStream("static/img/" + strURL);
            BufferedImage e = ImageIO.read(input);

            this.setSize(width, height);
            // docs.oracle.com/javase/7/docs/api/java/awt/Image.html
            ImageIcon icon = new ImageIcon(e.getScaledInstance(width, height, Image.SCALE_SMOOTH));

            this.setIcon(icon);

        } catch (Exception ex) {
            System.out.println("Error file " + strURL + " " + ex.getMessage());
        }
    }


    /**
     * Making an image with multiple images which we call sprite
     * @param strURL path to our static image (should be placed in static/img)
     * @param width output width of image
     * @param height output height of image
     * @param itemNumber how many images do we have in our sprite
     * @param setMe start with correct image
     */
    public utilSprite(String strURL, int width, int height, int itemNumber, int setMe) {

        // CREATE IMAGE ARRAY WITH "itemNumber" SCREENS
        this.spriteIcon = new ImageIcon[itemNumber];

        try {
            InputStream input = classLoader.getResourceAsStream("static/img/" + strURL);
            BufferedImage e = ImageIO.read(input);

            this.setSize(width / itemNumber, height);

            for (int i = 0; i <= itemNumber - 1; ++i) {
                // docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html
                this.spriteIcon[i] = new ImageIcon(e.getSubimage(i * (width / itemNumber), 0, width / itemNumber, height));
            }

            this.setIcon(this.spriteIcon[setMe]);

        } catch (Exception ex) {
            System.out.println("Error file " + strURL + " " + ex.getMessage());
        }

    }

}

