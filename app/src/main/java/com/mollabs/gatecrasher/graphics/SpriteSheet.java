package com.mollabs.gatecrasher.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.mollabs.gatecrasher.R;

import java.util.Random;

public class SpriteSheet {
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Sprite getPlayerSprite() {
        return new Sprite(this, new Rect(0, 0, 128, 128));
    }

    public Sprite getEnemySprite() {
        Random rand = new Random();
        int randomNum = rand.nextInt(4) + 1;

        if (randomNum == 1) {
            return new Sprite(this, new Rect(0, 128, 128, 256));
        } else if (randomNum == 2) {
            return new Sprite(this, new Rect(128, 128, 256, 256));
        } else if (randomNum == 3) {
            return new Sprite(this, new Rect(256, 128, 384, 256));
        } else {
            return new Sprite(this, new Rect(384, 128, 512, 256));
        }
    }


    public Bitmap getBitmap() {
        return bitmap;
    }
}
