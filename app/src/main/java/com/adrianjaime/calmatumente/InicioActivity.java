package com.adrianjaime.calmatumente;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Matute on 26/12/2016.
 */
public class InicioActivity extends AppCompatActivity {

    private ImageButton btnInicio, btnMeditacion, btnConfiguracion;
    private VideoView vvInicio;
    private Button btnWebLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Inicio todos los controles
        initControls();
        //Inicio el video de presentación
        initPresentacion();

    }

    /**
     * Declaro e inicio todos los controles
     */
    private void initControls() {
        btnInicio = (ImageButton) findViewById(R.id.btnInicio);
        btnMeditacion = (ImageButton) findViewById(R.id.btnMeditacion);
        btnConfiguracion = (ImageButton) findViewById(R.id.btnConfiguracion);
        vvInicio = (VideoView) findViewById(R.id.vvInicio);
        btnWebLink = (Button) findViewById(R.id.btnWebLink);
    }

    /**
     * Inico el vide de presentación y agrego los controles Media para el video
     */
    private void initPresentacion() {
        Uri path = Uri.parse("android.resource://calmatumente2.adrianjaime.com.calmatumente2/" + R.raw.presentacion);
        //AMPLIACIÓN DE LOS CONTROLES
        MediaController mediaCtrl = new MediaController(this);
        vvInicio.setMediaController(mediaCtrl);
        vvInicio.setVideoURI(path);
    }

    public void onInicioClick(View view) {
        Intent intent = new Intent(InicioActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onMeditacionClick(View view) {
        Intent intent = new Intent(InicioActivity.this, MeditacionActivity.class);
        intent.putExtra("meditacion", 1);
        startActivity(intent);
    }

    public void onConfiguracionClick(View view) {
        Intent intent = new Intent(InicioActivity.this, AlarmaActivity.class);
        startActivity(intent);
    }

    public void onWebLinkClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.adrianjaime.com.ar/"));
        startActivity(intent);
    }

}
