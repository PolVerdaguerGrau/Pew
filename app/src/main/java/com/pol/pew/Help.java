package com.pol.pew;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Help extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView t2 = (TextView)findViewById(R.id.textView);
        t2.setTextColor(Color.WHITE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help, menu);
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

    public void controls(View view) {
        Intent i = new Intent(this, Controls.class);
        startActivity(i);
    }

    public void mainpage(View view) {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
    public void train(View view) {
        Global.getInstance().setLevel(-1);
        Intent i = new Intent(this, GameSupport.class);
        startActivity(i);
    }
}
