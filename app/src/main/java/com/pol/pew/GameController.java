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

    private String finished;

    private Resources resources;
    private Context context;

    AsteroidController asteroids;
    Mussol mussol;

    private int screenWidth;
    private int screenHeight;

    private GameStatics gameStatics;

    Bitmap background;
    Bitmap bitmapLives;

    public GameController() {}

    public void Init(Context context){
        Global glob = Global.getInstance();
        this.level = glob.getLevel();
        this.context = context;
        this.resources = context.getResources();

        gameStatics = new GameStatics(resources, level,  this);

        asteroids = new AsteroidController(resources,this, level, screenWidth, screenHeight);
        mussol = new Mussol(resources, this);

        stdPaint = new Paint();
        stdPaint.setColor(Color.BLACK);
        stdPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(Color.LTGRAY);
        textPaint.setTextSize(40);

        background = BitmapFactory.decodeResource(resources, R.drawable.background);
        bitmapLives = BitmapFactory.decodeResource(resources, R.drawable.littlelive);
        bitmapLives = Bitmap.createScaledBitmap(bitmapLives, 25, 25, true);



        setSurfaceDimensions(240, 160);


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
                canvas.drawText(String.valueOf(level), 100, 200, textPaint);
                String lives = String.valueOf(gameStatics.getLives());
                canvas.drawText(lives, 100, 300, textPaint);
                String aster = String.valueOf(gameStatics.getAsteroids());
                canvas.drawText(aster, 100, 500, textPaint);
                for (int i = 1; i <= gameStatics.getLives(); ++i) {
                    canvas.drawBitmap(bitmapLives, 100+ i*25, 900, null);
                };
            } else if (gameStatics.isLost()) {
                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), stdPaint);
                finished = "YOU LOST";
                canvas.drawText(finished, 100, 200, textPaint);
                canvas.drawText(String.valueOf(level), 100, 300, textPaint);
                String lives = String.valueOf(gameStatics.getLives());
                canvas.drawText(lives, 100, 400, textPaint);


            } else if (gameStatics.isFinished()) {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), stdPaint);
                    finished = "LEVEL CLEAR";
                    canvas.drawText(finished, 100, 200, textPaint);
                    canvas.drawText(String.valueOf(level), 150, 200, textPaint);
                    String lives = String.valueOf(gameStatics.getLives());
                    canvas.drawText(lives, 100, 400, textPaint);
                    String points = String.valueOf(gameStatics.getPunctuation());
                    canvas.drawText(points, 100, 300, textPaint);
                }
            }
    }

    public void setSurfaceDimensions(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }
}
