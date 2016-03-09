package lincy_ellermeijer.doggywalk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddRouteActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;

    private DataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);
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
                // get image from drawable
                Bitmap image = BitmapFactory.decodeResource(getResources(),
                        R.drawable.google_maps_icon);

                // convert bitmap to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte imageInByte[] = stream.toByteArray();

                //Check if the title and descriptions have text
                if (!TextUtils.isEmpty(titleEditText.getText()) && !TextUtils.isEmpty(descriptionEditText.getText())) {

                    long routeId = datasource.addRoute(new Route(titleEditText.getText().toString(), descriptionEditText.getText().toString(), imageInByte));
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(RouteListActivity.EXTRA_ROUTE_ID, routeId);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                } else {
                    //Show a message to the user
                    Toast.makeText(AddRouteActivity.this, "Please enter some text in the title and description fields", Toast.LENGTH_LONG).show();
                }
            }
        });

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
