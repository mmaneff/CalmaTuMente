package com.adrianjaime.calmatumente.view.tipomeditacion.minmeditacion;

/**
 * Created by emaneff on 09/01/2017.
 */
public interface MinMeditacionPresenter {

    void initView();
    void startMeditacion();
    void stopMeditacion();
    void onSelectedInicio();
    void onSelectedMeditacion();
    void onSelectedAlarma();
    void onDestroy();

}
