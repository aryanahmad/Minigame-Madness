package com.example.game.FlipCardGame.FlipCardModels;

import com.example.game.FlipCardGame.Cards.FlipCards;
import com.example.game.FlipCardGame.FlipCardPresenter.FlipCardMainPresenter;
import com.example.game.FlipCardGame.FlipCardResult.FlipCardResult;

import java.util.ArrayList;

/**
 * The FlipCardMainGameModel class is one of the classes responsible for some FlipCard game aspects
 * that the user interacts with
 *
 * @author Gerald, Harbaksh
 */
public class FlipCardMainGameModel extends FlipCardGameModel {
    private String difficulty;
    private boolean firstClick;
    private ArrayList<Long> timeActionList;
    private ArrayList<FlipCards> cardActionList;

    /**
     * @param difficulty the difficulty
     * @param presenter  FlipCardMainPresenter obj
     */
    public FlipCardMainGameModel(String difficulty, FlipCardMainPresenter presenter) {
        this.flipCardGameManagerBuilder(difficulty, presenter);
    }

    /**
     * @param difficulty the difficulty
     * @param presenter  FlipCardMainPresenter obj
     */
    private void flipCardGameManagerBuilder(String difficulty, FlipCardMainPresenter presenter) {
        this.difficulty = difficulty;
        this.setNumMatches(this.difficulty);
        this.numCorrect = 0;
        this.numMatchAttempt = 0;
        this.presenter = presenter;
        this.firstClick = false;
        this.timeActionList = new ArrayList<>();
        this.cardActionList = new ArrayList<>();
    }

    /**
     * @param cardCalled FLipCards obj
     */
    @Override
    public void update(FlipCards cardCalled) {
        if (!this.firstClick) {
            this.presenter.startTimer();
            this.firstClick = true;
        }
        if (!cardCalled.isFlipped()) {

            this.cardActionList.add(cardCalled);
            this.timeActionList.add(this.returnElapsedTime());
            cardCalled.flipCard();
        }
        this.updateCards();
        if (this.numCorrect == this.numMatches) {
            this.presenter.stopTimer();
            long timeToCompleteMs = this.returnElapsedTime();
            FlipCardResult newResult =
                    new FlipCardResult(
                            this.difficulty, this.numCorrect, this.numMatchAttempt, timeToCompleteMs);
            this.presenter.endGame(newResult);
        }
    }

    /**
     * set numMatches based on the difficulty of the game selected buy the user
     *
     * @param difficulty the difficulty
     */
    private void setNumMatches(String difficulty) {
        if (difficulty.equals("easy")) this.numMatches = 8;
        else this.numMatches = 16;
    }

    /**
     * @return the instructions
     */
    public String getInstructions() {
        return "Match the cards!(Timer goes off when you click on one of them)";
    }

    /**
     * @return the cardActionList
     */
    public ArrayList<FlipCards> getCardActionList() {
        return this.cardActionList;
    }

    /**
     * @return the timeActionList
     */
    public ArrayList<Long> getTimeActionList() {
        return this.timeActionList;
    }

    /**
     * @return allCards
     */
    public ArrayList<FlipCards> getLastStateList() {
        return this.allCards;
    }
}
