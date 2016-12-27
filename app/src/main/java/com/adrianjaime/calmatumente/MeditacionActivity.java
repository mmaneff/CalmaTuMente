package com.adrianjaime.calmatumente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Matute on 26/12/2016.
 */
public class MeditacionActivity extends AppCompatActivity {

    private ImageButton btnInicio, btnMeditacion, btnConfiguracion;
    private Button btnMinutoMeditacion, btnMeditacionMenteCuerpo, btnMeditacionLiberaEstres, btnMeditacionInsomnio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditacion);

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
        btnMinutoMeditacion = (Button) findViewById(R.id.btnMinutoMeditacion);
        btnMeditacionMenteCuerpo = (Button) findViewById(R.id.btnMeditacionMenteCuerpo);
        btnMeditacionLiberaEstres = (Button) findViewById(R.id.btnMeditacionLiberaEstres);
        btnMeditacionInsomnio = (Button) findViewById(R.id.btnMeditacionInsomnio);
    }

    public void onInicioClick(View view) {
        Intent intent = new Intent(MeditacionActivity.this, InicioActivity.class);
        startActivity(intent);
    }

    public void onMeditacionClick(View view) {
        //No hacer nada en esta vista
    }

    public void onConfiguracionClick(View view) {
        Intent intent = new Intent(MeditacionActivity.this, AlarmaActivity.class);
        startActivity(intent);
    }

    public void onMinutoMeditacionClick(View view) {
        Intent intent = new Intent(MeditacionActivity.this, MinMeditacionActivity.class);
        startActivity(intent);
    }

    public void onMeditacionMenteCuerpoClick(View view) {
        Intent intent = new Intent(MeditacionActivity.this, TipoMeditacionActivity.class);
        intent.putExtra("meditacion", 1);
        startActivity(intent);
    }

    public void onMeditacionLiberaEstresClick(View view) {
        Intent intent = new Intent(MeditacionActivity.this, TipoMeditacionActivity.class);
        intent.putExtra("meditacion", 2);
        startActivity(intent);
    }

    public void onMeditacionInsomnioClick(View view) {
        Intent intent = new Intent(MeditacionActivity.this, TipoMeditacionActivity.class);
        intent.putExtra("meditacion", 3);
        startActivity(intent);
    }

}
