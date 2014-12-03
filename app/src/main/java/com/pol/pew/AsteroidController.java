package com.pol.pew;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by pol on 26/11/14.
 */
public class AsteroidController {
    ArrayList<Asteroid> asteroids;
    Bitmap asteroidBitmap;
    Bitmap asteroidMigBitmap;
    Bitmap asteroidXicBitmap;
    Paint paint;
    Global global;
    int xPantalla;
    int yPantalla;
    private GameController gameController;

    public AsteroidController (Resources resources, GameController gameController, int lvl, int screenWidth, int screenHeight) {
        asteroidBitmap = BitmapFactory.decodeResource(resources,R.drawable.asteroid);
        asteroidBitmap = Bitmap.createScaledBitmap(asteroidBitmap, Global.getSTANDARD_SIZE_ASTEROID(), Global.getSTANDARD_SIZE_ASTEROID(), true);

        asteroidMigBitmap = BitmapFactory.decodeResource(resources,R.drawable.asteroidmig);
        asteroidMigBitmap = Bitmap.createScaledBitmap(asteroidBitmap, Global.getSTANDARD_SIZE_ASTEROID()/2, Global.getSTANDARD_SIZE_ASTEROID()/2, true);

        asteroidXicBitmap = BitmapFactory.decodeResource(resources,R.drawable.asteroidxic);
        asteroidXicBitmap = Bitmap.createScaledBitmap(asteroidBitmap, Global.getSTANDARD_SIZE_ASTEROID()/4, Global.getSTANDARD_SIZE_ASTEROID()/4, true);

        global = new Global();
        xPantalla = global.getX_PANTALLA();
        yPantalla = global.getY_PANTALLA();
        paint = new Paint();
        this.gameController = gameController;
        if(lvl == 1) {
            asteroids = new ArrayList<Asteroid>();
            int x, y, dir;
            double v;
            for(int i = 0; i < 10; ++i) {
                x = (int)(Math.random()*xPantalla);
                y = 100;
                dir = 90;
                v = 0.5+Math.random()*4;
                Random rand = new Random();
                v = 1+rand.nextDouble()*4;
                asteroids.add(new Asteroid(x, y, dir, v, asteroidBitmap));
            }

        }
        else if(lvl == 2) {
            asteroids = new ArrayList<Asteroid>();
            int x, y, dir;
            double v;
            for(int i = 0; i < 4; ++i) {
                x = ((i+2)%2)*xPantalla;
                y = ((i/2))*yPantalla;
                double angle = (Math.atan((double)yPantalla/xPantalla));
                angle = Math.toDegrees(angle);
                if(i == 0) dir = (int)angle;
                else if(i == 1) dir = (int)(180-angle);
                else if(i == 2) dir = -(int)angle;
                else dir = -((int)(180-angle));
                v = 2;
                asteroids.add(new Asteroid(x,y,dir,v,asteroidBitmap));
            }

        }
        else {
             //LEVEL 3 ONLY WORKING
            asteroids = new ArrayList<Asteroid>();
            int x, y, dir;
            double v;
            for(int i = 0; i < lvl*2; ++i) {
                x = (int)(Math.random()*xPantalla);
                y = (int)(Math.random()*yPantalla);
                dir = (int)(Math.random()*360);
                v = 1+Math.random()*lvl*3;
                asteroids.add(new Asteroid(x,y,dir,v,asteroidBitmap));
            }

        }


    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }


    public void die(Asteroid asteroid) {
        asteroid.die();
    }
    public void update(int screenWidth, int screenHeight) {
        Iterator<Asteroid> asteroidIterator =  asteroids.iterator();
        while(asteroidIterator.hasNext()) {
            Asteroid asteroid = asteroidIterator.next();
            if(asteroid.isAlive()) {
                asteroid.move(screenWidth, screenHeight);
            } else {
                asteroidIterator.remove();
                gameController.asteroidBreaks();
                if(asteroid.getMida() == Global.getSTANDARD_SIZE_ASTEROID()) {
                    asteroids.add(new Asteroid(asteroid.getPosx(), asteroid.getPosy(), asteroid.getDir()+45, asteroid.getVelocity(), asteroidMigBitmap, asteroid.getMida()/2));
                    asteroids.add(new Asteroid(asteroid.getPosx(), asteroid.getPosy(), asteroid.getDir()-45, asteroid.getVelocity(), asteroidMigBitmap, asteroid.getMida()/2));
                    asteroids.add(new Asteroid(asteroid.getPosx(), asteroid.getPosy(), asteroid.getDir()+135, asteroid.getVelocity(), asteroidMigBitmap, asteroid.getMida()/2));
                    asteroids.add(new Asteroid(asteroid.getPosx(), asteroid.getPosy(), asteroid.getDir()-135, asteroid.getVelocity(), asteroidMigBitmap, asteroid.getMida()/2));
                } else if(asteroid.getMida() == Global.getSTANDARD_SIZE_ASTEROID()/2) {
                    asteroids.add(new Asteroid(asteroid.getPosx(), asteroid.getPosy(), asteroid.getDir()+30, asteroid.getVelocity(), asteroidXicBitmap, asteroid.getMida()/2));
                    asteroids.add(new Asteroid(asteroid.getPosx(), asteroid.getPosy(), asteroid.getDir()-30, asteroid.getVelocity(), asteroidXicBitmap, asteroid.getMida()/2));
                }
                break;
            }
        }
    }

    public void draw(Canvas canvas) {
        for(Asteroid asteroid : asteroids) {
            asteroid.draw(canvas, asteroid.getPosx(), asteroid.getPosy(), paint);
        }
    }
}
