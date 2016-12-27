package com.adrianjaime.calmatumente;

import android.content.Intent;
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
 * Created by Matute on 26/12/2016.
 */
public class TipoMeditacionActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private VideoView videoView;
    private int[] videos;
    private Uri path;
    int currentVideo = 0;

    private RelativeLayout meditacionFondo;
    private LinearLayout layoutVideo;
    private ImageButton btnInicio, btnMeditacion, btnConfiguracion, btnPlay, btnStop, btnEscena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_meditacion);

        //Recupero el dato pasado por el intent
        Bundle datos = this.getIntent().getExtras();

        //Inicio todos los controles
        initControls();
        //Cargo un arreglo que contiene los videos
        initVideos();
        //Seteo el fondo y el video en base al parametro que recibo
        setFondoVideo(datos.getInt("meditacion"));
    }

    /**
     * Declaro e inicio todos los controles
     */
    private void initControls() {
        meditacionFondo = (RelativeLayout) findViewById(R.id.meditacionFondo);
        layoutVideo = (LinearLayout) findViewById(R.id.layoutVideo);
        videoView = (VideoView) findViewById(R.id.vidInicio);
        btnInicio = (ImageButton) findViewById(R.id.btnInicio);
        btnMeditacion = (ImageButton) findViewById(R.id.btnMeditacion);
        btnConfiguracion = (ImageButton) findViewById(R.id.btnConfiguracion);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnStop = (ImageButton) findViewById(R.id.btnStop);
        btnEscena = (ImageButton) findViewById(R.id.btnEscena);
    }

    /**
     *
     */
    private void initVideos() {
        videos = new int[]{
                R.raw.med_lago,
                R.raw.med_playa,
                R.raw.med_rio};
    }

    /**
     *
     * @param meditacion
     */
    private void setFondoVideo(int meditacion) {
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
    }

    public void onInicioClick(View view) {
        limpiarMediaPlayer();
        limpiarVideoView();
        Intent intent = new Intent(TipoMeditacionActivity.this, InicioActivity.class);
        startActivity(intent);
        finish();
    }

    public void onMeditacionClick(View view) {
        limpiarMediaPlayer();
        limpiarVideoView();
        Intent intent = new Intent(TipoMeditacionActivity.this, MeditacionActivity.class);
        startActivity(intent);
        finish();
    }

    public void onConfiguracionClick(View view) {
        limpiarMediaPlayer();
        limpiarVideoView();
        Intent intent = new Intent(TipoMeditacionActivity.this, AlarmaActivity.class);
        startActivity(intent);
        finish();
    }

    public void onPlayClick(View view) {
        startVideoView();
    }

    public void onStopClick(View view) {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void onEscenaClick(View view) {
        startVideoView();
    }

    private void limpiarMediaPlayer() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            //mediaPlayer = null;
        }
    }

    private void limpiarVideoView() {
        if(videoView.isPlaying()) {
            videoView.pause();
            //videoView = null;
        }
    }

    private void startVideoView() {
        if(layoutVideo.getVisibility() == View.GONE) {
            layoutVideo.setVisibility(View.VISIBLE);
        }

        if(videoView.isPlaying()) {
            videoView.pause();
        }

        path = Uri.parse("android.resource://calmatumente2.adrianjaime.com.calmatumente2/" + videos[currentVideo]);
        videoView.setVideoURI(path);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        mediaPlayer.start();
        videoView.start();

        if(currentVideo == 0) {
            currentVideo = 1;
        } else if(currentVideo == 1) {
            currentVideo = 2;
        } else if(currentVideo == 2) {
            currentVideo = 0;
        }
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
