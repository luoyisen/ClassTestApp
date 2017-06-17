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

import com.example.i.classtestapp.interfaces.SettingListener1;
import com.example.i.classtestapp.interfaces.SettingListener10;
import com.example.i.classtestapp.interfaces.SettingListener11;
import com.example.i.classtestapp.interfaces.SettingListener2;
import com.example.i.classtestapp.interfaces.SettingListener3;
import com.example.i.classtestapp.interfaces.SettingListener4;
import com.example.i.classtestapp.interfaces.SettingListener5;
import com.example.i.classtestapp.interfaces.SettingListener6;
import com.example.i.classtestapp.interfaces.SettingListener7;
import com.example.i.classtestapp.interfaces.SettingListener8;
import com.example.i.classtestapp.interfaces.SettingListener9;
import com.example.i.classtestapp.service.PlayBackgroundMusic;
import com.example.i.classtestapp.ui.ChoiceAlertDialog1;
import com.example.i.classtestapp.ui.ChoiceAlertDialog2;
import com.example.i.classtestapp.ui.ChoiceAlertDialog3;
import com.example.i.classtestapp.ui.ChoiceAlertDialog4;
import com.example.i.classtestapp.ui.ChoiceAlertDialog5;
import com.example.i.classtestapp.ui.JudgeAlertDialog1;
import com.example.i.classtestapp.ui.JudgeAlertDialog11;
import com.example.i.classtestapp.ui.JudgeAlertDialog2;
import com.example.i.classtestapp.ui.JudgeAlertDialog3;
import com.example.i.classtestapp.ui.JudgeAlertDialog4;
import com.example.i.classtestapp.ui.JudgeAlertDialog5;
import com.example.i.classtestapp.views.TextViewTypeface;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends Activity implements SettingListener1 {
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
                basket.setX(getScreenwidth(this) / 22 - 50);
                JudgeAlertDialog1 alertDialog1 = new JudgeAlertDialog1(this);
                //alertDialog1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.roundedcornerbakground));
                alertDialog1.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog1.setOnSettingListener1(this);///监听dialog
                alertDialog1.show();
                break;
            case R.id.apple2:
                basket.setX(getScreenwidth(this) / 22 * 3 - getScreenwidth(this) / 192 * 10);
                JudgeAlertDialog2 alertDialog2 = new JudgeAlertDialog2(this);
                alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog2.setOnSettingListener2(new SettingListener2() {
                    @Override
                    public void onSetting2(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple2);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple2);
                        }
                        apple2.setClickable(false);
                    }
                });
                alertDialog2.show();
                break;
            case R.id.apple3:
                basket.setX(getScreenwidth(this) / 22 * 5 - getScreenwidth(this) / 192 * 10);
                JudgeAlertDialog3 alertDialog3 = new JudgeAlertDialog3(this);
                alertDialog3.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog3.setOnSettingListener3(new SettingListener3() {
                    @Override
                    public void onSetting3(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple3);

                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple3);

                        }
                        apple3.setClickable(false);

                    }
                });
                alertDialog3.show();
                break;
            case R.id.apple4:
                basket.setX(getScreenwidth(this) / 22 * 7 - getScreenwidth(this) / 192 * 10);
                JudgeAlertDialog4 alertDialog4 = new JudgeAlertDialog4(this);
                alertDialog4.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);

                alertDialog4.setOnSettingListener4(new SettingListener4() {
                    @Override
                    public void onSetting4(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple4);

                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple4);
                        }
                        apple4.setClickable(false);

                    }
                });
                alertDialog4.show();
                break;
            case R.id.apple5:
                basket.setX(getScreenwidth(this) / 22 * 9 - getScreenwidth(this) / 192 * 10);
                JudgeAlertDialog5 alertDialog5 = new JudgeAlertDialog5(this);
                alertDialog5.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog5.setOnSettingListener5(new SettingListener5() {
                    @Override
                    public void onSetting5(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple5);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple5);
                        }
                        apple5.setClickable(false);
                    }
                });
                alertDialog5.show();
                break;
            case R.id.apple11:
                basket.setX(getScreenwidth(this) / 22 * 22 - getScreenwidth(this) / 192 * 16);
                JudgeAlertDialog11 alertDialog11 = new JudgeAlertDialog11(this);
                alertDialog11.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog11.setOnSettingListener11(new SettingListener11() {
                    @Override
                    public void onSetting11(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple11);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple11);
                        }
                        apple11.setClickable(false);


                    }
                });
                alertDialog11.show();
                break;
            case R.id.apple12:
                basket.setX(getScreenwidth(this) / 22 - getScreenwidth(this) / 192 * 10);
                JudgeAlertDialog11 alertDialog12 = new JudgeAlertDialog11(this);
                alertDialog12.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog12.setOnSettingListener11(new SettingListener11() {
                    @Override
                    public void onSetting11(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple12);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple12);
                        }
                        apple12.setClickable(false);
                    }
                });
                alertDialog12.show();
                break;
            case R.id.apple13:
                basket.setX(getScreenwidth(this) / 22 * 5 - getScreenwidth(this) / 192 * 10);
                JudgeAlertDialog5 alertDialog13 = new JudgeAlertDialog5(this);
                alertDialog13.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog13.setOnSettingListener5(new SettingListener5() {
                    @Override
                    public void onSetting5(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple13);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple13);
                        }
                        apple13.setClickable(false);
                    }
                });
                alertDialog13.show();
                break;
            case R.id.apple14:
                basket.setX(getScreenwidth(this) / 22 * 7 - getScreenwidth(this) / 192 * 10);
                JudgeAlertDialog3 alertDialog14 = new JudgeAlertDialog3(this);
                alertDialog14.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog14.setOnSettingListener3(new SettingListener3() {
                    @Override
                    public void onSetting3(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple14);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple14);
                        }
                        apple14.setClickable(false);


                    }
                });
                alertDialog14.show();
                break;
            case R.id.apple15:
                basket.setX(getScreenwidth(this) / 22 * 9 - getScreenwidth(this) / 192 * 10);
                ChoiceAlertDialog2 alertDialog15 = new ChoiceAlertDialog2(this);
                alertDialog15.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog15.setOnSettingListener7(new SettingListener7() {
                    @Override
                    public void onSetting7(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple15);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple15);
                        }
                        apple15.setClickable(false);
                    }
                });
                alertDialog15.show();
                break;
            case R.id.apple16:
                basket.setX(getScreenwidth(this) / 22 * 11 - getScreenwidth(this) / 192 * 10);
                ChoiceAlertDialog1 alertDialog16 = new ChoiceAlertDialog1(this);
                alertDialog16.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog16.setOnSettingListener6(new SettingListener6() {
                    @Override
                    public void onSetting6(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple16);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple16);
                        }
                        apple16.setClickable(false);
                    }
                });
                alertDialog16.show();
                break;
            case R.id.apple17:
                basket.setX(getScreenwidth(this) / 22 * 13 - getScreenwidth(this) / 192 * 10);
                JudgeAlertDialog3 alertDialog17 = new JudgeAlertDialog3(this);
                alertDialog17.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog17.setOnSettingListener3(new SettingListener3() {
                    @Override
                    public void onSetting3(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple17);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple17);
                        }
                        apple17.setClickable(false);
                    }
                });
                alertDialog17.show();
                break;
            case R.id.apple18:
                basket.setX(getScreenwidth(this) / 22 * 15 - getScreenwidth(this) / 192 * 10);
                ChoiceAlertDialog4 alertDialog18 = new ChoiceAlertDialog4(this);
                alertDialog18.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog18.setOnSettingListener9(new SettingListener9() {
                    @Override
                    public void onSetting9(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple18);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple18);
                        }
                        apple18.setClickable(false);
                    }
                });
                alertDialog18.show();
                break;
            case R.id.apple19:
                basket.setX(getScreenwidth(this) / 22 * 19 - getScreenwidth(this) / 192 * 10);
                JudgeAlertDialog1 alertDialog19 = new JudgeAlertDialog1(this);
                alertDialog19.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog19.setOnSettingListener1(new SettingListener1() {
                    @Override
                    public void onSetting1(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple19);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple19);
                        }
                        apple19.setClickable(false);
                    }
                });
                alertDialog19.show();
                break;
            case R.id.apple20:
                basket.setX(getScreenwidth(this) / 22 * 22 - getScreenwidth(this) / 192 * 16);
                ChoiceAlertDialog5 alertDialog20 = new ChoiceAlertDialog5(this);
                alertDialog20.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                alertDialog20.setOnSettingListener10(new SettingListener10() {
                    @Override
                    public void onSetting10(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple20);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple20);
                        }
                        apple20.setClickable(false);
                    }
                });
                alertDialog20.show();
                break;
            case R.id.apple6:
                basket.setX(getScreenwidth(this) / 22 * 11 - getScreenwidth(this) / 192 * 10);
                ChoiceAlertDialog1 choiceAlertDialog1 = new ChoiceAlertDialog1(this);
                choiceAlertDialog1.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog1.setOnSettingListener6(new SettingListener6() {
                    @Override
                    public void onSetting6(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple6);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple6);
                        }
                        apple6.setClickable(false);
                    }
                });
                choiceAlertDialog1.show();
                break;
            case R.id.apple7:
                basket.setX(getScreenwidth(this) / 22 * 13 - getScreenwidth(this) / 192 * 10);
                ChoiceAlertDialog2 choiceAlertDialog2 = new ChoiceAlertDialog2(this);
                choiceAlertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog2.setOnSettingListener7(new SettingListener7() {
                    @Override
                    public void onSetting7(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple7);

                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple7);
                        }
                        apple7.setClickable(false);
                    }
                });
                choiceAlertDialog2.show();
                break;
            case R.id.apple8:
                basket.setX(getScreenwidth(this) / 22 * 15 - getScreenwidth(this) / 192 * 10);
                ChoiceAlertDialog3 choiceAlertDialog3 = new ChoiceAlertDialog3(this);
                choiceAlertDialog3.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog3.setOnSettingListener8(new SettingListener8() {
                    @Override
                    public void onSetting8(boolean isfalse) {
                        if (!isfalse) {

                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple8);

                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple8);
                        }

                        apple8.setClickable(false);
                    }
                });
                choiceAlertDialog3.show();
                break;
            case R.id.apple9:
                basket.setX(getScreenwidth(this) / 22 * 17 - getScreenwidth(this) / 192 * 10);
                ChoiceAlertDialog4 choiceAlertDialog4 = new ChoiceAlertDialog4(this);
                choiceAlertDialog4.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog4.setOnSettingListener9(new SettingListener9() {
                    @Override
                    public void onSetting9(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple9);

                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple9);
                        }
                        apple9.setClickable(false);
                    }
                });
                choiceAlertDialog4.show();
                break;
            case R.id.apple10:
                basket.setX(getScreenwidth(this) / 22 * 19 - getScreenwidth(this) / 192 * 10);
                ChoiceAlertDialog5 choiceAlertDialog5 = new ChoiceAlertDialog5(this);
                choiceAlertDialog5.getWindow().setBackgroundDrawableResource(R.drawable.roundedcornerbakground);
                choiceAlertDialog5.setOnSettingListener10(new SettingListener10() {
                    @Override
                    public void onSetting10(boolean isfalse) {
                        if (!isfalse) {
                            ++score;
                            finishedquestions += 1;
                            startAnimations(apple10);
                        } else {
                            finishedquestions += 1;
                            startShakeAnimations(apple10);
                        }
                        apple10.setClickable(false);
                    }
                });
                choiceAlertDialog5.show();
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


    @Override
    public void onSetting1(boolean isfalse) {
        if (!isfalse) {
            ++score;
            finishedquestions += 1;
            startAnimations(apple1);
        } else {
            ++finishedquestions;
            startShakeAnimations(apple1);
        }
        apple1.setClickable(false);
    }

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

