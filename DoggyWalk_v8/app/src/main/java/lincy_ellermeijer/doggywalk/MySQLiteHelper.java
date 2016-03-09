package lincy_ellermeijer.doggywalk;

/**
 * Created by lincypc on 7-3-2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Table
    public static final String TABLE_PLANTS = "plants";

    // Columns
    public static final String COLUMN_PLANT_ID = "plant_id";
    public static final String COLUMN_PLANT_TITLE = "plant_title";
    public static final String COLUMN_PLANT_DESCRIPTION = "plant_description";
    public static final String COLUMN_PLANT_IMAGE = "plant_image";

    // Database info
    private static final String DATABASE_NAME = "doggywalk.db";
    private static final int DATABASE_VERSION = 1;

    // Mandatory constructor which passes the context, database name and database version and passes it to the parent
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Creating the table
        String DATABASE_CREATE_PLANTS =
                "CREATE TABLE " + TABLE_PLANTS +
                        "(" +
                        COLUMN_PLANT_ID + " integer primary key," +
                        COLUMN_PLANT_TITLE + " text," +
                        COLUMN_PLANT_DESCRIPTION + " text," +
                        COLUMN_PLANT_IMAGE + " BLOB" +
                        ");";

        // Execute the sql to create the tables for plants and routes
        database.execSQL(DATABASE_CREATE_PLANTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	/*
     	* When the database gets upgraded you should handle the update to make sure there is no data loss.
     	* This is the default code you put in the upgrade method, to delete the table and call the oncreate again.
     	*/
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANTS);
        onCreate(db);
    }
}
