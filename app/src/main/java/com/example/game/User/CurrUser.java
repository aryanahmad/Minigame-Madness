package com.example.game.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;

import static android.content.Context.MODE_PRIVATE;

/**
 * CurrUser class is a object that holds all data from SQLite database corresponding to
 * the currently logged in user, other classes access the users data / update new data through this class
 * @author Henry
 */

public class CurrUser {

    private MediaPlayer currMedia;
    private Context context;

    private SharedPreferences sharedPreferences;
    private SQLiteDatabase gameDB;
    private Cursor c;

    private String username;

    private int colorSelected, musicSelected;
    private String difficultySelected;

    private int currLevel;

    // Level 1 (Button Click)
    private int l1EasyBestScore, l1HardBestScore, l1RecentScore;

    // Level 2 (Math Game)
    private int l2EasyBestScore, l2HardBestScore, l2RecentScore;

    // Level 3 (Flip Card)
    private int l3EasyBestScore, l3HardBestScore, l3RecentScore;

    public CurrUser(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        username = sharedPreferences.getString("loggedInUsername", "NA");
        gameDB = context.openOrCreateDatabase("gameDB", MODE_PRIVATE, null);
        c = gameDB.rawQuery("SELECT * FROM users WHERE username='" + username + "'", null);
        c.moveToFirst();

        // Fill customizations
        colorSelected = c.getInt(c.getColumnIndex("colorSelected"));
        difficultySelected = c.getString(c.getColumnIndex("difficultySelected"));
        musicSelected = c.getInt(c.getColumnIndex("musicSelected"));

        // Fill level left off at
        currLevel = c.getInt(c.getColumnIndex("currLevel"));

        // Fill Level 1 Data
        l1EasyBestScore = c.getInt(c.getColumnIndex("l1EasyBestScore"));
        l1HardBestScore = c.getInt(c.getColumnIndex("l1HardBestScore"));
        l1RecentScore = c.getInt(c.getColumnIndex("l1RecentScore"));

        // Fill Level 2 Data
        l2EasyBestScore = c.getInt(c.getColumnIndex("l2EasyBestScore"));
        l2HardBestScore = c.getInt(c.getColumnIndex("l2HardBestScore"));
        l2RecentScore = c.getInt(c.getColumnIndex("l2RecentScore"));

        // Fill Level 3 Data
        l3EasyBestScore = c.getInt(c.getColumnIndex("l3EasyBestScore"));
        l3HardBestScore = c.getInt(c.getColumnIndex("l3HardBestScore"));
        l3RecentScore = c.getInt(c.getColumnIndex("l3RecentScore"));
    }

    /**
     * Stops any music that is currently playing, and starts the most recently chosen music
     * customization associated with the user
     */
    public void playMusic() {
        if (currMedia != null) {
            currMedia.stop();
        }
        if (this.getMusicSelected() != 0) {
            currMedia = MediaPlayer.create(this.context, this.getMusicSelected());
            currMedia.start();
        }
    }

    public void stopMusic() {
        if (currMedia != null) {
            currMedia.stop();
        }
    }

    /**
     * Updates user's best score for Button press Game to the corresponding difficulty
     */
    public void updateL1BestScore() {
        if (this.getDifficultySelected().equals("easy")) {
            if (this.getL1RecentScore() > this.getL1EasyBestScore()) {
                this.setL1EasyBestScore(this.getL1RecentScore());
            }
        } else {
            if (this.getL1RecentScore() > this.getL1HardBestScore()) {
                this.setL1HardBestScore(this.getL1RecentScore());
            }
        }
    }

    /**
     * Updates user's best score for Math Game to the corresponding difficulty
     */
    public void updateL2BestScore() {
        if (this.getDifficultySelected().equals("easy")) {
            if (this.getL2RecentScore() > this.getL2EasyBestScore()) {
                this.setL2EasyBestScore(this.getL2RecentScore());
            }
        } else {
            if (this.getL2RecentScore() > this.getL2HardBestScore()) {
                this.setL2HardBestScore(this.getL2RecentScore());
            }
        }
    }

    /**
     * Updates user's best score for Flip Card Game to the corresponding difficulty
     */
    public void updateL3BestScore() {
        if (this.getDifficultySelected().equals("easy")) {
            if (this.getL3RecentScore() < this.getL3EasyBestScore()) {
                this.setL1EasyBestScore(this.getL1RecentScore());
            }
        } else {
            if (this.getL3RecentScore() < this.getL3HardBestScore()) {
                this.setL3HardBestScore(this.getL3RecentScore());
            }
        }
    }


    public String getUsername() {
        return username;
    }

    public int getColorSelected() {
        return colorSelected;
    }

