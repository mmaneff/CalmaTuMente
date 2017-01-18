package com.adrianjaime.calmatumente.view.tipomeditacion.alarma;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.adrianjaime.calmatumente.db.DataSource;
import com.adrianjaime.calmatumente.pojo.Alarma;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by emaneff on 09/01/2017.
 */
public class AlarmaPresenterImpl implements AlarmaPresenter, AlarmaInteractor {

    private final static String LOGCAT = "AlarmaPresenterImpl";

    private Context context;
    private AlarmaView alarmaView;
    private AlarmaInteractor alarmaInteractor;
    private DataSource dataSource;
    private Alarma alarmaToUpdate = null;


    public AlarmaPresenterImpl(AlarmaView alarmaView) {
        this.alarmaView = alarmaView;
        this.alarmaInteractor = new AlarmaInteractorImpl();
    }

    public AlarmaPresenterImpl(AlarmaView alarmaView, Context context) {
        this.alarmaView = alarmaView;
        this.context = context;
        this.alarmaInteractor = new AlarmaInteractorImpl();

        dataSource = new DataSource(context);
        dataSource.open();
    }

    @Override
    public void initView() {
        try {
            //Inicio todos los controles
            alarmaView.initControls();
            //Cambio el color de texto en el TimePicker
            alarmaView.setTimePickerTextColor();
        } catch (Exception ex) {
            Log.e(LOGCAT, ex.getMessage());
        }
    }

    @Override
    public void loadAlarmas(Bundle savedInstanceState) {
        try {
            ArrayList<Alarma> alarmas = dataSource.getAll();
            if (alarmas.size() == 0) {
                Calendar calendar = Calendar.getInstance();
                dataSource.create(createAlarma(
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE) + 2,
                        calendar.get(Calendar.DAY_OF_WEEK)));
            }
            alarmas = dataSource.getAll();

            alarmaView.loadAlarmas(savedInstanceState, alarmas);
        } catch (Exception ex) {
            Log.e(LOGCAT, ex.getMessage());
        }
    }

    @Override
    public void saveTime(Alarma alarma, int hora, int minuto) {
        try {
            if(alarma == null) {
                Calendar calendar = Calendar.getInstance();
                dataSource.create(createAlarma(hora, minuto, calendar.get(Calendar.DAY_OF_WEEK)));
            } else {
                if(alarma.getId() > 0) {
                    alarma.setHora(hora);
                    alarma.setMinuto(minuto);

                    dataSource.update(alarma);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    dataSource.create(createAlarma(hora, minuto, calendar.get(Calendar.DAY_OF_WEEK)));
                }
            }

            alarmaView.loadAdapter(dataSource.getAll());

        } catch (Exception ex) {
            Log.e(LOGCAT, ex.getMessage());
        }
    }

    @Override
    public void onSelectedInicio() {
        if(alarmaView != null){
            alarmaView.showInicio();
        }
    }

    @Override
    public void onSelectedCrearAlarma() {
        if(alarmaView != null){
            alarmaView.showTimePicker();
        }
    }

    @Override
    public void onSelectedCancelTime() {
        if(alarmaView != null){
            alarmaView.hideTimePicker();
        }
    }

    /**
     * @param hora
     * @param minuto
     * @param dia
     * @return
     */
    private Alarma createAlarma(int hora, int minuto, int dia) {
        Alarma alarma = new Alarma(hora, minuto, dia,
                (dia == Calendar.MONDAY) ? true : false,
                (dia == Calendar.TUESDAY) ? true : false,
                (dia == Calendar.WEDNESDAY) ? true : false,
                (dia == Calendar.THURSDAY) ? true : false,
                (dia == Calendar.FRIDAY) ? true : false,
                (dia == Calendar.SATURDAY) ? true : false,
                (dia == Calendar.SUNDAY) ? true : false);

        return alarma;
    }

    @Override
    public void onDestroy() {
        alarmaView = null;
    }
}
