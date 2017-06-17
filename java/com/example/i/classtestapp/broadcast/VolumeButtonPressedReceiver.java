package com.example.i.classtestapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by I on 2017/6/13.
 */

public class VolumeButtonPressedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
            int newVolume = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
            int oldVolume = intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", 0);
            if (newVolume != oldVolume) {
//                Intent i = new Intent();
//                i.setClass(context, MainActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(context, "changed", Toast.LENGTH_LONG).show();
            }
        }

    }
}
