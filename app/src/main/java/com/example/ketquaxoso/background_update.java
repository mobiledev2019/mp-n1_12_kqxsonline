package com.example.ketquaxoso;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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

public class background_update extends AsyncTask<String, Void, String> {
    AlertDialog dialog;
    Context context;
    public background_update( Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Update status");
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println(s);
        String output = "";
        if(s.equals("update thanh cong") == true){
            Toast.makeText(context, "Đã lưu lại kết quả thay đổi", Toast.LENGTH_LONG);
        }
        else{
            output = "Quá trình thay đổi xảy ra lỗi. Thử lại sau!";
            dialog.setMessage(output);
            dialog.show();
        }
    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String user = voids[0];
        String money = voids[1];

        String constr = "http://192.168.42.90/update.php";
        try {
            URL url = new URL(constr);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&&" +  URLEncoder.encode("money", "UTF-8") + "="
                    + URLEncoder.encode(money, "UTF-8");
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
