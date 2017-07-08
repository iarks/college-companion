package com.astr.collegecompanion;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGeneralNotices extends Fragment
{
    ArrayList<GeneralNoticesDataset> newsdetails = new ArrayList<GeneralNoticesDataset>();
    ListView lv;
    SwipeRefreshLayout swipeContainer;
    public FragmentGeneralNotices()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        newsdetails.clear();

        //Putting static data in the
        GeneralNoticesDataset n1 = new GeneralNoticesDataset();
        n1.header="TCS Codevita contest registration";
        n1.date="27/07/2016";
        n1.body="Students are advised to register for the upcoming Code Vita competition organised by Tata Consultancy Services." +
                "Please visit the following link for mode details." +
                "\n\nbitly.in/codevita5";
        n1.urgent=0;
        newsdetails.add(n1);

        GeneralNoticesDataset n2 = new GeneralNoticesDataset();
        n2.header="Revised rules & regulation for fee payment";
        n2.date="25/07/2015";
        n2.urgent=1;
        n2.body="Students please note that the rules and regulations regarding fee payment has been updated." +
                "The revised ruled and regulations may be downloaded from the following link." +
                "\nrandomlink.randomdomain/randomfilename.pdf";
        newsdetails.add(n2);

        GeneralNoticesDataset n3 = new GeneralNoticesDataset();
        n3.header="Holiday on 1st August";
        n3.date="21/07/2015";
        n3.urgent=0;
        n3.body="Note that college will remain closed on the 1st of August. Library and offices will remain open till 2:00pm";
        newsdetails.add(n3);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_generalnotices, container, false);

        //We define an object named p1 of the products class. We can use this to access its variables
        lv=(ListView)v.findViewById(R.id.lv_generalnotices);
        AdapterGeneralNotices adapter = new AdapterGeneralNotices(getActivity(),newsdetails);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(click_list);

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

    AdapterView.OnItemClickListener click_list = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            GeneralNoticesDataset obj = new GeneralNoticesDataset();
            obj = newsdetails.get(position);
            Toast.makeText(getActivity(),obj.header,Toast.LENGTH_SHORT).show();

            Intent switch_to_sub = new Intent(getActivity(),ActivitySub.class);
            switch_to_sub.putExtra("head", obj.header);
            switch_to_sub.putExtra("body", obj.body);
            switch_to_sub.putExtra("date", obj.date);
            switch_to_sub.putExtra("urgent", obj.urgent);
            startActivity(switch_to_sub);
        }
    };

    @Override
    public void onResume()
    {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("General Notices");
    }
}