package com.example.ketquaxoso;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    private static int number_db ;
    private static int number_loto;
    private static int money_db = 0;
    private static int money_loto = 0;
    private static int tongTienDuDoan;
    public static int duDoan[] = { -1, -1, -1, -1};
    private String userAccount;
    private int moneyAccount;
    private int moneyAccountOlder;
    private SharedPreferences myPreference;
    private static final String LOG_TAG = Main2Activity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        userAccount = intent.getStringExtra("user");
        moneyAccount = intent.getIntExtra("money", 0);
        moneyAccountOlder = moneyAccount;
        //Set giá trị lúc trước
       // myPreference = PreferenceManager.getDefaultSharedPreferences(Main2Activity.this);
        myPreference = getSharedPreferences("Activity2", 0);
        number_loto = myPreference.getInt("LO", -1);
        number_db = myPreference.getInt("DB", -1);
        money_db = myPreference.getInt("TIEN_DB", 0);
        money_loto = myPreference.getInt("TIEN_LO", 0);
        System.out.println(number_loto);
        System.out.println(number_db);
        final EditText tvtiendb = findViewById(R.id.editText2);
        final EditText tvtienlo = findViewById(R.id.editText3);
        ((EditText)findViewById(R.id.editText2)).setText(Integer.toString(money_db)) ;
        ((EditText)findViewById(R.id.editText3)).setText(Integer.toString(money_loto)) ;
        ((EditText)findViewById(R.id.editText5)).setText(Integer.toString(number_db)) ;
        ((EditText)findViewById(R.id.editText6)).setText(Integer.toString(number_loto));
        ((TextView)findViewById(R.id.ngayDuDoan1)).setText(myPreference.getString("NGAYDOAN", ""));
        ((TextView)findViewById(R.id.textView13)).setText(Integer.toString(moneyAccount));
