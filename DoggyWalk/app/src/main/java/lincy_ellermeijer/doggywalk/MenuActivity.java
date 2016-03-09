package lincy_ellermeijer.doggywalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button1 = (Button) findViewById(R.id.routeButton);
        Button button2 = (Button) findViewById(R.id.plantButton);

        //Click this button to go to the list of routes
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this, RouteListActivity.class);
                startActivity(myIntent);

            }
        });

        //Click this button to go to the list of poisonous plants
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this, PlantListActivity.class);
                startActivity(myIntent);

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
}
