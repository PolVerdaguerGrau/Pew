package com.pol.pew;

import android.content.Context;
import android.content.res.Resources;
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

    private Resources resources;
    private Context context;

    AsteroidController asteroids;

    private int screenWidth;
    private int screenHeight;


    public GameController() {


    }

    public void Init(Context context){
        Global glob = Global.getInstance();
        this.level = glob.getLevel();

        this.context = context;
        this.resources = context.getResources();

        asteroids = new AsteroidController(resources, level);
        stdPaint = new Paint();
        stdPaint.setColor(Color.BLACK);
        stdPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(Color.LTGRAY);
        textPaint.setTextSize(40);

        setSurfaceDimensions(240, 160);


    }

    public void Update() {
        time = new SimpleDateFormat("HH:mm:ss").format(new Date());

        asteroids.update(screenWidth, screenHeight);

    }

    public void Draw(Canvas canvas){



        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), stdPaint);
        asteroids.draw(canvas);

        canvas.drawText(time, 30, 100, textPaint);
        canvas.drawText(String.valueOf(level), 100, 200, textPaint);

    }

    public void setSurfaceDimensions(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }
}
