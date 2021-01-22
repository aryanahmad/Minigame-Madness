package com.example.game.ScoreBoard;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import static android.content.Context.MODE_PRIVATE;


/**
 * ScoreBoardDataInput is responsible for saving data generated from game results
 * into SQLite database to be displayed on ScoreBoard
 * @author Henry
 */

public class ScoreBoardDataInput {
    private Context context;
    private SQLiteDatabase gameDB;
    private String saveRequestMessage;

    private int level;
    private String difficulty;
    private int score;

    private String tableDB;

    public ScoreBoardDataInput(Context context, int level, String difficulty){
        this.context = context;
        this.saveRequestMessage = "Save to ScoreBoard?";

        this.level = level;
        this.difficulty = difficulty;

        this.gameDB = context.openOrCreateDatabase("gameDB", MODE_PRIVATE, null);
        this.tableDB = difficulty + Integer.toString(this.level);
    }

    public void requestSavePermission (final int score){
        // Initiates popup to requests permission to
        // add user's score to the scoreboard with the user's name of choice

        final EditText userInput = new EditText(context);
        userInput.setHint("Enter Name");
        final String tableName = this.tableDB;
        // can't access this.tableDB inside the AlertDialog object
        // Since this refers to the AlertDialog object

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(userInput)
                .setTitle(this.saveRequestMessage)
                .setPositiveButton(android.R.string.ok, null) // Override on click later
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(userInput.getText().toString().equals("")){
                            Toast.makeText(context, "Please enter a Name", Toast.LENGTH_SHORT).show();
                        }else{
                            gameDB.execSQL("INSERT INTO '"+ tableName +"' (name, score) " +
                                    "VALUES ('" + userInput.getText().toString() + "', '" + score + "')");
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        dialog.show();

    }

}
