package lincy_ellermeijer.doggywalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lincypc on 15-2-2016.
 */
public class DetailsActivity extends AppCompatActivity {

    /*private ImageView icon;
    private TextView title, description;
    */

    private DataSource datasource;
    private Plant plant;
    private EditText editTitleText;
    private EditText editDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Dit is oude details ding voor database

        //Initialize layout items
        icon = (ImageView) findViewById(R.id.activity_details_icon);
        title = (TextView) findViewById(R.id.activity_details_title);
        description = (TextView) findViewById(R.id.activity_details_description);

        //Get the values from the intent
        int imageResource = getIntent().getIntExtra("image-resource", 0);
        String titleString = getIntent().getStringExtra("title");
        String descriptionString = getIntent().getStringExtra("description");

        //Set the values from the intent to the views
        icon.setImageResource(imageResource);
        title.setText(titleString);
        description.setText(descriptionString);
    */

        datasource = new DataSource(this);
        long plantId = getIntent().getLongExtra(PlantListActivity.EXTRA_PLANT_ID, -1);
        plant = datasource.getPlant(plantId);

        TextView title = (TextView) findViewById(R.id.activity_details_title);
        title.setText(plant.getPlantTitle().toString());

        TextView textView = (TextView) findViewById(R.id.activity_details_description);
        textView.setText(plant.getPlantDescription().toString());

        editTitleText = (EditText) findViewById(R.id.details_updateTitleText);
        editDescriptionText = (EditText) findViewById(R.id.details_updateDescriptionText);

        Button updateButton = (Button) findViewById(R.id.details_updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plant.setPlantTitle(editTitleText.getText().toString());
                plant.setPlantDescription(editDescriptionText.getText().toString());
                datasource.updatePlant(plant);
                Toast.makeText(DetailsActivity.this, "Assignment Updated", Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

