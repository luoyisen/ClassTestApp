package com.example.i.classtestapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by I on 2017/6/11.
 */

public class AboutActivity extends Activity {
    public int clickcount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private boolean quit = false; //设置退出标识

//    @Override
//    public void onBackPressed() {
//        if (!quit) { //询问退出程序
//            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            new Timer(true).schedule(new TimerTask() { //启动定时任务
//                @Override
//                public void run() {
//                    quit = false; //重置退出标识
//                }
//            }, 2000); //2秒后运行run()方法
//            quit = true;
//        } else { //确认退出程序
//            super.onBackPressed();
//            finish();
//        }
//    }

    @Override
    public void onBackPressed() {
        ++clickcount;
        if (clickcount == 1) {
            Toast.makeText(this, "双击退出", Toast.LENGTH_LONG).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    clickcount = 0;
                }
            }, 1000);
            clickcount = 1;
        } else {

            super.onBackPressed();
            finish();
        }
    }
}
