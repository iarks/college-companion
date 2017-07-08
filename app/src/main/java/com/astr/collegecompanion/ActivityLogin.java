package com.astr.collegecompanion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class ActivityLogin extends AppCompatActivity
{
    private EditText etxt_university_roll, etxt_password;
    private Button btn_sign_in;
    private CheckBox ch_show_password, ch_remember_me;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        etxt_password = (EditText) findViewById(R.id.etxt_password);
        etxt_university_roll = (EditText) findViewById(R.id.etxt_university_roll);

        //set up clickable objects
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);

        //set up check boxes
        ch_remember_me = (CheckBox) findViewById(R.id.ch_remember_me);
        ch_show_password = (CheckBox) findViewById(R.id.ch_show_password);

        //Fetching shared preferences
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        saveLogin = loginPreferences.getBoolean("saveLogin", false);



        //checking is Shared preference is present
        if (saveLogin)//if present then perform auto login
        {
            etxt_university_roll.setText(loginPreferences.getString("username", ""));
            etxt_password.setText(loginPreferences.getString("password", ""));
            ch_remember_me.setChecked(true);
            attemptLogin();
        }

        //else register listeners for the clickable, checkable items and text UI objects
        try
        {
            etxt_password.addTextChangedListener(watch_password);
            etxt_university_roll.addTextChangedListener(watch_universityroll);

            btn_sign_in.setOnClickListener(click_sign_in);

            ch_show_password.setOnCheckedChangeListener(check_show_password);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    View.OnClickListener click_sign_in = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if (etxt_university_roll.getText().toString().equals(""))
            {
                etxt_university_roll.setError("Required Field");
                etxt_university_roll.requestFocus();
            }
            else if (etxt_university_roll.getText().toString().length()!=9)
            {
                etxt_university_roll.setError("Invalid Roll");
                etxt_university_roll.requestFocus();
            }
            else if (etxt_password.getText().toString().equals(""))
            {
                etxt_password.setError("Required Field");
                etxt_university_roll.requestFocus();
            }
            else
            {
                attemptLogin();
            }
        }
    };

    TextWatcher watch_password = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            if (etxt_password.getText().toString().equals(""))
            {
                etxt_password.setError("Required Field");
                etxt_password.requestFocus();
            }
        }
    };

    TextWatcher watch_universityroll = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            if (etxt_university_roll.getText().toString().length()>9)
            {
                etxt_university_roll.setError("Invalid Roll");
                etxt_university_roll.requestFocus();
            }

        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            if (etxt_university_roll.getText().toString().equals(""))
            {
                etxt_university_roll.setError("Required Field");
                etxt_university_roll.requestFocus();
            }
        }
    };

    private void attemptLogin()
    {
        //before attempting login, set the errors to null
        etxt_university_roll.setError(null);
        etxt_password.setError(null);

        //performing static login
        if(etxt_university_roll.getText().toString().equals("130202022") && etxt_password.getText().toString().equals("131230110175")
                || etxt_university_roll.getText().toString().equals("130202111") && etxt_password.getText().toString().equals("131230110264"))
        {
            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();

            //checking to see if remember me is checked
            //if checked shared preferences are created
            if(ch_remember_me.isChecked())
            {
                loginPrefsEditor = loginPreferences.edit();
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", etxt_university_roll.getText().toString());
                loginPrefsEditor.putString("password", etxt_password.getText().toString());
                loginPrefsEditor.commit();
            }
            Intent switch_to_main = new Intent(getApplicationContext(),ActivityMain.class);
            switch_to_main.putExtra("password", etxt_password.getText().toString());
            switch_to_main.putExtra("universityroll", etxt_university_roll.getText().toString());
            startActivity(switch_to_main);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Login Unsuccessful. Invalid roll number and password combination",Toast.LENGTH_LONG).show();
        }
    }

    CheckBox.OnCheckedChangeListener check_show_password = new CompoundButton.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if(!isChecked)
            {
                etxt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                etxt_password.setSelection(etxt_password.getText().length());
            }
            else
            {
                etxt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                etxt_password.setSelection(etxt_password.getText().length());
            }
        }
    };

}
