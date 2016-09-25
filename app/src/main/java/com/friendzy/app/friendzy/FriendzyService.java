package com.friendzy.app.friendzy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FriendzyService extends Service {

    SMSAlarm alarm = new SMSAlarm();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarm.setAlarm(this);
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        alarm.setAlarm(this);
    }

    public FriendzyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
