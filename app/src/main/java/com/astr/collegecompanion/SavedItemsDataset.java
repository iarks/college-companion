package com.astr.collegecompanion;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Arkadeep Saha on 7/26/2016.
 */
public class SavedItemsDataset extends AppCompatActivity
{
    String head, date,body,key;
    int urgent;

    //default constructor
    public SavedItemsDataset()
    {
        head = null;
        date = null;
        body = null;
        key = null;
        urgent=1;
    }

    // parametrised constructor
    public SavedItemsDataset(String id, String head, String date, String body)
    {
        this.key = id;
        this.head = head;
        this.date = date;
        this.body = body;
    }

    // getting key
    public String getKey()
    {
        return this.key;
    }

    // setting key
    public void setKey(String key)
    {
        this.key = key;
    }

    // getting header
    public String getHeader()
    {
        return this.head;
    }

    // setting header
    public void setHeader(String head)
    {
        this.head = head;
    }

    // getting date
    public String getDate()
    {
        return this.date;
    }

    // setting date
    public void setDate(String date)
    {
        this.date = date;
    }

    // getting body
    public String getBody()
    {
        return this.body;
    }

    // setting body
    public void setBody(String body)
    {
        this.body = body;
    }

    // getting urgency of notice
    public int getUrgent()
    {
        return this.urgent;
    }

    // setting urgency of notice
    public void setUrgent(int urgent)
    {
        this.urgent = urgent;
    }
}