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
 * Created by Arkadeep Saha on 7/25/2016.
 */
public class AdapterDepartmentNews extends ArrayAdapter<DepartmentNewsDataset>
{
    /*When the class CustomProductAdapter, our newly created class,
    extends class ArrayAdapter<Products>, CustomProductAdapter
    automatically has all variables and methods defined in class ArrayAdapter class
    (except private variable and methods). This means that we can modify the override function in ArrayAdapter class
    as we prefer to customize it.
    */

    Activity context;//?
    ArrayList<DepartmentNewsDataset> products;//?

    //This is the constructor of the class
    public AdapterDepartmentNews(Activity context, ArrayList<DepartmentNewsDataset> objects)
    {
        /*The ArrayList<Products> basically defines an object named 'objects' of the ArrayList<Products> type
         *It contains the variables that have been wrapped in the Product class
         */
        super(context, R.layout.row_deparmentnews, objects);
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
        View v=inflater.inflate(R.layout.row_deparmentnews,null,true);
        /*Converts an XML file item_product into its Views*/

        /*So now that we have the layout views at hand, we can start
         *linking variables to the XML elements in that layout
         */
        TextView txtheader=(TextView)v.findViewById(R.id.txt_newsheader);
        TextView txtdate=(TextView)v.findViewById(R.id.txt_newsdate);
        TextView txtbody=(TextView)v.findViewById(R.id.txt_newsbody);
        ImageView urgency=(ImageView)v.findViewById(R.id.urgency);
        //We use v.findViewById because v is the reference to the XML file item_product

        //since we are working with a class we have defined ourselves in this project, we need to define an object of the class
        DepartmentNewsDataset p = new DepartmentNewsDataset();


        p = products.get(position);//?

        //we initialise the layout items in the XML files by using the variables in class Products through the object of the class, namely p
        txtheader.setText(p.header);
        txtdate.setText(p.date);
        txtbody.setText(p.body);
        if(p.urgent!=1)
        {
            urgency.setBackground(new ColorDrawable(Color.parseColor("#009688")));
        }
        //we return this completed view
        return v;
    }
}
