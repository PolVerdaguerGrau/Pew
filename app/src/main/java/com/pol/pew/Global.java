package com.pol.pew;

import android.graphics.Bitmap;

/**
 * Created by pol on 26/11/14.
 */
public class Global {

    private static Global global = new Global();
    private int level = 1;
    private int score = 0;
    private boolean finished = false;
    private int lastScore = 0;


    private boolean explotat = false;

    private static GameSupport gameSupport = new GameSupport();


    private static int X_PANTALLA = 1050;
    private static int Y_PANTALLA = 1750;
    private static int STANDARD_VELOCITY_PEW = 20;
    private static int STANDARD_SIZE_ASTEROID = 150;
    private static int STARTING_LIVES = 3;
    private static int MUSSOL_SIZE = 70;
    private static int FIRST_SPLIT = 4;
    private static int SECOND_SPLIT = 2;
    private static int ASTEROIDS_LEVEL2 = calcAsteroids(10);
    private static int ASTEROIDS_LEVEL1 = calcAsteroids(4);
    private static int ASTEROIDS_LEVEL3 = calcAsteroids(6);
    private Bitmap asteroidBitmap;

    private Bitmap pewBitmap;

    private Bitmap LivesBitmap;


    public Bitmap getMussolBitmap() {

        return mussolBitmap;
    }

    public void setMussolBitmap(Bitmap mussolBitmap) {
        this.mussolBitmap = mussolBitmap;
    }

    private Bitmap mussolBitmap;

    public Bitmap getAsteroidMigBitmap() {
        return asteroidMigBitmap;
    }

    public void setAsteroidMigBitmap(Bitmap asteroidMigBitmap) {
        this.asteroidMigBitmap = asteroidMigBitmap;
    }

    public Bitmap getAsteroidBitmap() {
        return asteroidBitmap;
    }

    public void setAsteroidBitmap(Bitmap asteroidBitmap) {
        this.asteroidBitmap = asteroidBitmap;
    }

    public Bitmap getAsteroidXicBitmap() {
        return asteroidXicBitmap;
    }

    public void setAsteroidXicBitmap(Bitmap asteroidXicBitmap) {
        this.asteroidXicBitmap = asteroidXicBitmap;
    }

    private Bitmap asteroidMigBitmap;
    private Bitmap asteroidXicBitmap;


    private static int calcAsteroids(int asteroids) {
        return asteroids + asteroids * FIRST_SPLIT + asteroids * FIRST_SPLIT * SECOND_SPLIT;
    }

    public int getSTARTING_LIVES() {
        return STARTING_LIVES;
    }

    public static int getX_PANTALLA() {
        return X_PANTALLA;
    }

    public static int getY_PANTALLA() {
        return Y_PANTALLA;
    }

    public static int getSTANDARD_VELOCITY_PEW() {
        return STANDARD_VELOCITY_PEW;
    }

    public static int getSTANDARD_SIZE_ASTEROID() {
        return STANDARD_SIZE_ASTEROID;
    }

    public static int getMIDA_MUSSOL() {
        return MUSSOL_SIZE;
    }

    public static Global getInstance() {
        return global;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAsteroids(int lvl) {
        if (lvl == 1) return ASTEROIDS_LEVEL1;
        else if (lvl == 2) return ASTEROIDS_LEVEL2;
        else {
            return calcAsteroids(lvl * 2);
        }
    }

    public static int getASTEROIDS_LEVEL2() {
        return ASTEROIDS_LEVEL2;
    }

    public static int getASTEROIDS_LEVEL1() {
        return ASTEROIDS_LEVEL1;
    }

    public static int getASTEROIDS_LEVEL3() {
        return ASTEROIDS_LEVEL3;
    }

    public static GameSupport getGameSupport() {
        return gameSupport;
    }

    public static void setGameSupport(GameSupport gameSupportt) {
        gameSupport = gameSupportt;
    }

    public boolean isFinished() {
        return finished;
    }

    public void finish(boolean finished) {
        this.finished = finished;
    }

    public void setExplotat(boolean explotat) {
        this.explotat = explotat;
    }

    public boolean getExplotat() {
        return explotat;
    }

    public static void setX_PANTALLA(int x_PANTALLA) {
        X_PANTALLA = x_PANTALLA;
    }

    public static void setY_PANTALLA(int y_PANTALLA) {
        Y_PANTALLA = y_PANTALLA;
    }

    public Bitmap getPewBitmap() {
        return pewBitmap;
    }

    public void setPewBitmap(Bitmap pewBitmap) {
        this.pewBitmap = pewBitmap;
    }


    public Bitmap getLivesBitmap() {
        return LivesBitmap;
    }

    public void setLivesBitmap(Bitmap livesBitmap) {
        LivesBitmap = livesBitmap;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }
}