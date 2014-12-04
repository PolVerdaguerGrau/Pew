package com.pol.pew;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
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
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        gameController.setSurfaceDimensions(width, height);
    }

    private float lastTouchX;
    private float lastTouchY;
    private long touchDownTime = 0;
    private static final int INVALID_POINTER_ID = -1;
    private int activePointerId = INVALID_POINTER_ID;

   @Override
   public boolean onTouchEvent(MotionEvent motionEvent) {
       final int action = motionEvent.getAction();

       switch (action & MotionEvent.ACTION_MASK) {

           case MotionEvent.ACTION_DOWN: {
               final float x = motionEvent.getX();
               final float y = motionEvent.getY();

               lastTouchX = x;
               lastTouchY = y;
               touchDownTime = System.currentTimeMillis();
               activePointerId = motionEvent.getPointerId(0);
               break;
           }

           case MotionEvent.ACTION_MOVE: {
               activePointerId = motionEvent.getPointerId(0);

               final int pointerIndex = motionEvent
                       .findPointerIndex(activePointerId);
               final float x = motionEvent.getX(pointerIndex);
               final float y = motionEvent.getY(pointerIndex);
               final float dX = x - lastTouchX;
               final float dY = y - lastTouchY;

               lastTouchX = x;
               lastTouchY = y;

               if (dY < -5) {
                   gameController.gas();
                   break;
               }
               if (dX > 1) {
                   gameController.rotateRight(-dX);
                   break;
               }
               if (dX < -1) {
                   gameController.rotateLeft(dX);
                   break;
               }
               break;
           }

           case MotionEvent.ACTION_UP: {
               if (System.currentTimeMillis() < touchDownTime + 150) {
                   gameController.disparar();
               }
               activePointerId = INVALID_POINTER_ID;
               break;
           }

           case MotionEvent.ACTION_CANCEL: {
               activePointerId = INVALID_POINTER_ID;
               break;
           }

           case MotionEvent.ACTION_POINTER_UP: {
               final int pointerIndex = (action & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
               final int pointerId = motionEvent.getPointerId(pointerIndex);
               if (pointerId == activePointerId) {
                   final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                   lastTouchX = motionEvent.getX(newPointerIndex);
                   lastTouchY = motionEvent.getY(newPointerIndex);
                   activePointerId = motionEvent.getPointerId(newPointerIndex);
               }
               break;
           }
       }
       return true;
   }

}
