package com.example.ketquaxoso;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class ket_qua_truc_tiep extends AppCompatActivity {
    private String url = "https://ketqua.net/xo-so-truyen-thong.php?ngay=";
    private ArrayList<String>  arr;
    private Calendar calendar;
    private TextView TB;
    private TextView gdb;
    private TextView g10;
    private TextView g20;
    private TextView g21;
    private TextView g30;
    private TextView g31;
    private TextView g32;
    private TextView g33;
    private TextView g34;
    private TextView g35;
    private TextView g40;
    private TextView g41;
    private TextView g42;
    private TextView g43;
    private TextView g50;
    private TextView g51;
    private TextView g52;
    private TextView g53;
    private TextView g54;
    private TextView g55;
    private TextView g60;
    private TextView g61;
    private TextView g62;
    private TextView g70;
    private TextView g71;
    private TextView g72;
    private TextView g73;
    StringBuilder ngay;
    Calendar time ;
    private int h,m,M,y,d;
    public void getday(int d, int m, int y){
        if(m >= 10){
            ngay = (new StringBuilder().append(d).append("-").append(m).append("-").append(y));
        }
        else
            ngay = (new StringBuilder().append(d).append("-").append(0).append(m).append("-").append(y));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua_truc_tiep);

        time = Calendar.getInstance();
        m = time.get(Calendar.MINUTE);
        h = time.get(Calendar.HOUR_OF_DAY);
        y = time.get(Calendar.YEAR);
        M = time.get(Calendar.MONTH);
        d = time.get(Calendar.DAY_OF_MONTH);
        getday(d,M+1,y);
        TB = findViewById(R.id.TB);
        gdb = findViewById(R.id.gdb);
        g10 = findViewById(R.id.g10);
        g20= findViewById(R.id.g20);
        g21= findViewById(R.id.g21);
        g30= findViewById(R.id.g30);
        g31= findViewById(R.id.g31);
        g32= findViewById(R.id.g32);
        g33= findViewById(R.id.g33);
        g34= findViewById(R.id.g34);
        g35= findViewById(R.id.g35);
        g40= findViewById(R.id.g40);
        g41= findViewById(R.id.g41);
        g42= findViewById(R.id.g42);
        g43= findViewById(R.id.g43);
        g50= findViewById(R.id.g50);
        g51= findViewById(R.id.g51);
        g52= findViewById(R.id.g52);
        g53= findViewById(R.id.g53);
        g54= findViewById(R.id.g54);
        g55= findViewById(R.id.g55);
        g60= findViewById(R.id.g60);
        g61= findViewById(R.id.g61);
        g62= findViewById(R.id.g62);
        g70= findViewById(R.id.g70);
        g71= findViewById(R.id.g71);
        g72= findViewById(R.id.g72);
        g73= findViewById(R.id.g73);
        Toast.makeText(ket_qua_truc_tiep.this,ngay,Toast.LENGTH_LONG).show();
        if(h > 18 ||( h == 18 && m > 30)){
            TB.setText("Kết quả hôm nay đã có!");
            new getKQXS().execute();
        }
        else if(h < 18){
            TB.setText("Chưa đến giờ quay số");
            new getKQXS().execute();
        }
        else if(h == 18 && m <= 30 ){
            TB.setText("Đang quay số...");
            new getDMM().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);
        }


    }
    private class getDMM extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            getKQXS task;
            while (h == 18){
                task = new getKQXS();
                task.execute();
                while (task.getStatus() != Status.FINISHED){
                    //System.out.println("");
                }
                time = Calendar.getInstance();
                m = time.get(Calendar.MINUTE);
                h = time.get(Calendar.HOUR_OF_DAY);
                task.cancel(false);

            }
            return null;
        }
    }


    private class getKQXS extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                arr = new ArrayList<String>();
                Document doc = Jsoup.connect(url+ngay).get();
                if(doc.getElementById("rs_0_0")!= null){
                    arr.add(doc.getElementById("rs_0_0").text());
                    arr.add(doc.getElementById("rs_1_0").text());
                    arr.add(doc.getElementById("rs_2_0").text());
                    arr.add(doc.getElementById("rs_2_1").text());
                    arr.add(doc.getElementById("rs_3_0").text());
                    arr.add(doc.getElementById("rs_3_1").text());
                    arr.add(doc.getElementById("rs_3_2").text());
                    arr.add(doc.getElementById("rs_3_3").text());
                    arr.add(doc.getElementById("rs_3_4").text());
                    arr.add(doc.getElementById("rs_3_5").text());
                    arr.add(doc.getElementById("rs_4_0").text());
                    arr.add(doc.getElementById("rs_4_1").text());
                    arr.add(doc.getElementById("rs_4_2").text());
                    arr.add(doc.getElementById("rs_4_3").text());
                    arr.add(doc.getElementById("rs_5_0").text());
                    arr.add(doc.getElementById("rs_5_1").text());
                    arr.add(doc.getElementById("rs_5_2").text());
                    arr.add(doc.getElementById("rs_5_3").text());
                    arr.add(doc.getElementById("rs_5_4").text());
                    arr.add(doc.getElementById("rs_5_5").text());
                    arr.add(doc.getElementById("rs_6_0").text());
                    arr.add(doc.getElementById("rs_6_1").text());
                    arr.add(doc.getElementById("rs_6_2").text());
                    arr.add(doc.getElementById("rs_7_0").text());
                    arr.add(doc.getElementById("rs_7_1").text());
                    arr.add(doc.getElementById("rs_7_1").text());
                    arr.add(doc.getElementById("rs_7_3").text());
                }

            } catch (IOException e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(arr.size() != 0){
                gdb.setText(arr.get(0));
                g10.setText(arr.get(1));
                g20.setText(arr.get(2));
                g21.setText(arr.get(3));
                g30.setText(arr.get(4));
                g31.setText(arr.get(5));
                g32.setText(arr.get(6));
                g33.setText(arr.get(7));
                g34.setText(arr.get(8));
                g35.setText(arr.get(9));
                g40.setText(arr.get(10));
                g41.setText(arr.get(11));
                g42.setText(arr.get(12));
                g43.setText(arr.get(13));
                g50.setText(arr.get(14));
                g51.setText(arr.get(15));
                g52.setText(arr.get(16));
                g53.setText(arr.get(17));
                g54.setText(arr.get(18));
                g55.setText(arr.get(19));
                g60.setText(arr.get(20));
                g61.setText(arr.get(21));
                g62.setText(arr.get(22));
                g70.setText(arr.get(23));
                g71.setText(arr.get(24));
                g72.setText(arr.get(25));
                g73.setText(arr.get(26));
            }
          // Toast.makeText(ket_qua_truc_tiep.this,"Đang quay...",Toast.LENGTH_LONG).show();
        }
    }

}
