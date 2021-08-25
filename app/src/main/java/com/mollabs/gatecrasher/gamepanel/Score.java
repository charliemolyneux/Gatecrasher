package com.mollabs.gatecrasher.gamepanel;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.preference.PreferenceManager;

import androidx.core.content.ContextCompat;

import com.mollabs.gatecrasher.R;
import com.mollabs.gatecrasher.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.mollabs.gatecrasher.main.MainActivity.dateScorePreferences;
import static com.mollabs.gatecrasher.main.MainActivity.highScorePreferences;
import static com.mollabs.gatecrasher.main.MainActivity.sharedPreferencesEditor;

public class Score {
    Context context;

    public Score(Context context) {
        this.context = context;
    }

    public void draw(Canvas canvas, int score) {
        String scoreText = "Score: ";
        float x = 100;
        float y = 140;
        float textSize = 70;


        Paint scorePaint = new Paint();
        int color = ContextCompat.getColor(context, R.color.score);
        scorePaint.setColor(color);
        scorePaint.setTextSize(textSize);
        scorePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        canvas.drawText(scoreText + score , x, y, scorePaint);
    }

    public static void setHighScore(String highScoreKey, int score) {
        sharedPreferencesEditor = MainActivity.highScorePreferences.edit();
        sharedPreferencesEditor.putInt(highScoreKey, score);
        sharedPreferencesEditor.apply();
    }

    public static int getHighScore(int highScoreNum) {
        if (highScorePreferences != null) {
            switch (highScoreNum) {
                case 1:
                    return highScorePreferences.getInt("HighScore1", 0);
                case 2:
                    return highScorePreferences.getInt("HighScore2", 0);
                case 3:
                    return highScorePreferences.getInt("HighScore3", 0);
                case 4:
                    return highScorePreferences.getInt("HighScore4", 0);
                case 5:
                    return highScorePreferences.getInt("HighScore5", 0);
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    public static void setScores(int scoreCurrent) {
        // ===================================================================================
        //  Check top 5 high scores and set current score as new high score if it is bigger, set all previous high scores down 1 rank
        //  And set the current date of the new high score, move dates to be in line with its respective score
        // ===================================================================================
        if (scoreCurrent > getHighScore(5) && scoreCurrent < getHighScore(4)) {
            setHighScore("HighScore5", scoreCurrent);
            setScoreDate("DateScore5", setDate());

        } else if (scoreCurrent > getHighScore(4) && scoreCurrent < getHighScore(3)) {
            setHighScore("HighScore5", getHighScore(4));
            setScoreDate("DateScore5", getScoreDate(4));
            setHighScore("HighScore4", scoreCurrent);
            setScoreDate("DateScore4", setDate());

        } else if (scoreCurrent > getHighScore(3) && scoreCurrent < getHighScore(2)) {
            setHighScore("HighScore5", getHighScore(4));
            setScoreDate("DateScore5", getScoreDate(4));
            setHighScore("HighScore4", getHighScore(3));
            setScoreDate("DateScore4", getScoreDate(3));
            setHighScore("HighScore3", scoreCurrent);
            setScoreDate("DateScore3", setDate());

        } else if (scoreCurrent > getHighScore(2) && scoreCurrent < getHighScore(1)){
            setHighScore("HighScore5", getHighScore(4));
            setScoreDate("DateScore5", getScoreDate(4));
            setHighScore("HighScore4", getHighScore(3));
            setScoreDate("DateScore4", getScoreDate(3));
            setHighScore("HighScore3", getHighScore(2));
            setScoreDate("DateScore3", getScoreDate(2));
            setHighScore("HighScore2", scoreCurrent);
            setScoreDate("DateScore2", setDate());

        } else if (scoreCurrent > getHighScore(1)) {
            setHighScore("HighScore5", getHighScore(4));
            setScoreDate("DateScore5", getScoreDate(4));
            setHighScore("HighScore4", getHighScore(3));
            setScoreDate("DateScore4", getScoreDate(3));
            setHighScore("HighScore3", getHighScore(2));
            setScoreDate("DateScore3", getScoreDate(2));
            setHighScore("HighScore2", getHighScore(1));
            setScoreDate("DateScore2", getScoreDate(1));
            setHighScore("HighScore1", scoreCurrent);
            setScoreDate("DateScore1", setDate());

        }
    }

    public static void setScoreDate(String dateScoreKey, String date) {
        sharedPreferencesEditor = MainActivity.dateScorePreferences.edit();
        sharedPreferencesEditor.putString(dateScoreKey, date);
        sharedPreferencesEditor.apply();
    }

    public static String getScoreDate(int dateScoreNum) {
        if (dateScorePreferences != null) {
            switch (dateScoreNum) {
                case 1:
                    return dateScorePreferences.getString("DateScore1", "");
                case 2:
                    return dateScorePreferences.getString("DateScore2", "");
                case 3:
                    return dateScorePreferences.getString("DateScore3", "");
                case 4:
                    return dateScorePreferences.getString("DateScore4", "");
                case 5:
                    return dateScorePreferences.getString("DateScore5", "");
                default:
                    return "null";
            }
        } else {
            return "null";
        }
    }

    public static String setDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return df.format(c.getTime());
    }

}
