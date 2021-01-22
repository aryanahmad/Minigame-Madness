package com.example.game.LoginScreen;

import android.database.sqlite.SQLiteDatabase;

/**
 * This class sets up the tables used in SQLite database for user and public ScoreBoard data
 * @author Henry
 */

public class GenerateDataBase {

    SQLiteDatabase gameDB;
    GenerateDataBase(SQLiteDatabase gameDB){
        this.gameDB = gameDB;
    }

    public void GenerateTables(){
        gameDB.execSQL("CREATE TABLE IF NOT EXISTS users (username VARCHAR, password VARCHAR, currLevel INT(2), " +
                "colorSelected INT(11), difficultySelected VARCHAR, musicSelected INT(11), " +
                "l1EasyBestScore INT(4), l1HardBestScore INT(4), l1RecentScore INT(4), " +
                "l2EasyBestScore INT(4), l2HardBestScore INT(4), l2RecentScore INT(4), " +
                "l3EasyBestScore INT(4), l3HardBestScore INT(4), l3RecentScore INT(4))");

        gameDB.execSQL("CREATE TABLE IF NOT EXISTS easy1 (name VARCHAR, score INT(4))");
        gameDB.execSQL("CREATE TABLE IF NOT EXISTS hard1 (name VARCHAR, score INT(4))");
        gameDB.execSQL("CREATE TABLE IF NOT EXISTS easy2 (name VARCHAR, score INT(4))");
        gameDB.execSQL("CREATE TABLE IF NOT EXISTS hard2 (name VARCHAR, score INT(4))");
        gameDB.execSQL("CREATE TABLE IF NOT EXISTS easy3 (name VARCHAR, score INT(4))");
        gameDB.execSQL("CREATE TABLE IF NOT EXISTS hard3 (name VARCHAR, score INT(4))");
    }
}
