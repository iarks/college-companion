package com.astr.collegecompanion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    String universityroll;

    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent get_from_previous = getIntent();
        universityroll = get_from_previous.getStringExtra("universityroll");

        //initialising the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialising fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(click);//setting up an action listener for fab

        //initialising drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //initialising the navigation menu
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Static checking of login credentials
        if (universityroll.equals("130202022"))
        {
            View headerLayout = navigationView.getHeaderView(0);
            TextView txt_universityroll = (TextView)headerLayout.findViewById(R.id.txt_universityroll);
            TextView txt_name=(TextView)headerLayout.findViewById(R.id.txt_name);
            CircularImageView circularImageView = (CircularImageView)headerLayout.findViewById(R.id.img_profile);
            circularImageView.setImageResource(R.drawable.ic_as);
            circularImageView.setBorderWidth(5);
            txt_universityroll.setText(universityroll);
            txt_name.setText("ARKADEEP SAHA");
            year=4;
        }
        else if (universityroll.equals("130202111"))
        {
            View headerLayout = navigationView.getHeaderView(0);
            TextView txt_universityroll = (TextView)headerLayout.findViewById(R.id.txt_universityroll);
            TextView txt_name=(TextView)headerLayout.findViewById(R.id.txt_name);
            CircularImageView circularImageView = (CircularImageView)headerLayout.findViewById(R.id.img_profile);
            circularImageView.setImageResource(R.drawable.ic_tr);
            circularImageView.setBorderWidth(5);
            txt_universityroll.setText(universityroll);
            txt_name.setText("TUHIN ROY");
            year=3;
        }
        loadDefault();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    //Should be filled and implemented by the loaded fragment
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //choosing action
        if (id == R.id.action_feedback)
        {
            Intent open_email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "arkadeepsaha11@gmail.com"));
            open_email.putExtra(Intent.EXTRA_SUBJECT, "Hey, I've been using your app for some time, and have something to say");
            startActivity(open_email);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings ("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_class_schedule)
        {
            getSupportActionBar().setTitle("Class schedule");
            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            FragmentClassSchedule frag = new FragmentClassSchedule();
            trans.replace(R.id.main_content_frame,frag);
            trans.addToBackStack(null);
            trans.commit();
        }
        else if (id == R.id.nav_department_news)
        {
            getSupportActionBar().setTitle("Department News");
            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            FragmentDepartmentNews frag = new FragmentDepartmentNews();
            trans.replace(R.id.main_content_frame,frag);
            trans.addToBackStack(null);
            trans.commit();
        }
        else if (id == R.id.nav_general_notices)
        {
            getSupportActionBar().setTitle("General Notices");
            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            FragmentGeneralNotices frag = new FragmentGeneralNotices();
            trans.replace(R.id.main_content_frame,frag);
            trans.addToBackStack(null);
            trans.commit();
        }
        else if (id == R.id.nav_library)
        {
            getSupportActionBar().setTitle("Library");
            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            FragmentLibrary frag = new FragmentLibrary();
            trans.replace(R.id.main_content_frame,frag);
            trans.addToBackStack(null);
            trans.commit();
        }

        else if (id == R.id.nav_campus_drive)
        {

            if(year==4)
            {
                getSupportActionBar().setTitle("Campus drive");
                FragmentManager mgr = getSupportFragmentManager();
                FragmentTransaction trans = mgr.beginTransaction();
                FragmentCampusDrive frag = new FragmentCampusDrive();
                trans.replace(R.id.main_content_frame, frag);
                trans.addToBackStack(null);
                trans.commit();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"This section is available only for students of final year",Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.nav_events)
        {
            Calendar cal = new GregorianCalendar();
            cal.setTime(new Date());
            long time = cal.getTime().getTime();
            Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
            builder.appendPath("time");
            builder.appendPath(Long.toString(time));
            Intent intent = new Intent(Intent.ACTION_VIEW, builder.build());
            startActivity(intent);
        }
        else if (id == R.id.nav_saved_items)
        {
            getSupportActionBar().setTitle("Saved Items");
            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            FragmentSavedItems frag = new FragmentSavedItems();
            trans.replace(R.id.main_content_frame,frag);
            trans.addToBackStack("6");
            trans.commit();
        }
        else if (id == R.id.nav_settings)
        {
            Intent switch_to_settings = new Intent(getApplicationContext(),SettingsScreenActivity.class);
            startActivity(switch_to_settings);
        }
        else if (id == R.id.nav_about)
        {
            Intent switch_to_about = new Intent(getApplicationContext(), ActivityAbout.class);
            startActivity(switch_to_about);
        }
        else if (id == R.id.nav_logout)
        {
            SharedPreferences loginPreferences;
            SharedPreferences.Editor loginPrefsEditor;
            loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            loginPrefsEditor = loginPreferences.edit();
            loginPrefsEditor.putBoolean("saveLogin", false);
            loginPrefsEditor.putString("username", "");
            loginPrefsEditor.putString("password", "");
            loginPrefsEditor.commit();
            this.deleteDatabase("BookmarkedItems");
            Intent logout = new Intent(getApplicationContext(), ActivityLogin.class);
            startActivity(logout);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    View.OnClickListener click = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
            startActivity(intent);
        }
    };

    Void loadDefault()
    {
        FragmentManager mgr = getSupportFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        FragmentClassSchedule frag = new FragmentClassSchedule();
        trans.replace(R.id.main_content_frame,frag);
        trans.commit();
        return null;
    }
}
