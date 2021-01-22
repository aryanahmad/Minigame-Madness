package com.example.game.FlipCardGame.Cards;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

import com.example.game.FlipCardGame.FlipCardModels.FlipCardGameModel;

/**
 * The FlipCards class, holds information about the cards and
 *
 * @author Gerald, Harbaksh
 */
public class FlipCards {
    private Button btnInstance;
    private String symbol;
    private boolean flipped;
    private Drawable fullColor;
    private boolean enabled;
    private FlipCardGameModel manager;
    public static boolean disableCards = false;

    /**
     * @param packageContext Context
     * @param cardBackColor  int
     * @param symbol         String
     * @param row            TableRow
     * @param btnHeight      int
     * @param btnWidth       int
     * @param manager        FlipCardGameModel
     */
    FlipCards(
            Context packageContext,
            int cardBackColor,
            String symbol,
            TableRow row,
            int btnHeight,
            int btnWidth,
            FlipCardGameModel manager) {
        this.build(packageContext, cardBackColor, symbol, row, btnHeight, btnWidth, manager);
    }

    /**
     * @param packageContext Context
     * @param cardBackColor  int
     * @param symbol         String
     * @param row            TableRow
     * @param btnHeight      int
     * @param btnWidth       int
     * @param manager        FlipCardGameModel
     */
    private void build(
            Context packageContext,
            int cardBackColor,
            String symbol,
            TableRow row,
            int btnHeight,
            int btnWidth,
            FlipCardGameModel manager) {
        this.flipped = false;
        btnInstance = new Button(packageContext);
        this.symbol = symbol;
        row.addView(btnInstance);
        this.btnInstance.setOnClickListener(handleOnClick(this.btnInstance));
        this.btnInstance.setWidth(btnWidth);
        this.btnInstance.setHeight(btnHeight);
        this.fullColor = this.initBoarderColor(this.btnInstance, cardBackColor, btnWidth);
        this.enabled = true;
        this.manager = manager;
    }

    /**
     * setting the color with the boarder of the card
     *
     * @param btnInstance   Button
     * @param cardBackColor int
     * @param btnWidth      int
     * @return a Drawable object with the given specifications
     */
    private Drawable initBoarderColor(Button btnInstance, int cardBackColor, int btnWidth) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(btnWidth, Color.BLACK);
        drawable.setColor(cardBackColor);
        btnInstance.setBackground(drawable);
        return drawable;
    }

    /**
     * @return True if flipped, else False
     */
    public boolean isFlipped() {
        return this.flipped;
    }

    /**
     * flip the card
     */
    public void flipCard() {
        if (enabled) {
            if (!flipped) {
                this.btnInstance.setText(symbol);
                btnInstance.setBackgroundColor(Color.WHITE);
                flipped = !flipped;
            } else {
                this.turnCardToBack();
                flipped = !flipped;
            }
        }
    }

    /**
     * @return the symbol on the card
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * disables the card and prevents it from being clicked again
     */
    public void lockCard() {
        this.btnInstance.setEnabled(false);
        this.enabled = false;
        this.flipped = false;
    }

    /**
     * resetting the state of the card
     */
    public void resetState() {
        this.enabled = true;
        this.flipped = false;
        this.turnCardToBack();
    }

    /**
     * setting the manager
     *
     * @param manager FlipCardGameModel
     */
    public void setManager(FlipCardGameModel manager) {
        this.manager = manager;
    }

    /**
     * disabling the button
     */
    public void disableBtnCall() {
        this.btnInstance.setEnabled(true);
        this.btnInstance.setOnClickListener(null);
    }

    /**
     * turns the card to its back
     */
    private void turnCardToBack() {
        this.btnInstance.setText("");
        btnInstance.setBackground(fullColor);
    }

    /**
     * updating the manager
     */
    public void callManagerUpdate() {
        manager.update(this);
    }

    /**
     * when the card is clicked, it flips then call the update on the
     * observer(FlipCardMainGameModel)
     *
     * @param button Button
     * @return a new OnClickListener object
     */
    private View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                if (!disableCards) {
                    callManagerUpdate();
                }
            }
        };
    }
}
