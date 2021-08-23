package com.mollabs.gatecrasher.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/*
* MainActivity runs the game
*/
public class MainActivity extends Activity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "onCreate()");
        super.onCreate(savedInstanceState);

        // Set content view to game, so the objects in the game class can be rendered to the screen
        game = new Game(this);
        setContentView(game);
    }

    @Override
    protected void onStart() {
        Log.d("MainActivity.java", "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity.java", "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity.java", "onPause()");
        game.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity.java", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity.java", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Log.d("MainActivity.java", "onBackPressed()");

        // comment out the super so the back button is disabled in the game activity
        super.onBackPressed();
    }
}