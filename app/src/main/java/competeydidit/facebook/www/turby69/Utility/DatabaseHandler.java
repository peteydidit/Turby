package competeydidit.facebook.www.turby69.Utility;

/**
 * Created by petey on 3/23/2015.
 */

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.net.Uri;

        import java.util.ArrayList;
        import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "dealManager",
            TABLE_DEALS = "deals",
            KEY_ID = "id",
            KEY_NAME = "name",
            KEY_PHONE = "phone",
            KEY_EMAIL = "email",
            KEY_ADDRESS = "address",
            KEY_IMAGEURI = "imageUri";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_DEALS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_PHONE + " TEXT," + KEY_EMAIL + " TEXT," + KEY_ADDRESS + " TEXT," + KEY_IMAGEURI + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEALS);

        onCreate(db);
    }

    public void createDeal(Deal deal) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, deal.getName());
        values.put(KEY_PHONE, deal.getPhone());
        values.put(KEY_EMAIL, deal.getEmail());
        values.put(KEY_ADDRESS, deal.getAddress());
        values.put(KEY_IMAGEURI, deal.getImageURI().toString());

        db.insert(TABLE_DEALS, null, values);
        db.close();
    }

    public Deal getDeal(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_DEALS, new String[] { KEY_ID, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_ADDRESS, KEY_IMAGEURI }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null );

        if (cursor != null)
            cursor.moveToFirst();

        Deal deal = new Deal(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), Uri.parse(cursor.getString(5)));
        db.close();
        cursor.close();
        return deal;
    }

    public void deleteDeal(Deal deal) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_DEALS, KEY_ID + "=?", new String[] { String.valueOf(deal.getId()) });
        db.close();
    }

    public int getDealsCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DEALS, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }

    public int updateDeal(Deal deal) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, deal.getName());
        values.put(KEY_PHONE, deal.getPhone());
        values.put(KEY_EMAIL, deal.getEmail());
        values.put(KEY_ADDRESS, deal.getAddress());
        values.put(KEY_IMAGEURI, deal.getImageURI().toString());

        int rowsAffected = db.update(TABLE_DEALS, values, KEY_ID + "=?", new String[] { String.valueOf(deal.getId()) });
        db.close();

        return rowsAffected;
    }

    public List<Deal> getAllDeals() {
        List<Deal> deals = new ArrayList<Deal>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DEALS, null);

        if (cursor.moveToFirst()) {
            do {
                deals.add(new Deal(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), Uri.parse(cursor.getString(5))));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return deals;
    }
}
