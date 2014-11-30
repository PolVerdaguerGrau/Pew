package com.pol.pew;

/**
 * Created by pol on 26/11/14.
 */
public class Global {

    private static Global global = new Global();
    private int level;

    private static int X_PANTALLA = 1050;
    private static int Y_PANTALLA = 1750;

    public static int getX_PANTALLA() {
        return X_PANTALLA;
    }

    public static int getY_PANTALLA() {
        return Y_PANTALLA;
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
}