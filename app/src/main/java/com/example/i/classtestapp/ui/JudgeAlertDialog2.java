package com.example.i.classtestapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener2;

/**
 * Created by I on 2017/6/10.
 */

public class JudgeAlertDialog2 extends AlertDialog {
    private Context mContext;
    private Button mBtnOk;
    private Button mBtnCancel;
    private EditText textViewquestion;
    private SettingListener2 mSettingListener2;

    public JudgeAlertDialog2(Context context) {
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
        textViewquestion.setText(R.string.qu2);
    }

    private void initData() {
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettingListener2 != null) {
                    mSettingListener2.onSetting2(true);
                }
                dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettingListener2 != null) {
                    mSettingListener2.onSetting2(false);
                }
                dismiss();
            }
        });
    }

    public void setOnSettingListener2(SettingListener2 listener2) {
        mSettingListener2 = listener2;
    }






}
