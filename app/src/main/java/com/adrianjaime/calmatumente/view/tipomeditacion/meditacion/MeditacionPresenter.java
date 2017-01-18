package com.adrianjaime.calmatumente.view.tipomeditacion.meditacion;

/**
 * Created by emaneff on 09/01/2017.
 */

public interface MeditacionPresenter {

    void initView();
    void onSelectedInicio();
    void onSelectedMeditacion();
    void onSelectedAlarma();
    void onSelectedMinutoMeditacion();
    void onSelectedMeditacionMenteCuerpo();
    void onSelectedMeditacionLiberaEstres();
    void onSelectedMeditacionInsomnio();
    void onDestroy();

}
