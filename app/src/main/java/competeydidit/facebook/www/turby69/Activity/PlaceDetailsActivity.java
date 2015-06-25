package competeydidit.facebook.www.turby69.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import competeydidit.facebook.www.turby69.R;
import competeydidit.facebook.www.turby69.Utility.DatabaseHandler;
import competeydidit.facebook.www.turby69.Utility.Day;
import competeydidit.facebook.www.turby69.Utility.Deal;
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
        DatabaseHandler dbh = new DatabaseHandler();
        List<Deal> dealList = dbh.getDealsFromGooglePlaceId(place.place_id);
        ListAdapter adapter = new ArrayAdapter<Deal>(this, android.R.layout.simple_list_item_1, dealList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

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

        ImageButton call = (ImageButton)findViewById(R.id.call); //call button
        call.setTag(place);
        call.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                Place place = (Place) v.getTag();
                String num = place.formatted_phone_number.replaceAll("\\s+|[(]|[)]","");
                callIntent.setData(Uri.parse("tel:" + num));
                startActivity(callIntent);
            }
        }
        );

        ImageButton directions = (ImageButton)findViewById(R.id.directions); //call button
        directions.setTag(place);
        directions.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Place place = (Place) v.getTag();
                String addr = place.formatted_address;
                Intent dirIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + addr + "&directionsmode=driving"));
                dirIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(dirIntent);
            }
        }
        );

        ImageButton web = (ImageButton)findViewById(R.id.website); //call button
        web.setTag(place);
        web.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Place place = (Place) v.getTag();
                String url = place.website;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        }
        );

        Button submitDeal = (Button)findViewById(R.id.submitDeal); //call button
        submitDeal.setTag(place);
        submitDeal.setOnClickListener(new Button.OnClickListener() {
                                   public void onClick(View v) {
                                       /*ParseObject testObject = new ParseObject("TestObject");
                                       testObject.put("foo", "bar");
                                       testObject.saveInBackground();*/
                                       Place place = (Place) v.getTag();
                                       DatabaseHandler dbh = new DatabaseHandler();
                                       Day[] strArray = {Day.MON, Day.TUES, Day.WED};
                                       dbh.saveDeal(new Deal("55", new Date(), 3, 1, place.place_id, new Date(),
                                               new Date(), 420, 500, strArray, true,
                                               false, "UF Student/UFID", "Test"));
                                   }
                               }
        );

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
