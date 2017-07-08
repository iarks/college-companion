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
public class AdapterGeneralNotices extends ArrayAdapter<GeneralNoticesDataset>
{
    Activity context;
    ArrayList<GeneralNoticesDataset> products;

    //This is the constructor of the class
    public AdapterGeneralNotices(Activity context, ArrayList<GeneralNoticesDataset> objects)
    {
        super(context, R.layout.row_generalnotices, objects);
        this.context=context;
        this.products=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(R.layout.row_generalnotices,null,true);

        TextView txtheader=(TextView)v.findViewById(R.id.txt_newsheader);
        TextView txtdate=(TextView)v.findViewById(R.id.txt_newsdate);
        TextView txtbody=(TextView)v.findViewById(R.id.txt_newsbody);
        ImageView urgency=(ImageView)v.findViewById(R.id.urgency);

        GeneralNoticesDataset notices = new GeneralNoticesDataset();
        notices = products.get(position);

        //we initialise the layout items in the XML files by using the variables in class Products through the object of the class, namely p
        txtheader.setText(notices.header);
        txtdate.setText(notices.date);
        txtbody.setText(notices.body);
        if(notices.urgent!=1)
        {
            urgency.setBackground(new ColorDrawable(Color.parseColor("#009688")));
        }
        //we return this completed view
        return v;
    }
}


