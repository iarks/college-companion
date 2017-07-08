package com.astr.collegecompanion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLibrary extends Fragment
{


    ArrayList<LibraryDataset> library_info = new ArrayList<LibraryDataset>();
    ListView lv;
    SwipeRefreshLayout swipeContainer;

    public FragmentLibrary()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        library_info.clear();

        //Putting static data into list view
        LibraryDataset lb1 = new LibraryDataset();
        lb1.bookid="130212";
        lb1.bookname="Antennas and wave propagation";
        lb1.author="V Harish, A.R. Sachidananda";
        lb1.issuedate="17/03/2016";
        library_info.add(lb1);

        LibraryDataset lb2 = new LibraryDataset();
        lb2.bookid="132451";
        lb2.bookname="Principles of Electromagnetic field theory";
        lb2.author="Matthew N.O. Sadiku";
        lb2.issuedate="21/03/2016";
        library_info.add(lb2);

        LibraryDataset lb3 = new LibraryDataset();
        lb3.bookid="128547";
        lb3.bookname="Microwave Engineering";
        lb3.author="David M. Pozar";
        lb3.issuedate="15/04/2016";
        library_info.add(lb3);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_library, container, false);

        //We define an object named p1 of the products class. We can use this to access its variables
        lv=(ListView)v.findViewById(R.id.lv_library);
        AdapterLibrary adapter = new AdapterLibrary(getActivity(),library_info);

        lv.setAdapter(adapter);

        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipecontainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                //perform async task
                swipeContainer.setRefreshing(false);
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Library");
    }
}
