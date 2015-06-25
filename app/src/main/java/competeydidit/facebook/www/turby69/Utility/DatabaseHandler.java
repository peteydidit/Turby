package competeydidit.facebook.www.turby69.Utility;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DatabaseHandler {

    List<Deal> returnDeals;
    public static final String TAG = DatabaseHandler.class.getSimpleName();

    public DatabaseHandler() {
    }

    public List<Deal> getDealsFromGooglePlaceId(String googlePlaceId)
    {
        returnDeals = new ArrayList<Deal>();
        ParseQuery<ParseObject> dealQuery = ParseQuery.getQuery("Deal");
        dealQuery.whereEqualTo("google_places_id", googlePlaceId);
        dealQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> deals, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved " + deals.size() + " deals");
                    for (ParseObject deal : deals) returnDeals.add(parseDealObjectToDeal(deal));
                    //returnDeals = new ArrayList<Deal>(parseObjectListToDealList(deals));
                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
        try {
            Thread.sleep(500);
        }
        catch (Exception e)
        {

        }
        return returnDeals;
    }

    private List<Deal> parseObjectListToDealList(List<ParseObject> objects)
    {
        List<Deal> deals = new ArrayList<Deal>();
        for(ParseObject object : objects)
        {
            deals.add(parseDealObjectToDeal(object));
        }
        return deals;
    }

    private Deal parseDealObjectToDeal(ParseObject object)
    {
        String[] stringDays = object.getString("days").split(", ");
        Day[] enumDays = new Day[stringDays.length];
        for(int i = 0; i < stringDays.length; i++)
        {
            enumDays[i] = Day.valueOf(stringDays[i]);
        }
        return new Deal(object.getObjectId(), object.getCreatedAt(), object.getNumber("upvote_count").intValue(), object.getNumber("downvote_count").intValue(), object.getString("google_places_id"),
                object.getDate("start_day"), object.getDate("end_day"), object.getNumber("day_start_time").intValue(), object.getNumber("day_end_time").intValue(), enumDays,
                object.getBoolean("weekly_recurrence"), object.getBoolean("monthly_recurrence"), object.getString("deal_requirements"), object.getString("deal_descrip"));
    }

    public void saveDeal(Deal deal)
    {
        String stringDays = "";
        for(int i = 0; i < deal.getDays().length - 1; i++)
        {
            stringDays += deal.getDays()[i].toString() + ", ";
        }
        stringDays += deal.getDays()[deal.getDays().length - 1].toString();
        ParseObject po = new ParseObject("Deal");
        po.put("upvote_count", deal.getUpvoteCount());
        po.put("downvote_count", deal.getDownvoteCount());
        po.put("google_places_id", deal.getGooglePlacesId());
        po.put("start_day", deal.getStartDay());
        po.put("end_day", deal.getEndDay());
        po.put("day_start_time", deal.getDayStartTime());
        po.put("day_end_time", deal.getDayEndTime());
        po.put("days", stringDays);
        po.put("weekly_recurrence", deal.getWeeklyRecurrence());
        po.put("monthly_recurrence", deal.getMonthlyRecurrence());
        po.put("deal_requirements", deal.getRequirements());
        po.put("deal_descrip", deal.getDescription());
        po.saveInBackground();
        Log.d(TAG, "Saved deal.");
    }

}
