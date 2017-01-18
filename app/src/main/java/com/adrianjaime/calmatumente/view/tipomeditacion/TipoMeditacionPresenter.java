package com.adrianjaime.calmatumente.view.tipomeditacion;

/**
 * Created by emaneff on 09/01/2017.
 */
public interface TipoMeditacionPresenter {

    void initView();
    void onStartVideoView();
    void onStopVideoView();
    void onSelectedInicio();
    void onSelectedMeditacion();
    void onSelectedAlarma();
    void onDestroy();

}
