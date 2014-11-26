package com.pol.pew;

/**
 * Created by pol on 26/11/14.
 */
public class Global {

    private static Global global = new Global();
    private int level;


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