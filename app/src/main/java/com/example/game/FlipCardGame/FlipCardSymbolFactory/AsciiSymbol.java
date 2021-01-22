package com.example.game.FlipCardGame.FlipCardSymbolFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The AsciiSymbol class is a subclass of the FlipCardSymbol class and deals with the creation
 * of the ascii symbols that might appear on the cards
 *
 * @author Gerald, Harbaksh
 */
class AsciiSymbol extends FlipCardSymbol {
    //factory will check if the number of matches is greater than 16
    private ArrayList<String> asciiList;

    /**
     * This method assigns a list with numMatches amount of ascii strings to this.asciiList
     *
     * @param numMatches the number of possible card matches
     */
    AsciiSymbol(int numMatches) {
        this.asciiList = this.generateSymbol(numMatches);
    }

    /**
     * Generates the symbols for the cards
     *
     * @param numMatches the number of possible card matches
     * @return an ArrayList with string objects
     */
    @Override
    ArrayList<String> generateSymbol(int numMatches) {
        //getting 16 cool ascii art
        String[] asciiArray = {"C|_|", "{-_-}", "^O^", "O='`o", ".-=-.", "`o._.o'", "(ㆆ _ ㆆ)",
                "•͡˘㇁•͡˘", "ʕっ•ᴥ•ʔっ", "( 0 _ 0 )", "(-_-)", "( •͡˘ _•͡˘)ノð", "( ͡° ᴥ ͡°)",
                "ヽ( •_)ᕗ", "(⌐■_■)", "(︶︹︶)", "69=69)"};
        List<String> asciiShuffledList = Arrays.asList(asciiArray);
        ArrayList<String> asciiList = new ArrayList<>();
        for (int i = 0; i < numMatches; i++) {
            asciiList.add(asciiShuffledList.get(i));
            asciiList.add(asciiShuffledList.get(i));
        }
        this.shuffleArray(asciiList);
        return asciiList;
    }

    /**
     * Symbol getter
     *
     * @return an ArrayList with the ascii symbols
     */
    @Override
    public ArrayList<String> getSymbols() {
        return this.asciiList;
    }
}
