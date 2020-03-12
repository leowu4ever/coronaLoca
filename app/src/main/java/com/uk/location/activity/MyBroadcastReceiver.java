package com.uk.location.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private final String ALARM_ACTION_LABEL = "LOCATION_ALARM";
    private AlarmManager am;
    private Intent intent;
    private PendingIntent pi;
    private final int LOCATION_UPLOAD_INTERVAL = 1000 * 60 * 5;

    public static LocationHelper locationHelper;

    public void onReceive(Context context, Intent intent) {
        locationHelper = new LocationHelper(context);
        locationHelper.getLocation(true);
        startAlarm(context);
    }

    public void startAlarm(Context context) {
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(context, MyBroadcastReceiver.class);
        intent.setAction(ALARM_ACTION_LABEL);
        pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(pi);
        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, (System.currentTimeMillis() + LOCATION_UPLOAD_INTERVAL), pi); //Next alarm in 5s

        LocationHelper.setTrackingState(true);
        Log.d("loa1", "alarm starts");
    }

    public void stopAlarm(Context context) {
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(context, MyBroadcastReceiver.class);
        intent.setAction(ALARM_ACTION_LABEL);
        pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(pi);
        pi.cancel();

        LocationHelper.setTrackingState(false);
        Log.d("loa1", "alarm stops");
    }
}
