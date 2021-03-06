package com.uk.location.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class TrackingAlarmReceiver extends BroadcastReceiver {

    private final String ALARM_ACTION_LABEL = "LOCATION_ALARM";
    private AlarmManager am;
    private Intent intent;
    private PendingIntent pi;
    private final int LOCATION_UPLOAD_INTERVAL = 1000 * 5 * 60;
    private PowerManager pm;
    public static LocationHelper locationHelper;


    public void onReceive(Context context, Intent intent) {
        String currentUser = intent.getStringExtra("USER_ID");
        String token = intent.getStringExtra("USER_TOKEN");
        locationHelper = new LocationHelper(currentUser, token, context);
        locationHelper.getLocation(true);
        startAlarm(currentUser, token, context);
    }

    public void startAlarm(String currentUser, String token, Context context) {
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(context, TrackingAlarmReceiver.class);
        intent.putExtra("USER_ID", currentUser);
        intent.putExtra("USER_TOKEN", token);
        intent.setAction(ALARM_ACTION_LABEL);
        pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(pi);
        AlarmManager.AlarmClockInfo ac = new AlarmManager.AlarmClockInfo(System.currentTimeMillis()+LOCATION_UPLOAD_INTERVAL, pi);
        am.setAlarmClock(ac, pi);
        LocationHelper.setTrackingState(true);
        Log.d("loa1", "alarm starts");
    }

    public void stopAlarm(String currentUser, String token, Context context) {
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(context, TrackingAlarmReceiver.class);
        intent.putExtra("USER_ID", currentUser);
        intent.putExtra("USER_TOKEN", token);
        intent.setAction(ALARM_ACTION_LABEL);
        pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(pi);
        pi.cancel();
        LocationHelper.setTrackingState(false);
        Log.d("loa1", "alarm stops");
    }
}
