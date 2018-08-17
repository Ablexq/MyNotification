package com.example.administrator.mynotification.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.mynotification.util.MyNotificationManager;
import com.example.administrator.mynotification.util.MyShortcutManager;
import com.example.administrator.mynotification.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyNotificationManager.initNotificationChannel(this);
        initView();
    }

    private void initView() {
        Button mBtnAddShortcut = findViewById(R.id.btn_add_shortcut);
        mBtnAddShortcut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                MyShortcutManager.addShortCut(getApplicationContext());
            }
        });

        Button mBtnSendNotificationChannel1 = findViewById(R.id.btn_send_notification_channel1);
        mBtnSendNotificationChannel1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                MyNotificationManager.showChannel1Notification(getApplicationContext());
            }
        });

        Button mBtnSendNotificationChannel2 = findViewById(R.id.btn_send_notification_channel2);
        mBtnSendNotificationChannel2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                MyNotificationManager.showChannel2Notification(getApplicationContext());
            }
        });
    }
}