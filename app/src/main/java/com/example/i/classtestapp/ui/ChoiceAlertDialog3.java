package com.example.i.classtestapp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener8;

/**
 * Created by I on 2038/6/30.
 */

public class ChoiceAlertDialog3 extends AlertDialog {
    private Context mContext;
    private CheckBox checkBoxa, checkBoxb;
    EditText editText2;
    private SettingListener8 mSettingListener8;


    public ChoiceAlertDialog3(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choicealertdialog2);
        initView();
        initData();
    }

    private void initView() {
        editText2 = (EditText) findViewById(R.id.choiceedittext2);
        checkBoxa = (CheckBox) findViewById(R.id.choicea2);
        checkBoxb = (CheckBox) findViewById(R.id.choiceb2);
        editText2.setText(R.string.qu9);
    }

    private void initData() {
        checkBoxa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxb.setChecked(false);
                if (mSettingListener8 != null) {
                    mSettingListener8.onSetting8(false);
                }
                dismiss();

            }
        });
        checkBoxb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxa.setChecked(false);

                if (mSettingListener8 != null) {
                    mSettingListener8.onSetting8(true);
                }
                dismiss();

            }
        });


    }

    public void setOnSettingListener8(SettingListener8 listener) {
        mSettingListener8 = listener;
    }

}
