package com.example.game.FunctionsForAllLevels;

import android.content.Context;

import com.example.game.ScoreBoard.ScoreBoardDataInput;
import com.example.game.User.CurrUser;

/**
 * ResultFacade, groups together functions from CurrUser and ScoreBoardDataInput
 * that would be called in the same sequence in all 3 game's result screen
 * @author Henry
 */
public class ResultFacade {

    private CurrUser user;
    private int currLevel;
    private ScoreBoardDataInput scoreBoardDataInput;
    private Context context;


    public ResultFacade(Context context){
        this.context = context;
        this.user = new CurrUser(context);

        this.scoreBoardDataInput = new ScoreBoardDataInput(context, this.user.getCurrLevel(), this.user.getDifficultySelected());
        this.currLevel = this.user.getCurrLevel();
    }

    public CurrUser getUser() {
        return user;
    }

    /**
     * Saves the achieved score to corresponding difficulty / level field in SQLite database
     * and initiates popup asking to save the score to the public ScoreBoard
     *
     * @param score score achieved by user in any of our 3 games on any difficulty
     */
    public void dataSave (int score){
        if (this.currLevel == 1){
            user.setL1RecentScore(score);
            user.updateL1BestScore();
        }else if(this.currLevel == 2){
            user.setL2RecentScore(score);
            user.updateL2BestScore();
        }else{
            user.setL3RecentScore(score);
            user.updateL3BestScore();
        }
        
        // begins a popup asking to save to score board
        this.scoreBoardDataInput.requestSavePermission(score);

        // updates currLevel back to 0
        this.user.setCurrLevel(0);
    }

}
