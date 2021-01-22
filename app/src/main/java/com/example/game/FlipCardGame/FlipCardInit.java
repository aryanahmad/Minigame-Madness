package com.example.game.FlipCardGame;


import android.content.Context;
import android.content.Intent;

import com.example.game.FlipCardGame.FlipCardView.FlipCardCustomizationView;

/**
 * The FlipCardInit class, this class acts as a front facing interface for others to initialize the
 * FlipCardGameModel
 *
 * @author Gerald, Harbaksh
 */
public class FlipCardInit {

    /**
     * empty constructor
     */
    public FlipCardInit() {
    }

    /**
     * creating a new intent with the current Context and starting the activity
     *
     * @param currContext Context
     */
    public void startGame(Context currContext) {
        Intent toCustomizationScreen = new Intent(currContext, FlipCardCustomizationView.class);
        currContext.startActivity(toCustomizationScreen);
    }
}
