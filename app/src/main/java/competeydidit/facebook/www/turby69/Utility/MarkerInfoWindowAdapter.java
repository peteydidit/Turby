package competeydidit.facebook.www.turby69.Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

import competeydidit.facebook.www.turby69.R;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;
    HashMap<Marker, Place> markerPlaceHashMap;

    public MarkerInfoWindowAdapter(Context context, HashMap<Marker, Place> markerPlaceHashMap)
    {
        this.context = context;
        this.markerPlaceHashMap = markerPlaceHashMap;
    }

    @Override
    public View getInfoWindow(Marker marker)
    {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker)
    {
        View v  = LayoutInflater.from(context).inflate(R.layout.infowindow, null);
        Place place = markerPlaceHashMap.get(marker);

        //ImageView markerIcon = (ImageView) v.findViewById(R.id.marker_icon);
        TextView markerLabel = (TextView)v.findViewById(R.id.place_name);
        //markerIcon.setImageResource(manageMarkerIcon(myMarker.getmIcon()));
        markerLabel.setText(place.name);

        return v;
    }
}
