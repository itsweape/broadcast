package com.example.pertemuan10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button sendBtn;

    //receiver
    private CustomReceiver nReceiver = new CustomReceiver();

    //custom broadcast
    private final static String ACTION_CUSTOM_BROADCAST = "action-custom-broadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = findViewById(R.id.send_btn);

        //membuat filter untuk menambah action
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        //mengirim intent ke customreceiver
        this.registerReceiver(nReceiver, filter);

        this.registerCustomeBroadcast();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendBroadcast();
            }
        });
    }

    //custome broadcast
    private void sendBroadcast(){
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }

    private void registerCustomeBroadcast(){
        IntentFilter filter = new IntentFilter(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(nReceiver, filter);
    }
}