package com.example.game.ButtonClickGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.game.User.CurrUser;
import com.example.game.UserHome.HomePage;
import com.example.game.MathGame.MathGame;
import com.example.game.R;
import com.example.game.FunctionsForAllLevels.ResultFacade;

/**
 * This class displays the final results from the ButtonClick Game. It shows Total Clicks
 * as well as Score.
 *
 * @author Aryan Ahmad, Mohammad Hasan
 */
public class ButtonClickResult extends AppCompatActivity {

    int numberClicks;
    int correctScore;
//    CurrUser user;
    ResultFacade resultFacade;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click_result);
//        user = new CurrUser(this);
        TextView numClicksText = findViewById(R.id.numClicksScore);
        TextView numCorrectClicksText = findViewById(R.id.numCorrectClicks);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            numberClicks = (int) bundle.get("Total Clicks");
            correctScore = (int) bundle.get("Score");
        }
        numClicksText.setText(String.valueOf(numberClicks));
        numCorrectClicksText.setText(String.valueOf(correctScore));
        resultFacade = new ResultFacade(this);
        resultFacade.dataSave(correctScore);
//        user.setL1RecentScore(correctScore);
//        user.updateL1BestScore();
//        user.setCurrLevel(2);
    }

    /**
     * This Method Sends the game to the next Level (Level 2), the Math game.
     * @param view the current view module
     */
    public void toHomePage(View view) {
        Intent homePage = new Intent(this, HomePage.class);
        startActivity(homePage);
        finish();
    }

    /**
     * This Method overrides the built in onBackPressed, which is what happens when the back button
     * is pressed on the device. Overridden to go back to the home page.
     */
    @Override
    public void onBackPressed() {
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        start.putExtra("androidBack", 1);
        startActivity(start);
        finish();
    }
}
