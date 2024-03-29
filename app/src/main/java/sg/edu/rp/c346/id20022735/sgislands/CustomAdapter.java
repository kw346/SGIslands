package sg.edu.rp.c346.id20022735.sgislands;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Island> verlist;

    public CustomAdapter(Context context, int resource, ArrayList<Island> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        verlist = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvname = rowView.findViewById(R.id.islandname);
        TextView tvdes = rowView.findViewById(R.id.islanddes);
        TextView tvdis = rowView.findViewById(R.id.distance);

        ImageView iv = rowView.findViewById(R.id.sticker);
        RatingBar rb = rowView.findViewById(R.id.ratingBar2);

        // Obtain the Android Version information based on the position
        Island currentVersion = verlist.get(position);

        // Set values to the TextView to display the corresponding information
        tvname.setText(currentVersion.getName());
        tvdes.setText(currentVersion.getDescription());
        rb.setRating(currentVersion.getStars());
        tvdis.setText(currentVersion.getDistance() + "");

        if (currentVersion.getDistance() > 4){
            iv.setImageResource(R.drawable.far);
        }
        else{
            iv.setVisibility(View.INVISIBLE);
        }
        return rowView;
    }
}
