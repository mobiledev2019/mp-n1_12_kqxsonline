package com.example.ketquaxoso;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class KetQua extends AppCompatActivity {
//    private static final String LOG_TAG = KetQua.class.getSimpleName();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ket_qua);
//    }
//    public void chuyenSangActionChoiThu(View view){
//        Log.d(LOG_TAG, "Da chuyen sang Activity choi thu");
//        Intent intent = new Intent(this, Main2Activity.class);
//        startActivity(intent);
//    }
//    public void suKienHenGio(View view){
//        Log.d(LOG_TAG, "Da chuyen sang Activity hen gio!");
//        Intent intent = new Intent(this, Setting.class);
//        startActivity(intent);
//    }
private TextView mTextMessage;
    private String url = "https://ketqua.net/xo-so-truyen-thong.php?ngay=";
    private ArrayList<String> arr;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
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
    private  String result_date;
    private Button bt_chon_ngay;
    private int y,m,d;
    private String userAccount;
    private int moneyAccount;
    public String ketQua = "";
    public SharedPreferences MyPreference;
    public String ngayDoan ="";
    public String ketQuaDuDoan = "";
    public static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        Intent intent = getIntent();

        moneyAccount = intent.getIntExtra("money", 0);
        userAccount = intent.getStringExtra("user");
        calendar = Calendar.getInstance();
        y = calendar.get(Calendar.YEAR);
        m = calendar.get(Calendar.MONTH);
        d = calendar.get(Calendar.DAY_OF_MONTH);
        MyPreference = getSharedPreferences("Activity2", MODE_PRIVATE);
        ngayDoan = MyPreference.getString("NGAYDOAN","");
        ketQuaDuDoan = MyPreference.getString("KetQuaVaNgayDoan", "");
        System.out.println(MyPreference.getString("NGAYDOAN", ""));
        System.out.println(MyPreference.getString("KetQuaVaNgayDoan", ""));
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
        dateView = (TextView) findViewById(R.id.textView_ngay);
        showday(d,m+1,y);
        bt_chon_ngay = findViewById(R.id.set_date);
        getKQXS temp = new getKQXS();
        temp.execute();
        bt_chon_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(KetQua.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        showday(dayOfMonth,month+1,year);
                        new getKQXS().execute();
                    }
                },y,m,d);
                datePickerDialog.show();
            }
        });
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        new getKQXS().execute();
    }

    public void showday(int d ,int m, int y){
        if(m >= 10){
            dateView.setText(new StringBuilder().append(d).append("-").append(m).append("-").append(y));
        }
        else
            dateView.setText(new StringBuilder().append(d).append("-").append(0).append(m).append("-").append(y));
    }

    private class getKQXS extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String ngay = (String) dateView.getText();
                arr = new ArrayList<String>();
                Document doc = Jsoup.connect(url+ngay).get();
                System.out.println("KET QUA CUA NGAY: " + ngay);
                result_date = doc.getElementById("result_date").text();
                System.out.println("RESULT DATE: " + result_date);
                if(doc.getElementById("rs_0_0")!= null){
                    arr.add(doc.getElementById("rs_0_0").text());
                    arr.add(doc.getElementById("rs_1_0").text());
                    arr.add(doc.getElementById("rs_2_0").text());
                    arr.add(doc.getElementById("rs_2_1").text());
                    arr.add(doc.getElementById("rs_3_1").text());
                    arr.add(doc.getElementById("rs_3_0").text());
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
            if(arr.size() > 0){
                gdb.setText(arr.get(0));    ketQua =  layHaiSoCuoi(arr.get(0));
                g10.setText(arr.get(1));    ketQua += "|" + layHaiSoCuoi(arr.get(1));
                g20.setText(arr.get(2));    ketQua += "|" + layHaiSoCuoi(arr.get(2));
                g21.setText(arr.get(3));    ketQua += "|" + layHaiSoCuoi(arr.get(3));
                g30.setText(arr.get(4));    ketQua += "|" + layHaiSoCuoi(arr.get(4));
                g31.setText(arr.get(5));    ketQua += "|" + layHaiSoCuoi(arr.get(5));
                g32.setText(arr.get(6));    ketQua += "|" + layHaiSoCuoi(arr.get(6));
                g33.setText(arr.get(7));    ketQua += "|" + layHaiSoCuoi(arr.get(7));
                g34.setText(arr.get(8));    ketQua += "|" + layHaiSoCuoi(arr.get(8));
                g35.setText(arr.get(9));    ketQua += "|" + layHaiSoCuoi(arr.get(9));
                g40.setText(arr.get(10));   ketQua += "|" + layHaiSoCuoi(arr.get(10));
                g41.setText(arr.get(11));   ketQua += "|" + layHaiSoCuoi(arr.get(11));
                g42.setText(arr.get(12));   ketQua += "|" + layHaiSoCuoi(arr.get(12));
                g43.setText(arr.get(13));   ketQua += "|" + layHaiSoCuoi(arr.get(13));
                g50.setText(arr.get(14));   ketQua += "|" + layHaiSoCuoi(arr.get(14));
                g51.setText(arr.get(15));   ketQua += "|" + layHaiSoCuoi(arr.get(15));
                g52.setText(arr.get(16));   ketQua += "|" + layHaiSoCuoi(arr.get(16));
                g53.setText(arr.get(17));   ketQua += "|" + layHaiSoCuoi(arr.get(17));
                g54.setText(arr.get(18));   ketQua += "|" + layHaiSoCuoi(arr.get(18));
                g55.setText(arr.get(19));   ketQua += "|" + layHaiSoCuoi(arr.get(19));
                g60.setText(arr.get(20));   ketQua += "|" + layHaiSoCuoi(arr.get(20));
                g61.setText(arr.get(21));   ketQua += "|" + layHaiSoCuoi(arr.get(21));
                g62.setText(arr.get(22));   ketQua += "|" + layHaiSoCuoi(arr.get(22));
                g70.setText(arr.get(23));   ketQua += "|" + layHaiSoCuoi(arr.get(23));
                g71.setText(arr.get(24));   ketQua += "|" + layHaiSoCuoi(arr.get(24));
                g72.setText(arr.get(25));   ketQua += "|" + layHaiSoCuoi(arr.get(25));
                g73.setText(arr.get(26));   ketQua += "|" + layHaiSoCuoi(arr.get(26));
                System.out.println(ketQua);
                System.out.println(" DEBUG--------------");
                String d1 = "20/02/2019";
                String d2 = "25/02/2019";

                Date day1, day2;
                try {
                    day1 = getDate(d1);
                    day2 = getDate(d2);
                    long ngayTru = day2.getTime() - 86400000*7;
                    System.out.println(df.format(new Date(ngayTru)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if(ngayDoan != "") {
                    String ngayHienTai = ngayDoan.split("-")[0];
                    if(ngayHienTai.length() == 1) ngayHienTai = "0" + ngayHienTai;
                    String thangHienTai = ngayDoan.split("-")[1];
                    if(thangHienTai.length() == 1) thangHienTai = "0" + thangHienTai;
                    String namHienTai = ngayDoan.split("-")[2];
                    String date_lich = result_date.split(" ")[3];
                    String ngayTrenLich = date_lich.split("-")[0];
                    System.out.println(ngayTrenLich);
                    String thangTrenLich = date_lich.split("-")[1];
                    System.out.println(thangTrenLich);
                    if (Integer.parseInt(ngayHienTai) == Integer.parseInt(ngayTrenLich) && (Integer.parseInt(thangHienTai) == Integer.parseInt(thangTrenLich))) {
                        System.out.println("VAO ROI!");
                        kiemTraTrungThuong();
                    }
                }
            }
            else {
                Toast.makeText(KetQua.this, "Chưa có kết quả!!!", Toast.LENGTH_LONG).show();
            }
        }
    }
    public Date getDate(String date) throws ParseException {
        return df.parse(date);
    }

    public long TinhSoNgay(Date d1, Date d2){
        long diff = (d1.getTime() - d2.getTime())/86400000;
        return Math.abs(diff);
    }
    public String layHaiSoCuoi(String temp){
        String result = "";
        result = result +  temp.charAt(temp.length() - 2);
        result = result +  temp.charAt(temp.length() - 1);
        return result;
    }
    public void chuyenSangActionChoiThu(View view){
        //Log.d(LOG_TAG, "Da chuyen sang Activity choi thu");
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void suKienHenGio(View view){
        //Log.d(LOG_TAG, "Da chuyen sang Activity hen gio!");
        Intent intent = new Intent(this, Setting.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionXemKetQua(View view){
        //Log.d(LOG_TAG, "Da chuyen sang Activity xem ket qua");
        Intent intent = new Intent(this, KetQua.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionThongKe(View view){
        //Log.d(LOG_TAG, "Da chuyen sang Activity xem Thong Ke!");
        Intent intent = new Intent(KetQua.this, ThongKeKetQua.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void kiemTraTrungThuong(){
        Calendar clTest = Calendar.getInstance();
        int ngayLich1;
        int thangLich1;
        int namLich1;
        ngayLich1 = clTest.get(Calendar.DAY_OF_MONTH);
        thangLich1 = clTest.get(Calendar.MONTH) + 1;
        namLich1 = clTest.get(Calendar.YEAR);
        System.out.println(ngayLich1 + "/" + thangLich1 + "/" + namLich1);
        String ngayTest = Integer.toString(ngayLich1);
        String thangTest = Integer.toString(thangLich1);
        String namTest = Integer.toString(namLich1);

        if(ngayLich1 < 10){
            ngayTest = "0" + Integer.toString(ngayLich1);
        }
        if(thangLich1 < 10){
            thangTest = "0" + Integer.toString(thangLich1);
        }
        String dateNow = ngayTest + "/" + thangTest + "/" + namTest;
        System.out.println(ngayTest + " - " + thangTest + " - "+ namTest);

        int check = 0;
        String soDB = ketQuaDuDoan.split("\\|")[1];
        String soLO = ketQuaDuDoan.split("\\|")[2];
        String tienDB = ketQuaDuDoan.split("\\|")[3];
        String tienLO = ketQuaDuDoan.split("\\|")[4];
        int moneyTrungThuong = 0;
        int moneyDatCuoc = 0;
        moneyDatCuoc = - Integer.parseInt(tienDB) - Integer.parseInt(tienLO)*23;
        DatabaseHandler db = new DatabaseHandler(this);
        account truTien = new account(userAccount, dateNow, Integer.toString(moneyDatCuoc));
        db.addAccount(truTien);
        if(soDB.equals(layHaiSoCuoi(arr.get(0)))){
            check = 1;
        }
        int soluong = 0;
        for(int i = 0; i < arr.size(); i++){
            if(layHaiSoCuoi(arr.get(i)).equals(soLO)){
                soluong++;
                check = 1;
            }
        }
        if(check==1){
            moneyTrungThuong += Integer.parseInt(tienDB)*80;
        }
        if(soluong > 0){
            moneyTrungThuong += Integer.parseInt(tienLO)*80;
        }

        background_update bgUpdate = new background_update(this);
        moneyAccount += moneyTrungThuong;
        bgUpdate.execute(userAccount, Integer.toString(moneyAccount));
        SharedPreferences.Editor myEditor = MyPreference.edit();
        myEditor.remove("NGAYDOAN");
        myEditor.remove("KetQuaVaNgayDoan");
        myEditor.remove("LO");
        myEditor.remove("DB");
        myEditor.remove("TIENDB");
        myEditor.remove("TIENLO");
        myEditor.commit();
        if(moneyTrungThuong > 0){
            account acCongTien = new account(userAccount, dateNow, Integer.toString(moneyTrungThuong));
            db.addAccount(acCongTien);
        }

    }
}
