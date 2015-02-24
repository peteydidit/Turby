package competeydidit.facebook.www.turby69.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import competeydidit.facebook.www.turby69.R;

public class startingScreen extends ActionBarActivity {

    private static final String TAG = "myMessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        Log.i(TAG, "onCreate");
        Button start_scout = (Button)findViewById(R.id.start_scout); //starting screen scout button
        start_scout.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        //TextView titleText = (TextView)findViewById(R.id.titleText);
                        //titleText.setText("scouting nearby areas");
                        Intent myIntent = new Intent(startingScreen.this, MapsActivity.class);
                        startingScreen.this.startActivity(myIntent);
                    }
                }
        );
        Button start_settings = (Button)findViewById(R.id.start_settings); //starting screen scout button
        start_settings.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        //TextView titleText = (TextView)findViewById(R.id.titleText);
                        //titleText.setText("scouting nearby areas");
                        Intent myIntent = new Intent(startingScreen.this, SettingsActivity.class);
                        startingScreen.this.startActivity(myIntent);
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_starting_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
