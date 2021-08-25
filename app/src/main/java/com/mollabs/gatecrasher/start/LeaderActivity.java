package com.mollabs.gatecrasher.start;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mollabs.gatecrasher.R;

/*
* LeaderActivity displays the leaderboards to the user
* including world and friend leaderboards
* */
public class LeaderActivity extends AppCompatActivity {
    SharedPreferences highScorePreferences;
    SharedPreferences dateScorePreferences;
    TextView highScore1Text, highScore2Text, highScore3Text, highScore4Text, highScore5Text;
    TextView dateScore1Text, dateScore2Text, dateScore3Text, dateScore4Text, dateScore5Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);

        getSupportActionBar().setTitle("High Scores");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTextView();

        highScorePreferences = getApplicationContext().getSharedPreferences("HighScoreDB", MODE_PRIVATE);
        dateScorePreferences = getApplicationContext().getSharedPreferences("DateScoreDB", MODE_PRIVATE);

        if (highScorePreferences != null) {
            int highScore1 = highScorePreferences.getInt("HighScore1", 0);
            int highScore2 = highScorePreferences.getInt("HighScore2", 0);
            int highScore3 = highScorePreferences.getInt("HighScore3", 0);
            int highScore4 = highScorePreferences.getInt("HighScore4", 0);
            int highScore5 = highScorePreferences.getInt("HighScore5", 0);

            highScore1Text.setText("" + highScore1);
            highScore2Text.setText("" + highScore2);
            highScore3Text.setText("" + highScore3);
            highScore4Text.setText("" + highScore4);
            highScore5Text.setText("" + highScore5);
        }

        if (dateScorePreferences != null) {
            String dateScore1 = dateScorePreferences.getString("DateScore1", "");
            String dateScore2 = dateScorePreferences.getString("DateScore2", "");
            String dateScore3 = dateScorePreferences.getString("DateScore3", "");
            String dateScore4 = dateScorePreferences.getString("DateScore4", "");
            String dateScore5 = dateScorePreferences.getString("DateScore5", "");

            dateScore1Text.setText("" + dateScore1);
            dateScore2Text.setText("" + dateScore2);
            dateScore3Text.setText("" + dateScore3);
            dateScore4Text.setText("" + dateScore4);
            dateScore5Text.setText("" + dateScore5);

        }
    }

    private void setTextView() {
        highScore1Text = findViewById(R.id.highScore1);
        highScore2Text = findViewById(R.id.highScore2);
        highScore3Text = findViewById(R.id.highScore3);
        highScore4Text = findViewById(R.id.highScore4);
        highScore5Text = findViewById(R.id.highScore5);
        dateScore1Text = findViewById(R.id.dateNum1);
        dateScore2Text = findViewById(R.id.dateNum2);
        dateScore3Text = findViewById(R.id.dateNum3);
        dateScore4Text = findViewById(R.id.dateNum4);
        dateScore5Text = findViewById(R.id.dateNum5);
    }
}
