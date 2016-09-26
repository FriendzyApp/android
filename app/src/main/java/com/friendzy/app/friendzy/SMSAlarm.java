package com.friendzy.app.friendzy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.PowerManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SMSAlarm extends BroadcastReceiver {
    String SMS_READ_COLUMN = "read";
    String WHERE_CONDITION = SMS_READ_COLUMN + " = 0";
    String SORT_ORDER = "date DESC";

    public SMSAlarm() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        //TODO get texts
        Log.v("FriendzyALARM", "Start getting texts");
        Toast.makeText(context, "Getting users unread texts", Toast.LENGTH_SHORT).show();
        context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        Cursor cursor = context.getContentResolver().query(
                Uri.parse("content://sms/inbox"),
                new String[] { "_id", "thread_id", "address", "person", "date", "body" },
                WHERE_CONDITION,
                null,
                SORT_ORDER);

        if (cursor != null && cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);

                }
                // use msgData
                Log.v("FriendzyALARM", "TEXTREAD: "+ msgData);

            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
            Log.v("FriendzyALARM", "No SMS");
        }

//        if (cursor != null) {
//            try {
//                count = cursor.getCount();
//                if (count > 0) {
//                    cursor.moveToFirst();
//                    // String[] columns = cursor.getColumnNames();
//                    // for (int i=0; i<columns.length; i++) {
//                    // Log.v("columns " + i + ": " + columns[i] + ": " + cursor.getString(i));
//                    // }
//                    long messageId = cursor.getLong(0);
//                    long threadId = cursor.getLong(1);
//                    String address = cursor.getString(2);
//                    long contactId = cursor.getLong(3);
//                    String contactId_string = String.valueOf(contactId);
//                    long timestamp = cursor.getLong(4);
//
//                    String body = cursor.getString(5);
//                    if (!unreadOnly) {
//                        count = 0;
//                    }
//
//                    SmsMmsMessage smsMessage = new SmsMmsMessage(context, address,
//                            contactId_string, body, timestamp,
//                            threadId, count, messageId, SmsMmsMessage.MESSAGE_TYPE_SMS);
//                    return smsMessage;
//                }
//            } finally {
//                cursor.close();
//            }
//        }

        wl.release();
    }

    public void setAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, SMSAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60, pi); // Millisec * Second * Minute
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, SMSAlarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
