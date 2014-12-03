package com.pol.pew;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class HighScores extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int score = sharedPreferences.getInt(getString(R.string.saved_high_score_1), 0);
        TextView t = (TextView)findViewById(R.id.textView);
        t.setText("1.  "+String.valueOf(score)+"  POL");

        int score2 = sharedPreferences.getInt(getString(R.string.saved_high_score_2), 0);
        TextView t2 = (TextView)findViewById(R.id.textView2);
        t2.setText("2.  "+String.valueOf(score2)+"  POL");

        int score3 = sharedPreferences.getInt(getString(R.string.saved_high_score_3), 0);
        TextView t3 = (TextView)findViewById(R.id.textView3);
        t3.setText("3.  "+String.valueOf(score3)+"  POL");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.high_scores, menu);
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
}
