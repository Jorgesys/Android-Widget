package com.puisormobile.widget;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Utils {

    public static String getCurrentDate(){
        DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
        String date = dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        String time = dfTime.format(Calendar.getInstance().getTime());
        return date + " " + time;
    }

    //Create notification
    public static void sendNotification(Context ctx, String title, String message) {

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle().setBigContentTitle("Jorgesys is cool!").bigText(message).setSummaryText("Created by Jorgesys");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setTicker(message)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setLights(Color.BLUE, 1000, 2000)
                        .setStyle(style);
        NotificationManager mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(Utils.getIdNotification(), mBuilder.build());
    }

    public static int getIdNotification(){
        Random rnd = new Random();
        return rnd.nextInt(10000);
    }

}
