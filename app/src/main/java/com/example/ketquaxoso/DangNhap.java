package com.example.ketquaxoso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DangNhap extends AppCompatActivity {
    private static SharedPreferences myPreference;
    EditText pas,usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        usr = (EditText)findViewById(R.id.editText7);
        pas = (EditText)findViewById(R.id.editText4);
    }
    public void xuLyDangNhap(View view) {
        TextView tvMessage = (TextView)findViewById(R.id.textView12);
        String username = usr.getText().toString();
        String password = pas.getText().toString();
        background bg = new background(this);
        bg.execute(username, password);
    }
    public void xuLyDangKy(View view){
        String username = usr.getText().toString();
        String password = pas.getText().toString();
        background_dangky bg = new background_dangky(this);
        bg.execute(username, password);
    }
}
