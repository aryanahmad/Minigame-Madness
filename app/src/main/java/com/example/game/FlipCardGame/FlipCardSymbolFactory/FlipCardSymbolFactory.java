package com.example.game.FlipCardGame.FlipCardSymbolFactory;

import java.util.ArrayList;

/**
 * the FlipCardSymbolFactory class is a factory that creates different symbols for the cards
 * depending on what the user selected
 *
 * @author Gerald, Harbaksh
 */
public class FlipCardSymbolFactory {
    private ArrayList<String> supportedSymbols;

    /**
     * Constructor that gets the different types of symbol names
     */
    public FlipCardSymbolFactory() {
        this.supportedSymbols = this.getSymbolNames();
    }

    /**
     * Symbol names getter
     *
     * @return an ArrayList that contains the different types of symbols the user can select
     */
    private ArrayList<String> getSymbolNames() {
        ArrayList<String> strSupportedSymbolList = new ArrayList<>();
        strSupportedSymbolList.add("Character");
        strSupportedSymbolList.add("Number");
        strSupportedSymbolList.add("Ascii");
        return strSupportedSymbolList;
    }

    /**
     * returns a FlipCardSymbol object of type CharacterSymbol, NumberSymbol, or AsciiSymbol
     * depending on what symbol type the user selected
     *
     * @param numMatches the number of possible card matches
     * @param symbolType a string that describes the symbol type
     * @return FlipCardSymbol object
     */
    public FlipCardSymbol getSymbol(int numMatches, String symbolType) {
        if (1 <= numMatches && numMatches <= 16) {
            switch (symbolType) {
                case "Character":
                    return new CharacterSymbol(numMatches);
                case "Number":
                    return new NumberSymbol(numMatches);
                case "Ascii":
                    return new AsciiSymbol(numMatches);
            }
        } else {
            throw new UnsupportedOperationException("This Number of Buttons is not supported");
        }
        return null;
    }

    /**
     * Supported Symbol getter
     *
     * @return an ArrayList with the supported symbols for the game
     */
    public ArrayList<String> getSupportedSymbols() {
        return this.supportedSymbols;
    }
}
