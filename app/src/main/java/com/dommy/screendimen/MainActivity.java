package com.dommy.screendimen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = getDisplayMetrics(this);
        String text = "widthPixels=" + displayMetrics.widthPixels
                + ", heightPixels=" + displayMetrics.heightPixels
                + ", density=" + displayMetrics.density
                + ", densityDpi=" + displayMetrics.densityDpi
                + ", widthDp=" + px2dip(this, displayMetrics.widthPixels, displayMetrics.density)
                + ", heightDp=" + px2dip(this, displayMetrics.heightPixels, displayMetrics.density);
        ((TextView) findViewById(R.id.txt_dimen)).setText(text);
    }

    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //4.2开始有虚拟导航栏，增加了该方法才能准确获取屏幕高度
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics;
    }

    public static int dip2px(Context context, float dpValue, float density) {
        return (int) (dpValue * density + 0.5f);
    }

    public static int px2dip(Context context, float pxValue, float density) {
        return (int) (pxValue / density + 0.5f);
    }

}
