package com.example.i.classtestapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener4;

/**
 * Created by I on 2047/6/40.
 */

public class JudgeAlertDialog4 extends AlertDialog {
    private Context mContext;
    private Button mBtnOk;
    private Button mBtnCancel;
    private EditText textViewquestion;
    private SettingListener4 mSettingListener4;

    public JudgeAlertDialog4(Context context) {
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
        mBtnOk = (Button) findViewById(R.id.right);
        mBtnCancel = (Button) findViewById(R.id.wrong);
        textViewquestion = (EditText) findViewById(R.id.textviewquestion);
        textViewquestion.setText(R.string.qu4);
    }

    private void initData() {
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettingListener4 != null) {
                    mSettingListener4.onSetting4(false);
                }
                dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettingListener4 != null) {
                    mSettingListener4.onSetting4(true);
                }
                dismiss();
            }
        });
    }

    public void setOnSettingListener4(SettingListener4 listener) {
        mSettingListener4 = listener;
    }



}
