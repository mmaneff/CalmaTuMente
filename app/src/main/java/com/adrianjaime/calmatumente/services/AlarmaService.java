package com.adrianjaime.calmatumente.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by emaneff on 29/12/2016.
 * http://www.bogotobogo.com/Android/android20NotificationService.php
 */
public class AlarmaService extends Service {

    public AlarmaService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //super.onCreate();
        Toast.makeText(this,"Service created", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        //super.onDestroy();
        Toast.makeText(this,"Service destroyed", Toast.LENGTH_LONG).show();
    }
    /*
    @Override
    public void onStart(Intent intent, int startId) {
        super.onCreate();
        Toast.makeText(this,"Service started", Toast.LENGTH_LONG).show();
    }
    */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service started", Toast.LENGTH_LONG).show();
        return START_NOT_STICKY;
    }


}
