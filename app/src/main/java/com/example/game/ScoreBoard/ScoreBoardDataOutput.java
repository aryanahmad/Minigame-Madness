package com.example.game.ScoreBoard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ScoreBoardDataOutput {

    SQLiteDatabase gameDB;

    String table;

    String difficulty;
    int level;
    Context context;

    ArrayList<String> namesArray;
    ArrayList<Integer> scoresArray;

    public ArrayList<String> getNamesArray() {
        return namesArray;
    }

    public ArrayList<Integer> getScoresArray() {
        return scoresArray;
    }

    public void setScoresArray(ArrayList<Integer> scoresArray) {
        this.scoresArray = scoresArray;
    }

    ScoreBoardDataOutput (Context context, String difficulty, int level){
        this.context = context;
        this.difficulty = difficulty;
        this.level = level;
        this.table = difficulty + Integer.toString(level);
        this.gameDB = context.openOrCreateDatabase("gameDB", MODE_PRIVATE, null);

        this.namesArray = new ArrayList<String>();
        this.scoresArray = new ArrayList<Integer>();
    }


    public void rePopulateArrays (String difficulty, int level){

        this.difficulty = difficulty;
        this.level = level;
        this.table = this.difficulty + Integer.toString(this.level);

        namesArray.clear();
        scoresArray.clear();

        Cursor c;
        if (level == 3){
            c = this.gameDB.rawQuery("SELECT * FROM '"+this.table+"' ORDER BY score DESC", null);
        }else{
            c = this.gameDB.rawQuery("SELECT * FROM '"+this.table+"' ORDER BY score ASC", null);
        }

        boolean hasData = c.moveToFirst(); // c.moveToFirst() return true if there are items in database

        while (hasData){
            namesArray.add(c.getString(c.getColumnIndex("name")));
            scoresArray.add(c.getInt(c.getColumnIndex("score")));
            hasData = c.moveToNext();
        }
    }
}
