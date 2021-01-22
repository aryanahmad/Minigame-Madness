package com.example.game.UserHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.game.ButtonClickGame.ButtonClickMain;
import com.example.game.R;
import com.example.game.User.CurrUser;

/**
 * Activity page for user to select the game they want to play
 *
 * @author Henry
 */

public class LevelSelectScreen extends AppCompatActivity {

    CurrUser currUser;

    Button btnButtonPress, btnMathGame, btnCardFlip;

    Intent goCustomization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select_screen);

        currUser = new CurrUser(this);
        goCustomization = new Intent(getApplicationContext(), CustomizationScreen.class);
    }

    /**
     * Starts the intent to go to Customization Screen
     */
    public void goCustomizationScreen(){
        startActivity(goCustomization);
        finish();
    }

    /**
     *  Notifies Customization screen, user selected Button Press Game (by int Extra)
     * @param view Button for Button Press Game
     */
    public void btnButtonPressFunc(View view){
        goCustomization.putExtra("Level", 1);
        goCustomizationScreen();
    }

    /**
     *  Notifies Customization screen, user selected Math Game Game (by int Extra)
     * @param view Button for Math Game
     */
    public void btnMathGameFunc(View view){
        goCustomization.putExtra("Level", 2);
        goCustomizationScreen();
    }

    /**
     *  Notifies Customization screen, user selected Flip Card Game (by int Extra)
     * @param view Button for Flip Card Game
     */
    public void btnFlipCardFunc(View view){
        goCustomization.putExtra("Level", 3);
        goCustomizationScreen();
    }

}
