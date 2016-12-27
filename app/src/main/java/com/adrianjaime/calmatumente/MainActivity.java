package com.adrianjaime.calmatumente;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnInicio, btnMeditacion, btnConfiguracion;
    private TextView tvAppName, tvDoctorName;
    private ImageView imgViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicio todos los controles
        initControls();
        //Defino el tipo de fuente
        initTypeFace();
        //Inicio la animación del logo y el titulo
        initAnimation();
    }

    /**
     * Declaro e inicio todos los controles
     */
    private void initControls() {
        btnInicio = (ImageButton) findViewById(R.id.btnInicio);
        btnMeditacion = (ImageButton) findViewById(R.id.btnMeditacion);
        btnConfiguracion = (ImageButton) findViewById(R.id.btnConfiguracion);
        tvAppName = (TextView) findViewById(R.id.tvAppName);
        tvDoctorName = (TextView) findViewById(R.id.tvDoctorName);
        imgViewLogo = (ImageView) findViewById(R.id.imgViewLogo);
    }

    /**
     * Cambio el tipo de fuente por otra personalizada
     */
    private void initTypeFace() {
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Light.ttf");
        tvAppName.setTypeface(customFont);
    }

    /**
     * Inicio la animación cuando se carga la vista principal
     */
    private void initAnimation() {
        final Animation animationFadeIn_1 = AnimationUtils.loadAnimation(this, R.anim.fadein_1);
        final Animation animationFadeIn_2 = AnimationUtils.loadAnimation(this, R.anim.fadein_2);
        final Animation animationFadeIn_3 = AnimationUtils.loadAnimation(this, R.anim.fadein_3);

        btnInicio.startAnimation(animationFadeIn_1);
        btnMeditacion.startAnimation(animationFadeIn_1);
        btnConfiguracion.startAnimation(animationFadeIn_1);
        imgViewLogo.startAnimation(animationFadeIn_2);
        tvDoctorName.startAnimation(animationFadeIn_2);
        tvAppName.startAnimation(animationFadeIn_3);
    }

    public void onInicioClick(View view) {
        Intent intent = new Intent(MainActivity.this, InicioActivity.class);
        startActivity(intent);
    }

    public void onMeditacionClick(View view) {
        Intent intent = new Intent(MainActivity.this, MeditacionActivity.class);
        intent.putExtra("meditacion", 1);
        startActivity(intent);
    }

    public void onConfiguracionClick(View view) {
        Intent intent = new Intent(MainActivity.this, AlarmaActivity.class);
        startActivity(intent);
    }







}
