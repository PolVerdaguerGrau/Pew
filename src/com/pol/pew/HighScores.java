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
import android.widget.ImageView;
import android.widget.TextView;


public class HighScores extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        int score = sharedPreferences.getInt(getString(R.string.saved_high_score_1), 0);
        String name = sharedPreferences.getString(getString(R.string.saved_name_score_1), "PEW");
        TextView t = (TextView)findViewById(R.id.textView);
        t.setTextColor(Global.getInstance().getLastScore() == 1 ? Color.YELLOW : Color.WHITE);
        t.setText("1.  " + String.valueOf(score) + "   " + name);
        if(score == 0) {
            t.setText("Empty");
        }

        int score2 = sharedPreferences.getInt(getString(R.string.saved_high_score_2), 0);
        String name2 = sharedPreferences.getString(getString(R.string.saved_name_score_2), "PEW");
        TextView t2 = (TextView)findViewById(R.id.textView2);
        t2.setTextColor(Global.getInstance().getLastScore() == 2 ? Color.YELLOW : Color.WHITE);
        t2.setText("2.  "+String.valueOf(score2)+"   "+name2);
        if(score2 == 0) {
            t2.setVisibility(View.INVISIBLE);
        }


        int score3 = sharedPreferences.getInt(getString(R.string.saved_high_score_3), 0);
        String name3 = sharedPreferences.getString(getString(R.string.saved_name_score_3), "PEW");
        TextView t3 = (TextView)findViewById(R.id.textView3);
        t3.setTextColor(Global.getInstance().getLastScore() == 3 ? Color.YELLOW : Color.WHITE);
        t3.setText("3.  "+String.valueOf(score3)+"   "+name3);
        if(score3 == 0) {
            t3.setVisibility(View.INVISIBLE);
        }

        int score4 = sharedPreferences.getInt(getString(R.string.saved_high_score_4), 0);
        String name4 = sharedPreferences.getString(getString(R.string.saved_name_score_4), "PEW");
        TextView t4 = (TextView)findViewById(R.id.textView4);
        t4.setTextColor(Global.getInstance().getLastScore() == 4 ? Color.YELLOW : Color.WHITE);
        t4.setText("4.  "+String.valueOf(score4)+"   "+name4);
        if(score4 == 0) {
            t4.setVisibility(View.INVISIBLE);
        }

        int score5 = sharedPreferences.getInt(getString(R.string.saved_high_score_5), 0);
        String name5 = sharedPreferences.getString(getString(R.string.saved_name_score_5), "PEW");
        TextView t5 = (TextView)findViewById(R.id.textView5);
        t5.setTextColor(Global.getInstance().getLastScore() == 5 ? Color.YELLOW : Color.WHITE);
        t5.setText("5.  "+String.valueOf(score5)+"   "+name5);
        if(score5 == 0) {
            t5.setVisibility(View.INVISIBLE);
        }

    }

    public void clear(View view) {
        ImageView sure = (ImageView) findViewById(R.id.sure);
        ImageView yes = (ImageView) findViewById(R.id.yes);
        ImageView no = (ImageView) findViewById(R.id.no);
        ImageView clear = (ImageView) findViewById(R.id.clear);
        ImageView back = (ImageView) findViewById(R.id.back);
        sure.setVisibility(View.VISIBLE);
        yes.setVisibility(View.VISIBLE);
        no.setVisibility(View.VISIBLE);
        clear.setVisibility(View.INVISIBLE);
        back.setVisibility(View.INVISIBLE);

    }

    public void no(View view) {
        ImageView sure = (ImageView) findViewById(R.id.sure);
        ImageView yes = (ImageView) findViewById(R.id.yes);
        ImageView no = (ImageView) findViewById(R.id.no);
        ImageView clear = (ImageView) findViewById(R.id.clear);
        ImageView back = (ImageView) findViewById(R.id.back);
        sure.setVisibility(View.INVISIBLE);
        yes.setVisibility(View.INVISIBLE);
        no.setVisibility(View.INVISIBLE);
        clear.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);
    }

    public void yes(View view) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i = 1; i <= 5; ++i) {
            String actualString = "HighScore" + i;
            String actualName = "Name" + i;
            editor.putInt(actualString, 0);
            editor.putString(actualName, "PEW");
        }
        editor.commit();
        Intent intent = new Intent(this, HighScores.class);
        startActivity(intent);
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

    public void mainpage(View view) {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
}
