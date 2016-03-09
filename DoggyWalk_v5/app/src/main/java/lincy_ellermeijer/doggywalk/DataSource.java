package lincy_ellermeijer.doggywalk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lincypc on 7-3-2016.
 */
public class DataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] plantAllColumns = { MySQLiteHelper.COLUMN_PLANT_ID, MySQLiteHelper.COLUMN_PLANT_TITLE, MySQLiteHelper.COLUMN_PLANT_DESCRIPTION };
    //private String[] routeAllColumns = { MySQLiteHelper.COLUMN_ROUTE_ID, MySQLiteHelper.COLUMN_ROUTE };


    public DataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
        dbHelper.close();
    }

    // Opens the database to use it
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Closes the database when you no longer need it
    public void close() {
        dbHelper.close();
    }

    // CREATE plants
    public long createPlant(String plantTitle, String plantDescription) {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_PLANT_TITLE, plantTitle);
        values.put(MySQLiteHelper.COLUMN_PLANT_DESCRIPTION, plantDescription);
        long insertId = database.insert(MySQLiteHelper.TABLE_PLANTS, null, values);

        // If the database is open, close it
        if (database.isOpen())
            close();
        return insertId;
    }

    // DELETE
    public void deletePlant(Plant plant) {
        if (!database.isOpen())
            open();

        database.delete(MySQLiteHelper.TABLE_PLANTS, MySQLiteHelper.COLUMN_PLANT_ID + " =?", new String[]{Long.toString(plant.getId())});

        if (database.isOpen())
            close();
    }

    // UPDATE
    public void updatePlant(Plant plant) {
        if (!database.isOpen())
            open();

        ContentValues args = new ContentValues();
        args.put(MySQLiteHelper.COLUMN_PLANT_TITLE, plant.getPlantTitle());
        args.put(MySQLiteHelper.COLUMN_PLANT_DESCRIPTION, plant.getPlantDescription());
        database.update(MySQLiteHelper.TABLE_PLANTS, args, MySQLiteHelper.COLUMN_PLANT_ID + "=?", new String[]{Long.toString(plant.getId())});
        if (database.isOpen())
            close();
    }

    public List<Plant> getAllPlants() {
        if (!database.isOpen())
            open();

        List<Plant> plants = new ArrayList<Plant>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PLANTS, plantAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Plant plant = cursorToPlant(cursor);
            plants.add(plant);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        if (database.isOpen())
            close();

        return plants;
    }

    private Plant cursorToPlant(Cursor cursor) {
        try {
            Plant plant = new Plant();
            plant.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_PLANT_ID)));
            plant.setPlantTitle(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_PLANT_TITLE)));
            plant.setPlantDescription(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_PLANT_DESCRIPTION)));
            return plant;
        } catch(CursorIndexOutOfBoundsException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Plant getPlant(long columnId) {
        if (!database.isOpen())
            open();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PLANTS, plantAllColumns, MySQLiteHelper.COLUMN_PLANT_ID + "=?", new String[] { Long.toString(columnId)}, null, null, null);

        cursor.moveToFirst();
        Plant plant = cursorToPlant(cursor);
        cursor.close();

        if (database.isOpen())
            close();

        return plant;
    }


}