package com.example.i.classtestapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.i.classtestapp.interfaces.SettingListener;
import com.example.i.classtestapp.service.PlayBackgroundMusic;
import com.example.i.classtestapp.ui.ChoiceAlertDialog;
import com.example.i.classtestapp.views.TextViewTypeface;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends Activity {
    @BindView(R.id.apple1)
    ImageButton apple1;
    @BindView(R.id.apple12)
    ImageButton apple12;
    @BindView(R.id.apple2)
    ImageButton apple2;
    @BindView(R.id.apple3)
    ImageButton apple3;
    @BindView(R.id.apple13)
    ImageButton apple13;
    @BindView(R.id.apple4)
    ImageButton apple4;
    @BindView(R.id.apple14)
    ImageButton apple14;
    @BindView(R.id.apple5)
    ImageButton apple5;
    @BindView(R.id.apple15)
    ImageButton apple15;
    @BindView(R.id.apple6)
    ImageButton apple6;
    @BindView(R.id.apple16)
    ImageButton apple16;
    @BindView(R.id.apple7)
    ImageButton apple7;
    @BindView(R.id.apple17)
    ImageButton apple17;
    @BindView(R.id.apple8)
    ImageButton apple8;
    @BindView(R.id.apple18)
    ImageButton apple18;
    @BindView(R.id.apple9)
    ImageButton apple9;
    @BindView(R.id.apple10)
    ImageButton apple10;
    @BindView(R.id.apple19)
    ImageButton apple19;
    @BindView(R.id.apple11)
    ImageButton apple11;
    @BindView(R.id.apple20)
    ImageButton apple20;
    @BindView(R.id.basket)
    ImageButton basket;
    @BindView(R.id.btn_changesong)
    FloatingActionButton btnChangesong;
    @BindView(R.id.btn_getanswer)
    FloatingActionButton btnGetanswer;
    @BindView(R.id.btn_getscore)
    FloatingActionButton btnGetscore;
    @BindView(R.id.textview_showsongname)
    TextViewTypeface textviewShowsongname;
    @BindView(R.id.soundonoroff)
    ImageView soundonoroff;
    @BindView(R.id.seekbar_changevolume)
    SeekBar seekbarChangevolume;
    @BindView(R.id.nowplayinglocationintime)
    TextViewTypeface nowplayinglocationintime;
    @BindView(R.id.nowplayinglocationinprogress)
    ProgressBar nowplayinglocationinprogress;
    @BindView(R.id.totalplayingtime)
    TextViewTypeface totalplayingtime;
    private int finishedquestions = 0;
    private static int score = 0;
    private int soundonoroffclicktimes = 0;
    private AudioManager audioManager;
    public int currentVolume;
    public int previousvolume;
    public Intent startserviceIntent;
    public static final String CTRL_TO_CHANGE_SONG_ACTION = "手动切换歌曲的Action";
    public static final String AUTOMACIT_TO_CHANGE_SONG_ACTION = "自动切换歌曲的Action";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_viewpager);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekbarChangevolume.setMax(maxVolume / 2);
        currentVolume = seekbarChangevolume.getProgress();
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);

        seekbarChangevolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (soundonoroffclicktimes % 2 == 1) {
                        soundonoroff.setImageResource(R.drawable.soundon);//变图标和
                        ++soundonoroffclicktimes;
                    }
                    currentVolume = progress;
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        nowplayinglocationinprogress.setProgress(23);
        btnGetanswer.setClickable(false);
        initMusic();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {//控制关闭屏幕或者开启屏幕以后不进行activity的oncreate()方法
        super.onConfigurationChanged(newConfig);
    }

    private void initMusic() {
        ActivityReceiver activityreceiver = new ActivityReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(AUTOMACIT_TO_CHANGE_SONG_ACTION);
        registerReceiver(activityreceiver, filter);
        startserviceIntent = new Intent(this, PlayBackgroundMusic.class);
        startService(startserviceIntent);
    }

    @OnClick({R.id.apple1, R.id.apple12, R.id.apple2, R.id.apple3, R.id.apple13, R.id.apple4, R.id.apple14, R.id.apple5, R.id.apple15, R.id.apple6, R.id.apple16, R.id.apple7, R.id.apple17, R.id.apple8, R.id.apple18, R.id.apple9, R.id.apple10, R.id.apple19, R.id.apple11, R.id.apple20, R.id.basket, R.id.btn_changesong, R.id.btn_getanswer, R.id.btn_getscore, R.id.soundonoroff})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_changesong:
