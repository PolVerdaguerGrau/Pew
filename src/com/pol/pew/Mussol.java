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
    private int maxxPantalla;
    private int maxyPantalla;
    private int minPantalla;
    private int radiMussol;
    private Resources resources;
    private GameController gameController;
	private ArrayList<Pew> ppppp = new ArrayList<Pew>();


    private Global global;

    ArrayList<Pew> pews;
    Bitmap pewBitmap;
    Paint paint;

    public Mussol (Resources resources, GameController gameController) {
        global = Global.getInstance();
        maxxPantalla = Global.getX_PANTALLA()-15;
        maxyPantalla = Global.getY_PANTALLA()-15;
        radiMussol = Global.getMIDA_MUSSOL()/2;
        minPantalla = 0 +radiMussol;
        posx = maxxPantalla/2;
        posy = maxyPantalla/2;
        bitmap = global.getMussolBitmap();
        if(bitmap == null) {
        	bitmap = BitmapFactory.decodeResource(resources, R.drawable.mussol);
        	bitmap = Bitmap.createScaledBitmap(bitmap, Global.getMIDA_MUSSOL(), Global.getMIDA_MUSSOL(), true);
        	global.setMussolBitmap(bitmap);
        }

        velocityX = 0;
        velocityY = 0;
        directionFace = 0;
        alive = true;
        this.resources = resources;
        this.gameController = gameController;

        pewBitmap = global.getPewBitmap();
        if(pewBitmap == null) {
            pewBitmap = BitmapFactory.decodeResource(resources, R.drawable.pew);
            global.setPewBitmap(pewBitmap);
        }

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
    	if(pews.size() <= 20) {
    		Pew p = new Pew(posx,posy, directionFace, pewBitmap);
        	pews.add(p);
    	}
    }

    public void updatePews(int screenWidth, int screenHeight, AsteroidController asteroidController) {
    	Iterator<Pew> ite =  pews.iterator();
        while(ite.hasNext()) {
            Pew pew = ite.next();
            if(pew.isAlive()) {
                for (Iterator<Asteroid> asteroidIterator = asteroidController.getAsteroids().iterator();asteroidIterator.hasNext(); ) {
                    Asteroid asteroid = asteroidIterator.next();
                    if(pew.posx >= asteroid.getPosx()-asteroid.getMida()/2 && pew.posx  <= asteroid.getPosx()+asteroid.getMida()/2) {
                        if(pew.posy >= asteroid.getPosy()-asteroid.getMida()/2 && pew.posy <= asteroid.getPosy()+asteroid.getMida()/2) {
                            asteroidController.die(asteroid);
                            pew.die();
                        }
                    }
                }
                pew.move(screenWidth, screenHeight);
            } else {
                ppppp.add(pew);
            }
        }
    }

    public void update(int screenWidth, int screenHeight, AsteroidController asteroidController) {

        Iterator<Asteroid> asteroidIterator =  asteroidController.getAsteroids().iterator();
        while(asteroidIterator.hasNext()) {
            Asteroid asteroid = asteroidIterator.next();
            if( (posx+radiMussol >= asteroid.getPosx() - asteroid.getMida()/2) && (posx+radiMussol <= asteroid.getPosx() + asteroid.getMida()/2) && (posy+radiMussol >= asteroid.getPosy() - asteroid.getMida()/2) && (posy+radiMussol <= asteroid.getPosy() + asteroid.getMida()/2)) {
                alive = false;
                asteroidController.die(asteroid);
                gameController.mussolCollides();
            } else if( (posx-radiMussol >= asteroid.getPosx() - asteroid.getMida()/2) && (posx-radiMussol <= asteroid.getPosx() + asteroid.getMida()/2) && (posy-radiMussol >= asteroid.getPosy() - asteroid.getMida()/2) && (posy-radiMussol <= asteroid.getPosy() + asteroid.getMida()/2)) {
                alive = false;
                asteroidController.die(asteroid);
                gameController.mussolCollides();
            }
        }
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

        updatePews(screenWidth, screenHeight, asteroidController);
    }


    public void drawPews(Canvas canvas) {
        for (Iterator<Pew> it = pews.iterator(); it.hasNext(); ) {
            Pew pew = it.next();
            if(pew.isAlive()) {
                pew.draw(canvas, pew.posx, pew.posy, paint);
            }
        }
        for(Pew pew : ppppp) {
        	pews.remove(pew);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
    	drawPews(canvas);
        canvas.rotate(directionFace, posx, posy);
        global = Global.getInstance();
        if(!alive || global.isFinished()) {
            bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.boom);
            bitmap = Bitmap.createScaledBitmap(bitmap, Global.getMIDA_MUSSOL()+20, Global.getMIDA_MUSSOL()+20, true);
        }
        else {
            global.setExplotat(true);
            bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.mussol);
            bitmap = Bitmap.createScaledBitmap(bitmap, Global.getMIDA_MUSSOL(), Global.getMIDA_MUSSOL(), true);
        }
        canvas.drawBitmap(bitmap, posx - bitmap.getWidth() / 2, posy - bitmap.getHeight() / 2, paint);
        if(global.isFinished()) global.setExplotat(true);
        else {alive = true;}
        canvas.restore();
    }
}
