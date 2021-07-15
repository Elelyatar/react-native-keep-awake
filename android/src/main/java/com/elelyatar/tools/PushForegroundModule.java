package com.elelyatar.tools;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.Class;
import java.util.List;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

class PushForegroundModule extends ReactContextBaseJavaModule {

    PushForegroundModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "PushForeground";
    }

    @ReactMethod
    public String getCurrentForeground() {
        ActivityManager am = (ActivityManager) getReactApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        Log.d("topActivity", "CURRENT Activity ::" + taskInfo.get(0).topActivity.getClassName());
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        return componentInfo.getPackageName();
    }

    @ReactMethod
    public void setForegroundActivity(String activityToStart) {
        try {
            ReactApplicationContext context = getReactApplicationContext();
            Class<?> c = Class.forName(activityToStart);
            Intent intent = new Intent(context, c);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.d(e.getMessage(), "ClassNotFoundException");
        }
    }

    @ReactMethod
    public void setForegroundPackage(String packageName) {
        ReactApplicationContext context = getReactApplicationContext();
        PackageManager pm = context.getPackageManager();
        try {
            context.startActivity(pm.getLaunchIntentForPackage(packageName));
        } catch (Exception e) {
            Log.d(e.getMessage(), "ZANAHORIA Package could not start");
        }
    }
}