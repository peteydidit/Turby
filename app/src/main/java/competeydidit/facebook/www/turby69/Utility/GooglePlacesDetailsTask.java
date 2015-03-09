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

public class GooglePlacesDetailsTask extends AsyncTask<String, Integer, PlacesList> {

    public static final String TAG = GooglePlacesDetailsTask.class.getSimpleName();
    private static final String PLACES_DETAILS_URL =  "https://maps.googleapis.com/maps/api/place/details/json?";
    private static final String API_KEY = "AIzaSyD4kwYTjSE-FRNIIhlJa_1phvI-JkRB73o";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    @Override
    protected PlacesList doInBackground(String... params) {
        PlacesList placesList = new PlacesList();
        for (String param : params)
        {
            HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
            try {
                HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(PLACES_DETAILS_URL));
                request.getUrl().put("key", API_KEY);
                request.getUrl().put("placeid", param);

                PlaceDetails placeDetails = request.execute().parseAs(PlaceDetails.class);
                Log.i(TAG, placeDetails.status);
                /* possible status codes:
                    OK indicates that no errors occurred; the place was successfully detected and at least one result was returned.
                    UNKNOWN_ERROR indicates a server-side error; trying again may be successful.
                    ZERO_RESULTS indicates that the reference was valid but no longer refers to a valid result. This may occur if the establishment is no longer in business.
                    OVER_QUERY_LIMIT indicates that you are over your quota.
                    REQUEST_DENIED indicates that your request was denied, generally because of lack of an invalid key parameter.
                    INVALID_REQUEST generally indicates that the query (reference) is missing.
                    NOT_FOUND indicates that the referenced location was not found in the Places database.
                */
                if(placeDetails.status.equals("OK") || placeDetails.status.equals("ZERO_RESULTS"))
                {
                    placesList.results.add(placeDetails.result);
                }
                else
                {
                    Log.w(TAG, placeDetails.status);
                }
                placesList.status = placeDetails.status;
                Log.i(TAG, placeDetails.result.toString());
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

