package com.example.game.UserHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.game.ButtonClickGame.ButtonClickMain;
import com.example.game.FlipCardGame.FlipCardInit;
import com.example.game.MathGame.MathGame;
import com.example.game.R;
import com.example.game.User.CurrUser;

/**
 * This Activity allows the user to select customizations going into our games
 *
 * @author Henry
 */
public class CustomizationScreen extends AppCompatActivity {

    RadioGroup radioGroupColor, radioGroupDifficulty, radioGroupSoundtrack;
    RadioButton radioCheckedColor, radioCheckedDifficulty, radioCheckedSoundtrack;
    RadioButton radioRed, radioBlue, radioGreen;
    RadioButton radioNoSoundtrack, radioSoundtrack1, radioSoundtrack2;
    CurrUser user;

    /**
     * Saves customizations selected by user into SQLite database and starts a level
     *
     * @param view Start button
     */
    public void btnStartFunc(View view) {
        radioCheckedColor = findViewById(radioGroupColor.getCheckedRadioButtonId());
        radioCheckedDifficulty = findViewById(radioGroupDifficulty.getCheckedRadioButtonId());
        radioCheckedSoundtrack = findViewById(radioGroupSoundtrack.getCheckedRadioButtonId());

        user.setColorSelected(Integer.parseInt(radioCheckedColor.getTag().toString()));
        user.setDifficultySelected(radioCheckedDifficulty.getTag().toString());
        user.setMusicSelected(Integer.parseInt(radioCheckedSoundtrack.getTag().toString()));

        Intent intent = getIntent();
        int level = intent.getIntExtra("Level", 1);
        user.setCurrLevel(level);

        Intent goLevel;
        if (level == 1){
            goLevel = new Intent(this, ButtonClickMain.class);
            startActivity(goLevel);
            finish();
        }else if (level == 2){
            goLevel = new Intent(this, MathGame.class);
            startActivity(goLevel);
            finish();
        }else{
            FlipCardInit newFlipCardGame = new FlipCardInit();
            newFlipCardGame.startGame(this );
            finish();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization_screen);

        radioGroupColor = findViewById(R.id.radioGroupColor);
        radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty);
        radioGroupSoundtrack = findViewById(R.id.radioGroupSoundtrack);

        radioRed = findViewById(R.id.radioRed);
        radioBlue = findViewById(R.id.radioBlue);
        radioGreen = findViewById(R.id.radioGreen);

        radioRed.setTag(Color.RED);

        int lightBlue = Color.parseColor("#4290f5");
        radioBlue.setTag(lightBlue);

        radioGreen.setTag(Color.GREEN);

        radioSoundtrack1 = findViewById(R.id.radioSoundtrack1);
        radioSoundtrack2 = findViewById(R.id.radioSoundtrack2);
        radioNoSoundtrack = findViewById(R.id.radioNoSountrack);

        radioNoSoundtrack.setTag(0);
        radioSoundtrack1.setTag(R.raw.soundtrack1);
        radioSoundtrack2.setTag(R.raw.soundtrack2);

        user = new CurrUser(this);
    }
}