//                new Thread() {
//                    @Override
//                    public void run() {
//                        super.run();
//                        for (int i = 0; i < 500; i++) {
//                            try {
//                                Thread.sleep(1000);
//                                handler.sendEmptyMessage(1);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }.start();
                sendBroadcast(new Intent(CTRL_TO_CHANGE_SONG_ACTION));
                break;
            case R.id.soundonoroff:
                ++soundonoroffclicktimes;
                if (soundonoroffclicktimes % 2 == 1) {
                    soundonoroff.setImageResource(R.drawable.soundoff);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                    previousvolume = currentVolume;
                    currentVolume = 0;
                    seekbarChangevolume.setProgress(0);
                } else {
                    seekbarChangevolume.post(new Runnable() {
                        @Override
                        public void run() {
                            seekbarChangevolume.setProgress(previousvolume);
                        }
                    });
                    soundonoroff.setImageResource(R.drawable.soundon);
                    currentVolume = previousvolume;
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, previousvolume, 0);
                }
                break;
            case R.id.apple1:
                apple1.setClickable(false);
                basket.setX(getScreenwidth(this) / 22 - 50);
                final ChoiceAlertDialog choiceAlertDialog1 = new ChoiceAlertDialog(this);
                choiceAlertDialog1.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog1.setQuestion("单片机中，需要接上拉电阻的I/O端口是（ ）");
                choiceAlertDialog1.setChoiceA("p0");
                choiceAlertDialog1.setChoiceB("p1");
                choiceAlertDialog1.setChoiceC("p2");
                choiceAlertDialog1.setChoiceD("p3");
                choiceAlertDialog1.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (a && !b && !c && !d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple1);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple1);
                        }
                    }
                });
                choiceAlertDialog1.show();
                break;
            case R.id.apple2:
                apple2.setClickable(false);
                basket.setX(getScreenwidth(this) / 22 * 3 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog2 = new ChoiceAlertDialog(this);
                choiceAlertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog2.setQuestion("C51中，用于定义位操作的关键字是（ ）");
                choiceAlertDialog2.setChoiceA("include");
                choiceAlertDialog2.setChoiceB("define");
                choiceAlertDialog2.setChoiceC("sbit");
                choiceAlertDialog2.setChoiceD("void");
                choiceAlertDialog2.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && !b && c && !d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple2);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple2);
                        }
                    }
                });
                choiceAlertDialog2.show();
                break;
            case R.id.apple3:
                apple3.setClickable(false);
                basket.setX(getScreenwidth(this) / 22 * 5 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog3 = new ChoiceAlertDialog(this);
                choiceAlertDialog3.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog3.setQuestion("多位数码管同时显示时，一般采用（ ）");
                choiceAlertDialog3.setChoiceA("静态扫描显示");
                choiceAlertDialog3.setChoiceB("动态扫描显示");
                choiceAlertDialog3.setChoiceC("");
                choiceAlertDialog3.setChoiceD("");
                choiceAlertDialog3.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && b) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple3);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple3);
                        }
                    }
                });
                choiceAlertDialog3.show();
                break;
            case R.id.apple4:
                apple4.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 7 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog4 = new ChoiceAlertDialog(this);
                choiceAlertDialog4.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog4.setQuestion("采用T0定时器欲定时50ms，应采用哪种工作方式？");
                choiceAlertDialog4.setChoiceA("方式0");
                choiceAlertDialog4.setChoiceB("方式1");
                choiceAlertDialog4.setChoiceC("方式2");
                choiceAlertDialog4.setChoiceD("方式3");
                choiceAlertDialog4.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && b && !c && !d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple4);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple4);
                        }
                    }
                });
                choiceAlertDialog4.show();
                break;
            case R.id.apple5:
                apple5.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 9 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog5 = new ChoiceAlertDialog(this);
                choiceAlertDialog5.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog5.setQuestion("按键在按下的时候，其抖动时间一般为？");
                choiceAlertDialog5.setChoiceA("0-5ms");
                choiceAlertDialog5.setChoiceB("0-10ms");
                choiceAlertDialog5.setChoiceC("0-15ms");
                choiceAlertDialog5.setChoiceD("0-20ms");
                choiceAlertDialog5.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && !b && !c && d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple5);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple5);
                        }
                    }
                });
                choiceAlertDialog5.show();
                break;
            case R.id.apple11:
                apple11.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 22 - getScreenwidth(this) / 192 * 16);
                final ChoiceAlertDialog choiceAlertDialog11 = new ChoiceAlertDialog(this);
                choiceAlertDialog11.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog11.setQuestion("单片机中，需要接上拉电阻的I/O端口是（ ）");
                choiceAlertDialog11.setChoiceA("p0");
                choiceAlertDialog11.setChoiceB("p1");
                choiceAlertDialog11.setChoiceC("p2");
                choiceAlertDialog11.setChoiceD("p3");
                choiceAlertDialog11.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (a && !b && !c && !d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple11);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple11);
                        }
                    }
                });
                choiceAlertDialog11.show();
                break;
            case R.id.apple12:
                apple12.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog12 = new ChoiceAlertDialog(this);
                choiceAlertDialog12.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog12.setQuestion("下列哪些事件会导致LED故障？");
                choiceAlertDialog12.setChoiceA("LED损坏");
                choiceAlertDialog12.setChoiceB("限流电阻过大");
                choiceAlertDialog12.setChoiceC("无限流电阻");
                choiceAlertDialog12.setChoiceD("LED接点接触不良");
                choiceAlertDialog12.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (a && b && c && d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple12);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple12);
                        }
                    }
                });
                choiceAlertDialog12.show();
                break;
            case R.id.apple13:
                apple13.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 5 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog13 = new ChoiceAlertDialog(this);
                choiceAlertDialog13.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog13.setQuestion("下列哪些事件会导致蜂鸣器不出声？");
                choiceAlertDialog13.setChoiceA("蜂鸣器电源故障");
                choiceAlertDialog13.setChoiceB("单片机最小系统故障");
                choiceAlertDialog13.setChoiceC("蜂鸣器损坏");
                choiceAlertDialog13.setChoiceD("蜂鸣器引脚接点断开");
                choiceAlertDialog13.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (a && b && c && d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple13);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple13);
                        }
                    }
                });
                choiceAlertDialog13.show();
                break;
            case R.id.apple14:
                apple14.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 7 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog14 = new ChoiceAlertDialog(this);
                choiceAlertDialog14.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog14.setQuestion("下列哪些现象属于按键的故障现象？");
                choiceAlertDialog14.setChoiceA("按键检测灵敏");
                choiceAlertDialog14.setChoiceB("按键按下无反应");
                choiceAlertDialog14.setChoiceC("按键多检");
                choiceAlertDialog14.setChoiceD("按键少检");
                choiceAlertDialog14.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && b && c && d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple14);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple14);
                        }
                    }
                });
                choiceAlertDialog14.show();
                break;
            case R.id.apple15:
                apple15.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 9 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog15 = new ChoiceAlertDialog(this);
                choiceAlertDialog15.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog15.setQuestion("下列哪些现象属于数码管显示的故障现象？");
                choiceAlertDialog15.setChoiceA("数码管不显示");
                choiceAlertDialog15.setChoiceB("数码管显示正常");
                choiceAlertDialog15.setChoiceC("数码管显示断码缺失");
                choiceAlertDialog15.setChoiceD("数码管显示闪烁不稳定");
                choiceAlertDialog15.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (a && !b && c && d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple15);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple15);
                        }
                    }
                });
                choiceAlertDialog15.show();
                break;
            case R.id.apple16:
                apple16.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 11 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog16 = new ChoiceAlertDialog(this);
                choiceAlertDialog16.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog16.setQuestion("单片机中，需要接上拉电阻的I/O端口是（ ）");
                choiceAlertDialog16.setChoiceA("p0");
                choiceAlertDialog16.setChoiceB("p1");
                choiceAlertDialog16.setChoiceC("p2");
                choiceAlertDialog16.setChoiceD("p3");
                choiceAlertDialog16.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (a && !b && !c && !d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple16);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple16);
                        }
                    }
                });
                choiceAlertDialog16.show();
                break;
            case R.id.apple17:
                apple17.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 13 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog17 = new ChoiceAlertDialog(this);
                choiceAlertDialog17.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog17.setQuestion("按键在按下的时候，其抖动时间一般为？");
                choiceAlertDialog17.setChoiceA("0-5ms");
                choiceAlertDialog17.setChoiceB("0-10ms");
                choiceAlertDialog17.setChoiceC("0-15ms");
                choiceAlertDialog17.setChoiceD("0-20ms");
                choiceAlertDialog17.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && !b && !c && d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple17);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple17);
                        }
                    }
                });
                choiceAlertDialog17.show();
                break;
            case R.id.apple18:
                apple18.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 15 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog18 = new ChoiceAlertDialog(this);
                choiceAlertDialog18.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog18.setQuestion("C51中，用于定义位操作的关键字是（ ）");
                choiceAlertDialog18.setChoiceA("include");
                choiceAlertDialog18.setChoiceB("define");
                choiceAlertDialog18.setChoiceC("sbit");
                choiceAlertDialog18.setChoiceD("void");
                choiceAlertDialog18.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && !b && c && !d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple18);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple18);
                        }
                    }
                });
                choiceAlertDialog18.show();
                break;
            case R.id.apple19:
                apple19.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 19 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog19 = new ChoiceAlertDialog(this);
                choiceAlertDialog19.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog19.setQuestion("采用T0定时器欲定时50ms，应采用哪种工作方式？");
                choiceAlertDialog19.setChoiceA("方式0");
                choiceAlertDialog19.setChoiceB("方式1");
                choiceAlertDialog19.setChoiceC("方式2");
                choiceAlertDialog19.setChoiceD("方式3");
                choiceAlertDialog19.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && b && !c && !d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple19);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple19);
                        }
                    }
                });
                choiceAlertDialog19.show();
                break;
            case R.id.apple20:
                apple20.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 22 - getScreenwidth(this) / 192 * 16);
                final ChoiceAlertDialog choiceAlertDialog20 = new ChoiceAlertDialog(this);
                choiceAlertDialog20.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog20.setQuestion("多位数码管同时显示时，一般采用（ ）");
                choiceAlertDialog20.setChoiceA("静态扫描显示");
                choiceAlertDialog20.setChoiceB("动态扫描显示");
                choiceAlertDialog20.setChoiceC("");
                choiceAlertDialog20.setChoiceD("");
                choiceAlertDialog20.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && b) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple20);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple20);
                        }
                    }
                });
                choiceAlertDialog20.show();
                break;
            case R.id.apple6:
                apple6.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 11 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog6 = new ChoiceAlertDialog(this);
                choiceAlertDialog6.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog6.setQuestion("C51中，用于定义位操作的关键字是（ ）");
                choiceAlertDialog6.setChoiceA("include");
                choiceAlertDialog6.setChoiceB("define");
                choiceAlertDialog6.setChoiceC("sbit");
                choiceAlertDialog6.setChoiceD("void");
                choiceAlertDialog6.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && !b && c && !d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple6);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple6);
                        }
                    }
                });
                choiceAlertDialog6.show();
                break;
            case R.id.apple7:
                apple7.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 13 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog7 = new ChoiceAlertDialog(this);
                choiceAlertDialog7.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog7.setQuestion("下列哪些现象属于数码管显示的故障现象？");
                choiceAlertDialog7.setChoiceA("数码管不显示");
                choiceAlertDialog7.setChoiceB("数码管显示正常");
                choiceAlertDialog7.setChoiceC("数码管显示断码缺失");
                choiceAlertDialog7.setChoiceD("数码管显示闪烁不稳定");
                choiceAlertDialog7.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (a && !b && c && d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple7);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple7);
                        }
                    }
                });
                choiceAlertDialog7.show();
                break;
            case R.id.apple8:
                apple8.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 15 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog8 = new ChoiceAlertDialog(this);
                choiceAlertDialog8.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog8.setQuestion("下列哪些现象属于按键的故障现象？");
                choiceAlertDialog8.setChoiceA("按键检测灵敏");
                choiceAlertDialog8.setChoiceB("按键按下无反应");
                choiceAlertDialog8.setChoiceC("按键多检");
                choiceAlertDialog8.setChoiceD("按键少检");
                choiceAlertDialog8.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (!a && b && c && d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple8);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple8);
                        }
                    }
                });
                choiceAlertDialog8.show();
                break;
            case R.id.apple9:
                apple9.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 17 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog9 = new ChoiceAlertDialog(this);
                choiceAlertDialog9.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog9.setQuestion("下列哪些事件会导致蜂鸣器不出声？");
                choiceAlertDialog9.setChoiceA("蜂鸣器电源故障");
                choiceAlertDialog9.setChoiceB("单片机最小系统故障");
                choiceAlertDialog9.setChoiceC("蜂鸣器损坏");
                choiceAlertDialog9.setChoiceD("蜂鸣器引脚接点断开");
                choiceAlertDialog9.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (a && b && c && d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple9);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple9);
                        }
                    }
                });
                choiceAlertDialog9.show();
                break;
            case R.id.apple10:
                apple10.setClickable(false);

                basket.setX(getScreenwidth(this) / 22 * 19 - getScreenwidth(this) / 192 * 10);
                final ChoiceAlertDialog choiceAlertDialog10 = new ChoiceAlertDialog(this);
                choiceAlertDialog10.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog10.setQuestion("下列哪些事件会导致LED故障？");
                choiceAlertDialog10.setChoiceA("LED损坏");
                choiceAlertDialog10.setChoiceB("限流电阻过大");
                choiceAlertDialog10.setChoiceC("无限流电阻");
                choiceAlertDialog10.setChoiceD("LED接点接触不良");
                choiceAlertDialog10.setOnSettingListener(new SettingListener() {
                    @Override
                    public void onResult(boolean a, boolean b, boolean c, boolean d) {
                        if (a && b && c && d) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple10);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple10);
                        }
                    }
                });
                choiceAlertDialog10.show();
                break;
            case R.id.btn_getanswer:
                if (finishedquestions == 20) {
                    btnGetanswer.setClickable(true);
                    startActivity(new Intent(this, AboutActivity.class));
                    finishedquestions = 20;
                }
                break;
            case R.id.btn_getscore:
                String ss = "做完了:" + finishedquestions + "   " + "得分:" + score + "";
                Toast.makeText(getApplicationContext(), ss, Toast.LENGTH_LONG).show();
                break;
        }
    }

    public class ActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String currentSongname = intent.getStringExtra("currentSongname");
            String currentSong = intent.getStringExtra("currentSong");
            textviewShowsongname.setText(currentSongname);
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            //String string = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "/netease/cloudmusic/Music/Alan Walker - Spectre.mp3";
//            //String string = "assets/" + currentSong;
            //String duration = null;
            AssetFileDescriptor assetFileDescriptor = null;
            try {
                assetFileDescriptor = getAssets().openFd(currentSong);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Long fileSize = assetFileDescriptor.getLength();
            long duration = fileSize / 320000 * 8;
            //String s = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
            //long bitRate = Long.parseLong(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE));
            //long duration = (fileSize * 8) / (bitRate);
            //FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();

//                afd = assetManager.openFd(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "/netease/cloudmusic/Music/Alan Walker - Spectre.mp3");

            String s = duration + "";
//            try {
//                mediaMetadataRetriever.setDataSource(fileDescriptor);
            //duration = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//            String durations = duration + "";
            //s = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            }

            totalplayingtime.setText(s);
        }
    }


