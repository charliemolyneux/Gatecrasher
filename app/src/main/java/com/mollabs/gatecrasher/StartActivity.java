package com.mollabs.gatecrasher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StartActivity extends AppCompatActivity {

    private Button startButton;
    private Button exitButton;
    private FloatingActionButton leaderBoardButton;
    private FloatingActionButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startButton = findViewById(R.id.startButton);
        exitButton = findViewById(R.id.exitButton);
        leaderBoardButton = findViewById(R.id.leaderboardsButton);
        settingsButton = findViewById(R.id.settingsButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start Main activity on click
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close app on click
                finish();
                System.exit(0);
            }
        });

        leaderBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open leaderboards on click
                startActivity(new Intent(StartActivity.this, LeaderActivity.class));
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open settings on click
                startActivity(new Intent(StartActivity.this, SettingsActivity.class));
            }
        });

    }
}
