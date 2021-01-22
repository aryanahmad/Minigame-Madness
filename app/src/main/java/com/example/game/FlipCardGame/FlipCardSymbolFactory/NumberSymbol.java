package com.example.game.FlipCardGame.FlipCardSymbolFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * The NumberSymbol class is a subclass of the FlipCardSymbol class and deals with the creation
 * of the number symbols that might appear on the cards
 *
 * @author Gerald, Harbaksh
 */
class NumberSymbol extends FlipCardSymbol {
    private ArrayList<String> numberList;

    /**
     * This method assigns a list with numMatches amount of number strings to this.numberList
     *
     * @param numMatches the number of possible card matches
     */
    NumberSymbol(int numMatches) {
        this.numberList = generateSymbol(numMatches);
    }

    /**
     * Generates the symbols for the cards
     *
     * @param numMatches the number of possible card matches
     * @return an ArrayList with string objects
     */
    @Override
    ArrayList<String> generateSymbol(int numMatches) {
        Random rand = new Random();
        ArrayList<String> listOfNumbers = new ArrayList<>();
        for (int i = 0; i < numMatches; i++) {
            int randomInt = rand.nextInt(numMatches);
            String randomIntStr = Integer.toString(randomInt);
            while (listOfNumbers.contains(randomIntStr)) {
                randomInt = rand.nextInt(numMatches);
                randomIntStr = Integer.toString(randomInt);
            }
            listOfNumbers.add(randomIntStr);
            listOfNumbers.add(randomIntStr);
        }
        this.shuffleArray(listOfNumbers);
        return listOfNumbers;
    }

    /**
     * Symbol getter
     *
     * @return an ArrayList with the ascii symbols
     */
    @Override
    public ArrayList<String> getSymbols() {
        return this.numberList;
    }
}
