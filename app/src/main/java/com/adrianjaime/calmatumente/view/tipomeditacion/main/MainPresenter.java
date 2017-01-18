package com.adrianjaime.calmatumente.view.tipomeditacion.main;

/**
 * Created by emaneff on 09/01/2017.
 */
public interface MainPresenter {

    void initView();
    void startAlarmaService();
    void initIntentFilter();
    void onSelectedInicio();
    void onSelectedMeditacion();
    void onSelectedAlarma();
    void onDestroy();

}
