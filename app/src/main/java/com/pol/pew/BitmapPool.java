package com.pol.pew;

import android.graphics.Bitmap;
import android.os.Handler;

import java.util.Stack;

/**
 * Created by pol on 05/12/14.
 */
public class BitmapPool {

    private final int width;
    private final int height;
    private final Bitmap.Config config;
    private final Stack<Bitmap> bitmaps = new Stack<Bitmap>();
    private boolean isRecycled;

    private final Handler handler = new Handler();


    public BitmapPool(int bitmapWidth, int bitmapHeight,Bitmap.Config config) {
        this.width = bitmapWidth;
        this.height = bitmapHeight;
        this.config = config;
    }

        public void recycle() {
            isRecycled = true;
            for(Bitmap bitmap : bitmaps) {
                bitmap.recycle();
            }
            bitmaps.clear();
        }

    public IManagedBitmap getBitmap() {
        return new InterBitmap(bitmaps.isEmpty() ? Bitmap.createBitmap(width,height,config):bitmaps.pop());
    }

    private class InterBitmap implements IManagedBitmap {
        private int referenceCounter = 1;
        private final Bitmap bitmap;

        private InterBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        public Bitmap getBitmap() {
            return bitmap;
        }

        @Override
        public void recycle() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (--referenceCounter == 0) {
                        if (isRecycled) {
                            bitmap.recycle();
                        } else {
                            bitmaps.push(bitmap);
                        }
                    }
                }
            });
        }

        @Override
        public IManagedBitmap retain() {
            ++referenceCounter;
            return this;
        }
    }
}

