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
    private double velocityX;
    private double velocityY;
    private int directionMovement;
    private int directionFace;
    private boolean alive;
    Global global;
    int maxxPantalla;
    int maxyPantalla;
    int minPantalla;
    int radiMussol;

    public Mussol (Resources resources) {
        global = new Global();
        maxxPantalla = global.getX_PANTALLA()-15;
        maxyPantalla = global.getY_PANTALLA()-15;
        radiMussol = 40;
        minPantalla = 0 +radiMussol;
        posx = maxxPantalla/2;
        posy = maxyPantalla/2;
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.mussol);
        velocityX = 0;
        velocityY = 0;
        directionFace = 0;
        alive = true;
    }

    public void update(int screenWidth, int screenHeight) {

        decreaseSpeed();
        posx += velocityX;
        posy += velocityY;

        if(posx >= maxxPantalla) {
            posx = maxxPantalla;
            velocityX =0;
        }
        if(posy >= maxyPantalla) {
            posy = maxyPantalla;
            velocityY = 0;
        }
        if(posx <= minPantalla) {
            posx = minPantalla;
            velocityX = 0;
        }
        if(posy <= minPantalla) {
            posy = minPantalla;
            velocityY = 0;
        }
    }

    public void gas() {
        velocityX += Math.cos(Math.toRadians(directionFace-90));
        velocityY += Math.sin(Math.toRadians(directionFace-90));
    }

    public void rotateRight(float diff) {
        //directionFace -= 360*diff/maxxPantalla;
        int aps = 360*(int)diff/maxxPantalla;
        for(int i = 0; i < aps; ++i) {
            directionFace -= i;
        }
    }

    public void rotateLeft(float diff) {
        directionFace += 360*diff/maxxPantalla;
    }

    private void decreaseSpeed(){
         if(Math.abs(velocityX) <= 0.1) velocityX = 0;
         else if(velocityX != 0) {
             velocityX -= velocityX/50;
         }
         if(Math.abs(velocityY) <= 0.1) velocityY = 0;
         else if(velocityY != 0) {
             velocityY -= velocityY/50;
         }
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.rotate(directionFace, posx, posy);
        canvas.drawBitmap(bitmap, posx - bitmap.getWidth() / 2, posy - bitmap.getHeight() / 2, paint);
        canvas.restore();
    }
}
