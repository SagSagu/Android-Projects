package com.example.daksha.myapp;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import java.io.IOException;

public class AudioPlayerActivity extends AppCompatActivity {

    private Button btnRaw, btnOnline, btnChoose, btnReset;
    private String audioURL="http://vprbbc.streamguys.net:80/vprbbc24.mp3";
    MediaPlayer mediaPlayer=new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_player_layout);

        btnRaw=(Button)findViewById(R.id.btnRaw);
        btnOnline=(Button)findViewById(R.id.btnOnline);
        btnChoose=(Button)findViewById(R.id.btnChoose);
        btnReset=(Button)findViewById(R.id.btnReset);
    }

    public void play(View view){
        switch (view.getId()){
            case R.id.btnRaw:
                mediaPlayer.stop();
                btnOnline.setEnabled(false);
                btnChoose.setEnabled(false);
                mediaPlayer=MediaPlayer.create(this,R.raw.music);
                mediaPlayer.start();
                //btnRaw.setEnabled(false);
                break;
            case R.id.btnOnline:
                mediaPlayer.stop();
                btnRaw.setEnabled(false);
                btnChoose.setEnabled(false);

                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(audioURL);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e){
                    mediaPlayer.stop();
                }


               /* } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                break;
            case R.id.btnChoose:
                btnOnline.setEnabled(false);
                btnRaw.setEnabled(false);
                PopupMenu popupMenu=new PopupMenu(AudioPlayerActivity.this, btnChoose);
                popupMenu.getMenuInflater().inflate(R.menu.media_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle()=="RAW"){
                            mediaPlayer=MediaPlayer.create(AudioPlayerActivity.this,R.raw.music);
                            mediaPlayer.start();
                        } else if(item.getTitle()=="Online"){
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            try {
                                mediaPlayer.setDataSource(audioURL);
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } catch (Exception e){

                            }
                        }
                        return  true;
                    }
                });
                popupMenu.show();
                break;
            case R.id.btnReset:
                mediaPlayer.stop();
                btnOnline.setEnabled(true);
                btnChoose.setEnabled(true);
                btnRaw.setEnabled(true);
                break;
        }
    }
}
