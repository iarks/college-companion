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
public class FragmentDepartmentNews extends Fragment
{
    ArrayList<DepartmentNewsDataset> newsdetails = new ArrayList<DepartmentNewsDataset>();
    ListView lv;
    SwipeRefreshLayout swipeContainer;
    public FragmentDepartmentNews()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        newsdetails.clear();

        //adding static data to listview
        DepartmentNewsDataset n1 = new DepartmentNewsDataset();
        n1.header="NPTEL Course Registration";
        n1.date="24/07/2016";
        n1.urgent=0;
        n1.body="students are advised to register for the NPTEL courses online.";
        newsdetails.add(n1);

        DepartmentNewsDataset n2 = new DepartmentNewsDataset();
        n2.header="Schedule for verbal aptitude";
        n2.date="23/07/2016";
        n2.urgent=1;
        n2.body="The schedule for the verbal aptitude session organised by the department has been uploaded." +
                "The same can be found at the following link\nransomlink.randomdomain/randomfile.pdf";
        newsdetails.add(n2);

        DepartmentNewsDataset n3 = new DepartmentNewsDataset();
        n3.header="Training program from July 1, 2016";
        n3.date="23/07/2016";
        n3.urgent=0;
        n3.body="Training program organised by the department will begin from July 1." +
                "Any candidate that would like to register, but have not yet done so can submit their names to Mrs. Radhika Dey" +
                "by 26th July.";
        newsdetails.add(n3);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_departmentnews, container, false);

        //We define an object named p1 of the products class. We can use this to access its variables
        lv=(ListView)v.findViewById(R.id.lv_departmentnews);
        AdapterDepartmentNews adapter = new AdapterDepartmentNews(getActivity(), newsdetails);

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
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                swipeContainer.setRefreshing(false);
            }
        });

        return v;
    }

    AdapterView.OnItemClickListener click_list = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            DepartmentNewsDataset obj = new DepartmentNewsDataset();
            obj = newsdetails.get(position);
            Toast.makeText(getActivity(),obj.header,Toast.LENGTH_SHORT).show();

            Intent switch_to_sub = new Intent(getActivity(),ActivitySub.class);
            switch_to_sub.putExtra("head", obj.header);
            switch_to_sub.putExtra("body", obj.body);
            switch_to_sub.putExtra("date", obj.date);
            switch_to_sub.putExtra("urgent", obj.urgent);
            startActivity(switch_to_sub);
            //finish();
        }
    };

    @Override
    public void onResume()
    {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Department News");
    }
}