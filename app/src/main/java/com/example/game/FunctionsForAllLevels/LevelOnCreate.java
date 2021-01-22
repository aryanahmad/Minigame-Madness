package com.example.game.FunctionsForAllLevels;

import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;

import androidx.appcompat.app.AlertDialog;

/**
 * Initiates popup instructions on how to play, in all 3 levels
 * @author Henry
 */

public class LevelOnCreate {

    public LevelOnCreate(Context context, String instructions) {
        /**
         * constructor used to display game instructions for game without count down timer
         *
         * @param context context of activity that is calling this constructor
         * @param instructions instructions for the current level
         */
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Level Instructions")
                .setMessage(instructions)
                .setPositiveButton("OK", null)
                .show();
    }

    public LevelOnCreate(Context context, String instructions, final CountDownTimer t) {
        /**
         * constructor used to display game instructions for games with count down timers
         *
         * @param context context of activity that is calling this constructor
         * @param instructions instructions for the current level
         * @param t CountDownTimer object instantiated in a level
         */

        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Level Instructions")
                .setMessage(instructions)
                .setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                t.start();
                            }
                        })
                .show();
    }
}
