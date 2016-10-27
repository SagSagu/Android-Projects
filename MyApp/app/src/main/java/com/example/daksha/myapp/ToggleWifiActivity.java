package com.example.daksha.myapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ToggleWifiActivity extends AppCompatActivity {

    private int notificationID = 100;
    private NotificationManager mNotificationManager;
    private ToggleButton tbWifi;
    private TextView tvWifi;

    void displayNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Wifi Services");
        builder.setContentText("switvhed on Wifi ");
        builder.setTicker("Wifi Notification");
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.wifi);
        builder.build();
        Intent intent = new Intent(this, ToggleWifiActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ToggleWifiActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(notificationID, builder.build());

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toggle_wifi_layout);

        tbWifi = (ToggleButton) findViewById(R.id.tbWifi);


        tbWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tbWifi.isChecked()) {
                    displayNotification();
                    WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                    wifi.setWifiEnabled(true);
                } else {
                    WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                    wifi.setWifiEnabled(false);
                }

            }
        });







    }
}