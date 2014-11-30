package com.pol.pew;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    GameController gameController;
    SurfaceHolder surfaceHolder;
    Context context;
    private ThreadPintor threadPintor;

    public Game(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        InitView();
    }

    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        InitView();
    }

    void InitView() {
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameController = new GameController();
        gameController.Init(context);
        threadPintor = new ThreadPintor(surfaceHolder, context, new Handler(),
                gameController);
        setFocusable(true);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        threadPintor.state = ThreadPintor.PAUSED;
        while (retry) {
            try {
                threadPintor.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        if (threadPintor.state == ThreadPintor.PAUSED) {
            threadPintor = new ThreadPintor(getHolder(), context, new Handler(),
                    gameController);
            threadPintor.start();
        } else {
            threadPintor.start();
        }
        this.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeLeft() {
                System.out.println("LEFT");

                // gameController.gas();
            }
            @Override
            public void onSwipeRight() {
                System.out.println("RIGHT");
            }
            @Override
            public void onSwipeTop() {
                System.out.println("TOP");
            }
            @Override
            public void onSwipeBottom() {

            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        gameController.setSurfaceDimensions(width, height);
    }




    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
            return true;
        }
    }

}
