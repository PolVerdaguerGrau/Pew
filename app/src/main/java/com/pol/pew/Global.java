package com.pol.pew;

/**
 * Created by pol on 26/11/14.
 */
public class Global {

    private static Global global = new Global();
    private int level;

    private static int X_PANTALLA = 1050;
    private static int Y_PANTALLA = 1750;
    private static int STANDARD_VELOCITY_PEW = 20;
    private static int STANDARD_SIZE_ASTEROID = 52;
    private static int STARTING_LIVES = 3;

    public static int getSTARTING_LIVES() { return STARTING_LIVES; }

    public static int getX_PANTALLA() {return X_PANTALLA; }

    public static int getY_PANTALLA() {
        return Y_PANTALLA;
    }

    public static int getSTANDARD_VELOCITY_PEW() {
        return STANDARD_VELOCITY_PEW;
    }

    public static int getSTANDARD_SIZE_ASTEROID() {return STANDARD_SIZE_ASTEROID;}

    public static Global getInstance() {
        return global;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}