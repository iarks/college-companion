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
public class AdapterClassSchedule extends ArrayAdapter<ClassScheduleDataset>
{
    Activity context;//?
    ArrayList<ClassScheduleDataset> products;//?

    //This is the constructor of the class
    public AdapterClassSchedule(Activity context, ArrayList<ClassScheduleDataset> objects)
    {
        /*The ArrayList<Products> basically defines an object named 'objects' of the ArrayList<Products> type
         *It contains the variables that have been wrapped in the Product class
         */
        super(context, R.layout.row_classschedule, objects);
        /*so now we need to pass these information to the base class so that it knows what we are up to
         *along with the name of the layout file that we intend to use for each row of the custom list
         *in a way by doing this we initialise the ArrayAdapter Class
         */
        this.context=context;
        this.products=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        /*Now that we have passed the required stuff to the CustomArrayAdapter Class, we need to tell it how
        * to arrange those stuff in our layout.
        * To do that we use the getView method*/

        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(R.layout.row_classschedule,null,true);
        /*Converts an XML file item_product into its Views*/

        /*So now that we have the layout views at hand, we can start
         *linking variables to the XML elements in that layout
         */
        TextView txtdayname=(TextView)v.findViewById(R.id.txt_dayname);

        //We use v.findViewById because v is the reference to the XML file item_product

        //since we are working with a class we have defined ourselves in this project, we need to define an object of the class
        ClassScheduleDataset p = new ClassScheduleDataset();


        p = products.get(position);//?

        //we initialise the layout items in the XML files by using the variables in class Products through the object of the class, namely p
        txtdayname.setText(p.dayname);

        //we return this completed view
        return v;
}
}
