package com.example.ketquaxoso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String userAccount;
    private String passAccount;
    private int moneyAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        userAccount = intent.getStringExtra("user");
        passAccount = intent.getStringExtra("pass");
        moneyAccount = intent.getIntExtra("money", 0);
        ((TextView)findViewById(R.id.textView8)).setText("Hello: " + userAccount);
        ((TextView)findViewById(R.id.textView5)).setText(Integer.toString(moneyAccount));
        System.out.println("Da bat dau onCreat()");
        Log.d(LOG_TAG, "---------");
        Log.d(LOG_TAG, "Oncreat");


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG,"OnRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG,"OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG,"OnPause");
    }
    //ic_menu_today, ic_menu_agenda, ic_media_play, ic_lock_idle
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"OnDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG,"OnStop");
    }

    protected void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "OnStart") ;
    }
    public void chuyenSangActionTK(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity TK");
        Intent intent = new Intent(MainActivity.this, thongKe.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionTT(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity TT");
        Intent intent = new Intent(MainActivity.this, ket_qua_truc_tiep.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionChoiThu(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity choi thu");
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void suKienHenGio(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity hen gio!");
        Intent intent = new Intent(MainActivity.this, Setting.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionXemKetQua(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity xem ket qua");
        Intent intent = new Intent(MainActivity.this, KetQua.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionThongKe(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity xem Thong Ke!");
        Intent intent = new Intent(MainActivity.this, ThongKeKetQua.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }

}
