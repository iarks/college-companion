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
 * Created by Arkadeep Saha on 7/28/2016.
 */
public class AdapterSavedItems extends ArrayAdapter<SavedItemsDataset>
{
    Activity context;
    ArrayList<SavedItemsDataset> bookmarked;

    //This is the constructor of the class
    public AdapterSavedItems(Activity context, ArrayList<SavedItemsDataset> objects)
    {
        super(context, R.layout.row_saveditems, objects);
        this.context=context;
        this.bookmarked=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(R.layout.row_saveditems,null,true);

        TextView txtheader=(TextView)v.findViewById(R.id.txt_newsheader);
        TextView txtdate=(TextView)v.findViewById(R.id.txt_newsdate);
        TextView txtbody=(TextView)v.findViewById(R.id.txt_newsbody);
        ImageView urgency=(ImageView)v.findViewById(R.id.urgency);

        SavedItemsDataset bd = new SavedItemsDataset();
        bd = bookmarked.get(position);//?

        //we initialise the layout items in the XML files by using the variables in class Products through the object of the class, namely p
        txtheader.setText(bd.head);
        txtbody.setText(bd.date);
        txtdate.setText(bd.body);
       if(bd.urgent!=1)
        {
            urgency.setBackground(new ColorDrawable(Color.parseColor("#009688")));
        }

        //we return this completed view
        return v;
    }
}