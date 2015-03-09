package competeydidit.facebook.www.turby69.Utility;

import com.google.api.client.util.Key;

import java.io.Serializable;

public class Place implements Serializable {

    @Key
    public String status;

    @Key
    public String name;

    @Key
    public String id;

    @Key
    public String place_id;

    @Key
    public String reference;

    @Key
    public String icon;

    @Key
    public double rating;

    @Key
    public String vicinity;

    @Key
    public Geometry geometry;

    @Key
    public String formatted_address;

    @Override
    public String toString()
    {
        return name;
    }

    public static class Geometry implements Serializable
    {
        @Key
        public Location location;
    }

    public static class Location implements Serializable
    {
        @Key
        public double lat;

        @Key
        public double lng;
    }
}
