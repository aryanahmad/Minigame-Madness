package com.example.game.LoginScreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.game.R;

/**
 * Startup Class is the class for the window of the startup splash screen. It shows a gif,
 * and also hides the ActionBar. It then starts the MainActivity to start the game itself.
 *
 * @author Aryan Ahmad
 */

public class Startup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_startup);
        //remove action bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        //start gif, wait 1.5 seconds, then send to MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Startup.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        },2500);
    }


    }

