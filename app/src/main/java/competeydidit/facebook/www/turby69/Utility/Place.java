package competeydidit.facebook.www.turby69.Utility;

import com.google.api.client.util.Key;

import java.io.Serializable;
import java.util.ArrayList;

public class Place implements Serializable {

    public Place()
    {
        reviews = new ArrayList<Review>();
    }

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
    public int price_level;

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

    @Key
    public String formatted_phone_number;

    @Key
    public String website;

    @Key
    public ArrayList<Review> reviews;

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

    public static class Review implements Serializable
    {
        @Key
        public String author_name;

        @Key
        public String text;

        @Key
        public int time;

        @Key
        public int rating;
    }
}
