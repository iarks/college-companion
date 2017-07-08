package com.astr.collegecompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityDaySchedule extends AppCompatActivity
{
    ListView lv_onclickday;
    ArrayList<DayScheduleDatabase> subjects=new ArrayList<DayScheduleDatabase>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        lv_onclickday=(ListView)findViewById(R.id.lv_onclickday);

        //Adding static data to ListView
        DayScheduleDatabase n1 = new DayScheduleDatabase();
        n1.subject_name="Microwave Engineering";
        n1.subject_code="EC 601";
        n1.timing="10:15am-11:05am";
        subjects.add(n1);

        DayScheduleDatabase n2 = new DayScheduleDatabase();
        n2.subject_name="Digital Signal Processing";
        n2.subject_code="EC 602";
        n2.timing="11:05am-11:55am";
        subjects.add(n2);

        DayScheduleDatabase n3 = new DayScheduleDatabase();
        n3.subject_name="Computer Networking";
        n3.subject_code="EC 603";
        n3.timing="11:55am-12:45pm";
        subjects.add(n3);

        DayScheduleDatabase n4 = new DayScheduleDatabase();
        n4.subject_name="VLSI design";
        n4.subject_code="EC 604";
        n4.timing="12:45pm-1:30pm";
        subjects.add(n4);

        DayScheduleDatabase n5 = new DayScheduleDatabase();
        n5.subject_name="BREAK";
        n5.subject_code="-";
        n5.timing="1:30pm-2:10pm";
        subjects.add(n5);

        DayScheduleDatabase n6 = new DayScheduleDatabase();
        n6.subject_name="Financial Marketing";
        n6.subject_code="HU 681";
        n6.timing="2:10pm-3:00pm";
        subjects.add(n6);

        DayScheduleDatabase n7 = new DayScheduleDatabase();
        n7.subject_name="Electronics Measurement & Instrumentation";
        n7.subject_code="EC 605";
        n7.timing="3:00pm-3:50pm";
        subjects.add(n7);

        DayScheduleDatabase n8 = new DayScheduleDatabase();
        n8.subject_name="(Lab)Digital Signal Processing";
        n8.subject_code="EC 692";
        n8.timing="3:50pm-4:40pm";
        subjects.add(n8);

        DayScheduleDatabase n9 = new DayScheduleDatabase();
        n9.subject_name="(Lab)Digital Signal Processing";
        n9.subject_code="EC 692";
        n9.timing="4:40pm-5:30pm";
        subjects.add(n9);

        AdapterDaySchedule adapter = new AdapterDaySchedule(ActivityDaySchedule.this,subjects);

        lv_onclickday.setAdapter(adapter);

        Intent get_from_fragments = getIntent();
        String appbar=get_from_fragments.getStringExtra("dayname");
        getSupportActionBar().setTitle(appbar+"'s schedule");
    }
}