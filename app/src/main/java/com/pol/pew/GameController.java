package com.pol.pew;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pol on 26/11/14.
 */
public class GameController {

    private int level;

    Paint stdPaint;

    Paint textPaint;

    private String time;
    private String lost;

    private Resources resources;
    private Context context;

    AsteroidController asteroids;
    Mussol mussol;

    private int screenWidth;
    private int screenHeight;

    private GameStatics gameStatics;

    Bitmap background;
    Bitmap bitmapLives;

    public GameController() {


    }

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
        bitmapLives = BitmapFactory.decodeResource(resources, R.drawable.live);


        setSurfaceDimensions(240, 160);


    }

    public void mussolCollides() {
        gameStatics.decreaseLives();
    }

    public void setAsteroidBreaks() {
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
        time = new SimpleDateFormat("HH:mm:ss").format(new Date());

        asteroids.update(screenWidth, screenHeight);
        mussol.update(screenWidth, screenHeight, asteroids);

    }

    public void Draw(Canvas canvas){

        if(!gameStatics.isLost() && !gameStatics.isFinished()) {

            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), stdPaint);
            canvas.drawBitmap(background, 0, 0, null);
            canvas.drawText(time, 30, 100, textPaint);
            canvas.drawText(String.valueOf(level), 100, 200, textPaint);
            String lives = String.valueOf(gameStatics.getLives());
            canvas.drawText(lives, 100, 300, textPaint);

            for(int i = 1; i <= gameStatics.getLives(); ++i) {
                canvas.drawBitmap(bitmapLives, i*30, 30, null);
            }
            asteroids.draw(canvas);
            mussol.draw(canvas, stdPaint);
        }
        else if(gameStatics.isLost()) {
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), stdPaint);
            lost = "YOU LOST";
            canvas.drawText(lost, 100, 200, textPaint);
            canvas.drawText(String.valueOf(level), 100, 300, textPaint);
            String lives = String.valueOf(gameStatics.getLives());
            canvas.drawText(lives, 100, 400, textPaint);



        }
        else if(gameStatics.isFinished()) {

        }


    }

    public void setSurfaceDimensions(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }
}
