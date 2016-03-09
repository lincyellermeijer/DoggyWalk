package lincy_ellermeijer.doggywalk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lincy on 18-2-2016.
 */

public class PlantListActivity extends AppCompatActivity {

    ArrayList<Plant> imageArray = new ArrayList<Plant>();
    PlantImageAdapter adapter;

    private DataSource datasource;
    private ListView listView;
    public static final String EXTRA_PLANT_ID = "_extraPlantId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        datasource = new DataSource(this);
        listView = (ListView) findViewById(R.id.plant_list);

        adapter = new PlantImageAdapter(this, R.layout.screen_plantlist,
                imageArray);

        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        // get image from drawable
        Bitmap image = BitmapFactory.decodeResource(getResources(),
                R.drawable.plant_icon);

        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte imageInByte[] = stream.toByteArray();
        /**
         * CRUD Operations
         * */
        // Inserting Plants
        Log.d("Insert: ", "Inserting ..");
        datasource.addPlant(new Plant("Aloe Vera", "Symptoms: ...", imageInByte));

        // Reading all plants from database
        List<Plant> plants = datasource.getAllPlants();
        for (Plant pl : plants) {
            String log = "ID:" + pl.getID() + " Title: " + pl.getPlantTitle()
                    + " Description: " + pl.getPlantDescription() + " ,Image: " + pl.getPlantImage();

            // Writing Plants to log
            Log.d("Result: ", log);
            //add plants data in arrayList
            imageArray.add(pl);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PlantListActivity.this, PlantDetailsActivity.class);
                intent.putExtra(EXTRA_PLANT_ID, adapter.getItem(position).getID());
                startActivityForResult(intent, 2);
            }
        });

        // add fab for adding new items to the list
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddPlantActivity.class);
                startActivityForResult(intent, 1234);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Go to previous screen when app icon in action bar is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle() == "Delete") {
            Toast.makeText(getApplicationContext(), "Plant deleted", Toast.LENGTH_LONG).show();
            Plant plant = adapter.getItem(info.position);
            adapter.remove(plant);
            datasource.deletePlant(plant);

            updatePlantListView();
        } else {
            return false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check if the result code is the right one
        if (resultCode == RESULT_OK) {
            long plantId = data.getLongExtra(EXTRA_PLANT_ID, -1);
            if (plantId != -1) {
                Plant plant = datasource.getPlant(plantId);
                adapter.add(plant);
                updatePlantListView();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                updatePlantListView();

            }
        }
    }

    public void updatePlantListView() {
        List<Plant> plants = datasource.getAllPlants();
        adapter = new PlantImageAdapter(this, R.layout.screen_plantlist,
                imageArray);
        listView.setAdapter(adapter);
    }

}