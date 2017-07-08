package com.astr.collegecompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Arkadeep Saha on 7/27/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper
{
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "BookmarkedItems";
    // Contacts table name
    private static final String TABLE_SAVED = "saved_items";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_HEADER = "header";
    private static final String KEY_DATE = "date";
    private static final String KEY_BODY = "body";
    private static final String KEY_URGENT = "urgent";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_SAVED + "(" + KEY_ID + " VARCHAR(20) PRIMARY KEY," + KEY_HEADER + " TEXT," + KEY_DATE + " TEXT," + KEY_BODY + " TEXT," + KEY_URGENT + " INTEGER" +")";
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED);

        // Create tables again
        onCreate(db);
    }

    public ArrayList<SavedItemsDataset> getAll()
    {
        ArrayList<SavedItemsDataset> list = new ArrayList<SavedItemsDataset>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SAVED;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                SavedItemsDataset saved_item = new SavedItemsDataset();
                saved_item.setKey((cursor.getString(0)));
                saved_item.setHeader(cursor.getString(1));
                saved_item.setDate(cursor.getString(2));
                saved_item.setBody(cursor.getString(3));
                saved_item.setUrgent(cursor.getInt(4));
                // Adding contact to list
                list.add(saved_item);
            } while (cursor.moveToNext());
        }
        db.close();
        // return contact list
        return list;
    }

    public void deleteItem(SavedItemsDataset data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SAVED, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getKey()) });
        db.close();
    }

    // Adding new item
    public void addItem(SavedItemsDataset data)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, data.getKey());
        values.put(KEY_HEADER, data.getHeader());
        values.put(KEY_DATE, data.getDate());
        values.put(KEY_BODY, data.getBody());
        values.put(KEY_URGENT, data.getUrgent());

        // Inserting Row
        db.insert(TABLE_SAVED, null, values);
        db.close(); // Closing database connection
    }

    public int checkPresent(String k)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_SAVED;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                String x =cursor.getString(0);
                if (x.equals(k))
                {
                    db.close();
                    return 1;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return 0;
    }
}