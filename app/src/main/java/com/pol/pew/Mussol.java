package com.pol.pew;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;

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
    int maxxPantalla;
    int maxyPantalla;
    int minPantalla;
    int radiMussol;

    ArrayList<Pew> pews;
    Bitmap pewBitmap;
    Paint paint;

    public Mussol (Resources resources) {
        maxxPantalla = Global.getX_PANTALLA()-15;
        maxyPantalla = Global.getY_PANTALLA()-15;
        radiMussol = 40;
        minPantalla = 0 +radiMussol;
        posx = maxxPantalla/2;
        posy = maxyPantalla/2;
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.mussol);
        velocityX = 0;
        velocityY = 0;
        directionFace = 0;
        alive = true;

        pewBitmap = BitmapFactory.decodeResource(resources, R.drawable.pew);
        paint = new Paint();
        pews = new ArrayList<Pew>();
    }

    public void gas() {
        velocityX += Math.cos(Math.toRadians(directionFace-90));
        velocityY += Math.sin(Math.toRadians(directionFace-90));
    }

    public void rotateRight(float diff) {
        directionFace -= 300*diff/maxxPantalla;
    }

    public void rotateLeft(float diff) {
        directionFace += 300*diff/maxxPantalla;
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

    public void disparar() {
        Pew p = new Pew(posx,posy, directionFace, pewBitmap);
        pews.add(p);
    }

    public void updatePews(int screenWidth, int screenHeight) {
        Iterator<Pew> ite =  pews.iterator();
        while(ite.hasNext()) {
            Pew pew = ite.next();
            if(pew.isAlive()) {
                pew.move(screenWidth, screenHeight);
            } else {
                ite.remove();
            }

        }
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

        updatePews(screenWidth, screenHeight);
    }


    public void drawPews(Canvas canvas) {
        Iterator<Pew> ite =  pews.iterator();
        while(ite.hasNext()) {
            Pew pew = ite.next();
            pew.draw(canvas, pew.posx, pew.posy, paint);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        drawPews(canvas);
        canvas.save();
        canvas.rotate(directionFace, posx, posy);
        canvas.drawBitmap(bitmap, posx - bitmap.getWidth() / 2, posy - bitmap.getHeight() / 2, paint);
        canvas.restore();
    }
}
