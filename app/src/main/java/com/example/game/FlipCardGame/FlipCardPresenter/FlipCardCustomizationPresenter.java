package com.example.game.FlipCardGame.FlipCardPresenter;

import com.example.game.FlipCardGame.FlipCardSymbolFactory.FlipCardSymbolFactory;

import java.util.ArrayList;


/**
 * The FlipCardCustomizationPresenter class deals with the Flip Card Game customization screen
 * specifically with the spinner and the symbol used for the flipCards
 *
 * @author Gerald, Harbaksh
 */
public class FlipCardCustomizationPresenter {
    private FlipCardCustomizationPresenter.View view;
    private FlipCardSymbolFactory symbolFactory;

    /**
     * this method creates a new flipcard symbol factory and sets the view to the one passed in
     *
     * @param view the view
     */
    public FlipCardCustomizationPresenter(FlipCardCustomizationPresenter.View view) {
        this.symbolFactory = new FlipCardSymbolFactory();
        this.view = view;
    }

    /**
     * Initialize the screen with the specific card symbols
     */
    public void initializeScreen() {
        this.view.addToSpinner(this.symbolFactory.getSupportedSymbols());
    }

    /**
     * add the different types of symbols available to the spinner on the customization screen
     */
    public interface View {
        void addToSpinner(ArrayList<String> listOfSupportedSymbols);
    }
}
