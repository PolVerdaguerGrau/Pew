package com.pol.pew;

import android.graphics.Canvas;

/**
 * Created by pol on 02/12/14.
 */
public class GameStatics {
    private int lives;
    private int lvl;
    private int remainingAsteroids;
    private int fuelUsed;
    private int ammoUsed;
    private int puntuacio;
    private double startingTime;
    private double actualTime;
    private boolean lost;

    public GameStatics(int lvl, int remainingAsteroids) {
        lives = Global.getSTARTING_LIVES();
        fuelUsed = ammoUsed = puntuacio = 0;
        startingTime = System.nanoTime();
        this.lvl = lvl;
        this.remainingAsteroids = remainingAsteroids;
        lost = false;
    }

    public void decreaseLives() {
        if(lives == 0) {
            lost = true;
        } else {
            --lives;
        }
    }

    public void draw(Canvas canvas) {
        actualTime = System.nanoTime();
        if(lost) {
            //YOU LOSE
        } else if(remainingAsteroids == 0 && !lost) {
            //YOU WON
            //DISPLAY PUNCTUATION
        } else {
            //DISPLAY LIVES
        }
    }
}
