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
    Global global;
    int xPantalla;
    int yPantalla;

    public Mussol (Resources resources) {
        global = new Global();
        xPantalla = global.getX_PANTALLA();
        yPantalla = global.getY_PANTALLA();
        posx = xPantalla/2;
        posy = yPantalla/2;
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.mussol);
        velocity = 0;
        directionMovement = 0;
        directionFace = 0;
        alive = true;
    }

    public void update(int screenWidth, int screenHeight) {
        if(velocity > 0.1)velocity-= velocity/50;
        else velocity = 0;
        posx -= (int)Math.sin(directionFace)*velocity;
        posy -= (int)Math.cos(directionFace)*velocity;

        if(posx >= xPantalla) {
            posx = xPantalla;
        }
        if(posy >= yPantalla) {
            posy = yPantalla;
        }
        if(posx <= 0) {
            posx = 0;
        }
        if(posy <= 0) {
            posy = 0;
        }
    }

    public void gas() {
        velocity +=5;
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
