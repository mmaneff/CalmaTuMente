package com.adrianjaime.calmatumente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Matute on 26/12/2016.
 */
public class AlarmaActivity extends AppCompatActivity {

    private ImageButton btnInicio, btnMeditacion, btnConfiguracion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        //Inicio todos los controles
        initControls();

    }

    /**
     * Declaro e inicio todos los controles
     */
    private void initControls() {
        btnInicio = (ImageButton) findViewById(R.id.btnInicio);
        btnMeditacion = (ImageButton) findViewById(R.id.btnMeditacion);
        btnConfiguracion = (ImageButton) findViewById(R.id.btnConfiguracion);

    }

    public void onInicioClick(View view) {
        Intent intent = new Intent(AlarmaActivity.this, InicioActivity.class);
        startActivity(intent);
    }

    public void onMeditacionClick(View view) {
        Intent intent = new Intent(AlarmaActivity.this, MeditacionActivity.class);
        intent.putExtra("meditacion", 1);
        startActivity(intent);
    }

    public void onConfiguracionClick(View view) {
        Intent intent = new Intent(AlarmaActivity.this, AlarmaActivity.class);
        startActivity(intent);
    }

}
