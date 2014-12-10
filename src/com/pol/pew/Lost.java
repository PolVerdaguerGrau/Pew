package com.pol.pew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Lost extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
        Global global = Global.getInstance();
        int score = global.getScore();
        TextView t = (TextView) findViewById(R.id.text);
        t.setText(String.valueOf(score));
        EditText text = (EditText) findViewById(R.id.textEdit);
        text.setBackgroundColor(Color.DKGRAY);
        text.setTextColor(Color.WHITE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lost, menu);
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

    public void save(View view) {
        Global global = Global.getInstance();
        int score = global.getScore();
        int firstScore = score;
        EditText text = (EditText)findViewById(R.id.textEdit);
        String name = text.getText().toString();
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean changed = false;
        for(int i = 1; i <= 5; ++i) {
            String actualString = "HighScore" + i;
            String actualName = "Name" + i;
            int iScore = sharedPreferences.getInt(actualString, 0);
            String iName = sharedPreferences.getString(actualName, "PEW");
            Global.getInstance().setLastScore(0);
            if(iScore <= score) {
                if(!changed) {
                    changed = true;
                    Global.getInstance().setLastScore(i);
                }
                editor.putInt(actualString, score);
                editor.putString(actualName, name);
                score = iScore;
                name = iName;
                editor.commit();
            }
        }
        if(!changed){
        	Global.getInstance().setLastScore(0);
        }
        Intent intent = new Intent(this, HighScores.class);
        startActivity(intent);
    }

    public void exit(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
