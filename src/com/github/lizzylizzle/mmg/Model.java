package com.github.lizzylizzle.mmg;

/**
 * Game Model holding all configurations and game data
 */
class Model {

    private String theme;
    private int lives;
    private int score;
    private int highScore;
    private final int candies;

    private final int defaultLives = 2; // ARRAY 0-1-2 equals 3 lives, plus one you start with so 4 lives

    /**
     * Constructor for our Game Model
     * It currently holds: lives, score and high score
     */
    public Model() {
        theme = "summer";
        candies = 8;
        lives = defaultLives;
        score = 0;
        highScore = 0;
    }

    /**
     * Reset our model to begin state
     */
    public void reset(){
        this.score = 0;
        this.lives = defaultLives;
    }

    /**
     * How many lives do we have left
     * @return lives left
     */
    public int getLives() {
        return lives;
    }

    /**
     * Get current score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get current high score
     * @return high score
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * During the ui we are able to give an extra live
     */
    public void extraLife() {
        ++lives;
    }

    /**
     * Set new high score when user got best score during his session
     * @param score The score
     */
    public void setHighScore(int score) {
        this.highScore = score;
    }

    /**
     * Update our score with new one
     */
    public void scored(){
        score++;
    }

    /**
     * Take one live
     */
    public void missed() {
        --lives;
    }

    /**
     * Get our theme
     * @return theme given for graphics
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Get our amount of different candies
     * @return amount
     */
    public int getCandies() {
        return candies;
    }
}
