package competeydidit.facebook.www.turby69.Utility;

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

    public static final String TAG = GooglePlacesSearchTask.class.getSimpleName();
    private static final String PLACES_SEARCH_URL =  "https://maps.googleapis.com/maps/api/place/textsearch/json?";
    private static final String API_KEY = "AIzaSyD4kwYTjSE-FRNIIhlJa_1phvI-JkRB73o";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    @Override
    protected PlacesList doInBackground(GooglePlacesSearchParams... params) {
        PlacesList placesList = new PlacesList();
        for (GooglePlacesSearchParams param : params)
        {
            HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
            try {
                HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(PLACES_SEARCH_URL));
                request.getUrl().put("key", API_KEY);
                request.getUrl().put("location", param.getLocation().getLatitude() + "," + param.getLocation().getLongitude());
                request.getUrl().put("radius", 500);
                request.getUrl().put("query", param.getQuery());

                PlacesList places = request.execute().parseAs(PlacesList.class);
                Log.i(TAG, places.status);

                /* possible status codes
                    OK indicates that no errors occurred; the place was successfully detected and at least one result was returned.
                    ZERO_RESULTS indicates that the search was successful but returned no results. This may occur if the search was passed a latlng in a remote location.
                    OVER_QUERY_LIMIT indicates that you are over your quota.
                    REQUEST_DENIED indicates that your request was denied, generally because of lack of an invalid key parameter.
                    INVALID_REQUEST generally indicates that a required query parameter (location or radius) is missing.
                */
                if(places.status.equals("OK") || places.status.equals("ZERO_RESULTS"))
                {
                    placesList.results.addAll(places.results);  // exclude some types, ex. 'lodging'
                }
                else
                {
                    Log.w(TAG, places.status);
                }
                for (Place place : places.results) {
                    Log.i(TAG, place.toString());
                }
            }
            catch (IOException e)
            {
                Log.e(TAG, "Error");
                Log.e(TAG, e.toString());
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

