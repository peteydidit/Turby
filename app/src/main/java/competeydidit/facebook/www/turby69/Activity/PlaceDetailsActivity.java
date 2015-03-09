package competeydidit.facebook.www.turby69.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import competeydidit.facebook.www.turby69.R;
import competeydidit.facebook.www.turby69.Utility.GooglePlacesDetailsTask;
import competeydidit.facebook.www.turby69.Utility.Place;
import competeydidit.facebook.www.turby69.Utility.PlacesList;

public class PlaceDetailsActivity extends ActionBarActivity {

    public static final String TAG = PlaceDetailsActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        Place place = (Place) getIntent().getSerializableExtra("place");
        try {
            PlacesList placesList = new GooglePlacesDetailsTask().execute(place.place_id).get();
            if (placesList.results.size() > 0)
            {
                place = placesList.results.iterator().next();
            }
        }
        catch(InterruptedException|ExecutionException e)
        {
            Log.d(TAG, "Error executing GooglePlacesDetailsTask");
        }

        TextView placeNameLabel = (TextView) findViewById(R.id.place_name);
        placeNameLabel.setText(place.name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_details, menu);
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
