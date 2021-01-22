package com.example.game.MathGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.User.CurrUser;
import com.example.game.FlipCardGame.FlipCardInit;
import com.example.game.UserHome.HomePage;
import com.example.game.R;
import com.example.game.FunctionsForAllLevels.ResultFacade;

/**
 * Activity used to display result statistics for level 2 (MathGame)
 * @author Henry
 */

public class MathGameResult extends AppCompatActivity {

    int totalCorrect, totalFailedAttempts, time;
    double speed;
    TextView textTotalCorrect, textFailedAttempts, textSpeed;
    CurrUser user;
    ResultFacade resultFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game_result);

        textTotalCorrect = findViewById(R.id.textTotalCorrect);
        textFailedAttempts = findViewById(R.id.textFailedAttempts);
        textSpeed = findViewById(R.id.textSpeed);

        Intent intentOrigin = getIntent();
        totalCorrect = intentOrigin.getIntExtra("numCorrect", 0);
        totalFailedAttempts = intentOrigin.getIntExtra("numFailedAttempts", 0);
        time = intentOrigin.getIntExtra("time", 0);
        speed = Math.round(100 * (double) totalCorrect / time) / 100.0;
        // Math.round produces an int, so need to divide by 100.0 to get double
        // Math.round will round to nearest decimal, so if get a value of something like 0.131123 but if round this
        // to nearest decimal will be 0, so need to multiple by 100 --> 13.1123 then round to remove excess digits --> 13 divide by 100 --> 0.13
        String strTotalCorrect = Integer.toString(totalCorrect);
        String strTotalFailedAttempts = Integer.toString(totalFailedAttempts);
        String strSpeed = speed + "/s";
        textTotalCorrect.setText(strTotalCorrect);
        textFailedAttempts.setText(strTotalFailedAttempts);
        textSpeed.setText(strSpeed);

        resultFacade = new ResultFacade(this);
        resultFacade.dataSave(totalCorrect);
    }

    /**
     * Goes back to HomePage
     */
    public void goHome (){
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        startActivity(start);
        finish();
    }

    public void btnFinishFunc(View view) {
        goHome();
    }

    @Override
    public void onBackPressed() {
        goHome();
    }
}
