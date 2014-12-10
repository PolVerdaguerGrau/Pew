package com.pol.pew;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by pol on 26/11/14.
 */
public class GameController {

    private int level;

    Paint stdPaint;

    Paint textPaint;


    private Resources resources;
    private GameSupport gameSupport;

    AsteroidController asteroids;
    Mussol mussol;

    private int screenWidth;
    private int screenHeight;

    private GameStaticss gameStatics;

    Bitmap background;
    Bitmap bitmapLives;

    private int draws;

    public GameController() {}

    public void Init(Context context){
        Global glob = Global.getInstance();
        this.level = glob.getLevel();
        this.resources = context.getResources();

        gameStatics = new GameStaticss(level,  this);

        asteroids = new AsteroidController(resources,this, level, screenWidth, screenHeight);
        mussol = new Mussol(resources, this);

        stdPaint = new Paint();
        stdPaint.setColor(Color.BLACK);
        stdPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(Color.LTGRAY);
        textPaint.setTextSize(20);

        try {
            background = BitmapFactory.decodeResource(resources, R.drawable.background);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bitmapLives = glob.getLivesBitmap();
        if(bitmapLives == null) {
            bitmapLives = BitmapFactory.decodeResource(resources, R.drawable.littlelive);
            bitmapLives = Bitmap.createScaledBitmap(bitmapLives, 20, 20, true);
            glob.setLivesBitmap(bitmapLives);
        }

        setSurfaceDimensions(240, 160);

        draws = 0;

    }

    public void mussolCollides() {
        gameStatics.decreaseLives();
    }

    public void asteroidBreaks() {
        gameStatics.decreaseAsteroids();
    }
    public void gas() {
        mussol.gas();
    }

    public void rotateRight(float diff) {
        mussol.rotateRight(diff);
    }

    public void rotateLeft(float diff) {
        mussol.rotateLeft(diff);
    }

    public void disparar() { mussol.disparar(); }

    public void Update() {
        asteroids.update(screenWidth, screenHeight);
        mussol.update(screenWidth, screenHeight, asteroids);
    }

    public void Draw(Canvas canvas){
        if(canvas!=null) {
            if (!gameStatics.isLost() && !gameStatics.isFinished()) {

                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), stdPaint);
                canvas.drawBitmap(background, 0, 0, null);
                asteroids.draw(canvas);
                mussol.draw(canvas, stdPaint);
                canvas.drawText("Level: " + (level>0 ? String.valueOf(level):"Training Camp"), 50, 100, textPaint);
                String aster = String.valueOf(gameStatics.getAsteroids());
                canvas.drawText("Asteroids: " + aster, screenWidth - 200, 100, textPaint);
                for (int i = -1; i <= gameStatics.getLives() - 2; ++i) {
                    canvas.drawBitmap(bitmapLives, screenWidth / 2 + i * 41, 100, null);
                }
            } else if(Global.getInstance().getLevel() == -1){
                asteroids = new AsteroidController(resources,this, level, screenWidth, screenHeight);
                gameStatics = new GameStaticss(level, this);
            }
            else if (gameStatics.isLost()) {
                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), stdPaint);
                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), stdPaint);
                canvas.drawBitmap(background, 0, 0, null);
                asteroids.draw(canvas);
                Global global = Global.getInstance();
                mussol.draw(canvas, stdPaint);
                boolean explotat = global.getExplotat();
                canvas.drawText("Level: " + String.valueOf(level), 50, 100, textPaint);
                String aster = String.valueOf(gameStatics.getAsteroids());
                canvas.drawText("Asteroids: " + aster, screenWidth - 300, 100, textPaint);
                ++draws;
                if(draws >= 1 && explotat) {
                    long inittime = System.nanoTime()/100000;
                    long currenttime = inittime;
                    while (currenttime - inittime <= 1 && global.getExplotat()) {
                        currenttime = System.nanoTime()/100000;
                    }

                    gameSupport = Global.getGameSupport();
                        gameSupport.retry(level, gameStatics.getAsteroids());
                }

            } else if (gameStatics.isFinished()) {
                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), stdPaint);
                gameSupport = Global.getGameSupport();
                int lives = gameStatics.getLives();
                int time = gameStatics.getTime();
                gameSupport.lvlClear(level, lives, time);
            }
        }
    }

    public void setSurfaceDimensions(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }
}
