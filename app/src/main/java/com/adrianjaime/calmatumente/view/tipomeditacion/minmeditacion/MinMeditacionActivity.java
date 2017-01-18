package com.adrianjaime.calmatumente.view.tipomeditacion.minmeditacion;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.adrianjaime.calmatumente.R;
import com.adrianjaime.calmatumente.view.tipomeditacion.alarma.AlarmaActivity;
import com.adrianjaime.calmatumente.view.tipomeditacion.inicio.InicioActivity;
import com.adrianjaime.calmatumente.view.tipomeditacion.meditacion.MeditacionActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Matute on 26/12/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MinMeditacionActivity extends AppCompatActivity implements MinMeditacionView, View.OnClickListener {

    @Bind(R.id.btnInicio)       ImageButton btnInicio;
    @Bind(R.id.btnMeditacion)   ImageButton btnMeditacion;
    @Bind(R.id.btnConfiguracion)ImageButton btnConfiguracion;
    @Bind(R.id.btnPlay)         ImageButton btnPlay;
    @Bind(R.id.btnStop)         ImageButton btnStop;
    @Bind(R.id.btnEscena)       ImageButton btnEscena;
    @Bind(R.id.tvEstado)       TextView tvEstado;
    @Bind(R.id.tvCuenta)       TextView tvCuenta;

    private  MinMeditacionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_meditacion);

        presenter = new MinMeditacionPresenterImpl(this, getApplicationContext());
        presenter.initView();
    }

    @Override
    public void initControls() {
        ButterKnife.bind(this);
    }

    @Override
    public void showInicio() {
        Intent intent = new Intent(MinMeditacionActivity.this, InicioActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMeditacion() {
        Intent intent = new Intent(MinMeditacionActivity.this, MeditacionActivity.class);
        startActivity(intent);
    }

    @Override
    public void showAlarma() {
        Intent intent = new Intent(MinMeditacionActivity.this, AlarmaActivity.class);
        startActivity(intent);
    }

    @Override
    public void showEstado(String estado) {
        tvEstado.setText(estado);
    }

    @Override
    public void showCuenta(String cuenta) {
        tvCuenta.setText(cuenta);
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
                presenter.startMeditacion();
                break;
            }
            case R.id.btnStop: {
                presenter.stopMeditacion();
                break;
            }
            case R.id.btnEscena: {
                //TODO
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


}
