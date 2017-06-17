package com.example.i.classtestapp.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.i.classtestapp.R;

/**
 * Created by I on 2017/6/12.
 */

public class VolumeSetDialog extends Dialog {
    public VolumeSetDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_setvolume);
    }
}
