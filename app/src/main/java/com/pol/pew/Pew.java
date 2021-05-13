package com.pol.pew;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by pol on 02/12/14.
 */
public class Pew {

        private Bitmap bitmap;
        protected float posx;
        protected float posy;
        private double velocity;
        private int direction;
        private int size; //50 gran, 25 mitja, 15 petit;
        private boolean alive;



    public Pew(float x, float y, int dir, Bitmap bitmap) {
            this.bitmap = bitmap;
            alive = true;
            posx = x;
            posy = y;
            velocity = Global.getSTANDARD_VELOCITY_PEW();
            direction = dir-90;
            size = 10;
        }

        public boolean isAlive() {
            return alive;
        }

        public void die() {
            alive = false;
        }
        public void move(int screenWidth, int screenHeight) {

            posx +=(Math.cos(Math.toRadians(direction))*velocity);
            if(posx >= screenWidth) alive = false;
            else if(posx <= 0) alive = false;
            posy +=(Math.sin(Math.toRadians(direction))*velocity);
            if(posy >= screenHeight) alive = false;
            else if(posy <= 0) alive = false;
        }

        public void draw(Canvas canvas, float x, float y, Paint paint) {
            canvas.save();
            canvas.drawBitmap(bitmap, x - bitmap.getWidth() / 2, y - bitmap.getHeight() / 2, paint);
            canvas.restore();
        }



}
