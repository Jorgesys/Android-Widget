package com.puisormobile.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

//https://developer.android.com/reference/android/appwidget/AppWidgetProviderInfo.html
//Updates requested with updatePeriodMillis will not be delivered more than once every 30 minutes (1800000 ms).

public class MyWidgetProvider extends AppWidgetProvider {

    private static final String TAG = "MyWidgetProvider";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Log.d(TAG, "onUpdate() method called on " + Utils.getCurrentDate());
        // Get all ids
        ComponentName thisWidget = new ComponentName(context, MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        // Build the intent to call the service
        Intent intent = new Intent(context.getApplicationContext(), UpdateWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
        Log.d(TAG, "onUpdate() allWidgetIds " + allWidgetIds.length);
        // Update the widgets via the service
        context.startService(intent);
        Log.d(TAG, "onUpdate() Service started!");


       // Utils.sendNotification(context, "Jorgesys Widget", TAG + " onUpdate() method called on " + Utils.getCurrentDate());

    }
}
