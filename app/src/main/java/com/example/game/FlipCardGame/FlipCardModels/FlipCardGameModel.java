package com.example.game.FlipCardGame.FlipCardModels;

import android.os.CountDownTimer;

import com.example.game.FlipCardGame.Cards.FlipCards;
import com.example.game.FlipCardGame.FlipCardPresenter.FlipCardMainPresenter;

import java.util.ArrayList;

/**
 * The FlipCardGameModel Abstract class is one of the blueprints for the FlipCard game
 *      This specifies the functions that a class must implement if it wants to manage FlipCards
 * @author Gerald, Harbaksh
 */
public abstract class FlipCardGameModel {
    int numMatches;
    int numCorrect;
    int numMatchAttempt;
    ArrayList<FlipCards> allCards;
    FlipCardMainPresenter presenter;

    /**
     * Abstract method to update the flipCards
     *
     * @param flipCards
     */
    public abstract void update(FlipCards flipCards);

    /**
     * @return the number of matches
     */
    public int getNumMatches() {
        return this.numMatches;
    }

    /**
     * @param cardList the cards that will be played with
     */
    public void setCards(ArrayList<FlipCards> cardList) {
        this.allCards = cardList;
    }

    /**
     * calculating the time elapsed
     *
     * @return the elapsed time
     */
    long returnElapsedTime() {
        return this.presenter.getTimeElapsed();
    }

    /**
     * goes over the list of all cards, if 2 of them are flipped, check if they are a match
     * if they are then update cards and lock them
     * if they aren't then we put a delay then flip them back
     */
    void updateCards() {
        ArrayList<FlipCards> flipped = new ArrayList<>();
        for (FlipCards f : this.allCards) {
            if (f.isFlipped()) {
                flipped.add(f);
            }
            if (flipped.size() == 2) {
                FlipCards flippedCard1 = flipped.get(0);
                FlipCards flippedCard2 = flipped.get(1);
                if (flippedCard1.getSymbol().equals(flippedCard2.getSymbol())) {
                    flippedCard1.lockCard();
                    flippedCard2.lockCard();
                    this.numCorrect++;
                    this.numMatchAttempt++;
                    this.updateScore();
                } else {
                    FlipCards.disableCards = true;
                    this.nonMatchCardDelay(flippedCard1, flippedCard2);
                    this.numMatchAttempt++;
                    this.updateScore();
                }
                break;
            }
        }
    }

    /**
     * putting delay then flipping the 2 in correctly matched cards back
     *
     * @param f1 FlipCards obj
     * @param f2 FlipCards obj
     */
    private void nonMatchCardDelay(final FlipCards f1, final FlipCards f2) {
        new CountDownTimer(450, 450) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                f1.flipCard();
                f2.flipCard();
                FlipCards.disableCards = false;
            }
        }.start();
    }

    /**
     * update score board
     */
    private void updateScore() {
        this.presenter.updateScore(this.numCorrect, this.numMatchAttempt);
    }
}