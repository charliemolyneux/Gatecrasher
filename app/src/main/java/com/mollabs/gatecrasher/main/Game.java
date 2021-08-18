package com.mollabs.gatecrasher.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.mollabs.gatecrasher.R;
import com.mollabs.gatecrasher.object.Circle;
import com.mollabs.gatecrasher.object.Enemy;
import com.mollabs.gatecrasher.object.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
* Game manages all objects in the game and is responsible for updating all states
* and render all objects to the screen
* */
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    //private final Enemy enemy;
    private GameLoop gameLoop;
    private Joystick joystick;
    private List<Enemy> enemyList = new ArrayList<Enemy>();

    public Game(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        // Initialize GameLoop
        gameLoop = new GameLoop(this, surfaceHolder);
        // Initialize joystick
        joystick = new Joystick(1800, 800, 150, 50);
        // Initialize game objects
        player = new Player(getContext(), joystick, 500, 500, 30);
        //enemy = new Enemy(getContext(), player, 500, 500, 30);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle touch event actions
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    joystick.setIsPressed(true);
                }
                //player.setPosition((double) event.getX(), (double) event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.getIsPressed()) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                //player.setPosition((double) event.getX(), (double) event.getY());
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return true;
        }


        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        drawUPS(canvas);
        drawFPS(canvas);

        joystick.draw(canvas);
        player.draw(canvas);
        for (Enemy enemy : enemyList) {
            enemy.draw(canvas);
        }
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.colorData);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.colorData);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update() {
        // Update game state

        joystick.update();
        player.update();

        // Spawn enemy if it is time for new enemy to spawn
        if (Enemy.readyToSpawn()) {
            enemyList.add(new Enemy(getContext(), player));
        }
        // Update state of each enemy
        for (Enemy enemy : enemyList) {
            enemy.update();
        }
        // Iterate through enemy list and check for collision with player
        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()) {
            if (Circle.isColliding(iteratorEnemy.next(), player)) {
                // Remove enemy if it has collided with player
                iteratorEnemy.remove();
            }
        }

    }



}
