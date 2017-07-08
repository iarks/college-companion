package com.astr.collegecompanion;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tuhin Roy on 7/26/2016.
 */
public class AdapterCampusDrive extends ArrayAdapter<CampusdriveDataset>
{
    Activity context;
    ArrayList<CampusdriveDataset> campussingnotice;

    //This is the constructor of the class
    public AdapterCampusDrive(Activity context, ArrayList<CampusdriveDataset> objects)
    {
        super(context, R.layout.row_campusdrive, objects);
        /*so now we need to pass these information to the base class so that it knows what we are up to
         *along with the name of the layout file that we intend to use for each row of the custom list
         *in a way by doing this we initialise the ArrayAdapter Class
         */
        this.context=context;
        this.campussingnotice=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(R.layout.row_campusdrive,null,true);

        TextView txtheader=(TextView)v.findViewById(R.id.txt_newsheader);
        TextView txttime=(TextView)v.findViewById(R.id.txt_newsdate);
        TextView txtbody = (TextView)v.findViewById(R.id.txt_newsbody);
        ImageView urgency=(ImageView)v.findViewById(R.id.urgency);
        //We use v.findViewById because v is the reference to the XML file item_product

        //since we are working with a class we have defined ourselves in this project, we need to define an object of the class
        CampusdriveDataset campussingnotice_adapter = new CampusdriveDataset();

        campussingnotice_adapter = campussingnotice.get(position);//?

        txtheader.setText(campussingnotice_adapter.header);
        txttime.setText(campussingnotice_adapter.date);
        txtbody.setText(campussingnotice_adapter.body);

        if(campussingnotice_adapter.urgent!=1)
        {
            urgency.setBackground(new ColorDrawable(Color.parseColor("#009688")));
        }

        //we return this completed view
        return v;
    }
}