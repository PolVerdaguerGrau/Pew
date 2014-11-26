package com.pol.pew;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by pol on 26/11/14.
 */
public class AsteroidController {
    ArrayList<Asteroid> asteroids;
    Bitmap asteroidBitmap;
    Paint paint;

    public AsteroidController (Resources resources, int lvl) {
        asteroidBitmap = BitmapFactory.decodeResource(resources,R.drawable.asteroid);
        paint = new Paint();
        if(lvl == 1) {
            asteroids = new ArrayList<Asteroid>();
            int x, y, dir;
            double v;
            for(int i = 0; i < 10; ++i) {
                x = (int)(Math.random()*1000);
                y = 100;
                dir = 0;
                v = Math.random()*4;
                asteroids.add(new Asteroid(x,y,dir,v,asteroidBitmap));
            }

        }

    }

    public void update(int screenWidth, int screenHeight) {
        Iterator<Asteroid> ite =  asteroids.iterator();
        while(ite.hasNext()) {
            Asteroid asteroid = ite.next();
            asteroid.move(screenWidth, screenHeight);
        }
    }

    public void draw(Canvas canvas) {
        for(Asteroid asteroid : asteroids) {
            asteroid.draw(canvas, asteroid.posx, asteroid.posy, paint);
        }
    }
}
