package com.astr.collegecompanion;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ActivitySub extends AppCompatActivity
{

    Boolean isFabOpen = false;
    Animation fab_open,fab_close,rotate_forward,rotate_backward;
    FloatingActionButton fab,fab1,fab2;

    String h,b,d;
    int u;

    TextView head,body,dated;

    String noticekey;

    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Notice");


        head=(TextView)findViewById(R.id.header);
        body=(TextView)findViewById(R.id.body);
        dated=(TextView)findViewById(R.id.date);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_later);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_event);

        fab.setOnClickListener(click_fab);
        fab1.setOnClickListener(click_fab_later);
        fab2.setOnClickListener(click_fab_event);

        Intent get_from_fragment = getIntent();
        h = get_from_fragment.getStringExtra("head");
        b =  get_from_fragment.getStringExtra("body");
        d =  get_from_fragment.getStringExtra("date");
        u = get_from_fragment.getIntExtra("urgent",1);

        head.setText(h);
        body.setText(b);
        dated.setText(d);
        noticekey = h.substring(0,1)+(Integer.toString(h.length()))+d.substring(0,1)+Integer.toString(d.length())+
                (b.substring(0,1))+(Integer.toString(h.length()));

        //defining animations
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        db = new DatabaseHandler(getApplicationContext());

        if(db.checkPresent(noticekey)==1)
        {
            fab1.setImageResource(R.drawable.ic_bookmark_white_48dp);
        }
        else
        {
            fab1.setImageResource(R.drawable.ic_bookmark_border_white_48dp);
        }
    }

    View.OnClickListener click_fab = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            animateFAB();
        }
    };

    View.OnClickListener click_fab_event = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Calendar cal = new GregorianCalendar();
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE, h);
            startActivity(intent);
        }
    };

    public void animateFAB()
    {

        if(isFabOpen)
        {

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        }
        else
        {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }

    @Override
    public void onBackPressed()
    {
        if (isFabOpen)
        {
            animateFAB();
        }
        else
        {
            super.onBackPressed();
        }
    }

    View.OnClickListener click_fab_later = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            addToBookmark();
        }
    };

    Void addToBookmark()
    {

        if(db.checkPresent(noticekey)==1)
        {

            fab1.setImageResource(R.drawable.ic_bookmark_border_white_48dp);
            Toast.makeText(getApplicationContext(),"Bookmarked Notice Deleted",Toast.LENGTH_SHORT).show();

            SavedItemsDataset deleteitem = new SavedItemsDataset();
            deleteitem.setKey(noticekey);
            deleteitem.setHeader(h);
            deleteitem.setDate(b);
            deleteitem.setBody(d);
            deleteitem.setUrgent(u);
            db.deleteItem(deleteitem);
        }
        else
        {
            fab1.setImageResource(R.drawable.ic_bookmark_white_48dp);
            Toast.makeText(getApplicationContext(),"Notice Bookmarked",Toast.LENGTH_SHORT).show();

            SavedItemsDataset newaddition = new SavedItemsDataset();
            newaddition.setKey(noticekey);
            newaddition.setHeader(h);
            newaddition.setDate(b);
            newaddition.setBody(d);
            newaddition.setUrgent(u);
            db.addItem(newaddition);
        }
        return null;
    }
}