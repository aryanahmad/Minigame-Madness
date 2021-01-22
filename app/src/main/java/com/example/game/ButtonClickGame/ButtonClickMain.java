package com.example.game.ButtonClickGame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.User.CurrUser;
import com.example.game.UserHome.HomePage;
import com.example.game.FunctionsForAllLevels.LevelOnCreate;
import com.example.game.R;

import java.util.Random;

/**
 * The ButtonClickMain class is the main class for Level 1, the Button Click game. It houses all the
 * code for the functionality of the game then sends the results to ButtonClickResult.java.
 *
 * @author Mohammad Hasan, Aryan Ahmad
 */

public class ButtonClickMain extends AppCompatActivity {

    // -------Variable Declarations------
    private Button[][] buttons = new Button[5][4];
    private Random r = new Random();
    private CountDownTimer timer;
    private CurrUser user;
    private int diff_time;
    private int numClicks = 0;
    private int score = 0;
    private TextView scoreTxt;
    private String scorePlaceholder;

    // ----------------------------------

    /**
     * This method sends the score and total number of clicks to the ButtonClickResult class, and
     * then starts an Activity to go to that class.
     */
    public void goButtonClickResult() {
        user.stopMusic();
        Intent goResult = new Intent(this, ButtonClickResult.class);
        goResult.putExtra("Total Clicks", numClicks);
        goResult.putExtra("Score", score);
        startActivity(goResult);
        finish();
    }

    /**
     * This method randomly selects a button to be visible on the screen.
     *
     * @param buttons a two-dimensional array/table of Buttons
     */
    private void buttonVisVisible(Button[][] buttons) {
        // Selects a random button to make visible.
        int testR = r.nextInt(5);
        int testC = r.nextInt(4);
        buttons[testR][testC].setVisibility(View.VISIBLE);
    }

    /**
     * This method handles all the clicks on any visible button on the screen.
     *
     * @param button a Button
     * @return OnClickListener a listener for the button clicks
     */
    private View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            int timesClicked = 0;
            boolean b = false;

            public void onClick(View v) {
                if (button.getVisibility() == View.VISIBLE) {
                    numClicks += 1;
                    score += 1;
                    timesClicked += 1;
                }
                if (timesClicked == 3 && !b) {
                    b = true;
                    score = score * 2;
                }

                scorePlaceholder = score + " | " + numClicks;
                scoreTxt.setText(scorePlaceholder);
            }
        };
    }

    /**
     * This method handles all the incorrect clicks on the screen.
     *
     * @return OnClickListener a listener for all the clicks on the screen, including the clicks on
     * any visible button.
     */
    private View.OnClickListener handleIncorrect() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClicks += 1;
                scorePlaceholder = score + " | " + numClicks;
                scoreTxt.setText(scorePlaceholder);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_main);
        scoreTxt = findViewById(R.id.scoreBtnClick);
        final TextView countDownTxt = findViewById(R.id.countDownText);
        TableLayout tableLayout = findViewById(R.id.tableLayoutBtns);
        tableLayout.setOnClickListener(handleIncorrect());

        // --------User Creation and Parsing---------
        user = new CurrUser(this);
        user.setCurrLevel(1);
        user.playMusic();

        if (user.getDifficultySelected().equals("hard")) {
            diff_time = 333;
        } else {
            diff_time = 1000;
        }

        // ----Creation of Button Grid---------
        for (int r = 0; r < 5; r++) {
            TableRow currentRow = new TableRow(this);
            for (int c = 0; c < 4; c++) {
                Button currentButton = new Button(this);
                currentButton.setOnClickListener(handleOnClick(currentButton));
                currentButton.setBackgroundColor(user.getColorSelected()); // Colour Customization
                buttons[r][c] = currentButton;
                currentRow.addView(currentButton);
            }
            tableLayout.addView(currentRow);
        }

        // ---- Creation of CountDownTimer/Difficulty Customization-----
        diff_time = 1000;
        timer =
                new CountDownTimer(60000, diff_time) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        // Set every button to be invisible
                        for (int r = 0; r < 5; r++) {
                            for (int c = 0; c < 4; c++) {
                                buttons[r][c].setVisibility(View.INVISIBLE);
                            }
                        }
                        // Set a random button to visible
                        buttonVisVisible(buttons);
                        String countDownPlaceholder = (int) millisUntilFinished / 1000 + "s";
                        countDownTxt.setText(countDownPlaceholder);
                    }
                    // it should push hopefully
                    public void onFinish() {
                        // So far have made intent to go to result screen
                        goButtonClickResult();
                    }
                };

        String instructions;
        instructions = "Click on the moving button as many times as you can";
        LevelOnCreate levelOnCreate = new LevelOnCreate(this, instructions, timer);
    }

    /**
     * This method overrides the given function onBackPressed, which handles when the user clicks
     * the back button on the device.
     */
    @Override
    public void onBackPressed() {
        timer.cancel();
        user.stopMusic();
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        start.putExtra("androidBack", 1);
        startActivity(start);
        finish();
    }
}
