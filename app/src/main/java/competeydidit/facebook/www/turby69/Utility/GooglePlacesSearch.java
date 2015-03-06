package competeydidit.facebook.www.turby69.Utility;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class GooglePlacesSearch {

    public static final String TAG = GooglePlacesSearch.class.getSimpleName();
    private static final String PLACES_SEARCH_URL =  "https://maps.googleapis.com/maps/api/place/textsearch/json?";
    private static final String API_KEY = "AIzaSyD4kwYTjSE-FRNIIhlJa_1phvI-JkRB73o";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public PlacesList performPlacesSearch(double latitude, double longitude) {
        HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
        PlacesList places = new PlacesList();
        try {
            HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(PLACES_SEARCH_URL));
            request.getUrl().put("key", API_KEY);
            request.getUrl().put("location", latitude + "," + longitude);
            request.getUrl().put("radius", 500);
            request.getUrl().put("query", "restaurant");

            places = request.execute().parseAs(PlacesList.class);    //TODO: fix this. maybe make GooglePlacesSearch an AsyncTask
            Log.i(TAG, places.status.toString());
            for (Place place : places.results) {
                Log.i(TAG, place.toString());
            }

        }
        catch (IOException e)
        {
            Log.i(TAG, "ERROR");
            Log.i(TAG, e.toString());
        }
        return places;
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
