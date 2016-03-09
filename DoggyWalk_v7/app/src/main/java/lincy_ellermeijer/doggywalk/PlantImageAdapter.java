package lincy_ellermeijer.doggywalk;

/**
 * Created by lincypc on 8-3-2016.
 */
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

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
    int layoutResourceId;
    ArrayList<Plant> data=new ArrayList<Plant>();

    public PlantImageAdapter(Context context, int layoutResourceId, ArrayList<Plant> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ImageHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.plant_title);
            holder.txtDescr = (TextView)row.findViewById(R.id.plant_description);
            holder.imgIcon = (ImageView)row.findViewById(R.id.plant_image);
            row.setTag(holder);
        }
        else
        {
            holder = (ImageHolder)row.getTag();
        }
        Plant picture = data.get(position);
        holder.txtTitle.setText(picture ._plantTitle);
        holder.txtDescr.setText(picture ._plantDescription);
        //convert byte to bitmap take from contact class
        byte[] outImage=picture._plantImage;
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imgIcon.setImageBitmap(theImage);
        return row;
    }
    static class ImageHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtDescr;
    }
}