package competeydidit.facebook.www.turby69.Utility;

import java.util.HashSet;
import java.util.Set;

import com.google.api.client.util.Key;

public class PlacesList {

    @Key
    public String status;

    @Key
    public Set<Place> results;

    public PlacesList()
    {
        status = "";
        results = new HashSet<Place>();
    }
}
