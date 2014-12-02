package com.pol.pew;

import android.content.res.Resources;

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
    private boolean finished;
    private GameController gameController;
    private Resources resources;

    public GameStatics(Resources resources, int lvl, GameController gameController) {
        lives = Global.getSTARTING_LIVES();
        Global global = new Global();
        remainingAsteroids = global.getNumberAsteroids();
        fuelUsed = ammoUsed = puntuacio = 0;
        startingTime = System.nanoTime();
        this.lvl = lvl;
        this.remainingAsteroids = remainingAsteroids;
        lost = false;
        finished = false;
        this.gameController = gameController;
        this.resources = resources;
    }

    public void decreaseLives() {
        actualTime = System.nanoTime();
        if(lives == 0) {
            lost = true;
        } else {
            --lives;
        }
    }

    public void decreaseAsteroids() {
        actualTime = System.nanoTime();
        --remainingAsteroids;
        if (remainingAsteroids == 0) {
            finished = true;
        }
    }

    public double getPunctuation() {
        return actualTime-startingTime;
    }

    public boolean isLost() {return lost;}

    public boolean isFinished() {return finished;}

    public int getLives() {return lives;}
}
