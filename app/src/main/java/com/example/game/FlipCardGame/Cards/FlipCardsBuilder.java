package com.example.game.FlipCardGame.Cards;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.game.FlipCardGame.FlipCardModels.FlipCardGameModel;

import java.util.ArrayList;

/**
 * The FlipCardsBuilder class, builds the Flip Cards with the given specifications
 *
 * @author Gerald, Harbaksh
 */
public class FlipCardsBuilder {
    private Context currContext;
    private TableLayout stk;
    private ArrayList<String> symbolList;
    private FlipCardGameModel desiredManager;
    private int cardBackColor;

    /**
     * @param symbolList     ArrayList<String>
     * @param packageContext Context
     * @param stk            TableLayout
     * @param desiredManager FlipCardGameModel
     * @param cardBackColor  int
     */
    public FlipCardsBuilder(ArrayList<String> symbolList, Context packageContext, TableLayout stk, FlipCardGameModel desiredManager, int cardBackColor) {
        this.currContext = packageContext;
        this.stk = stk;
        this.desiredManager = desiredManager;
        this.cardBackColor = cardBackColor;
        this.symbolList = symbolList;
    }

    /**
     * generates a new table row in our table layout
     *
     * @return a TableRow obj
     */
    private TableRow generateNewRow() {
        TableRow tbRow = new TableRow(this.currContext);
        tbRow.setGravity(Gravity.CENTER_HORIZONTAL);
        tbRow.setLayoutParams(
                new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        return tbRow;
    }

    /**
     * Creating the FlipCards
     *
     * @return an ArrayList of FlipCard objects
     */
    public ArrayList<FlipCards> createCards() {
        ArrayList<FlipCards> allCardCreation = new ArrayList<>();
        this.stk.setGravity(Gravity.CENTER_VERTICAL);
        int i = 0;
        TableRow tbrow = this.generateNewRow();
        for (String s : symbolList) {
            if (i == 4) {
                this.stk.addView(tbrow);
                tbrow = this.generateNewRow();
                i = 0;
            }
            FlipCards f1 =
                    new FlipCards(this.currContext, this.cardBackColor, s, tbrow, 3, 3, this.desiredManager);
            allCardCreation.add(f1);
            i++;
        }
        stk.addView(tbrow);
        return allCardCreation;
    }
}
