package com.example.ketquaxoso;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class thongKe extends AppCompatActivity {

    private String id;
    private Button bt;
    private EditText editTextid;
    private TextView textView_tongsongayve;
    private TextView textView_tongsolanve;
    private TextView textView_tansotheongay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        bt = findViewById(R.id.button_id);
        editTextid = findViewById(R.id.edit_id);
        editTextid.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        textView_tansotheongay =findViewById(R.id.textView_tansotheongay);
        textView_tongsolanve = findViewById(R.id.textView_tongsolanve);
        textView_tongsongayve = findViewById(R.id.textView_tongsongayve);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = editTextid.getText().toString().trim();
                int x = Integer.parseInt(id);
                if(x>99 || x < 0){
                    Toast.makeText(thongKe.this,"Vui lòng nhập số từ 00-99.",Toast.LENGTH_LONG).show();
                }
                else{
                    new getTK().execute();
                }

            }
        });


    }

    private class getTK extends AsyncTask<Void, Void, Void>{
        String tongsolanve ;
        String tongsongayve;
        String tansotheongay ;
        Element info;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                id = editTextid.getText().toString().trim();
                Document doc = Jsoup.connect("https://ketqua.net/thong-ke-tan-suat-bo-so").get();
                Element el = doc.getElementById("tansuatboso");
                info = el.getElementById(id);
                Elements infos = info.getElementsByTag("td");
                tongsolanve = infos.get(2).text();
                tongsongayve = infos.get(1).text();
                tansotheongay = infos.get(3).text();

            } catch (IOException e) {
                //Thong bao loi
                Toast.makeText(thongKe.this,"Loi!!!",Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (info.hasText()){

                textView_tongsolanve.setText(tongsolanve);
                textView_tansotheongay.setText(tansotheongay);
                textView_tongsongayve.setText(tongsongayve);
            }
            else
                Toast.makeText(thongKe.this,"Loi!!!",Toast.LENGTH_LONG).show();



        }
    }

}
