package com.adrianjaime.calmatumente.view.tipomeditacion.alarma;

import android.os.Bundle;

import com.adrianjaime.calmatumente.pojo.Alarma;

import java.util.ArrayList;

/**
 * Created by emaneff on 09/01/2017.
 */
public interface AlarmaView {

    void initControls();
    void setTimePickerTextColor();
    void loadAlarmas(Bundle savedInstanceState, ArrayList<Alarma> alarmas);
    void loadAdapter(ArrayList<Alarma> alarmas);
    void showInicio();
    void showTimePicker();
    void hideTimePicker();

}
