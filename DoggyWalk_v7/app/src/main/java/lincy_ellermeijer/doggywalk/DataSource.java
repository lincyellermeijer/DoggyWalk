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
    //private String[] plantAllColumns = { MySQLiteHelper.COLUMN_PLANT_ID, MySQLiteHelper.COLUMN_PLANT_TITLE, MySQLiteHelper.COLUMN_PLANT_DESCRIPTION };
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

    // CREATE
    // adding plants
    public void addPlant (Plant plant) {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();

        // putting the values of the title, description and image
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_PLANT_TITLE, plant._plantTitle);
        values.put(dbHelper.COLUMN_PLANT_DESCRIPTION, plant._plantDescription);
        values.put(dbHelper.COLUMN_PLANT_IMAGE, plant._plantImage);

        // inserting values into table plants
        database.insert(dbHelper.TABLE_PLANTS, null, values);

        // If the database is open, close it
        if (database.isOpen())
            close();
    }

    // READ
    // Getting a single plant
    Plant getPlant(int id) {
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(dbHelper.TABLE_PLANTS, new String[]{dbHelper.COLUMN_PLANT_ID, dbHelper.COLUMN_PLANT_TITLE, dbHelper.COLUMN_PLANT_DESCRIPTION, dbHelper.COLUMN_PLANT_IMAGE}, dbHelper.COLUMN_PLANT_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Plant plant = new Plant(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(1), cursor.getBlob(1));

        // return plant
        return plant;

    }

    // Getting all plants
    public List<Plant> getAllPlants() {
        if (!database.isOpen())
            open();

        List<Plant> plantList = new ArrayList<Plant>();
        // Select all query
        String selectQuery = "SELECT * FROM plants ORDER BY plant_title";

        Cursor cursor = database.rawQuery(selectQuery, null);
        //Looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                Plant plant = new Plant();
                plant.setID(Integer.parseInt(cursor.getString(0)));
                plant.setPlantTitle(cursor.getString(1));
                plant.setPlantDescription(cursor.getString(2));
                plant.setPlantImage(cursor.getBlob(3));
                // Adding plant to list
                plantList.add(plant);
            } while (cursor.moveToNext());
        }
        // If the database is open, close it
        if (database.isOpen())
            close();
        // return plant list
        return plantList;

    }

    // UPDATE
    public int updatePlant(Plant plant) {
        if (!database.isOpen())
            open();

        // putting the values of the title, description and image
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_PLANT_TITLE, plant.getPlantTitle());
        values.put(dbHelper.COLUMN_PLANT_DESCRIPTION, plant.getPlantDescription());
        values.put(dbHelper.COLUMN_PLANT_IMAGE, plant.getPlantImage());

        // updating the database
        return database.update(dbHelper.TABLE_PLANTS, values, dbHelper.COLUMN_PLANT_ID + " = ?",
                new String[] { String.valueOf(plant.getID()) });

    }

     // DELETE
    public void deletePlant(Plant plant) {
        if (!database.isOpen())
            open();

        database.delete(dbHelper.TABLE_PLANTS, dbHelper.COLUMN_PLANT_ID + " = ?",
                new String[] { String.valueOf(plant.getID()) });

        if (database.isOpen())
            close();
    }



}