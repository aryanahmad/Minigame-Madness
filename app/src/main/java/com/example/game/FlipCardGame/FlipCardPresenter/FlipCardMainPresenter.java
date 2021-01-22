package com.example.game.FlipCardGame.FlipCardPresenter;

import com.example.game.FlipCardGame.FlipCardResult.FlipCardResult;
import com.example.game.FlipCardGame.FlipCardView.FlipCardGameView;

/**
 * The FlipCardMainPresenter Abstract class is one for the blueprints for the Flip Card game
 * Specifies the functions that must be implemented for a presenter to be able to present
 *         things on the Main View of the FlipCard Game
 * @author Gerald, Harbaksh
 */
public abstract class FlipCardMainPresenter {
    FlipCardGameView view;

    /**
     * abstract method, will be implemented by the class implementing it
     */
    public abstract void startDisplay();

    /**
     * @return the time elapsed
     */
    public long getTimeElapsed() {
        return this.view.timeElapsed();
    }

    /**
     * Starting the timer
     */
    public void startTimer() {
        this.view.startTime();
    }

    /**
     * stopping the timer
     */
    public void stopTimer() {
        this.view.stopTime();
    }

    /**
     * Updating the score
     *
     * @param numCorrect      correct matches
     * @param numMatchAttempt the number of the match attempt
     */
    public void updateScore(int numCorrect, int numMatchAttempt) {
        String toShow = (numCorrect) + " | " + (numMatchAttempt);
        this.view.updateScore(toShow);
    }

    /**
     * Ending the game
     *
     * @param results FlipCardResult Object
     */
    public void endGame(FlipCardResult results) {
        this.view.gameEnded(results);
    }
}
