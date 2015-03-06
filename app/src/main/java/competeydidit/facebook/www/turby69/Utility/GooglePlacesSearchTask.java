package competeydidit.facebook.www.turby69.Utility;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;

public class GooglePlacesSearchTask extends AsyncTask<GooglePlacesSearchParams, Integer, PlacesList> {

    public static final String TAG = GooglePlacesSearch.class.getSimpleName();
    private static final String PLACES_SEARCH_URL =  "https://maps.googleapis.com/maps/api/place/textsearch/json?";
    private static final String API_KEY = "AIzaSyD4kwYTjSE-FRNIIhlJa_1phvI-JkRB73o";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    @Override
    protected PlacesList doInBackground(GooglePlacesSearchParams... params) {
        PlacesList placesList = new PlacesList();
        for (GooglePlacesSearchParams param : params)
        {
            HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
            PlacesList places = new PlacesList();
            try {
                HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(PLACES_SEARCH_URL));
                request.getUrl().put("key", API_KEY);
                request.getUrl().put("location", param.getLocation().getLatitude() + "," + param.getLocation().getLongitude());
                request.getUrl().put("radius", 500);
                request.getUrl().put("query", param.getQuery());

                places = request.execute().parseAs(PlacesList.class);
                Log.i(TAG, places.status);
                if(places.status.equals("OK") || places.status.equals("ZERO_RESULTS"))
                {
                    placesList.results.addAll(places.results);  // exclude some types, ex. 'lodging'
                }
                for (Place place : places.results) {
                    Log.i(TAG, place.toString());
                }
            }
            catch (IOException e)
            {
                Log.i(TAG, "Error");
                Log.i(TAG, e.toString());
            }
        }
        return placesList;
    }

    public static HttpRequestFactory createRequestFactory(final HttpTransport transport) {

        return transport.createRequestFactory(new HttpRequestInitializer() {
            public void initialize(HttpRequest request) {
                HttpHeaders headers = new HttpHeaders();
                request.setHeaders(headers);
                JsonObjectParser parser = new JsonObjectParser(new JacksonFactory());
                request.setParser(parser);
            }
        });
    }
}

