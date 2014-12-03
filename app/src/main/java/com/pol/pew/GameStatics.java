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
        if(lvl == 1) {remainingAsteroids = Global.getASTEROIDS_LEVEL1();}
        else if(lvl == 2) {remainingAsteroids = Global.getASTEROIDS_LEVEL2();}
        else if(lvl == 3) {remainingAsteroids = Global.getASTEROIDS_LEVEL3();}
        fuelUsed = ammoUsed = puntuacio = 0;
        startingTime = System.nanoTime();
        this.lvl = lvl;
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

    public int getAsteroids() {return remainingAsteroids;}

    public int getTime() {
        return (int)((actualTime/100000000/1000-startingTime/1000000000/1000));
    }

    public boolean isLost() {return lost;}

    public boolean isFinished() {return finished;}

    public int getLives() {return lives;}
}
