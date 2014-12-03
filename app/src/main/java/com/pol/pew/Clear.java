package com.pol.pew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Clear extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);
        Global global = Global.getInstance();
        int score = global.getScore();
        TextView t = (TextView)findViewById(R.id.text);
        t.setText(String.valueOf(score));
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i = 1; i <= 3; ++i) {
            String actualString = "HighScore" + i;
            int iScore = sharedPreferences.getInt(actualString, 0);
            if(iScore <= score) {

                editor.putInt(actualString, score);
                score = iScore;
                editor.commit();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.clear, menu);
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

    public void retry(View view) {
        Intent intent = new Intent(this, GameSupport.class);
        startActivity(intent);
    }

    public void exit(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
