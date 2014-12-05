package com.pol.pew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Levels extends Activity {



    public void lvl2(View view) {
        Intent intent = new Intent(this, WaitingScreen.class);
        Global glob = Global.getInstance();
        glob.setLevel(2);
        startActivity(intent);
    }

    public void lvl1(View view) {
        System.out.println("LVL");
        Intent intent = new Intent(this, WaitingScreen.class);
        Global glob = Global.getInstance();
        glob.setLevel(1);
        startActivity(intent);
    }

    public void lvl3(View view) {
        Intent intent = new Intent(this, WaitingScreen.class);
        Global glob = Global.getInstance();
        glob.setLevel(3);
        startActivity(intent);
    }

    public void lvl4(View view) {
        Intent intent = new Intent(this, WaitingScreen.class);
        Global glob = Global.getInstance();
        glob.setLevel(-1);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.levels, menu);
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

    public void mainpage(View view) {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
}
