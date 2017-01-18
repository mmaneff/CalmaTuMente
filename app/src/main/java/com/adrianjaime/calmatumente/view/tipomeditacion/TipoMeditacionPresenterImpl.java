package com.adrianjaime.calmatumente.view.tipomeditacion;


import android.content.Context;

/**
 * Created by emaneff on 09/01/2017.
 */
public class TipoMeditacionPresenterImpl implements TipoMeditacionPresenter, TipoMeditacionInteractor {

    private final static String LOGCAT = "TipoMeditacionPresenterImpl";

    private Context context;
    private TipoMeditacionView tipoMeditacionView;
    private TipoMeditacionInteractor tipoMeditacionInteractor;


    public TipoMeditacionPresenterImpl(TipoMeditacionView tipoMeditacionView) {
        this.tipoMeditacionView = tipoMeditacionView;
        this.tipoMeditacionInteractor = new TipoMeditacionInteractorImpl();

    }

    public TipoMeditacionPresenterImpl(TipoMeditacionView tipoMeditacionView, Context context) {
        this.tipoMeditacionView = tipoMeditacionView;
        this.context = context;
        this.tipoMeditacionInteractor = new TipoMeditacionInteractorImpl();
    }

    @Override
    public void initView() {
        if(tipoMeditacionView != null) {
            tipoMeditacionView.initControls();
            //Cargo un arreglo que contiene los videos
            tipoMeditacionView.initVideos();
            //Seteo el fondo y el video en base al parametro que recibo
            tipoMeditacionView.setFondoVideo();
        }
    }

    @Override
    public void onStartVideoView() {
        if(tipoMeditacionView != null) {
            tipoMeditacionView.startVideoView();
        }
    }

    @Override
    public void onStopVideoView() {
        if(tipoMeditacionView != null) {
            tipoMeditacionView.stopVideoView();
        }
    }

    @Override
    public void onSelectedInicio() {
        if(tipoMeditacionView != null) {
            tipoMeditacionView.showInicio();
        }
    }

    @Override
    public void onSelectedMeditacion() {
        if(tipoMeditacionView != null) {
            tipoMeditacionView.showMeditacion();
        }
    }

    @Override
    public void onSelectedAlarma() {
        if(tipoMeditacionView != null) {
            tipoMeditacionView.showAlarma();
        }
    }

    @Override
    public void onDestroy() {
        tipoMeditacionView = null;
    }
}
