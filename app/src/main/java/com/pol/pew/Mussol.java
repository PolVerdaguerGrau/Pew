package com.pol.pew;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by pol on 27/11/14.
 */
public class Mussol {
    private Bitmap bitmap;
    protected float posx;
    protected float posy;
    private double velocity;
    private int directionMovement;
    private int directionFace;
    private boolean alive;

    public Mussol (Resources resources) {
        posx = 1050/2;
        posy = 1750/2;
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.mussol);
        velocity = 0;
        directionMovement = 0;
        directionFace = 0;
        alive = true;
    }

    public void update(int screenWidth, int screenHeight) {
        posx += (int)Math.cos(directionFace)*velocity;
        posy += (int)Math.cos(directionFace)*velocity;
    }

    public void rotateRight() {
        directionFace +=5;
    }

    public void rotateLeft() {
        directionFace -= 5;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.rotate(directionFace, posx, posy);
        canvas.drawBitmap(bitmap, posx - bitmap.getWidth() / 2, posy - bitmap.getHeight() / 2, paint);
        canvas.restore();
    }
}
