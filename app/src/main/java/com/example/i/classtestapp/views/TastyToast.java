package com.example.i.classtestapp.views;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.i.classtestapp.R;

/**
 * Created by I on 2017/6/11.
 */

public class TastyToast {
    static DefaultToastView defaultToastView;

    public static Toast makeText(Context context, String msg, int length) {

        Toast toast = new Toast(context);


        View layout = LayoutInflater.from(context).inflate(R.layout.default_toast_layout, null, false);

        TextView text = (TextView) layout.findViewById(R.id.toastMessage);
        text.setText(msg);
        defaultToastView = (DefaultToastView) layout.findViewById(R.id.defaultView);
        defaultToastView.startAnim();
        text.setBackgroundResource(R.drawable.default_toast);
        text.setTextColor(Color.parseColor("#FFFFFF"));
        toast.setView(layout);
        toast.setDuration(length);
        toast.show();
        return toast;
    }

}
