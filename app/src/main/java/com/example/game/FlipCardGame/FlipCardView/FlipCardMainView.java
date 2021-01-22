package com.example.game.FlipCardGame.FlipCardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.FlipCardGame.FlipCardModels.FlipCardMainGameModel;
import com.example.game.FlipCardGame.FlipCardPresenter.FlipCardGamePresenter;
import com.example.game.FlipCardGame.FlipCardPresenter.FlipCardReplayPresenter;
import com.example.game.FlipCardGame.FlipCardResult.FlipCardResult;
import com.example.game.UserHome.HomePage;
import com.example.game.FunctionsForAllLevels.LevelOnCreate;
import com.example.game.R;
import com.example.game.User.UserInfoFacade;

/**
 * The FlipCardMainView class
 * This class is responsible for displaying the view of the main game of flipcard
 *
 * @author Gerald, Harbaksh
 */
public class FlipCardMainView extends AppCompatActivity implements FlipCardGameView {
    private UserInfoFacade currUser;
    private TextView flipCardScore;
    private Chronometer timer;
    private TableLayout tableLayout;
    private Button btnInstantreplay;
    private Button btnFlipCardResult;
    private FlipCardResult result;
    private FlipCardGamePresenter presenter;

    /**
     * setting game information that will be useful later on
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card);
        this.flipCardScore = findViewById(R.id.flipCardScore);
        this.tableLayout = findViewById(R.id.tableLayoutFlipCard);
        this.btnFlipCardResult = findViewById(R.id.btnFlipCardResult);
        this.btnInstantreplay = findViewById(R.id.btnInstantReplay);
        this.btnInstantreplay.setVisibility(View.INVISIBLE);
        this.btnFlipCardResult.setVisibility(View.INVISIBLE);
        this.timer = findViewById(R.id.flipCardTimer);
        currUser = new UserInfoFacade(this);
        this.currUser.startMusic();
        this.presenter = new FlipCardGamePresenter(this);
        this.presenter.startDisplay();
    }

    /**
     * Displaying the instructions on the screen
     *
     * @param instructions String
     */
    @Override
    public void displayInstructions(String instructions) {
        LevelOnCreate level3 =
                new LevelOnCreate(this, instructions);
    }

    /**
     * to handle if the back button is pressed
     */
    @Override
    public void onBackPressed() {
        this.currUser.stopMusic();
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        start.putExtra("androidBack", 1);
        startActivity(start);
        finish();
    }

    /**
     * @return the symbol choice
     */
    @Override
    public String getSymbolChoice() {
        return getIntent().getStringExtra("symbolChoice");
    }

    /**
     * @return the color
     */
    @Override
    public int getColor() {
        return this.currUser.getSelectedColor();
    }

    /**
     * @return the difficulty
     */
    @Override
    public String getDifficulty() {
        return this.currUser.getSelectedDifficulty();
    }

    /**
     * @return TableLayout object
     */
    @Override
    public TableLayout getTableLayout() {
        return this.tableLayout;
    }

    /**
     * making the instant replay and result button visible and setting the result
     *
     * @param newResult FlipCardResult
     */
    @Override
    public void gameEnded(FlipCardResult newResult) {
        this.btnInstantreplay.setVisibility(View.VISIBLE);
        this.btnFlipCardResult.setVisibility(View.VISIBLE);
        this.result = newResult;

    }

    /**
     * start the time for the game
     */
    @Override
    public void startTime() {
        this.timer.setBase(SystemClock.elapsedRealtime());
        this.timer.start();
    }

    /**
     * stop the timer
     */
    @Override
    public void stopTime() {
        this.timer.stop();
    }

    /**
     * @return total time
     */
    @Override
    public long timeElapsed() {
        return SystemClock.elapsedRealtime() - this.timer.getBase();
    }

    /**
     * @param toShow String
     */
    @Override
    public void updateScore(String toShow) {
        this.flipCardScore.setText(toShow);
    }

    /**
     * @return Context
     */
    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    /**
     * @return the results obj
     */
    @Override
    public FlipCardResult getResults() {
        Bundle receiver = getIntent().getExtras();
        if (receiver != null) {
            return (FlipCardResult) receiver.get("flipCardResults");
        }
        return null;
    }

    /**
     * @return the current game
     */
    @Override
    public FlipCardMainGameModel getCurrGame() {
        return this.presenter.getCurrGame();
    }

    /**
     * showing the results
     *
     * @param view View
     */
    public void btnFlipCardResult(View view) {
        Intent showResult = new Intent(this, FlipCardResultView.class);
        showResult.putExtra("FlipCardResult", this.result);
        this.currUser.stopMusic();
        startActivity(showResult);
        finish();
    }

    /**
     * @param view View
     */
    public void btnInstantReplay(View view) {
        this.btnInstantreplay.setVisibility(View.INVISIBLE);
        FlipCardReplayPresenter replayPresenter = new FlipCardReplayPresenter(this);
        replayPresenter.startDisplay();
    }
}