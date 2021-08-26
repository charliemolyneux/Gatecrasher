package com.mollabs.gatecrasher.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.mollabs.gatecrasher.R;

import java.util.Random;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 128;
    private static final int SPRITE_HEIGHT_PIXELS = 128;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getPlayerSprite() {
        return getSpriteByIndex(0, 0);
    }

    public Sprite getEnemySprite() {
        Random rand = new Random();
        int randomNum = rand.nextInt(4) + 1;

        if (randomNum == 1) {
            return getSpriteByIndex(1, 0);
        } else if (randomNum == 2) {
            return getSpriteByIndex(1, 1);
        } else if (randomNum == 3) {
            return getSpriteByIndex(1, 2);
        } else {
            return getSpriteByIndex(1, 3);
        }
    }

    public Sprite getCobbleSprite() {
        return getSpriteByIndex(2, 0);
    }

    public Sprite getLavaSprite() {
        return getSpriteByIndex(2, 1);
    }

    public Sprite getGroundSprite() {
        return getSpriteByIndex(2, 2);
    }

    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                (idxCol + 1)*SPRITE_WIDTH_PIXELS,
                (idxRow + 1)*SPRITE_HEIGHT_PIXELS
        ));
    }

}
