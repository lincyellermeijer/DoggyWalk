package lincy_ellermeijer.doggywalk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by lincypc on 15-2-2016.
 */
public class RouteDetailsActivity extends AppCompatActivity {

    private ImageView icon;
    private TextView title, description;

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

       /* //Initialize layout items
        ImageView image = (ImageView) findViewById(R.id.activity_details_icon);
        plantTitle = (TextView) findViewById(R.id.activity_details_title);
        plantDescription = (TextView) findViewById(R.id.activity_details_description);

        //Get the values from the intent
        int imageResource = getIntent().getIntExtra("image-resource", 0);
        String titleString = getIntent().getStringExtra("title");
        String descriptionString = getIntent().getStringExtra("description");

        //Set the values from the intent to the views
        icon.setImageResource(imageResource);
        title.setText(titleString);
        description.setText(descriptionString);


        datasource = new DataSource(this);
        long plantId = getIntent().getLongExtra(PlantListActivity.EXTRA_PLANT_ID, -1);
        plant = datasource.getPlant(plantId);

        TextView title = (TextView) findViewById(R.id.activity_details_title);
        title.setText(plant.getPlantTitle().toString());

        TextView description = (TextView) findViewById(R.id.activity_details_description);
        description.setText(plant.getPlantDescription().toString());
        
        ImageView image = (ImageView) findViewById(R.id.activity_details_icon);
        image.setImageResource(R.drawable.plant_icon);
        
        editTitleText = (EditText) findViewById(R.id.details_updateTitleText);
        editDescriptionText = (EditText) findViewById(R.id.details_updateDescriptionText);

        datasource = new DataSource(this);

        Button updateButton = (Button) findViewById(R.id.details_updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get image from drawable
                Bitmap image = BitmapFactory.decodeResource(getResources(),
                        R.drawable.plant_icon);

                // convert bitmap to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte imageInByte[] = stream.toByteArray();

                // Check if the title and descriptions have text
                if (!TextUtils.isEmpty(editTitleText.getText()) && !TextUtils.isEmpty(editDescriptionText.getText())) {

                    long plantId = datasource.addPlant(new Plant(editTitleText.getText().toString(), editDescriptionText.getText().toString(), imageInByte));
                    datasource.updatePlant(plant);
                    Toast.makeText(DetailsActivity.this, "Assignment Updated", Toast.LENGTH_SHORT).show();

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(PlantListActivity.EXTRA_PLANT_ID, plantId);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();

                } else {
                    //Show a message to the user
                    Toast.makeText(DetailsActivity.this, "Please enter some text in the title and description fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        */

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
