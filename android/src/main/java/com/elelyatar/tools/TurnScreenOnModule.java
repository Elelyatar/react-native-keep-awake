// Adapted from
// https://github.com/corbt/react-native-keep-awake

package com.elelyatar.tools;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import android.view.WindowManager;
import android.hardware.display.DisplayManager;
import android.view.Display;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.concurrent.TimeUnit;

public class TurnScreenOnModule extends ReactContextBaseJavaModule {

    public TurnScreenOnModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "TurnScreenOn";
    }

    @ReactMethod
    public void activate() {
        final Activity activity = getCurrentActivity();

        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                        try {
                            activity.setTurnScreenOn(true);
                            activity.setShowWhenLocked(true);
                        } catch (Exception e) {
                            Log.e(e.getMessage(), "Enable setTurnScreenOn and setShowWhenLocked is not present on device!");
                        }
                    } else {
                        PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
                        pm.newWakeLock(
                            PowerManager.FULL_WAKE_LOCK
                            | PowerManager.ACQUIRE_CAUSES_WAKEUP
                            | PowerManager.ON_AFTER_RELEASE,
                            "mywakelock"
                        ).acquire(TimeUnit.SECONDS.toMillis(5));
                        activity.getWindow().addFlags(
                            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                            | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        );
                    }
                }
            });
        }
    }

    @ReactMethod
    public void deactivate() {
        final Activity activity = getCurrentActivity();

        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
                    activity.getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
                }
            });
        }
    }

    @ReactMethod
    public void isScreenOn(Callback callback) {
        ReactApplicationContext context = getReactApplicationContext();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
            boolean screenOn = false;
            for (Display display : dm.getDisplays()) {
                if (display.getState() != Display.STATE_OFF) {
                    screenOn = true;
                }
            }
            callback.invoke(screenOn);
        } else {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            //noinspection deprecation
            callback.invoke(pm.isScreenOn());
        }
    }
}