package com.puisormobile.widget;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            ((TextView) findViewById(R.id.texView)).setText(Html.fromHtml(getResources().getString(R.string.info), Html.FROM_HTML_MODE_LEGACY));
        }else{
            ((TextView) findViewById(R.id.texView)).setText(Html.fromHtml(getResources().getString(R.string.info)));
        }
    }
}
