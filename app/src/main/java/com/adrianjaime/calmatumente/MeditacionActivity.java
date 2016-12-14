package com.adrianjaime.calmatumente;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

/**
 * Created by Matute on 03/12/2016.
 */

public class MeditacionActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    //VideoView videoView = (VideoView) findViewById(R.id.vidInicio);
    VideoView videoView;
    private int[] videos;
    private Uri path;
    int currentVideo = 0;

    private LinearLayout layoutVideo;
    private ImageButton btnInicio, btnMeditacion, btnConfiguracion, btnPlay, btnStop, btnEscena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditacion);

        RelativeLayout meditacionFondo = (RelativeLayout) findViewById(R.id.meditacionFondo);
        btnInicio = (ImageButton) findViewById(R.id.btnInicio);
        btnMeditacion = (ImageButton) findViewById(R.id.btnMeditacion);
        btnConfiguracion = (ImageButton) findViewById(R.id.btnConfiguracion);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnStop = (ImageButton) findViewById(R.id.btnStop);
        btnEscena = (ImageButton) findViewById(R.id.btnEscena);

        Bundle datos = this.getIntent().getExtras();
        int meditacion = datos.getInt("meditacion");
        //String meditacion = datos.getString("variable_string");
        //float meditacion = datos.getFloat("objeto_float");

        if(meditacion == 1) {
            setTitle(getResources().getText(R.string.meditacion_2));
            meditacionFondo.setBackgroundResource(R.drawable.img_2);
            mediaPlayer = MediaPlayer.create(this, R.raw.meditacion_1);
        } else if(meditacion == 2) {
            setTitle(getResources().getText(R.string.meditacion_3));
            meditacionFondo.setBackgroundResource(R.drawable.img_3);
            mediaPlayer = MediaPlayer.create(this, R.raw.meditacion_2);
        } else {
            setTitle(getResources().getText(R.string.meditacion_4));
            meditacionFondo.setBackgroundResource(R.drawable.img_4);
            mediaPlayer = MediaPlayer.create(this, R.raw.meditacion_3);
        }

        videos = new int[]{
                R.raw.med_lago,
                R.raw.med_playa,
                R.raw.med_rio};

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeditacionActivity.this, HomeActivity.class));
                finish();
            }
        });

        btnMeditacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeditacionActivity.this, HomeActivity.class));
                finish();
            }
        });

        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeditacionActivity.this, HomeActivity.class));
                finish();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutVideo = (LinearLayout) findViewById(R.id.layoutVideo);
                layoutVideo.setVisibility(View.VISIBLE);

                videoView = (VideoView) findViewById(R.id.vidInicio);

                path = Uri.parse("android.resource://com.adrianjaime.calmatumente/" + videos[currentVideo]);
                videoView.setVideoURI(path);

                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                    }
                });
                /*
                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    videoView.start();
                }
                */
                mediaPlayer.start();
                videoView.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        });

        btnEscena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    videoView.pause();
                    videoView.resume();
                }
                if(currentVideo == 0) {
                    currentVideo = 1;
                } else if(currentVideo == 1) {
                    currentVideo = 2;
                } else if(currentVideo == 2) {
                    currentVideo = 0;
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

}
