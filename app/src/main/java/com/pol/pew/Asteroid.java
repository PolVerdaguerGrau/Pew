package com.pol.pew;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by pol on 26/11/14.
 */
public class Asteroid {
    private Bitmap bitmap;
    private float posx;
    private float posy;
    private double velocity;
    private int direction;
    private int size; //50 gran, 25 mitja, 15 petit;
    private boolean alive;

    public Asteroid(float x, float y, int dir, double v, Bitmap bitmap) {
        this.bitmap = bitmap;
        alive = true;
        posx = x;
        posy = y;
        velocity = v;
        direction = dir;
        size = Global.getSTANDARD_SIZE_ASTEROID();
    }

    public Asteroid(float x, float y, int dir, double v, Bitmap bitmap, int size) {
        this.bitmap = bitmap;
        alive = true;
        posx = x;
        posy = y;
        velocity = v;
        direction = dir;
        this.size = size;
    }

    public float getPosx() {return posx;}

    public float getPosy() {return posy;}

    public int getDir() {return direction;}

    public int getMida() {return size;}

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
    public double getVelocity() {return velocity;}
    public void move(int screenWidth, int screenHeight) {

        posx +=(Math.cos(Math.toRadians(direction))*velocity);
        if(posx >= screenWidth) posx = 0;
        else if(posx <= 0) posx = screenWidth;
        posy +=(Math.sin(Math.toRadians(direction))*velocity);
        if(posy >= screenHeight) posy = 0;
        else if(posy <= 0) posy = screenHeight;
    }

    public void draw(Canvas canvas, float x, float y, Paint paint) {
        canvas.save();
      //  canvas.rotate(-angle, x, y);
        canvas.drawBitmap(bitmap, x - bitmap.getWidth() / 2, y - bitmap.getHeight() / 2, paint);
        canvas.restore();
    }
}

