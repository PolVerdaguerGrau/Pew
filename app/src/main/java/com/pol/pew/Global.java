package com.pol.pew;

/**
 * Created by pol on 26/11/14.
 */
public class Global {

    private static Global global = new Global();
    private int level = 1;
    private int score = 0;

    private static GameSupport gameSupport = new GameSupport();

    private static int X_PANTALLA = 1050;
    private static int Y_PANTALLA = 1750;
    private static int STANDARD_VELOCITY_PEW = 20;
    private static int STANDARD_SIZE_ASTEROID = 150;
    private static int STARTING_LIVES = 3;
    private static int MUSSOL_SIZE = 70;
    private static int FIRST_SPLIT = 4;
    private static int SECOND_SPLIT = 2;
    private static int ASTEROIDS_LEVEL1 = calcAsteroids(10);
    private static int ASTEROIDS_LEVEL2 = calcAsteroids(4);
    private static int ASTEROIDS_LEVEL3 = calcAsteroids(6);

    private static int calcAsteroids(int asteroids) {
        return asteroids+asteroids*FIRST_SPLIT+asteroids*FIRST_SPLIT*SECOND_SPLIT;
    }
    public static int getSTARTING_LIVES() { return STARTING_LIVES; }

    public static int getX_PANTALLA() {return X_PANTALLA; }

    public static int getY_PANTALLA() {
        return Y_PANTALLA;
    }

    public static int getSTANDARD_VELOCITY_PEW() {
        return STANDARD_VELOCITY_PEW;
    }

    public static int getSTANDARD_SIZE_ASTEROID() {return STANDARD_SIZE_ASTEROID;}

    public static int getMIDA_MUSSOL() {return MUSSOL_SIZE;}

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
        if(lvl == 1) return ASTEROIDS_LEVEL1;
        else if(lvl == 2) return ASTEROIDS_LEVEL2;
        else {
            return calcAsteroids(lvl*2);
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

}