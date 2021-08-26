package com.mollabs.gatecrasher.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.mollabs.gatecrasher.graphics.SpriteSheet;

public abstract class Tile {
    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType {
        COBBLE_TILE,
        LAVA_TILE,
        GROUND_TILE
    }

    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch (TileType.values()[idxTileType]) {
            case COBBLE_TILE:
                return new CobbleTile(spriteSheet, mapLocationRect);
            case LAVA_TILE:
                return new LavaTile(spriteSheet, mapLocationRect);
            case GROUND_TILE:
                return new GroundTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }
    }

    public abstract void draw(Canvas canvas);


}
