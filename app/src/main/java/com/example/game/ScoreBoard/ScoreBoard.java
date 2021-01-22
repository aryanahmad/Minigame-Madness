package com.example.game.ScoreBoard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.UserHome.HomePage;

import java.util.ArrayList;

public class ScoreBoard extends AppCompatActivity {

    private ScoreBoardDataOutput scoreBoardDataOutput;
    private String difficulty;
    private int level;
    private TableLayout scoreBoard;
    private TextView gameNameText;

    private Button easyBtn;
    private Button hardBtn;

    private ArrayList<String> levelIndexNameValueArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        System.out.println("MARKKKKKKER");

        scoreBoard = findViewById(R.id.scoreBoard);
        gameNameText = findViewById(R.id.gameNameText);
        easyBtn = findViewById(R.id.easyBtn);
        hardBtn = findViewById(R.id.hardBtn);

        scoreBoardDataOutput = new ScoreBoardDataOutput(this, difficulty, level);

        difficulty = "easy";
        level = 1;

        levelIndexNameValueArray = new ArrayList<String>();
        levelIndexNameValueArray.add("ScoreBoard"); // Just to buffer index 0
        levelIndexNameValueArray.add("Button Press"); // index 1 (level 1)
        levelIndexNameValueArray.add("Math Game"); // index 2 (level 2)
        levelIndexNameValueArray.add("Flip Card"); //index 3 (level 3)

        updateScoreBoard(difficulty, level);
        updateGameNameText();
        updateDifficultyBtnColor();

    }

    public void updateScoreBoard(String difficulty, int level){
        scoreBoard.removeAllViews();

        scoreBoardDataOutput.rePopulateArrays(difficulty, level);

        for (int i=0; i < scoreBoardDataOutput.namesArray.size() ; i++){
            TableRow row = new TableRow(this);

            if (difficulty.equals("easy")){
                row.setBackgroundColor(Color.rgb(204, 255, 204));
            }else if (difficulty.equals("hard")){
                row.setBackgroundColor(Color.rgb(255, 77, 77));
            }

            TextView textName = new TextView(this);
            textName.setText(scoreBoardDataOutput.getNamesArray().get(i));
            textName.setWidth(400) ;
            textName.setTextSize(32);
            textName.setGravity(Gravity.CENTER);
            row.addView(textName);

            TextView textScore = new TextView(this);
            textScore.setText(Integer.toString(scoreBoardDataOutput.getScoresArray().get(i)));
            textScore.setTextSize(32);
            textScore.setWidth(425);
            textScore.setGravity(Gravity.CENTER);
            row.addView(textScore);

            scoreBoard.addView(row);

            // table width = 300dp
            // text1 width = 400
            // text2 width = 425
            // so 300dp = 825
        }
    }

    public void updateGameNameText(){
        gameNameText.setText(levelIndexNameValueArray.get(level));
    }

    public void gameLeftBtnFunc (View view){
        if (level == 1){
            level = 3;
        }else{
            level -= 1;
        }

        updateScoreBoard(difficulty, level);
        updateGameNameText();
    }

    public void gameRightBtnFunc (View view){
        if (level == 3){
            level = 1;
        }else{
            level += 1;
        }

        updateScoreBoard(difficulty, level);
        updateGameNameText();
    }

    public void easyBtnFunc (View view){
        difficulty = "easy";

        updateScoreBoard(difficulty, level);
        updateDifficultyBtnColor();
    }

    public void hardBtnFunc (View view){
        difficulty = "hard";
        updateScoreBoard(difficulty, level);
        updateDifficultyBtnColor();
    }

    public void updateDifficultyBtnColor(){
        if (difficulty == "easy"){
            easyBtn.setBackgroundColor(Color.parseColor("#64DD17"));
            hardBtn.setBackgroundColor(Color.parseColor("#D3D3D3"));
        }else{
            easyBtn.setBackgroundColor(Color.parseColor("#D3D3D3"));
            hardBtn.setBackgroundColor(Color.parseColor("#DD2C00"));
        }
    }

    @Override
    public void onBackPressed() {
        Intent start = new Intent(getApplicationContext(), HomePage.class);
        start.putExtra("androidBack", 1);
        startActivity(start);
        finish();
    }


}
