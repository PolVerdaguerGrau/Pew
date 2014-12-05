package com.pol.pew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class GameSupport extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_support);
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
        Global.setGameSupport(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_support, menu);
        return true;
    }

    public void retry(int lvl) {
        Intent intent = new Intent(this, Lost.class);
        Global global = Global.getInstance();
        global.setLevel(lvl);
        startActivity(intent);
    }

    public void lvlClear(int lvl, int lives, int time) {
        Intent intent = new Intent(this, Clear.class);
        Global global = Global.getInstance();
        int asteroids = global.getAsteroids(lvl);
        int score = asteroids + (((asteroids*lives)-time/10 > 0) ? (asteroids*lives)-time/10 : 0);
        global.setScore(score);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Levels.class);
        startActivity(intent);
    }
}

