package lincy_ellermeijer.doggywalk;

/**
 * Created by lincypc on 8-3-2016.
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PlantImageAdapter extends ArrayAdapter<Plant>{
    Context context;
    ArrayList<Plant> data=new ArrayList<Plant>();
    private LayoutInflater inflater;
    private TextView title;
    private TextView description;
    private ImageView image;

    public PlantImageAdapter(Context context, int layoutResourceId, ArrayList<Plant> data) {
        super(context, layoutResourceId, data);
        inflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
            row = inflater.inflate(R.layout.screen_plantlist, parent, false);

            title = (TextView) row.findViewById(R.id.plant_title);
            description = (TextView) row.findViewById(R.id.plant_description);
            image = (ImageView) row.findViewById(R.id.plant_image);

        Plant plant = getItem(position);
        title.setText(plant._plantTitle);
        description.setText(plant._plantDescription);

        //convert byte to bitmap take from contact class
        byte[] outImage=plant._plantImage;
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        image.setImageBitmap(theImage);
        return row;
    }

}