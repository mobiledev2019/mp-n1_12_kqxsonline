package com.example.ketquaxoso;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.helper.HttpConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class background extends AsyncTask<String, Void, String> {
    AlertDialog dialog;
    Context context;

    public background(Context context){
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Login status");
    }

    @Override
    protected void onPostExecute(String s) {
        String output = "";
        if((s.equals("login failed...") == true) || (s.equals(("Not Connected....!"))== true ) ){
            output = "Thông tin đăng nhập không chính xác";
            System.out.println(output);
            dialog.setMessage(output);
            dialog.show();
        }
        else {
            try {
                System.out.println("DA LOGIN");
                System.out.println(s);
                JSONObject account1 = new JSONObject(s);
                JSONArray infoAccount = account1.getJSONArray("account");
                String user = infoAccount.getJSONObject(0).getString("user");
                String pass = infoAccount.getJSONObject(0).getString("pass");
                int money = infoAccount.getJSONObject(0).getInt("money");

                Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("pass", pass);
                intent.putExtra("money", money);
                context.startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String user = voids[0];
        String pass = voids[1];

        String constr = "http://192.168.42.90/login.php";
        try {
            URL url = new URL(constr);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&" +  URLEncoder.encode("pass", "UTF-8") + "="
                    + URLEncoder.encode(pass, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
            String line = "";
            while((line = reader.readLine())!= null){
                result += line;
            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;


        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }


        return result;
    }
}
