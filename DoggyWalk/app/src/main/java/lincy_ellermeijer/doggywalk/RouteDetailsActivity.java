package lincy_ellermeijer.doggywalk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
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

    private DataSource datasource;
    private Route route;
    private EditText editTitleText;
    private EditText editDescriptionText;
    private Button updateButton;

    final String LOGTAG = "Test debug ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        datasource = new DataSource(this);
        int routeId = getIntent().getIntExtra(RouteListActivity.EXTRA_ROUTE_ID, -1);
        if (routeId != -1) {
            route = datasource.getRoute(routeId);

            TextView title = (TextView) findViewById(R.id.activity_details_title);
            title.setText(route.getRouteTitle().toString());

            TextView description = (TextView) findViewById(R.id.activity_details_description);
            description.setText(route.getRouteDescription().toString());

            ImageView image = (ImageView) findViewById(R.id.activity_details_icon);
            image.setImageResource(R.drawable.google_maps_icon);

            editTitleText = (EditText) findViewById(R.id.details_updateTitleText);
            editDescriptionText = (EditText) findViewById(R.id.details_updateDescriptionText);

            updateButton = (Button) findViewById(R.id.details_updateButton);

            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOGTAG, "button clicked");
                    // get image from drawable
                    Bitmap image = BitmapFactory.decodeResource(getResources(),
                            R.drawable.google_maps_icon);

                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte imageInByte[] = stream.toByteArray();

                    // Check if the title and descriptions have text
                    if (!TextUtils.isEmpty(editTitleText.getText()) && !TextUtils.isEmpty(editDescriptionText.getText())) {

                        route.setRouteTitle(editTitleText.getText().toString());
                        route.setRouteDescription(editDescriptionText.getText().toString());
                        route.setRouteImage(imageInByte);
                        datasource.updateRoute(route);
                        Toast.makeText(RouteDetailsActivity.this, "Route Updated", Toast.LENGTH_SHORT).show();

                        Intent resultIntent = new Intent();
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();

                    } else {
                        //Show a message to the user
                        Toast.makeText(RouteDetailsActivity.this, "Please enter some text in the title and description fields", Toast.LENGTH_LONG).show();
                    }

                }
            });

        }
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
