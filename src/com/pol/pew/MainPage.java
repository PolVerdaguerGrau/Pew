package com.pol.pew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Global global = Global.getInstance();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        global.setX_PANTALLA(size.x);
        global.setY_PANTALLA(size.y);
        global.setIntent(this.getIntent());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
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

       //lvls
    public void lvls(View view) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if((sharedPreferences.getInt(getString(R.string.level), -1)) == -1){
            editor.putInt(getString(R.string.level),1);
            editor.commit();
            Global.getInstance().setLevel(-1);
            Intent i = new Intent(this, Help.class);
            startActivity(i);
        } else {
            Intent i = new Intent(this, Levels.class);
            startActivity(i);
        }
    }

    public void highScores(View view) {
        Global global = Global.getInstance();
        global.setLastScore(0);
        Intent i = new Intent(this, HighScores.class);
        startActivity(i);
    }

    public void exit(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void help(View view) {
        Intent i = new Intent(this, Help.class);
        startActivity(i);
    }
}
