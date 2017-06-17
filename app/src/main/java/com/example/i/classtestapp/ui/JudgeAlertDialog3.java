package com.example.i.classtestapp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener3;

/**
 * Created by I on 3017/6/10.
 *
 */

public class JudgeAlertDialog3 extends AlertDialog {
    Context mContext;
    private Button mBtnOk;
    private Button mBtnCancel;
    private EditText textViewquestion;
    private SettingListener3 mSettingListener3;

    public JudgeAlertDialog3(Context context) {
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
        textViewquestion.setText(R.string.qu3);
    }

    private void initData() {
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettingListener3 != null) {
                    mSettingListener3.onSetting3(true);
                }
                dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettingListener3 != null) {
                    mSettingListener3.onSetting3(false);
                }
                dismiss();
            }
        });
    }

    public void setOnSettingListener3(SettingListener3 listener3) {
        mSettingListener3 = listener3;
    }
}
