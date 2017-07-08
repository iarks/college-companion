package com.astr.collegecompanion;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Tuhin Roy on 7/28/2016.
 */
public class AdapterDaySchedule extends ArrayAdapter<DayScheduleDatabase>
{
    Activity context;
    ArrayList<DayScheduleDatabase> day;

    //This is the constructor of the class
    public AdapterDaySchedule(Activity context, ArrayList<DayScheduleDatabase> objects)
    {
        super(context, R.layout.row_onclickday, objects);
        this.context = context;
        this.day = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(R.layout.row_onclickday,null,true);

        TextView txtsubjectname=(TextView)v.findViewById(R.id.txt_subjectname);
        TextView txttiming=(TextView)v.findViewById(R.id.txt_timing);
        TextView txtsubjectcode=(TextView)v.findViewById(R.id.txt_subjectcode);

        DayScheduleDatabase days = new DayScheduleDatabase();

        days = day.get(position);

        txtsubjectname.setText(days.subject_name);
        txttiming.setText(days.timing);
        txtsubjectcode.setText(days.subject_code);

        return v;
    }
}