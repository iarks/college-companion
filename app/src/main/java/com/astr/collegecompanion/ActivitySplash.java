package com.astr.collegecompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class ActivitySplash extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new mythread().execute();
    }

    class mythread extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Intent intent=new Intent(getApplicationContext(),ActivityLogin.class);
            startActivity(intent);
            finish();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            try
            {
                Thread.sleep(1500);
            }
            catch (Exception exp)
            {

            }
            return null;
        }
    }
}