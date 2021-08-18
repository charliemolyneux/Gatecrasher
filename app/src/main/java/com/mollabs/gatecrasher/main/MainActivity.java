package com.mollabs.gatecrasher.main;

import android.app.Activity;
import android.os.Bundle;
/*
* MainActivity runs the game
*/
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Game(this));



    }
}