//    @Override
//    public void onSetting1(boolean isfalse) {
//        if (!isfalse) {
//            ++score;
//            finishedquestions += 1;
//            startAnimations(apple1);
//        } else {
//            ++finishedquestions;
//            startShakeAnimations(apple1);
//        }
//        apple1.setClickable(false);
//    }

    public void startAnimations(final View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, basket.getY() - view.getY());
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setDuration(700);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (finishedquestions == 20) {
                    btnGetanswer.setClickable(true);
                }
                view.setVisibility(view.INVISIBLE);
                final MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.appledrop);
                player.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(translateAnimation);
    }

    public void startShakeAnimations(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animationshake);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (finishedquestions == 20) {
                    btnGetanswer.setClickable(true);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(animation);
    }

    //获取屏幕密度
    public int getScreenwidth(Context context) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stopService(startserviceIntent);
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("退出还是重新来过?")
                .setContentText("或者你还是再按一次返回键吧！")
                .setCancelText("重新来过!")
                .setConfirmText("退出算了!")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        reset();
                        sDialog.setTitleText("重置成功!")
                                .setContentText("你又得到了一次机会，好好珍惜！！！")
                                .setConfirmText("开始答题")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        stopService(startserviceIntent);
                        System.exit(0);
                        MainActivity.super.onBackPressed();
                    }
                })
                .show();
    }

    public void reset() {
        apple1.setVisibility(View.VISIBLE);
        apple2.setVisibility(View.VISIBLE);
        apple3.setVisibility(View.VISIBLE);
        apple4.setVisibility(View.VISIBLE);
        apple5.setVisibility(View.VISIBLE);
        apple6.setVisibility(View.VISIBLE);
        apple7.setVisibility(View.VISIBLE);
        apple8.setVisibility(View.VISIBLE);
        apple9.setVisibility(View.VISIBLE);
        apple10.setVisibility(View.VISIBLE);
        apple11.setVisibility(View.VISIBLE);
        apple12.setVisibility(View.VISIBLE);
        apple13.setVisibility(View.VISIBLE);
        apple14.setVisibility(View.VISIBLE);
        apple15.setVisibility(View.VISIBLE);
        apple16.setVisibility(View.VISIBLE);
        apple17.setVisibility(View.VISIBLE);
        apple18.setVisibility(View.VISIBLE);
        apple19.setVisibility(View.VISIBLE);
        apple20.setVisibility(View.VISIBLE);
        apple1.setClickable(true);
        apple2.setClickable(true);
        apple3.setClickable(true);
        apple4.setClickable(true);
        apple5.setClickable(true);
        apple6.setClickable(true);
        apple7.setClickable(true);
        apple8.setClickable(true);
        apple9.setClickable(true);
        apple10.setClickable(true);
        apple11.setClickable(true);
        apple12.setClickable(true);
        apple13.setClickable(true);
        apple14.setClickable(true);
        apple15.setClickable(true);
        apple16.setClickable(true);
        apple17.setClickable(true);
        apple18.setClickable(true);
        apple19.setClickable(true);
        apple20.setClickable(true);
        finishedquestions = 0;
        score = 0;
    }
}

