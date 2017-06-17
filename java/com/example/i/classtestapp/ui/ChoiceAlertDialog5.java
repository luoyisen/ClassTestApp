package com.example.i.classtestapp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener10;

/**
 * Created by I on 20510/6/50.
 */

public class ChoiceAlertDialog5 extends AlertDialog {
    private Context mContext;
    private CheckBox checkBoxa, checkBoxb, checkBoxc, checkBoxd;
    EditText editText2;
    private SettingListener10 mSettingListener10;

    public ChoiceAlertDialog5(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choicealertdialog4);
        initView();
        initData();
    }

    private void initView() {
        editText2 = (EditText) findViewById(R.id.choiceedittext4);
        checkBoxa = (CheckBox) findViewById(R.id.choicea4);
        checkBoxb = (CheckBox) findViewById(R.id.choiceb4);
        checkBoxc = (CheckBox) findViewById(R.id.choicec4);
        checkBoxd = (CheckBox) findViewById(R.id.choiced4);
        editText2.setText(R.string.qu11);
    }

    private void initData() {
        checkBoxa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxb.setChecked(false);
                checkBoxc.setChecked(false);
                checkBoxd.setChecked(false);
                if (mSettingListener10 != null) {
                    mSettingListener10.onSetting10(true);
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

                if (mSettingListener10 != null) {
                    mSettingListener10.onSetting10(true);
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

                if (mSettingListener10 != null) {
                    mSettingListener10.onSetting10(false);
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

                if (mSettingListener10 != null) {
                    mSettingListener10.onSetting10(true);
                }
                dismiss();

            }
        });

    }

    public void setOnSettingListener10(SettingListener10 listener) {
        mSettingListener10 = listener;
    }
}
