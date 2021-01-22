package com.example.game.FlipCardGame.FlipCardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.FlipCardGame.FlipCardResult.FlipCardResult;
import com.example.game.UserHome.HomePage;
import com.example.game.R;
import com.example.game.FunctionsForAllLevels.ResultFacade;
import com.example.game.User.UserInfoFacade;

/**
 * The FlipCardResultView class
 * Responsible for displaying the results of the FlipCard Game
 *
 * @author Gerald, Harbaksh
 */
public class FlipCardResultView extends AppCompatActivity {
    UserInfoFacade user;
    private FlipCardResult result;

    /**
     * Ends the game result page
     *
     * @param view View
     */
    public void endGame(View view) {
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        startActivity(start);
        finish();
    }

    /**
     * Displaying the scores with the given information
     *
     * @param newResult        FlipCardResult
     * @param difficulty       TextView
     * @param timeToCompletion TextView
     * @param numCorrect       TextView
     * @param numIncorrect     TextView
     */
    private void displayScores(
            FlipCardResult newResult,
            TextView difficulty,
            TextView timeToCompletion,
            TextView numCorrect,
            TextView numIncorrect) {
        difficulty.setText(newResult.getStrDifficulty());
        timeToCompletion.setText(newResult.getStrTimeToCompletion());
        numCorrect.setText(newResult.getStrNumCorrect());
        numIncorrect.setText(newResult.getStrNumIncorrect());
    }


    /**
     * receives an instance of the FlipCardResult Class then display the scores within that object
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card_result);
        this.user = new UserInfoFacade(this);
        Bundle receiver = getIntent().getExtras();
        if (receiver != null) {
            this.result = (FlipCardResult) receiver.get("FlipCardResult");
            this.setResult(this.result);
        }
        ResultFacade facade = new ResultFacade(this);
        facade.dataSave(result.getTimeToCompletion());
    }

    /**
     * set the results of the game
     *
     * @param result FlipCardResult
     */
    private void setResult(FlipCardResult result) {
        TextView difficulty = findViewById(R.id.DifficultyTextView);
        TextView timeToCompletion = findViewById(R.id.TimetoCompletionTextView);
        TextView numCorrect = findViewById(R.id.NumCorrectMatchesTextView);
        TextView numIncorrect = findViewById(R.id.NumIncorrectTextView);
        this.displayScores(result, difficulty, timeToCompletion, numCorrect, numIncorrect);
    }

    /**
     * Overriding the android default back button so it goes back to the home page
     */
    @Override
    public void onBackPressed() {
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        start.putExtra("androidBack", 1);
        startActivity(start);
        finish();
    }

}
