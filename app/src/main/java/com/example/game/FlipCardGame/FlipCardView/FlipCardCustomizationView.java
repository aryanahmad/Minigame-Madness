package com.example.game.FlipCardGame.FlipCardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.FlipCardGame.FlipCardPresenter.FlipCardCustomizationPresenter;
import com.example.game.UserHome.HomePage;
import com.example.game.R;
import com.example.game.User.UserInfoFacade;

import java.util.ArrayList;

/**
 * The FlipCardCustomizationView class is used for the customization page for the FlipCard game
 *
 * @author Gerald, Harbaksh
 */
public class FlipCardCustomizationView extends AppCompatActivity implements FlipCardCustomizationPresenter.View {
    private Spinner customizationSpn;
    private UserInfoFacade currUser;
    /**
     * Calling the super constructor and setting the spinner
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card_customization);
        this.customizationSpn = findViewById(R.id.spnPickSymbol);
        this.initializeScreenViaPresenter();
        this.currUser = new UserInfoFacade(this);
        this.currUser.setLevel(3);
    }

    /**
     * Initializing the customization Screen
     */
    private void initializeScreenViaPresenter() {
        FlipCardCustomizationPresenter presenter = new FlipCardCustomizationPresenter(this);
        presenter.initializeScreen();
    }

    /**
     * Adding the different types of symbols the user can select to the spinner
     *
     * @param supportedSymbols arrayList with the supported symbols
     */
    @Override
    public void addToSpinner(ArrayList<String> supportedSymbols) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, supportedSymbols);
        this.customizationSpn.setAdapter(adapter);
    }

    /**
     * gets the symbol selected by the user
     *
     * @return the value the user selected
     */
    private String spinnerValueGetter() {
        return this.customizationSpn.getSelectedItem().toString();
    }

    /**
     * Start the FlipCard game and end the Customization screen tasks
     *
     * @param view View
     */
    public void btnEndCustomizations(View view) {
        Intent mainGameIntent = new Intent(this, FlipCardMainView.class);
        mainGameIntent.putExtra("symbolChoice", this.spinnerValueGetter());
        startActivity(mainGameIntent);
        finish();
    }

    /**
     * Overriding the android default back button so it goes back to the home page
     */
    @Override
    public void onBackPressed() {
        this.currUser.stopMusic();
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        start.putExtra("androidBack", 1);
        startActivity(start);
        finish();
    }

}
