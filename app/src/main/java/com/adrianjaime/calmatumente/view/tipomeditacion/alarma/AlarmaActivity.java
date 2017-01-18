package com.adrianjaime.calmatumente.view.tipomeditacion.alarma;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.res.Resources;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.adrianjaime.calmatumente.R;
import com.adrianjaime.calmatumente.adapter.AlarmaAdapter;
import com.adrianjaime.calmatumente.pojo.Alarma;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Matute on 26/12/2016.
 * http://www.bogotobogo.com/Android/android20NotificationService.php
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AlarmaActivity extends ListActivity implements AlarmaView, View.OnClickListener {

    @Bind(R.id.btnSaveTime)     Button btnSaveTime;
    @Bind(R.id.btnCancelTime)   Button btnCancelTime;
    @Bind(R.id.btnVolver)       FloatingActionButton btnVolver;
    @Bind(R.id.btnCrearAlarma)  FloatingActionButton btnCrearAlarma;
    @Bind(R.id.layoutList)      LinearLayout layoutList;
    @Bind(R.id.layoutTimePicker)LinearLayout layoutTimePicker;
    @Bind(android.R.id.list)    ListView lstAlarmas;
    @Bind(R.id.timePicker)      TimePicker timePicker;


    private ArrayList<Alarma> data;
    private ArrayList<Alarma> alarmas;
    private AlarmaAdapter adapter;

    private Alarma alarmaToUpdate = null;

    private AlarmaPresenter presenter;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        presenter = new AlarmaPresenterImpl(this, getApplicationContext());
        presenter.initView();

        //Recupero el listado de alarmas desde la base para cargar el listView
        presenter.loadAlarmas(savedInstanceState);

        lstAlarmas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long mylng) {
                alarmaToUpdate = (Alarma)(lstAlarmas.getItemAtPosition(position));

                layoutList.setVisibility(View.GONE);
                layoutTimePicker.setVisibility(View.VISIBLE);

                timePicker.setCurrentHour(alarmaToUpdate.getHora());
                timePicker.setCurrentMinute(alarmaToUpdate.getMinuto());
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void initControls() {
        ButterKnife.bind(this);
    }

    @Override
    public void loadAlarmas(Bundle savedInstanceState, ArrayList<Alarma> alarmas) {
        if (savedInstanceState == null) {
            // use the SimpleCursorAdapter to show the
            // elements in a ListView
            adapter = new AlarmaAdapter(AlarmaActivity.this, alarmas);
        } else {
            data = savedInstanceState.getParcelableArrayList("savedData");
            adapter = new AlarmaAdapter(AlarmaActivity.this, alarmas);
        }
        setListAdapter(adapter);
    }

    /**
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("savedData", data);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void loadAdapter(ArrayList<Alarma> alarmas) {
        alarmaToUpdate = null;
        adapter.updateAdapter(alarmas);

        layoutTimePicker.setVisibility(View.GONE);
        layoutList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showInicio() {
        finish();
    }

    @Override
    public void showTimePicker() {
        if (layoutTimePicker.getVisibility() == View.GONE) {
            layoutList.setVisibility(View.GONE);
            layoutTimePicker.setVisibility(View.VISIBLE);
        } else {
            layoutTimePicker.setVisibility(View.GONE);
            layoutList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideTimePicker() {
        layoutTimePicker.setVisibility(View.GONE);
        layoutList.setVisibility(View.VISIBLE);
        alarmaToUpdate = null;
    }

    /**
     * Cambio el color del texto del TimePicker
     * http://stackoverflow.com/questions/26015798/change-timepicker-text-color
     */
    @Override
    public void setTimePickerTextColor() {
        Resources system = Resources.getSystem();
        int hour_id = system.getIdentifier("hour", "id", "android");
        int minute_id = system.getIdentifier("minute", "id", "android");
        int amPm_id = system.getIdentifier("amPm", "id", "android");

        NumberPicker hourNumberPicker = (NumberPicker) timePicker.findViewById(hour_id);
        NumberPicker minuteNumberPicker = (NumberPicker) timePicker.findViewById(minute_id);
        NumberPicker amPmNumberPicker = (NumberPicker) timePicker.findViewById(amPm_id);

        setNumberPickerTextColor(hourNumberPicker);
        setNumberPickerTextColor(minuteNumberPicker);
        setNumberPickerTextColor(amPmNumberPicker);
    }

    /**
     * Seteo el color de cada elemento en el TimePicker
     *
     * @param numberPicker
     */
    private void setNumberPickerTextColor(NumberPicker numberPicker) {
        final int count = numberPicker.getChildCount();
        final int color = getResources().getColor(R.color.font_white);

        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);

            try {
                Field wheelPaintField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
                wheelPaintField.setAccessible(true);

                ((Paint) wheelPaintField.get(numberPicker)).setColor(color);
                ((EditText) child).setTextColor(color);
                numberPicker.invalidate();
            } catch (NoSuchFieldException e) {
                Log.w("setNumberPickerTextCol", e);
            } catch (IllegalAccessException e) {
                Log.w("setNumberPickerTextCol", e);
            } catch (IllegalArgumentException e) {
                Log.w("setNumberPickerTextCol", e);
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnVolver: {
                presenter.onSelectedInicio();
                break;
            }
            case R.id.btnCrearAlarma: {
                presenter.onSelectedCrearAlarma();
                break;
            }
            case R.id.btnSaveTime: {
                presenter.saveTime(alarmaToUpdate, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                break;
            }
            case R.id.btnCancelTime: {
                presenter.onSelectedCancelTime();
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Alarma Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
