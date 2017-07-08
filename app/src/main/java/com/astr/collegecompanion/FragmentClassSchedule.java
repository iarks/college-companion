package com.astr.collegecompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentClassSchedule extends Fragment
{


    ArrayList<ClassScheduleDataset> days = new ArrayList<ClassScheduleDataset>();
    ListView lv;
    public FragmentClassSchedule()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        days.clear();

        //Adding Static data to the list views
        ClassScheduleDataset d1 = new ClassScheduleDataset();
        d1.dayname="Monday";
        days.add(d1);

        ClassScheduleDataset d2 = new ClassScheduleDataset();
        d2.dayname="Tuesday";
        days.add(d2);

        ClassScheduleDataset d3 = new ClassScheduleDataset();
        d3.dayname="Wednesday";
        days.add(d3);

        ClassScheduleDataset d4 = new ClassScheduleDataset();
        d4.dayname="Thursday";
        days.add(d4);

        ClassScheduleDataset d5 = new ClassScheduleDataset();
        d5.dayname="Friday";
        days.add(d5);

        ClassScheduleDataset d6 = new ClassScheduleDataset();
        d6.dayname="Saturday";
        days.add(d6);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_classschedule, container, false);

        //We define an object named p1 of the products class. We can use this to access its variables
        lv=(ListView)v.findViewById(R.id.lv_classschedule);
        AdapterClassSchedule adapter = new AdapterClassSchedule(getActivity(), days);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(item);

        return v;
    }

    AdapterView.OnItemClickListener item = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            ClassScheduleDataset day = days.get(position);
            Intent switch_to_next = new Intent(getActivity(),ActivityDaySchedule.class);
            switch_to_next.putExtra("dayname",day.dayname);
            startActivity(switch_to_next);
        }
    };

    @Override
    public void onResume()
    {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Class schedule");
    }
}
