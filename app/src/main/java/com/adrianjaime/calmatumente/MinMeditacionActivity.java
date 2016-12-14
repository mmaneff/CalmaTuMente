package com.adrianjaime.calmatumente;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


/**
 * Created by emaneff on 07/12/2016.
 */

public class MinMeditacionActivity extends AppCompatActivity {

    private ProgressBar mProgress;
    private TextView tvEstado, tvCuenta;
    int pStatus = 0;
    private Handler handler = new Handler();
    private ObjectAnimator animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_meditacion);

        tvCuenta = (TextView) findViewById(R.id.tvCuenta);
        tvEstado = (TextView) findViewById(R.id.tvEstado);
        ImageButton btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        ImageButton btnStop = (ImageButton) findViewById(R.id.btnStop);
        mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);


        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus < 100) {
                    pStatus += 1;

                    //long tiempo = millisUntilFinished / 1000;
                    //tvCuenta.setText(Long.toString(tiempo));

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            //tv.setText(pStatus + "/" + pBar.getMax());
                            //pBar2.setProgress(pStatus);
                            //tv2.setText(pStatus + "%");
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();




        /*
        animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 60000);
        animation.setDuration(5000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.i("Info", "Animacion iniciada");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //do something when the countdown is complete
                Log.i("Info", "Animacion finalizada");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Log.i("Info", "Animacion cancelada");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Log.i("Info", "Animacion se repite");
            }
        });
        animation.start();
        */

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
            if(animation.isRunning()) {
                Log.i("Info", "Esta corriendo");
            } else {
                animation.start();
            }
            */
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if(animation.isRunning()) {
                    animation.cancel();
                } else {
                    Log.i("Info", "Esta detenido");
                }
                */
            }
        });
    }


}
