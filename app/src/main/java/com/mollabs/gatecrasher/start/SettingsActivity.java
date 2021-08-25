package com.mollabs.gatecrasher.start;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mollabs.gatecrasher.R;

/*
* SettingsActivity displays settings the user can activate...
* */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
