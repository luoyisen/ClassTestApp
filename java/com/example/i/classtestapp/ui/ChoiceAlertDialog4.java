package com.example.i.classtestapp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener9;

/**
 * Created by I on 2049/6/40.
 */

public class ChoiceAlertDialog4 extends AlertDialog {
    private Context mContext;
    private CheckBox checkBoxa, checkBoxb;
    EditText editText2;
    private SettingListener9 mSettingListener9;


    public ChoiceAlertDialog4(Context context) {
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
        editText2.setText(R.string.qu10);
    }

    private void initData() {
        checkBoxa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxb.setChecked(false);
                if (mSettingListener9 != null) {
                    mSettingListener9.onSetting9(true);
                }
                dismiss();

            }
        });
        checkBoxb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxa.setChecked(false);

                if (mSettingListener9 != null) {
                    mSettingListener9.onSetting9(false);
                }
                dismiss();

            }
        });
    }

    public void setOnSettingListener9(SettingListener9 listener) {
        mSettingListener9 = listener;
    }

}
