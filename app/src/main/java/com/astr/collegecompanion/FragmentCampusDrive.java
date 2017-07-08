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
public class FragmentCampusDrive extends Fragment
{
    ArrayList<CampusdriveDataset> placements = new ArrayList<CampusdriveDataset>();
    ListView lv;
    SwipeRefreshLayout swipeContainer;

    public FragmentCampusDrive()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        placements.clear();

        //Defining static data to be filled into the app
        CampusdriveDataset n1 = new CampusdriveDataset();
        n1.header="Pool campussing at Narula Institute of Technology";
        n1.date="25/07/08";
        n1.body="Students please take note that Pool campussing for the 2017 batch will be held at the Narula Intitute of Technology " +
                "on 15th August, 2017. Your respective departments have been notified, and you will be receiving details about the same " +
                "soon. In case of any questions please contact your departments.";
        n1.urgent=1;
        placements.add(n1);

        CampusdriveDataset n2 = new CampusdriveDataset();
        n2.header="Genpact recruitment on 27/08/2016";
        n2.date="24/07/2016";
        n2.urgent=1;
        n2.body="Students please take note that Genpact Inc. is visiting the college for recruitmnet on the 27th of August, 2016." +
                "Interested students can send in their CVs to the following mail ID by 29th July, 2016\nrandommailid@randomdomainname.com"+
                "\n\tIn case of any doubts regarding the matter, please visit Mrs. Priyanka Bagchi at the placement office.";
        placements.add(n2);

        CampusdriveDataset n3 = new CampusdriveDataset();
        n3.header="Updated CV";
        n3.date="21/07/2016";
        n3.urgent=0;
        n3.body="Students please sent in your updated CVs to the following mail id:\nrandomname@randomdomain.com";
        placements.add(n3);

        CampusdriveDataset n4 = new CampusdriveDataset();
        n4.header="Mock Test";
        n4.date="21/07/2016";
        n4.urgent=1;
        n4.body="Mock test for recruitment exams will be held at the college auditorium on 29th July, 2016 at 2:00pm.\nAll students " +
                "are advised to attend";
        placements.add(n4);

        CampusdriveDataset n5 = new CampusdriveDataset();
        n5.header="Group Discussion Mocks";
        n5.date="21/07/2016";
        n5.urgent=1;
        n5.body="Mock group discussion sessions for recruitment training will be held at the college auditorium on 25th July, 2016 at 2:00pm.\nAll students " +
                "are advised to attend";
        placements.add(n5);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_campusdrive, container, false);

        //We define an object named p1 of the products class. We can use this to access its variables
        lv=(ListView)v.findViewById(R.id.lv_campusdrive);

        AdapterCampusDrive adapter= new AdapterCampusDrive(getActivity(), placements);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(click_list);



        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipecontainer);// Setup refresh listener which triggers new data loading
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
            CampusdriveDataset obj = new CampusdriveDataset();
            obj = placements.get(position);
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
        actionBar.setTitle("Campus drive");
    }

}