package com.puisormobile.widget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {


    private static final String TAG = "UpdateWidgetService";

    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind()" + intent.getData() );
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate() , service started..." + Utils.getCurrentDate());
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand() method called on " + Utils.getCurrentDate());

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());

        int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        //ComponentName thisWidget = new ComponentName(getApplicationContext(),MyWidgetProvider.class);
        //int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);

        Log.d(TAG, "onStart() allWidgetIds:" + allWidgetIds.length);


        //Update all Widgets found.
        for (int widgetId : allWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.widget_layout);
            Log.d(TAG, String.valueOf("Current Date : " + Utils.getCurrentDate()));
            // Set the text
            remoteViews.setTextViewText(R.id.txtUpdate, "Last Update : " + Utils.getCurrentDate());

            // Register an onClickListener
            Intent clickIntent = new Intent(this.getApplicationContext(), MyWidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.txtUpdate, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        stopSelf();

         Utils.sendNotification(this, "Jorgesys Widget", TAG + " onStart() " + Utils.getCurrentDate());

        return Service.START_STICKY;
    }


    public IBinder onUnBind(Intent arg0) {
        Log.i(TAG, "onUnBind()");
        return null;
    }

    public void onStop() {
        Log.i(TAG, "onStop()");
    }
    public void onPause() {
        Log.i(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
      Log.i(TAG, "onCreate() , service stopped...");
    }

    @Override
    public void onLowMemory() {
      Log.i(TAG, "onLowMemory()");
    }


/*
    @Override
    public void onStart(Intent intent, int startId) {

        Log.w(TAG, "onStart() method called on " + Utils.getCurrentDate());

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());

        int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        //ComponentName thisWidget = new ComponentName(getApplicationContext(),MyWidgetProvider.class);
        //int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);

        Log.w(TAG, "onStart() allWidgetIds:" + allWidgetIds.length);


        for (int widgetId : allWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.widget_layout);
            Log.w(TAG, String.valueOf("Current Date : " + Utils.getCurrentDate()));
            // Set the text
            remoteViews.setTextViewText(R.id.txtUpdate, "Jorgesys Widget\nCurrent Date : " + Utils.getCurrentDate());

            // Register an onClickListener
            Intent clickIntent = new Intent(this.getApplicationContext(), MyWidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.txtUpdate, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        stopSelf();

        super.onStart(intent, startId);
    }
*/


}