package com.example.game.LoginScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Responsible for sign up / login mechanisms in MainActivity
 *
 * @author Henry
 */
public class LoginManager{

    SharedPreferences sharedPreferences;
    SQLiteDatabase gameDB;
    Context context;

    LoginManager(Context context, SQLiteDatabase gameDB, SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
        this.gameDB = gameDB;
        this.context = context;
    }

    /**
     * Saves to shared preference that a user is logged in
     *
     * @param username username of the successfully logged in user
     */
    public void notifyLoginSuccess(String username){
        sharedPreferences.edit().putString("loggedInUsername", username).apply();
    }

    /**
     * Checks if there exists a user with the given username and password in SQLite database
     * If one exists, save username to currently logged in user in shared preferences
     * else display a toast message indicating possible input errors
     *
     * @param username username input in MainActivity's username EditText view
     * @param password password input in MainActivity's password EditText view
     * @return true if there exists a user with given username/password, false otherwise
     */
    public boolean attemptLogin (String username, String password){


        Cursor c = gameDB.rawQuery("SELECT * FROM users WHERE username = '" + username + "'", null);
        int passwordIndex;

        if (c.moveToFirst()) {
            passwordIndex = c.getColumnIndex("password");
            if (password.equals(c.getString(passwordIndex))) {
                //Found existing existing user with username / password matching inputs
                c.close();
                notifyLoginSuccess(username);
                return true;
            } else {
                Toast.makeText(this.context, "Incorrect Password", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this.context, "Username does not exist", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * Checks if there already exists a user in the database with the given username input
     * if one doesn't exist, sign up user (store username / password) in SQLite database
     *
     * @param username username input in MainActivity's username EditText view
     * @param password password input in MainActivity's password EditText view
     * @return true if there are no users in the database with the input username, false otherwise
     */
    public boolean attemptSignUp(String username, String password){
        Cursor c = gameDB.rawQuery("SELECT * FROM users WHERE username = '" + username + "'", null);

        if (!c.moveToFirst()) {  // c.moveToFirst() return true if there is data, false if no data
            // Sign up the user if the username typed in is not in database
            gameDB.execSQL("INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')");
            // Unspecified column values by default become Int = 0, VARCHAR = null
            c.close();
            notifyLoginSuccess(username);
            return true;
        }
        return false;
    }
}
