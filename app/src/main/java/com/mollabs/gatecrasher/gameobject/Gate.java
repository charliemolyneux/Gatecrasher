package com.mollabs.gatecrasher.gameobject;

import android.content.Context;
import com.mollabs.gatecrasher.R;
import androidx.core.content.ContextCompat;

/*
* Gate is a game object which the player travels through to destroy enemies and earn score
* Gate is an extension of line, which is an extension of GameObject
* */
public class Gate extends Line {

    public Gate(Context context, double startX, double startY, double stopX, double stopY) {
        super(ContextCompat.getColor(context, R.color.colorGate), startX, startY, stopX, stopY);
    }

    @Override
    public void update() {

    }
}
