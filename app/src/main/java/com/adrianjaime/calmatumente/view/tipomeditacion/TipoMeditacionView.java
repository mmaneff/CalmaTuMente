package com.adrianjaime.calmatumente.view.tipomeditacion;

/**
 * Created by emaneff on 09/01/2017.
 */

public interface TipoMeditacionView {

    void initControls();
    void initVideos();
    void setFondoVideo();
    void showInicio();
    void showMeditacion();
    void showAlarma();
    void startVideoView();
    void stopVideoView();

}
