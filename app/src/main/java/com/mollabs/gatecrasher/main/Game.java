package com.mollabs.gatecrasher.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.mollabs.gatecrasher.gameobject.Circle;
import com.mollabs.gatecrasher.gameobject.Enemy;
import com.mollabs.gatecrasher.gameobject.Player;
import com.mollabs.gatecrasher.gameobject.Spell;
import com.mollabs.gatecrasher.gamepanel.GameOver;
import com.mollabs.gatecrasher.gamepanel.Joystick;
import com.mollabs.gatecrasher.gamepanel.Performance;
import com.mollabs.gatecrasher.gamepanel.Score;
import com.mollabs.gatecrasher.graphics.SpriteSheet;
import com.mollabs.gatecrasher.map.TileMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
* Game manages all objects in the game and is responsible for updating all states
* and render all objects to the screen
* */
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final TileMap tileMap;
    private GameLoop gameLoop;
    private Performance performance;
    private Joystick joystick;
    private final Player player;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellList = new ArrayList<Spell>();
    private GameOver gameOver;
    private Score score;
    private int joystickPointerId = 0;
    private int scoreCurrent = 0;
    private SpriteSheet spriteSheet;
    private GameDisplay gameDisplay;

    public Game(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        // Initialize GameLoop
        gameLoop = new GameLoop(this, surfaceHolder);

        // Initialize game panels
        performance = new Performance(context, gameLoop);
        gameOver = new GameOver(context);
        score = new Score(context);
        joystick = new Joystick(1850, 850, 150, 50);

        // Initialize game objects
        spriteSheet = new SpriteSheet(context);
        player = new Player(context, joystick, 500, 500, 30, spriteSheet.getPlayerSprite());

        // Initialize game display and center it around player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(player, displayMetrics.widthPixels, displayMetrics.heightPixels);

        // Initialize tile map
        tileMap = new TileMap(spriteSheet);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle touch event actions
        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joystick.getIsPressed()) {
                    // Joystick was pressed before this event -> cast spell
                    spellList.add(new Spell(getContext(), player));
                } else if (joystick.isPressed(event.getX(), event.getY())) {
                    // Joystick is pressed -> setIsPressed(true)
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else {
                    // Joystick was not pressed, and is not pressed in current event -> cast spell
                    spellList.add(new Spell(getContext(), player));
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                // Joystick was pressed previously and is now moved
                if (joystick.getIsPressed()) {
                    joystick.setActuator(event.getX(), event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    // Joystick was released
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("Game.java", "surfaceCreated()");

        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("Game.java", "surfaceChanged()");

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("Game.java", "surfaceDestroyed()");

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // Draw tilemap
        tileMap.draw(canvas, gameDisplay);

        // Draw game panels
        joystick.draw(canvas);
        performance.draw(canvas);
        score.draw(canvas, scoreCurrent);
        
        // Draw game objects
        player.draw(canvas, gameDisplay);
        for (Enemy enemy : enemyList) {
            enemy.draw(canvas, gameDisplay);
        }
        for (Spell spell : spellList) {
            spell.draw(canvas, gameDisplay);
        }

        // Draw game over
        if (player.getHealthPoints() <= 0) {
            gameOver.draw(canvas);
        }
    }

    public void update() {
        // Stop updating the game if the player dies
        if (player.getHealthPoints() <= 0) {
            // Set high score (if applicable)
            Score.setScores(scoreCurrent);
            return;
        }

        // Update game state
        joystick.update();
        player.update();

        // Spawn enemy if it is time for new enemy to spawn
        if (Enemy.readyToSpawn()) {
            Enemy enemy = new Enemy(getContext(), player, spriteSheet.getEnemySprite());
            while (Enemy.spawnTooClose(enemy, player)) {
                enemy = new Enemy(getContext(), player, spriteSheet.getEnemySprite());
            }
            enemyList.add(enemy);
        }

        // Update state of each enemy
        for (Enemy enemy : enemyList) {
            enemy.update();
        }

        // Update state of each spell
        for (Spell spell : spellList) {
            spell.update();
        }
        // Iterate through enemy list and check for collision with player or another enemy
        // Check collision with all spells
        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()) {
            Circle enemy = iteratorEnemy.next();
            // Check for player + enemy collision
            if (Circle.isColliding(enemy, player)) {
                // Remove enemy if it has collided with player
                iteratorEnemy.remove();
                // Decrease health by 1
                player.setHealthPoints(player.getHealthPoints() - 1);
                continue;
            }
            // Check for collision between spell and enemy
            Iterator<Spell> iteratorSpell = spellList.iterator();
            while (iteratorSpell.hasNext()) {
                Circle spell = iteratorSpell.next();
                // Remove spell if it collides with enemy
                if (Circle.isColliding(spell, enemy)) {
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    scoreCurrent++;
                    break;
                }
            }
            // Check for enemy + enemy collision
            for (Circle currentEnemy : enemyList) {
                if (currentEnemy != enemy) {
                    if (Circle.isColliding(currentEnemy, enemy)) {
                        // Currently destroys one of the touching enemies,
                        // This should change to a small repel from each other (equal to their radii?)
                        Enemy.collisionDetection(currentEnemy, enemy);
                        break;
                    }
                }
            }
        }
        // Update game display at the end of the update method
        gameDisplay.update();
    }

    public void pause() {
        gameLoop.stopLoop();
    }

}
