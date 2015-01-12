package com.github.lizzylizzle.mmg;

import com.github.lizzylizzle.mmg.ui.utilSprite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// TODO: A CONTROLLER PER VIEW...

/**
 * Game controller
 */
class Controller {

    private Thread fThread;

    /**
     * Our ui loop runs when everything is initialised.
     * True goes into update loop. False blocks updating.
     */
    private boolean gameRunning = false;

    /**
     * Time between our frames in milliseconds
     */
    private long sleep = 0L;

    /**
     * We calculate frames in our loop, every 40st frame we will try to drop a candy
     */
    private int mmFallCount = 0;

    /**
     * Static vertical position of our catcher
     * (so we don't need to recalculate in ui loop but only when we first draw it)
     */
    private int bowlTopTop; // VERTICAL POSITION CATCHER
    private int bowlTopBottom;

    /**
     * Have our model and view foo
     */
    private final Model model;
    private final View view;


    /**
    * Our ui controller
    * @param  model our generic model for our ui
    * @param  view our generic ui view
    */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }


    /**
     * Game over. See if we have new high score.
     */
    void endGame() {
        gameRunning = false;

        // DID WE HAVE NEW HIGH SCORE?
        if (model.getScore() > model.getHighScore()) {
            model.setHighScore(model.getScore());
            view.myViewScore.setHighScore(model.getScore());
        }

        // Show new ui modal so user can start new ui
        view.empty();

    }


    /**
     * Every frame calls this function. Here is were our ui is at.
     */
    void updateScreen() {

        // UPDATE UI ELEMENTS
        view.update(this.sleep);

        // ONE CANDY EACH 40 FRAME UPDATES
        if (this.mmFallCount == 40) {
            this.mmFallCount = 0;
            view.myViewCandy.show();
        }
        ++mmFallCount;

        /**
         * Find out if we did catch our Candy or if it reached the ground
         */
        int screenHeight = view.getScreenHeight();
        int lives = model.getLives();


        utilSprite Bowl = view.myBowl.getBowl();
        utilSprite[] mm = view.myViewCandy.getCandy();

        int bowlTopLeft = Bowl.getX(); // TOP LEFT
        int bowlTopRight = Bowl.getX() + Bowl.getWidth(); // TOP RIGHT

        /**
         * Check for every candy which is occupied if it catch or reached ground
         */
        for (int x = 0; x < model.getCandies(); ++x) {

            // A FALLING M&M
            if (!mm[x].notOccupied) {

                int mmTopTop = mm[x].getY(); // MM top top
                int mmTopBottom = mm[x].getY() + mm[x].getHeight(); // MM top bottom
                int mmTopLeft = mm[x].getX(); // MM top left
                int mmTopRight = mm[x].getX() + mm[x].getWidth(); // MM top right

                // DID MM REACH GROUND?
                if (mmTopTop > screenHeight - 120) {

                    /**
                     * Candy reached ground
                     */
                    mm[x].Speed = 0;
                    mm[x].notOccupied = true;

                    // TAKE LIFE FROM GAME
                    if (lives >= 0) {
                        view.myViewLives.remove(model.getLives());
                        model.missed();
                    } else {
                        this.endGame();
                    }

                }

                // Did CATCHER catch our M&M
                if (mmTopBottom > bowlTopTop && mmTopBottom < bowlTopBottom &&
                        (mmTopRight >= bowlTopLeft && bowlTopRight >= mmTopRight ||
                                bowlTopRight >= mmTopLeft && mmTopLeft >= bowlTopLeft ||
                                bowlTopLeft >= mmTopLeft && mmTopRight >= bowlTopLeft)) {

                    /**
                     * Candy was saved
                     */
                    mm[x].Speed = 0;
                    mm[x].notOccupied = true;
                    mm[x].setVisible(false);

                    /**
                     * Add point to our model
                     */
                    model.scored();
                    // UPDATE SCORE IN OUR MODEL
                    int score = model.getScore();

                    /**
                     * Show new score to gamer
                     */
                    view.myViewScore.setScore(score);

                    /**
                     * Give one free live if user got 120 points
                     */
                    if (score == 120) {
                        model.extraLife();
                        view.myViewLives.extraLive();
                    }

                    /**
                     * After every 20 points we increase our speed (max speed is set in candy view)
                     */
                    int updateSpeed = score % 20;
                    if (updateSpeed == 0) {
                        view.myViewCandy.moreSpeed();
                        view.myBowl.moreSpeed();
                    }
                }
            }
        }
    }


    /**
     * Start new thread
     */
    void initThread() {
        try {
            if (this.fThread == null) {
                this.fThread = new Thread(Thread.currentThread());
                this.fThread.start();
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }

    }


    /**
     * Our catcher can now listen to key press left and right
     */
    void initBowl() {

        utilSprite Bowl = view.myBowl.getBowl();

        Bowl.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {

                // SPACEBAR: ONLY START GAME WHEN GAME IS OVER
                if (e.getKeyCode() == 32 && !gameRunning) {
                    reset();
                }

                if (e.getKeyCode() == 37) {
                    // 0 LEFT IMAGE FROM SPRITE
                    view.myBowl.move(0, -1);
                }

                if (e.getKeyCode() == 39) {
                    // 2 LEFT IMAGE FROM SPRITE
                    view.myBowl.move(2, 1);
                }

            }

            public void keyReleased(KeyEvent e) {
            }
        });


        // BOWL VERTICAL POSITION DOESN'T CHANGE SO WE CAN SET THEM ONCE
        this.bowlTopTop = Bowl.getY(); // TOP TOP
        this.bowlTopBottom = bowlTopTop + Bowl.getHeight(); // TOP BOTTOM

    }


    /**
     * Our magic ui loop!
     */
    void gameLoop() {

        long beforeTime = System.currentTimeMillis();

        if (this.fThread != null) {
            // Runs forever
            while (true) {

                this.sleep = System.currentTimeMillis() - beforeTime;
                beforeTime = System.currentTimeMillis();

                if (gameRunning) {
                    this.updateScreen();
                }

                try {
                    // FRAME SLEEPING TIME FOR NEXT UPDATE
                    int DELAY = 30;
                    Thread.sleep((long) DELAY);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }


    /**
     * Game over
     */
    void reset() {
        gameRunning = true;
        mmFallCount = 0;

        model.reset();

        view.myViewCandy.reset();
        view.myViewLives.setLives(model.getLives());
        view.myViewScore.setScore(model.getScore());

    }


    /**
     * Start button

    void initButton() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                view.getButton().setVisible(false);
                reset();
            }
        };

        view.getButton().addActionListener(actionListener);
    }
     */

    /**
     * Controller of our Game starts
     */
    public void init() {

        // Items come from model and passed into our view so they can paint
        view.ui(model.getLives(), model.getCandies(), model.getTheme());

        initThread();
        initBowl();
        /* initButton(); */

        /**
         * Important this function loops forever
         * So put as last in our setup
         */
        gameLoop();

    }


}

