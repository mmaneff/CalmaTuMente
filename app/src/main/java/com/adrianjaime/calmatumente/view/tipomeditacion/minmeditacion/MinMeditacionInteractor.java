package com.adrianjaime.calmatumente.view.tipomeditacion.minmeditacion;

/**
 * Created by emaneff on 09/01/2017.
 */
public interface MinMeditacionInteractor {

    interface OnMeditacionFinishedListener {
        void onSuccess();
    }

    void startMeditacion(OnMeditacionFinishedListener listener);

}
