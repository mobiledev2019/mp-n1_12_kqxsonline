package com.example.ketquaxoso;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class Setting extends AppCompatActivity {
    private static final String LOG_TAG = Setting.class.getSimpleName();
    private NotificationManager mNotificationManager;
    private static final int ID_NOTIFICATION = 0;
    private static final String ID_CHANNEL = "kenhthongbao";
    private String userAccount;
    private int moneyAccount;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                String tranData = data.getStringExtra("checkBingo");
                System.out.println(tranData);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent intent = getIntent();
        userAccount = intent.getStringExtra("user");
        moneyAccount = intent.getIntExtra("money", 0);
        Log.d(LOG_TAG, "Oncreat ----- Setting");
        final AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
        Intent notifyIntent = new Intent(this, AlarmReceived.class);

        //Biến để kiểm tra trạng thái xem có tồn tại alarm từ trước không?
        final boolean alarmUp =(PendingIntent.getBroadcast(this, ID_NOTIFICATION, notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
        final PendingIntent notifyPendingIntent =PendingIntent.getBroadcast(this,ID_NOTIFICATION, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Switch switch_thongbao = findViewById(R.id.switch1);
        switch_thongbao.setChecked(alarmUp);
        switch_thongbao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Calendar calendar = Calendar.getInstance();
                    System.out.println("Calendar time: "+ calendar.getTime() );
                    System.out.println("System time: " + System.currentTimeMillis());
                    //Set time cho lịch chạy
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, 19);
                    calendar.set(Calendar.MINUTE, 22);
                    //long thoiGianLapLai = 20*1000;
                    //long triggerTime = SystemClock.elapsedRealtime() + thoiGianLapLai;
                    //System.out.println("Thoi gian kich hoat: " + triggerTime);
                    if(alarmManager != null){
                        System.out.println("Co dong ho!");
                        System.out.println(calendar.getTimeInMillis());

                        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,notifyPendingIntent);
                        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, thoiGianLapLai, notifyPendingIntent);
                    }
                    else{
                        System.out.println("CHUA CO DONG HO!");
                    }
                    Toast.makeText(Setting.this,"Đã bật!", Toast.LENGTH_LONG).show();
                }
                else{
                    //mNotificationManager.cancelAll();
                    if(alarmManager != null){
                        alarmManager.cancel(notifyPendingIntent);
                    }
                    Toast.makeText(Setting.this, "Đã tắt!",Toast.LENGTH_LONG).show();
                }
            }
        });
        creatNotificationChannel();
    }
    public void creatNotificationChannel(){
        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE); // Khởi tạo bằng cách sử dụng getSystemService

        //Kiểm tra xem API hiện tại có phải cần thiết lập kênh truyền notify không
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(ID_CHANNEL, "Stand up Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);                                                 //Thiết lập các tham số cho kênh truyền
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("CHAY NGAY DI");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
    public void cheDoThongBao(View view){

    }
    public void chuyenSangActionChoiThu(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity choi thu");
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void suKienHenGio(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity hen gio!");
        Intent intent = new Intent(this, Setting.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionXemKetQua(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity xem ket qua");
        Intent intent = new Intent(this, KetQua.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
    public void chuyenSangActionThongKe(View view){
        Log.d(LOG_TAG, "Da chuyen sang Activity xem Thong Ke!");
        Intent intent = new Intent(Setting.this, ThongKeKetQua.class);
        intent.putExtra("user", userAccount);
        intent.putExtra("money", moneyAccount);
        startActivity(intent);
    }
}
