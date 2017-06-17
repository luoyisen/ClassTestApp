package com.example.i.classtestapp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener7;

/**
 * Created by I on 2027/7/20.
 */

public class ChoiceAlertDialog2 extends AlertDialog {
    private Context mContext;
    private CheckBox checkBoxa, checkBoxb, checkBoxc, checkBoxd;
    EditText editText2;
    private SettingListener7 mSettingListener7;


    public ChoiceAlertDialog2(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choicealertdialog3);
        initView();
        initData();
    }

    private void initView() {
        editText2 = (EditText) findViewById(R.id.choiceedittext3);
        checkBoxa = (CheckBox) findViewById(R.id.choicea3);
        checkBoxb = (CheckBox) findViewById(R.id.choiceb3);
        checkBoxc = (CheckBox) findViewById(R.id.choicec3);
        checkBoxd = (CheckBox) findViewById(R.id.choiced3);
        editText2.setText(R.string.qu8);
    }

    private void initData() {
        checkBoxa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxb.setChecked(false);
                checkBoxc.setChecked(false);
                checkBoxd.setChecked(false);
                if (mSettingListener7 != null) {
                    mSettingListener7.onSetting7(true);
                }
                dismiss();

            }
        });
        checkBoxb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxa.setChecked(false);
                checkBoxc.setChecked(false);
                checkBoxd.setChecked(false);

                if (mSettingListener7 != null) {
                    mSettingListener7.onSetting7(true);
                }
                dismiss();

            }
        });
        checkBoxc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxa.setChecked(false);
                checkBoxb.setChecked(false);
                checkBoxd.setChecked(false);

                if (mSettingListener7 != null) {
                    mSettingListener7.onSetting7(false);
                }
                dismiss();

            }
        });
        checkBoxd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxa.setChecked(false);
                checkBoxb.setChecked(false);
                checkBoxc.setChecked(false);

                if (mSettingListener7 != null) {
                    mSettingListener7.onSetting7(true);
                }
                dismiss();

            }
        });

    }

    public void setOnSettingListener7(SettingListener7 listener) {
        mSettingListener7 = listener;
    }

}
