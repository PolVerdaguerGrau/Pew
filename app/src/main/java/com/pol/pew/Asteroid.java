package com.pol.pew;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by pol on 26/11/14.
 */
public class Asteroid {
    private Bitmap bitmap;
    protected float posx;
    protected float posy;
    private double velocity;
    private int direction;
    private int size; //50 gran, 25 mitja, 15 petit;
    private boolean alive;

    public Asteroid(int x, int y, int dir, double v, Bitmap bitmap) {
        this.bitmap = bitmap;
        alive = true;
        posx = x;
        posy = y;
        velocity = v;
        direction = dir;
        size = 50;
    }

    public Asteroid(int x, int y, int dir, double v, Bitmap bitmap, int size) {
        this.bitmap = bitmap;
        alive = true;
        posx = x;
        posy = y;
        velocity = v;
        direction = dir;
        this.size = size;
    }

    public void move(int screenWidth, int screenHeight) {

        posx +=(Math.cos(Math.toRadians(direction))*velocity);
        posy +=(Math.sin(Math.toRadians(direction))*velocity);
    }

    public void draw(Canvas canvas, float x, float y, Paint paint) {
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
      //  canvas.rotate(-angle, x, y);
        canvas.drawBitmap(bitmap, x - bitmap.getWidth() / 2, y - bitmap.getHeight() / 2, paint);
        canvas.restore();
    }
}

