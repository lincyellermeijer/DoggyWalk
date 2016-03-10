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

public class RouteListActivity extends AppCompatActivity {

    ArrayList<Route> imageArray = new ArrayList<Route>();
    RouteImageAdapter adapter;

    private DataSource datasource;
    private ListView listView;
    public static final String EXTRA_ROUTE_ID = "_extraRouteId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routelist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        datasource = new DataSource(this);
        listView = (ListView) findViewById(R.id.route_list);

        // If list is empty display text
        /*TextView emptyView = (TextView) findViewById(R.id.route_list_empty);
        listView.setEmptyView(emptyView);
        */

        adapter = new RouteImageAdapter(this, R.layout.screen_routelist,
                imageArray);

        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        // get image from drawable
        Bitmap image = BitmapFactory.decodeResource(getResources(),
                R.drawable.google_maps_icon);

        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte imageInByte[] = stream.toByteArray();
        /**
         * CRUD Operations
         * */
        // Manually inserting a route
        /*
        Log.d("Insert: ", "Inserting ..");
        datasource.addRoute(new Route("Amsterdamse Bos", "Losloopgebied, Amsterdam - Noord-Holland", imageInByte));
        */


        // Reading all routes from database
        List<Route> routes = datasource.getAllRoutes();
        for (Route rt : routes) {
            String log = "ID:" + rt.getID() + " Title: " + rt.getRouteTitle()
                    + " Description: " + rt.getRouteDescription() + " ,Image: " + rt.getRouteImage();

            // Writing Routes to log
            Log.d("Result: ", log);
            //add route data in arrayList
            imageArray.add(rt);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RouteListActivity.this, RouteDetailsActivity.class);
                intent.putExtra(EXTRA_ROUTE_ID, adapter.getItem(position).getID());
                startActivityForResult(intent, 2);
            }
        });

        // add fab for adding new items to the list
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddRouteActivity.class);
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
            Toast.makeText(getApplicationContext(), "Route deleted", Toast.LENGTH_LONG).show();
            Route route = adapter.getItem(info.position);
            adapter.remove(route);
            datasource.deleteRoute(route);

            updateRouteListView();
        } else {
            return false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check if the result code is the right one
        if (resultCode == RESULT_OK) {
            long routeId = data.getLongExtra(EXTRA_ROUTE_ID, -1);
            if (routeId != -1) {
                Route route = datasource.getRoute(routeId);
                adapter.add(route);
                updateRouteListView();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                updateRouteListView();

            }
        }
    }

    public void updateRouteListView() {
        List<Route> routes = datasource.getAllRoutes();
        adapter = new RouteImageAdapter(this, R.layout.screen_routelist,
                imageArray);
        listView.setAdapter(adapter);
    }

}