package com.adrianjaime.calmatumente.view.tipomeditacion.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.adrianjaime.calmatumente.alarma.AlarmaActivity;
import com.adrianjaime.calmatumente.meditacion.MeditacionActivity;
import com.adrianjaime.calmatumente.R;
import com.adrianjaime.calmatumente.inicio.InicioActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    @Bind(R.id.btnInicio)       ImageButton btnInicio;
    @Bind(R.id.btnMeditacion)   ImageButton btnMeditacion;
    @Bind(R.id.btnConfiguracion)ImageButton btnConfiguracion;
    @Bind(R.id.imgViewLogo)     ImageView imgViewLogo;
    @Bind(R.id.imgViewAppName)  ImageView imgViewAppName;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this, getApplicationContext());
        presenter.initView();

        presenter.startAlarmaService();
        presenter.initIntentFilter();
    }

    @Override
    public void initControls() {
        ButterKnife.bind(this);
    }

    @Override
    public void initTypeFace() {
        //Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Light.ttf");
        //tvAppName.setTypeface(customFont);
        //tvAppName.setText(Html.fromHtml("<big><strong>calma</strong></big><small>tu</small><big><strong>mente</strong></big>"));
    }


    @Override
    public void initAnimation() {
        final Animation animationFadeIn_1 = AnimationUtils.loadAnimation(this, R.anim.fadein_1);
        final Animation animationFadeIn_2 = AnimationUtils.loadAnimation(this, R.anim.fadein_2);
        final Animation animationFadeIn_3 = AnimationUtils.loadAnimation(this, R.anim.fadein_3);

        btnInicio.startAnimation(animationFadeIn_1);
        btnMeditacion.startAnimation(animationFadeIn_1);
        btnConfiguracion.startAnimation(animationFadeIn_1);
        imgViewLogo.startAnimation(animationFadeIn_2);
        imgViewAppName.startAnimation(animationFadeIn_3);
    }

    @Override
    public void showInicio() {
        Intent intent = new Intent(MainActivity.this, InicioActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMeditacion() {
        Intent intent = new Intent(MainActivity.this, MeditacionActivity.class);
        intent.putExtra("meditacion", 1);
        startActivity(intent);
    }

    @Override
    public void showAlarma() {
        Intent intent = new Intent(MainActivity.this, AlarmaActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
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
        }
    }


}
