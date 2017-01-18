package com.adrianjaime.calmatumente.view.tipomeditacion.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.adrianjaime.calmatumente.services.AlarmaService;
import com.adrianjaime.calmatumente.services.Constants;

/**
 * Created by emaneff on 09/01/2017.
 */
public class MainPresenterImpl implements MainPresenter, MainInteractor {

    private final static String LOGCAT = "MainPresenterImpl";

    private Context context;
    private MainView mainView;
    private MainInteractor mainInteractor;
    protected Intent intentAlarmaService;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
    }

    public MainPresenterImpl(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
        this.mainInteractor = new MainInteractorImpl();
    }

    /**
     * Inicio los datos de la vista
     */
    @Override
    public void initView() {
        if(mainView != null) {
            //Inicio todos los controles
            mainView.initControls();
            //Defino el tipo de fuente
            mainView.initTypeFace();
            //Inicio la animación del logo y el titulo
            mainView.initAnimation();
        }
    }

    /**
     * Inicio el servicio local que se encarga de levantar las notificaciones
     */
    @Override
    public void startAlarmaService() {
        try {
            intentAlarmaService = new Intent(context, AlarmaService.class);
            context.startService(intentAlarmaService); //Iniciar Servicio
        } catch (Exception e) {
            Log.e(LOGCAT, e.toString());
        }
    }

    @Override
    public void onSelectedInicio() {
        if(mainView != null) {
            mainView.showInicio();
        }
    }

    @Override
    public void onSelectedMeditacion() {
        if(mainView != null) {
            mainView.showMeditacion();
        }
    }

    @Override
    public void onSelectedAlarma() {
        if(mainView != null) {
            mainView.showAlarma();
        }
    }

    /**
     * Filtro de acciones que serán alertadas
     */
    @Override
    public void initIntentFilter() {
        // Filtro de acciones que serán alertadas
        //IntentFilter filter = new IntentFilter(Constants.ACTION_RUN_ISERVICE);
        //filter.addAction(Constants.ACTION_RUN_SERVICE);
        //filter.addAction(Constants.ACTION_MEMORY_EXIT);
        //filter.addAction(Constants.ACTION_PROGRESS_EXIT);
        IntentFilter filter = new IntentFilter(Constants.ALARMA_LISTENER);

        // Crear un nuevo ResponseReceiver
        ResponseReceiver receiver = new ResponseReceiver();
        // Registrar el receiver y su filtro
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        mainView = null;
        try {
            context.stopService(intentAlarmaService); // Detener servicio
        } catch (Exception e) {
            Log.e("stopService", e.toString());
        }
    }


    // Broadcast receiver que recibe las emisiones desde los servicios
    private class ResponseReceiver extends BroadcastReceiver {

        // Sin instancias
        private ResponseReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            /*
            switch (intent.getAction()) {
                case Constants.ACTION_RUN_SERVICE:
                    Log.i("ACTION_RUN_SERVICE", intent.getStringExtra(Constants.EXTRA_MEMORY));
                    break;
                case Constants.ACTION_RUN_ISERVICE:
                    Log.i("ACTION_RUN_ISERVICE", intent.getIntExtra(Constants.EXTRA_PROGRESS, -1) + "");
                    break;
                case Constants.ACTION_MEMORY_EXIT:
                    Log.i("ACTION_MEMORY_EXIT", "Memoria");
                    break;
                case Constants.ACTION_PROGRESS_EXIT:
                    Log.i("ACTION_PROGRESS_EXIT", "Progreso");
                    break;
            }
            */

            Log.i("ALARMA_LISTENER", "ALARMA_LISTENER");
        }
    }

}
