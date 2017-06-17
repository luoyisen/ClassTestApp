package com.example.i.classtestapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener5;

/**
 * Created by I on 2057/6/50.
 */

public class JudgeAlertDialog5 extends AlertDialog {
    private Context mContext;
    private Button mBtnOk;
    private Button mBtnCancel;
    private EditText textViewquestion;

    private SettingListener5 mSettingListener5;

    public JudgeAlertDialog5(Context context) {
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
        textViewquestion.setText(R.string.qu5);
    }

    private void initData() {
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettingListener5 != null) {
                    mSettingListener5.onSetting5(false);
                }
                dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettingListener5 != null) {
                    mSettingListener5.onSetting5(true);
                }
                dismiss();
            }
        });
    }

    public void setOnSettingListener5(SettingListener5 listener) {
        mSettingListener5 = listener;
    }


}
