package com.example.game.FlipCardGame.FlipCardView;

import android.content.Context;
import android.widget.TableLayout;

import com.example.game.FlipCardGame.FlipCardModels.FlipCardMainGameModel;
import com.example.game.FlipCardGame.FlipCardResult.FlipCardResult;

/**
 * The FlipCardGameView Interface is one of the blueprints for the Flip Card game
 * This is an interface that specifies what functions that must be implemented for a class
 *       to become the main view of the FlipCard game
 *
 * @author Gerald, Harbaksh
 */
public interface FlipCardGameView {

    /**
     * Will be implemented by the class implementing the interface
     *
     * @param results FlipCardResult
     */
    void gameEnded(FlipCardResult results);

    /**
     * Will be implemented by the class implementing the interface
     *
     * @param toShow String
     */
    void updateScore(String toShow);

    /**
     * Will be implemented by the class implementing the interface
     * -Start the time
     */
    void startTime();

    /**
     * Will be implemented by the class implementing the interface
     * -Stop the time
     */
    void stopTime();

    /**
     * Will be implemented by the class implementing the interface
     *
     * @return time(long) elapsed
     */
    long timeElapsed();

    /**
     * Will be implemented by the class implementing the interface
     *
     * @return Context object
     */
    Context getContext();

    /**
     * Will be implemented by the class implementing the interface
     *
     * @return the difficulty of the game
     */
    String getDifficulty();

    /**
     * Will be implemented by the class implementing the interface
     *
     * @return the color
     */
    int getColor();

    /**
     * Will be implemented by the class implementing the interface
     *
     * @return the symbol choice
     */
    String getSymbolChoice();

    /**
     * Will be implemented by the class implementing the interface
     *
     * @return TableLayout
     */
    TableLayout getTableLayout();

    /**
     * Will be implemented by the class implementing the interface
     *
     * @param instructions for the flip card game
     */
    void displayInstructions(String instructions);

    /**
     * Will be implemented by the class implementing the interface
     *
     * @return the results of the game played
     */
    FlipCardResult getResults();

    /**
     * Will be implemented by the class implementing the interface
     *
     * @return the current game
     */
    FlipCardMainGameModel getCurrGame();
}