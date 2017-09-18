package com.example.i.classtestapp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.i.classtestapp.R;
import com.example.i.classtestapp.interfaces.SettingListener;

/***
 * Created by I on 2017/6/10.
 */

public class ChoiceAlertDialog extends AlertDialog {
    private Context mContext;
    private CheckBox checkBoxa, checkBoxb, checkBoxc, checkBoxd;
    EditText editText1;
    private String question, choiceA, choiceB, choiceC, choiceD;
    private SettingListener settingListener;
    private Button confirm_button, rechose_button;
    private int[] choices = new int[4];
    private boolean a, b, c, d;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    public boolean getA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public boolean getB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean getC() {
        return c;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public boolean getD() {
        return d;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public ChoiceAlertDialog(Context context) {
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
        confirm_button = (Button) findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingListener.onResult(getA(), getB(), getC(), getD());
                dismiss();
            }
        });
        findViewById(R.id.rechose_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxa.setOnCheckedChangeListener(null);
                checkBoxb.setOnCheckedChangeListener(null);
                checkBoxc.setOnCheckedChangeListener(null);
                checkBoxd.setOnCheckedChangeListener(null);
                checkBoxa.setChecked(false);
                checkBoxb.setChecked(false);
                checkBoxc.setChecked(false);
                checkBoxd.setChecked(false);
                checkBoxa.setOnCheckedChangeListener(onCheckedChangeListener);
                checkBoxb.setOnCheckedChangeListener(onCheckedChangeListener);
                checkBoxc.setOnCheckedChangeListener(onCheckedChangeListener);
                checkBoxd.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        });
        checkBoxa.setText(getChoiceA());
        checkBoxb.setText(getChoiceB());
        checkBoxc.setText(getChoiceC());
        checkBoxd.setText(getChoiceD());
        editText1.setText(getQuestion());
    }

    private void initData() {
        checkBoxa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxa.isChecked()) {
                    setA(true);
                } else
                    setA(false);
            }
        });
        checkBoxb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxb.isChecked()) {
                    setB(true);
                } else
                    setB(false);

            }
        });
        checkBoxc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxc.isChecked()) {
                    setC(true);
                } else
                    setC(false);

            }
        });
        checkBoxd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxd.isChecked()) {
                    setD(true);
                } else
                    setD(false);

            }
        });
    }

    public void setOnSettingListener(SettingListener listener) {
        settingListener = listener;
    }
}
