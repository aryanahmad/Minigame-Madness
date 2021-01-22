package com.example.game.FlipCardGame.FlipCardSymbolFactory;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The FlipCardSymbol Abstract class is the parent class for the different types of symbols that
 * appear on the cards, like NumberSymbol, CharacterSymbol or the AcsiiSymbol class
 *
 * @author Gerald, Harbaksh
 */
public abstract class FlipCardSymbol {

    /**
     * Will be implemented by subclasses
     *
     * @param numMatches int
     * @return ArrayList<String>
     */
    abstract ArrayList<String> generateSymbol(int numMatches);

    /**
     * shuffles the arraylist so the symbols are in a random order
     *
     * @param listToShuffle the list that has to be shuffled
     * @return the shuffled list
     */
    void shuffleArray(ArrayList<String> listToShuffle) {
        // ArrayList<String> newList = listToShuffle;
        Collections.shuffle(listToShuffle);
    }

    /**
     * symbol getter, will be implemented by subclasses
     *
     * @return an ArrayList of types String with the symbols
     */
    public abstract ArrayList<String> getSymbols();
}
