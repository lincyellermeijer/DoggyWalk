package lincy_ellermeijer.doggywalk;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lincy on 18-2-2016.
 */

public class PlantListActivity extends AppCompatActivity {

    private ListView listView;

    public static final String EXTRA_PLANT_ID = "_extraPlantId";

    private ArrayAdapter<Plant> plantArrayAdapter;
    private DataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.plant_list);
        TextView emptyView = (TextView) findViewById(R.id.plant_list_empty);
        listView.setEmptyView(emptyView);

        datasource = new DataSource(this);
        List<Plant> plants = datasource.getAllPlants();
        plantArrayAdapter = new ArrayAdapter<Plant>(this, android.R.layout.simple_list_item_1, plants);
        listView.setAdapter(plantArrayAdapter);

        registerForContextMenu(listView);

        /*items.add(new ListItem("Aloe Vera", "Symptoms: Vomiting, depression, diarrhea, anorexia, tremors, change in urine color.", R.drawable.plant_icon));
        items.add(new ListItem("Castor Bean", "Symptoms:  abdominal pain, drooling, vomiting, diarrhea, excessive thirst, weakness and loss of appetite. Severe cases of poisoning can result in dehydration, muscle twitching, tremors, seizures, coma and death", R.drawable.plant_icon));
        items.add(new ListItem("Tulip", "Symptoms:  Intense gastrointestinal irritation, drooling, loss of appetite, depression of the central nervous system, convulsions and cardiac abnormalities.", R.drawable.plant_icon));
        adapter.notifyDataSetChanged();
        */

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Create an Intent
                Intent intent = new Intent(PlantListActivity.this, DetailsActivity.class);

                ListItem clickedItem = (ListItem) parent.getItemAtPosition(position);
                intent.putExtra("title", clickedItem.getTitle());
                intent.putExtra("description", clickedItem.getDescription());
                intent.putExtra("image-resource", clickedItem.getImageResource());
                //Open the new screen by starting the activity
                startActivity(intent);
            }
        });
        */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddPlantActivity.class);
                startActivityForResult(intent, 1234);
            }
        });
    }


    // add fab for adding new items to the list

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
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        //Get the clicked item

        //Inflate the context menu from the resource file
        getMenuInflater().inflate(R.menu.context_menu, menu);

        //Let Android do its magic
        super.onCreateContextMenu(menu, view, menuInfo);
    }

    /*@Override
    public boolean onContextItemSelected(MenuItem item) {
        //Retrieve info about the long pressed list item
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.context_menu_delete_item) {
            //Remove the item from the list
            items.remove(itemInfo.position);

            //Update the adapter to reflect the list change
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }
    */

    // Dit weghalen als database goed werkt
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check if the result code is the right one
        if (resultCode == Activity.RESULT_OK) {
            //Check if the request code is correct
            if (requestCode == 1234) {
                //Everything's fine, get the values;
                String title = data.getStringExtra("title");
                String description = data.getStringExtra("description");
                int imageResource = data.getIntExtra("image-resource", 0);

                //Create a list item from the values
                ListItem item = new ListItem(title, description, imageResource);

                //Add the new item to the adapter;
                items.add(item);

                //Have the adapter update
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check if the result code is the right one
        if (resultCode == RESULT_OK) {
            long plantId = data.getLongExtra(EXTRA_PLANT_ID, -1);
            if (plantId != -1) {
                Plant plant = datasource.getPlant(plantId);
                plantArrayAdapter.add(plant);
                updatePlantListView();
            }
        }
    }

    public void updatePlantListView() {
        List<Plant> plants = datasource.getAllPlants();
        plantArrayAdapter = new ArrayAdapter<Plant>(this, android.R.layout.simple_list_item_1, plants);
        listView.setAdapter(plantArrayAdapter);
    }

}
