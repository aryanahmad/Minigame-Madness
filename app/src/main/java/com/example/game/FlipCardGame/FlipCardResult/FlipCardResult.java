package com.example.game.FlipCardGame.FlipCardResult;

import java.io.Serializable;

/**
 * The FlipCardResult class, a class to keep track of all the results in the flip card game
 *
 * @author Gerald, Harbaksh
 */
public class FlipCardResult implements Serializable {
    private int numCorrect;
    private int timeToCompletion;
    private int numTotalMatches;
    private String difficulty;

    /**
     * Constructor
     *
     * @param difficulty      String
     * @param numMatches      int
     * @param numTotalMatches int
     * @param time            long
     */
    public FlipCardResult(String difficulty, int numMatches, int numTotalMatches, long time) {
        this.difficulty = difficulty;
        this.numCorrect = numMatches;
        this.timeToCompletion = this.roundTime(time);
        this.numTotalMatches = numTotalMatches;
    }

    /**
     * rounding the time
     *
     * @param time long
     * @return the rounded time in int
     */
    private int roundTime(long time) {
        return Math.round(time / 1000);
    }

    /**
     * @return time till completion in int
     */
    public int getTimeToCompletion() {
        return this.timeToCompletion;
    }

    /**
     * @return time to completion in seconds
     */
    public String getStrTimeToCompletion() {
        return (this.timeToCompletion) + " seconds";
    }

    /**
     * @return the number of correct matches
     */
    public String getStrNumCorrect() {
        return Integer.toString(this.numCorrect);
    }

    /**
     * @return the number of incorrect matches
     */
    public String getStrNumIncorrect() {
        return Integer.toString(this.numTotalMatches - this.numCorrect);
    }

    /**
     * @return the difficulty
     */
    public String getStrDifficulty() {
        return this.difficulty;
    }
}