//        tvtiendb.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                tongTienDuDoan = money_loto*23 + Integer.parseInt(tvtiendb.getText().toString());
//                ((TextView)findViewById(R.id.textView14)).setText(R.string.congThucTongTien+"=" + Integer.toString(tongTienDuDoan));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        tvtienlo.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                tongTienDuDoan = money_db + Integer.parseInt(tvtienlo.getText().toString())*23;
//                ((TextView)findViewById(R.id.textView14)).setText(R.string.congThucTongTien+"=" + Integer.toString(tongTienDuDoan));
//            }
//        });
        //((TextView)findViewById(R.id.textView14)).setText(R.string.congThucTongTien + "= "+ );
    }
    public void xacNhanDuDoan(View view){

        SharedPreferences.Editor myEditor = myPreference.edit();
        number_loto = myPreference.getInt("LO", -1);
        number_db = myPreference.getInt("DB", -1);
        money_db = myPreference.getInt("TIEN_DB", 0);
        money_loto = myPreference.getInt("TIEN_LO", 0);
        //moneyAccountOlder = myPreference.getInt("moneyAccount", moneyAccountOlder);
        EditText number_dudoan_db = (EditText)findViewById(R.id.editText5);
        int so_db = Integer.parseInt(number_dudoan_db.getText().toString());
        EditText number_dudoan_lo = (EditText)findViewById(R.id.editText6);
        int so_lo = Integer.parseInt(number_dudoan_lo.getText().toString());
        EditText emoney_db = (EditText)findViewById(R.id.editText2);
        EditText emoney_lo = (EditText)findViewById(R.id.editText3);
        int tien_db = Integer.parseInt(emoney_db.getText().toString()) ;
        int tien_lo = Integer.parseInt(emoney_lo.getText().toString()) ;

        if(moneyAccount >= (tien_db - money_db) + (tien_lo - money_loto)*23){
            moneyAccount = moneyAccount - (tien_db - money_db) - (tien_lo - money_loto)*23;
            myEditor.putInt("DB", so_db);
            myEditor.putInt("LO", so_lo);
            myEditor.putInt("TIEN_DB", tien_db) ;
            myEditor.putInt("TIEN_LO", tien_lo);
            myEditor.putInt("moneyAccount", moneyAccount);
            System.out.println("TONG TIEN: " + moneyAccount);
            System.out.println("TIEN DB: "+ money_db);
            System.out.println("TIEN LO: "+ money_loto);
            System.out.println("TONG TIEN CU: " +moneyAccountOlder);
            Calendar calendar =Calendar.getInstance();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DAY_OF_MONTH);
//             Xử lý ngày dự đoán nếu sau 18h30
            if(calendar.get(Calendar.HOUR_OF_DAY) == 18 && calendar.get(Calendar.MINUTE) >= 30){
                d = d + 1;
            }
            if(calendar.get(Calendar.HOUR_OF_DAY) > 18){
                d = d + 1;
            }
            TextView ngayDoan = (TextView)findViewById(R.id.ngayDuDoan1);
            String stringNgayDoan = new String(new StringBuilder().append(d).append("-").append(m).append("-").append(y));
            // format: ngay|soDB|soLO|tienDB|tienLO
            String ketQuaVaNgayDoan = stringNgayDoan + "|"  + so_db + "|" + so_lo + "|" + tien_db + "|" + tien_lo;
            System.out.println("KET QUA VA NGAY DOAN: " + ketQuaVaNgayDoan);
            myEditor.putString("NGAYDOAN", stringNgayDoan);
            myEditor.putString("KetQuaVaNgayDoan", ketQuaVaNgayDoan);
            System.out.println(stringNgayDoan);
            System.out.println(ketQuaVaNgayDoan);
            ngayDoan.setText(stringNgayDoan);
            myEditor.commit();
            ((TextView)findViewById(R.id.textView13)).setText(Integer.toString(moneyAccount));
            ((TextView)findViewById(R.id.textView14)).setText("Tổng cược = Đặc biệt + lô*23 = " + Integer.toString(tien_db + tien_lo*23));
            Toast.makeText(Main2Activity.this,"Đã lưu kết quả dự đoán", Toast.LENGTH_SHORT).show();
            // Cap nhat money
            background_update bgupdate = new background_update(this);

            bgupdate.execute(userAccount, Integer.toString(moneyAccount));
        }
        else{
            Toast.makeText(Main2Activity.this,"Số tiền đặt cược không đủ", Toast.LENGTH_SHORT).show();
        }


    }
    public void suKienHenGio(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity hen gio!");
        Intent intent = new Intent(this, Setting.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionChoiThu(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity choi thu");
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void xoaDuDoan(View view){
        SharedPreferences.Editor myEditor = myPreference.edit();
        myEditor.remove("DB");
        myEditor.remove("LO");
        int tiendb = Integer.parseInt(((EditText)findViewById(R.id.editText2)).getText().toString());
        int tienlo = Integer.parseInt(((EditText)findViewById(R.id.editText3)).getText().toString());
        myEditor.remove("TIEN_DB");
        myEditor.remove("TIEN_LO");
        myEditor.remove("NGAYDOAN");
        moneyAccount = moneyAccount + tiendb + tienlo*23;
        myEditor.putInt("moneyAccount", moneyAccount);
        //myEditor.remove("moneyAccount");
        ((EditText)findViewById(R.id.editText5)).setText("");
        ((EditText)findViewById(R.id.editText6)).setText("");
        ((EditText)findViewById(R.id.editText2)).setText("");
        ((EditText)findViewById(R.id.editText3)).setText("");
        ((TextView)findViewById(R.id.textView13)).setText(Integer.toString(moneyAccount));
        ((TextView)findViewById(R.id.ngayDuDoan1)).setText("Chưa dự đoán");
        myEditor.commit();
        Toast.makeText(Main2Activity.this, "Đã xóa kết quả dự đoán", Toast.LENGTH_SHORT).show();

    }
    public void chuyenSangActionXemKetQua(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity xem ket qua");
        Intent intent = new Intent(Main2Activity.this, KetQua.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionThongKe(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity xem Thong Ke!");
        Intent intent = new Intent(Main2Activity.this, ThongKeKetQua.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
}
