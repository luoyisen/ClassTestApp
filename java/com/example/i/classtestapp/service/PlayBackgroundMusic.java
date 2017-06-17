package com.example.i.classtestapp.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.i.classtestapp.MainActivity;

import java.io.IOException;

/**
 * Created by I on 2017/6/13.
 */

public class PlayBackgroundMusic extends Service {
    private boolean quit = false;
    MyBroadcastReceiver serviceReceiver;
    AssetManager assetManager;
    MediaPlayer mediaPlayer;
    String[] musics = new String[]{
            "welcometoplaneturf.mp3",
            "untren.mp3",
            "sadangel.mp3",
            "still.mp3"
    };
    String[] musicnames = new String[]{
            "League of Legends - Welcome to Planet Urf",
            "John H. Clarke - Un Tren",
            "伊戈尔.克鲁托伊 - Sad Angel",
            "Timirage - Still"
    };
    int currentSong = 0;
    int nextSong = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        serviceReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(MainActivity.CTRL_TO_CHANGE_SONG_ACTION);
        registerReceiver(serviceReceiver, filter);
        assetManager = getAssets();
        mediaPlayer = new MediaPlayer();
        /**
         * Math.random() 可以产生一个 大于等于 0 且 小于 1 的双精度伪随机数，假设需要产生
         * 0<= 随机数 <=10 的随机数，可以这样做:
         * int num =(int)(Math.random() * 11);
         */
        currentSong = (int) (Math.random() * 4);
        if(!quit){
            prepareAndPlay(musics[currentSong]);
            Intent Intent_sendFromService = new Intent(MainActivity.AUTOMACIT_TO_CHANGE_SONG_ACTION);
            Intent_sendFromService.putExtra("currentSongname", musicnames[currentSong]);
            Intent_sendFromService.putExtra("currentSong", musics[currentSong]);
            sendBroadcast(Intent_sendFromService);
        }


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextSong = (int) (Math.random() * 4);
                while (nextSong == currentSong) {
                    nextSong = (int) (Math.random() * 4);//一直等到下一个要播放的歌曲和上一个不一样才能开始播放
                }
                currentSong = nextSong;
                Intent Intent_sendFromService = new Intent(MainActivity.AUTOMACIT_TO_CHANGE_SONG_ACTION);
                Intent_sendFromService.putExtra("currentSongname", musicnames[currentSong]);
                Intent_sendFromService.putExtra("currentSong", musics[currentSong]);
                sendBroadcast(Intent_sendFromService);
                prepareAndPlay(musics[currentSong]);
                String s = "正在播放" + musics[currentSong];
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            currentSong = (int) (Math.random() * 4);
//            prepareAndPlay(musics[currentSong]);
//            Intent Intent_sendFromService = new Intent(MainActivity.AUTOMACIT_TO_CHANGE_SONG_ACTION);
//            Intent_sendFromService.putExtra("currentSongname", musicnames[currentSong]);
//            sendBroadcast(Intent_sendFromService);
            nextSong = (int) (Math.random() * 4);
            while (nextSong == currentSong) {
                nextSong = (int) (Math.random() * 4);//一直等到下一个要播放的歌曲和上一个不一样才能开始播放
            }
            currentSong = nextSong;
            prepareAndPlay(musics[currentSong]);
            Intent Intent_sendFromService = new Intent(MainActivity.AUTOMACIT_TO_CHANGE_SONG_ACTION);
            Intent_sendFromService.putExtra("currentSongname", musicnames[currentSong]);
            Intent_sendFromService.putExtra("currentSong", musics[currentSong]);
            sendBroadcast(Intent_sendFromService);

        }
    }

    private void prepareAndPlay(String music) {
        try {
            // 打开指定音乐文件
            AssetFileDescriptor afd = assetManager.openFd(music);
            mediaPlayer.reset();
            // 使用MediaPlayer加载指定的声音文件。
            mediaPlayer.setDataSource(afd.getFileDescriptor(),
                    afd.getStartOffset(), afd.getLength());
            // 准备声音
            mediaPlayer.prepare();
            // 播放
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