    /**
     * Updates the color selected field corresponding the user in SQLite Database
     *
     * @param colorSelected int value corresponding to the color selected by user in customization screen
     */
    public void setColorSelected(int colorSelected) {
        this.colorSelected = colorSelected;
        gameDB.execSQL(
                "UPDATE users SET colorSelected= '"
                        + colorSelected
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    public String getDifficultySelected() {
        return difficultySelected;
    }

    /**
     * Updates the difficulty field corresponding the user in SQLite Database
     *
     * @param difficultySelected String corresponding to the difficulty selected by user in customization screen
     */
    public void setDifficultySelected(String difficultySelected) {
        this.difficultySelected = difficultySelected;
        gameDB.execSQL(
                "UPDATE users SET difficultySelected= '"
                        + difficultySelected
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    private int getMusicSelected() {
        return musicSelected;
    }

    /**
     * Updates the music selected field corresponding the user in SQLite Database
     *
     * @param musicSelected int value corresponding to the music selected by user in customization screen
     */
    public void setMusicSelected(int musicSelected) {
        this.musicSelected = musicSelected;

        gameDB.execSQL(
                "UPDATE users SET musicSelected= '"
                        + musicSelected
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    public int getCurrLevel() {
        return currLevel;
    }

    /**
     * Updates the current level field corresponding the user in SQLite Database
     *
     * @param currLevel int value corresponding the level the user most recently left off at without completing
     */
    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
        gameDB.execSQL(
                "UPDATE users SET currLevel= '"
                        + currLevel
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    private int getL1EasyBestScore() {
        return l1EasyBestScore;
    }

    /**
     * Updates the user's best score in button press game on easy mode in SQLite Database
     *
     * @param l1EasyBestScore best score achieved by user in button press game, easy mode
     */
    private void setL1EasyBestScore(int l1EasyBestScore) {
        this.l1EasyBestScore = l1EasyBestScore;
        gameDB.execSQL(
                "UPDATE users SET l1EasyBestScore= '"
                        + l1EasyBestScore
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    private int getL1HardBestScore() {
        return l1HardBestScore;
    }

    /**
     * Updates the user's best score in button press game on hard mode in SQLite Database
     *
     * @param l1HardBestScore best score achieved by user in button press game, hard mode
     */
    private void setL1HardBestScore(int l1HardBestScore) {
        this.l1HardBestScore = l1HardBestScore;
        gameDB.execSQL(
                "UPDATE users SET l1HardBestScore= '"
                        + l1HardBestScore
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    int getL1RecentScore() {
        return l1RecentScore;
    }

    /**
     * Updates the user's most recent score in button press game to SQLite database
     *
     * @param l1RecentScore most recent score achieved by user in button press game (any difficulty)
     */
    public void setL1RecentScore(int l1RecentScore) {
        this.l1RecentScore = l1RecentScore;
        gameDB.execSQL(
                "UPDATE users SET l1RecentScore= '"
                        + l1RecentScore
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    private int getL2EasyBestScore() {
        return l2EasyBestScore;
    }

    /**
     * Updates best score by user on level 2 easy mode to database
     *
     * @param l2EasyBestScore best score achieved by user on level 2 easy mode
     */
    private void setL2EasyBestScore(int l2EasyBestScore) {
        this.l2EasyBestScore = l2EasyBestScore;
        gameDB.execSQL(
                "UPDATE users SET l2EasyBestScore= '"
                        + l2EasyBestScore
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    private int getL2HardBestScore() {
        return l2HardBestScore;
    }

    /**
     * Updates best score by user on level 2 hard mode to database
     *
     * @param l2HardBestScore best score achieved by user on level 2 hard mode
     */
    private void setL2HardBestScore(int l2HardBestScore) {
        this.l2HardBestScore = l2HardBestScore;
        gameDB.execSQL(
                "UPDATE users SET l2HardBestScore= '"
                        + l2HardBestScore
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    int getL2RecentScore() {
        return l2RecentScore;
    }

    /**
     * Updates user's most recent level 2 score to database
     *
     * @param l2RecentScore Most recent score on level 2 by achieved by user (any mode)
     */
    public void setL2RecentScore(int l2RecentScore) {
        this.l2RecentScore = l2RecentScore;
        gameDB.execSQL(
                "UPDATE users SET l2RecentScore= '"
                        + l2RecentScore
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    private int getL3EasyBestScore() {
        return l3EasyBestScore;
    }

    /**
     * Updates best score by user on level 3 easy mode to database
     *
     * @param l3EasyBestScore best score achieved by user on level 3 easy mode
     */
    public void setL3EasyBestScore(int l3EasyBestScore) {
        this.l3EasyBestScore = l3EasyBestScore;
        gameDB.execSQL(
                "UPDATE users SET l3EasyBestScore= '"
                        + l3EasyBestScore
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    private int getL3HardBestScore() {
        return l3HardBestScore;
    }

    /**
     * Updates best score by user on level 3 hard mode to database
     *
     * @param l3HardBestScore best score achieved by user on level 3 hard mode
     */
    private void setL3HardBestScore(int l3HardBestScore) {
        this.l3HardBestScore = l3HardBestScore;
        gameDB.execSQL(
                "UPDATE users SET l3HardBestScore= '"
                        + l3HardBestScore
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }

    int getL3RecentScore() {
        return l3RecentScore;
    }

    /**
     * Updates user's most recent level 3 score to database
     *
     * @param l3RecentScore Most recent score on level 3 by achieved by user (any mode)
     */
    public void setL3RecentScore(int l3RecentScore) {
        this.l3RecentScore = l3RecentScore;
        gameDB.execSQL(
                "UPDATE users SET l3RecentScore= '"
                        + l3RecentScore
                        + "' WHERE username = '"
                        + this.getUsername()
                        + "'");
    }
}
