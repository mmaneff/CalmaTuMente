package com.adrianjaime.calmatumente.view.tipomeditacion.alarma;

import android.os.Bundle;

import com.adrianjaime.calmatumente.pojo.Alarma;

/**
 * Created by emaneff on 09/01/2017.
 */

public interface AlarmaPresenter {

    void initView();
    void loadAlarmas(Bundle savedInstanceState);
    void saveTime(Alarma alarma, int hora, int minuto);
    void onSelectedInicio();
    void onSelectedCrearAlarma();
    void onSelectedCancelTime();
    void onDestroy();

}
