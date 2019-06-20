package com.example.ketquaxoso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ThongKeKetQua extends AppCompatActivity {
    private String userAccount;
    private int moneyAccount;
    public static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_ket_qua);
        Intent intent = getIntent();
        userAccount = intent.getStringExtra("user");
        moneyAccount = intent.getIntExtra("money", 0);
        DatabaseHandler dbtest = new DatabaseHandler(this);
        System.out.println("USER: " + userAccount);
        ArrayList<account> result = dbtest.getAccoutByName(userAccount);
        System.out.println(result.size());
        for(int i = 0; i < result.size(); i++){
            account simpletest = result.get(i);
            System.out.println(simpletest.getIdAccount());
            System.out.println(simpletest.getUser());
            System.out.println(simpletest.getDate());
            System.out.println(simpletest.getMoney());
        }
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
        TextView batDau = findViewById(R.id.textView21);
        TextView ketThuc = findViewById(R.id.textView22);
        TextView matTien = findViewById(R.id.textView23);
        TextView duocTien = findViewById(R.id.textView24);
        TextView tongTien = findViewById(R.id.textView26);
        System.out.println(dateNow);
        ketThuc.setText(dateNow);
        try {
            Date d1 = getDate(dateNow);
            long ngayTru = d1.getTime() - 86400000*7;
            String ngayBatDau = df.format(new Date(ngayTru));
            Date d2 = getDate(ngayBatDau);
            batDau.setText(ngayBatDau);
            int tienMat = 0;
            int tienDuoc = 0;
            for(int i = 0; i < result.size(); i++){
                String dateElement = result.get(i).getDate();
                System.out.println(dateElement);
                Date checkd1 = getDate(dateElement);
                if(checkd1.getTime() >= d2.getTime()) {
                    if (Integer.parseInt(result.get(i).getMoney()) < 0) {
                        tienMat += Integer.parseInt(result.get(i).getMoney());
                    } else {
                        tienDuoc += Integer.parseInt(result.get(i).getMoney());
                    }
                }
            }
            matTien.setText(Integer.toString(tienMat*-1));
            duocTien.setText(Integer.toString(tienDuoc));
            tongTien.setText(Integer.toString(tienDuoc+ tienMat));

        } catch (ParseException e) {
            e.printStackTrace();
        }
//        account acc1 = dbtest.get1Account(5);
//        System.out.println(acc1.getIdAccount());
//        System.out.println(acc1.getUser());
//        account resultAc = dbtest.getAccount(3);
//        System.out.println(resultAc.getUser());
//        System.out.println(resultAc.getPass());
    }

    public Date getDate(String date) throws ParseException {
        return df.parse(date);
    }

    public long TinhSoNgay(Date d1, Date d2){
        long diff = (d1.getTime() - d2.getTime())/86400000;
        return Math.abs(diff);
    }
    public void chuyenSangActionChoiThu(View view){

        Intent intent = new Intent(ThongKeKetQua.this, Main2Activity.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void suKienHenGio(View view){

        Intent intent = new Intent(ThongKeKetQua.this, Setting.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionXemKetQua(View view){

        Intent intent = new Intent(ThongKeKetQua.this, KetQua.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionThongKe(View view){
        Intent intent = new Intent(this, ThongKeKetQua.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
}
