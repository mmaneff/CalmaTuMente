package com.adrianjaime.calmatumente.view.tipomeditacion;

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

import com.adrianjaime.calmatumente.R;
import com.adrianjaime.calmatumente.alarma.AlarmaActivity;
import com.adrianjaime.calmatumente.inicio.InicioActivity;
import com.adrianjaime.calmatumente.meditacion.MeditacionActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Matute on 26/12/2016.
 */
public class TipoMeditacionActivity extends AppCompatActivity implements TipoMeditacionView, View.OnClickListener {

    @Bind(R.id.btnInicio)       ImageButton btnInicio;
    @Bind(R.id.btnMeditacion)   ImageButton btnMeditacion;
    @Bind(R.id.btnConfiguracion)ImageButton btnConfiguracion;
    @Bind(R.id.btnPlay)         ImageButton btnPlay;
    @Bind(R.id.btnStop)         ImageButton btnStop;
    @Bind(R.id.btnEscena)       ImageButton btnEscena;
    @Bind(R.id.vidInicio)       VideoView videoView;

    @Bind(R.id.meditacionFondo) RelativeLayout meditacionFondo;
    @Bind(R.id.layoutVideo)     LinearLayout layoutVideo;


    private MediaPlayer mediaPlayer;
    private int[] videos;
    private Uri path;
    private int meditacion;
    int currentVideo = 0;

    private  TipoMeditacionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_meditacion);

        //Recupero el dato pasado por el intent
        Bundle datos = this.getIntent().getExtras();
        meditacion = datos.getInt("meditacion");

        presenter = new TipoMeditacionPresenterImpl(this, getApplicationContext());
        presenter.initView();
    }

   @Override
    public void initControls() {
        ButterKnife.bind(this);
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public void showInicio() {
        Intent intent = new Intent(TipoMeditacionActivity.this, InicioActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMeditacion() {
        Intent intent = new Intent(TipoMeditacionActivity.this, MeditacionActivity.class);
        startActivity(intent);
    }

    @Override
    public void showAlarma() {
        Intent intent = new Intent(TipoMeditacionActivity.this, AlarmaActivity.class);
        startActivity(intent);
    }

    @Override
    public void initVideos() {
        videos = new int[]{
                R.raw.med_lago,
                R.raw.med_playa,
                R.raw.med_rio};
    }

    @Override
    public void setFondoVideo() {
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

    private void limpiarMediaPlayer() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void limpiarVideoView() {
        if(videoView.isPlaying()) {
            videoView.pause();
            videoView = null;
        }
    }

    @Override
    public void startVideoView() {
        if(layoutVideo.getVisibility() == View.GONE) {
            layoutVideo.setVisibility(View.VISIBLE);
        }

        if(videoView.isPlaying()) {
            videoView.pause();
        }

        path = Uri.parse("android.resource://com.adrianjaime.calmatumente/" + videos[currentVideo]);
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
    public void stopVideoView() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInicio: {
                presenter.onSelectedInicio();
                break;
            }
            case R.id.btnMeditacion: {
                presenter.onSelectedMeditacion();
                break;
            }
            case R.id.btnConfiguracion: {
                presenter.onSelectedAlarma();
                break;
            }
            case R.id.btnPlay: {
                presenter.onStartVideoView();
                break;
            }
            case R.id.btnStop: {
                presenter.onStopVideoView();
                break;
            }
            case R.id.btnEscena: {
                presenter.onStartVideoView();
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        limpiarMediaPlayer();
        limpiarVideoView();
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        mediaPlayer.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mediaPlayer.pause();
        super.onPause();
    }
}
