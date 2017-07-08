package com.astr.collegecompanion;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tuhin Roy on 7/26/2016.
 */
public class AdapterLibrary extends ArrayAdapter<LibraryDataset>
{

    Activity context;
    ArrayList<LibraryDataset> products;


    public AdapterLibrary(Activity context, ArrayList<LibraryDataset> objects)
    {
        super(context, R.layout.row_library, objects);
        this.context=context;
        this.products=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(R.layout.row_library,null,true);

        TextView txtbookname=(TextView)v.findViewById(R.id.txt_bookname);
        TextView txtauthor=(TextView)v.findViewById(R.id.txt_author);
        TextView txtissuedate=(TextView) v.findViewById(R.id.txt_issuedate);
        TextView txtbookid=(TextView) v.findViewById(R.id.txt_bookid);

        LibraryDataset libAdapter = new LibraryDataset();
        libAdapter = products.get(position);

        txtbookname.setText(libAdapter.bookname);
        txtauthor.setText(libAdapter.author);
        txtissuedate.setText(libAdapter.issuedate);
        txtbookid.setText(libAdapter.bookid);

        return v;
    }
}