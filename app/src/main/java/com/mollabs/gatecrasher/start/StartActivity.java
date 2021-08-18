package com.mollabs.gatecrasher.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mollabs.gatecrasher.main.MainActivity;
import com.mollabs.gatecrasher.R;

/**
 * StartActivity is the Entry Point of the Application
 * */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startButton = findViewById(R.id.startButton);
        Button exitButton = findViewById(R.id.exitButton);
        FloatingActionButton leaderBoardButton = findViewById(R.id.leaderboardsButton);
        FloatingActionButton settingsButton = findViewById(R.id.settingsButton);

        // Starts the game
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start Main activity on click
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        });

        // Exits the application
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close app on click
                finish();
                System.exit(0);
            }
        });

        // Displays leaderboard
        leaderBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open leaderboards on click
                startActivity(new Intent(StartActivity.this, LeaderActivity.class));
            }
        });

        // Displays settings
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open settings on click
                startActivity(new Intent(StartActivity.this, SettingsActivity.class));
            }
        });

    }
}
