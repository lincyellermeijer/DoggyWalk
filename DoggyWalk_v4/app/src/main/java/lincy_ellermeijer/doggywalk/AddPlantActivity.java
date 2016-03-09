package lincy_ellermeijer.doggywalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AddPlantActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;

    private DataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleEditText = (EditText) findViewById(R.id.new_item_title);
        descriptionEditText = (EditText) findViewById(R.id.new_item_description);

        datasource = new DataSource(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long plantId = datasource.createPlant(titleEditText.getText().toString(), descriptionEditText.getText().toString());
                Intent resultIntent = new Intent();
                resultIntent.putExtra(PlantListActivity.EXTRA_PLANT_ID, plantId);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });




        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if the title and descriptions have text
                if (!TextUtils.isEmpty(titleEditText.getText()) && !TextUtils.isEmpty(descriptionEditText.getText())) {

                //Create a new intent with the entered data
                Intent data = new Intent();
                data.putExtra("title", titleEditText.getText().toString());
                data.putExtra("description", descriptionEditText.getText().toString());
                data.putExtra("image-resource", R.drawable.plant_icon);

                //Send the result back to the activity
                setResult(Activity.RESULT_OK, data);

                //Finish this activity
                finish();
                } else {
                    //Show a message to the user
                    Toast.makeText(AddPlantActivity.this, "Please enter some text in the title and description fields", Toast.LENGTH_LONG).show();
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
