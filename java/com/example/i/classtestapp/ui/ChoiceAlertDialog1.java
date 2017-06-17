package com.example.i.classtestapp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener6;

/**
 * Created by I on 2017/6/10.
 */

public class ChoiceAlertDialog1 extends AlertDialog {
    private Context mContext;
    private CheckBox checkBoxa, checkBoxb, checkBoxc, checkBoxd;
    EditText editText1;
    private SettingListener6 mSettingListener6;


    public ChoiceAlertDialog1(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choicealertdialog);
        initView();
        initData();
    }

    private void initView() {
        editText1 = (EditText) findViewById(R.id.choiceedittext);
        checkBoxa = (CheckBox) findViewById(R.id.choicea);
        checkBoxb = (CheckBox) findViewById(R.id.choiceb);
        checkBoxc = (CheckBox) findViewById(R.id.choicec);
        checkBoxd = (CheckBox) findViewById(R.id.choiced);
        editText1.setText(R.string.qu7);
    }

    private void initData() {
        checkBoxa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxb.setChecked(false);
                checkBoxc.setChecked(false);
                checkBoxd.setChecked(false);
                if (mSettingListener6 != null) {
                    mSettingListener6.onSetting6(true);
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

                if (mSettingListener6 != null) {
                    mSettingListener6.onSetting6(false);
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

                if (mSettingListener6 != null) {
                    mSettingListener6.onSetting6(true);
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

                if (mSettingListener6 != null) {
                    mSettingListener6.onSetting6(true);
                }
                dismiss();

            }
        });
    }

    public void setOnSettingListener6(SettingListener6 listener) {
        mSettingListener6 = listener;
    }

}
