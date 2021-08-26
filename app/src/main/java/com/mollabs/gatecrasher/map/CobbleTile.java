package com.mollabs.gatecrasher.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.mollabs.gatecrasher.graphics.Sprite;
import com.mollabs.gatecrasher.graphics.SpriteSheet;

public class CobbleTile extends Tile {
    private final Sprite sprite;

    public CobbleTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getCobbleSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
