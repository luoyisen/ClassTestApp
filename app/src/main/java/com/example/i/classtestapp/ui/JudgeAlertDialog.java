package com.example.i.classtestapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener;

/**
 * Created by I on 2017/6/9.
 */

public class JudgeAlertDialog extends AlertDialog {
    private Context mContext;
    private Button mBtnright;
    private Button mBtnfalse;
    private EditText textViewquestion;
    private SettingListener mSettingListener;

    public JudgeAlertDialog(Context context) {
        super(context);
        mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.judgealertdialog);
        initView();
        initData();
    }
    private void initView() {
        mBtnright = (Button) findViewById(R.id.right);
        mBtnfalse = (Button) findViewById(R.id.wrong);
        textViewquestion = (EditText) findViewById(R.id.textviewquestion);
        textViewquestion.setText(R.string.qu1);
    }

    private void initData() {
        mBtnright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBtnfalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setOnSettingListener1(SettingListener listener) {
        mSettingListener = listener;
    }



}

