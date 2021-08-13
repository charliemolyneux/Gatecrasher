package com.mollabs.gatecrasher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import io.github.controlwear.virtual.joystick.android.JoystickView;


public class MainActivity extends AppCompatActivity {

    JoystickView joystick;
    TextView joystickText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joystick = findViewById(R.id.joystickView);
        joystickText = findViewById(R.id.joystickText);

        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                joystickText.setText("Angle: " + angle + "°" + "\n"
                        + "Strength: " + strength + "%");

                /* TODO :   Create player 1.
                            Translate and rotate player1.    */





                /* TODO :   check if player1 goes through a gate.
                            if true, destroy enemies within range.
                            check if player1 is hit by enemy (Endgame).    */
            }
        });


    }
}