package com.adrianjaime.calmatumente.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adrianjaime.calmatumente.R;
import com.adrianjaime.calmatumente.db.DataSource;
import com.adrianjaime.calmatumente.pojo.Alarma;

/**
 * Created by emaneff on 27/12/2016.
 * https://elbauldelprogramador.com/adapter-personalizado-en-android/
 */
public class AlarmaAdapter extends BaseAdapter {

    static class ViewHolder {
        ImageView ivSave;
        ImageView ivDelete;
        TextView tvAlarma;
        CheckBox chkLunes;
        CheckBox chkMartes;
        CheckBox chkMiercoles;
        CheckBox chkJueves;
        CheckBox chkViernes;
        CheckBox chkSabados;
        CheckBox chkDomingos;
    }

    private static final String TAG = "CustomAdapter";
    private static int convertViewCounter = 0;

    private ArrayList<Alarma> data;
    private LayoutInflater inflater = null;
    private Context context;
    private DataSource dataSource;

    /**
     *
     * @param context
     * @param alarmas
     */
    public AlarmaAdapter(Context context, ArrayList<Alarma> alarmas) {
        Log.v(TAG, "Constructing CustomAdapter");
        this.data = alarmas;
        this.context = context;
        inflater = LayoutInflater.from(context);

        dataSource = new DataSource(context);
        dataSource.open();
    }

    @Override
    public int getCount()
    {
        Log.v(TAG, "in getCount()");
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        Log.v(TAG, "in getItem() for position " + position);
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        Log.v(TAG, "in getItemId() for position " + position);
        return position;
    }

    @Override
    public int getViewTypeCount()
    {
        Log.v(TAG, "in getViewTypeCount()");
        return 1;
    }

    @Override
    public int getItemViewType(int position)
    {
        Log.v(TAG, "in getItemViewType() for position " + position);
        return 0;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    /**
     *
     * @param alarmas
     */
    public void updateAdapter(ArrayList<Alarma> alarmas) {
        this.data = alarmas;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //index = position;

        Log.v(TAG, "in getView for position " + position + ", convertView is "
                + ((convertView == null) ? "null" : "being recycled"));

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_alarma, null);

            convertViewCounter++;
            Log.v(TAG, convertViewCounter + " convertViews have been created");

            holder = new ViewHolder();

            //Instancio todos los controles
            holder.ivSave = (ImageView) convertView.findViewById(R.id.ivSave);
            holder.ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            holder.tvAlarma = (TextView) convertView.findViewById(R.id.tvAlarma);
            holder.chkLunes = (CheckBox) convertView.findViewById(R.id.chkLunes);
            holder.chkMartes = (CheckBox) convertView.findViewById(R.id.chkMartes);
            holder.chkMiercoles = (CheckBox) convertView.findViewById(R.id.chkMiercoles);
            holder.chkJueves = (CheckBox) convertView.findViewById(R.id.chkJueves);
            holder.chkViernes = (CheckBox) convertView.findViewById(R.id.chkViernes);
            holder.chkSabados = (CheckBox) convertView.findViewById(R.id.chkSabados);
            holder.chkDomingos = (CheckBox) convertView.findViewById(R.id.chkDomingos);

            //Le asigno el evento Onclick a los checkbox
            holder.chkLunes.setOnClickListener(checkLunesListener);
            holder.chkMartes.setOnClickListener(checkMartesListener);
            holder.chkMiercoles.setOnClickListener(checkMiercolesListener);
            holder.chkJueves.setOnClickListener(checkJuevesListener);
            holder.chkViernes.setOnClickListener(checkViernesListener);
            holder.chkSabados.setOnClickListener(checkSabadosListener);
            holder.chkDomingos.setOnClickListener(checkDomingosListener);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Para porde hacer click en el checkbox
        Alarma alarma = (Alarma) getItem(position);
        holder.chkLunes.setTag(alarma);
        holder.chkMartes.setTag(alarma);
        holder.chkMiercoles.setTag(alarma);
        holder.chkJueves.setTag(alarma);
        holder.chkViernes.setTag(alarma);
        holder.chkSabados.setTag(alarma);
        holder.chkDomingos.setTag(alarma);

        // Setting all values in listview
        holder.tvAlarma.setText(data.get(position).toString());
        holder.chkLunes.setChecked(data.get(position).getLunes());
        holder.chkMartes.setChecked(data.get(position).getMartes());
        holder.chkMiercoles.setChecked(data.get(position).getMiercoles());
        holder.chkJueves.setChecked(data.get(position).getJueves());
        holder.chkViernes.setChecked(data.get(position).getViernes());
        holder.chkSabados.setChecked(data.get(position).getSabados());
        holder.chkDomingos.setChecked(data.get(position).getDomingos());

        holder.ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alarma alarma = (Alarma) getItem(position);
                dataSource.update(alarma);
                notifyDataSetChanged();
                Toast.makeText(context, "La Alarma fue actualizada", Toast.LENGTH_SHORT).show();
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("Quitar Alarma", position);
            }
        });

        return convertView;
    }

    /**
     *
     * @param title
     */
    private void showAlertDialog(String title, final int position) {
        final Alarma alarma = (Alarma) getItem(position);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage("¿Desea quitar la alarma " + alarma.toString() +"?");
        alertDialog.setIcon(R.drawable.ic_delete);

        alertDialog.setPositiveButton("Quitar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dataSource.delete(alarma);
                data.remove(alarma);

                notifyDataSetChanged();
            } });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            } });

        alertDialog.show();
    }


    /**
     *
     * @param position
     */
    public void setCheck(int position) {
        Alarma alarma = data.get(position);
        alarma.setLunes(!alarma.getLunes());
        alarma.setMartes(!alarma.getMartes());
        alarma.setMiercoles(!alarma.getMiercoles());
        alarma.setJueves(!alarma.getJueves());
        alarma.setViernes(!alarma.getViernes());
        alarma.setSabados(!alarma.getSabados());
        alarma.setDomingos(!alarma.getDomingos());

        notifyDataSetChanged();
    }

    /**
     *
     * @param state
     */
    public void checkAll(boolean state) {
        for (int i = 0; i < data.size(); i++)
            data.get(i).setLunes(state);
    }

    /**
     *
     */
    public void cancelSelectedPost() {
        int i = 0;
        while (i < getCount())
        {
            if (data.get(i).getLunes())
            {
                data.remove(data.indexOf(data.get(i)));
            } else
                i++;
        }
        notifyDataSetChanged();
    }

    /***
     *
     * @return
     */
    public boolean haveSomethingSelected() {
        for (int i = 0; i < data.size(); i++)
            if (data.get(i).getLunes())
                return true;
        return false;
    }

    /**
     * Este método es para poder seleccionar una fila directamente con el
     * checkbox en lugar de tener que pulsar en la liste en sí
     */
    private OnClickListener checkLunesListener = new OnClickListener() {
        @Override
        public void onClick(View view)
        {
            Alarma alarma = (Alarma) view.getTag();
            alarma.setLunes(!alarma.getLunes());
        }
    };

    /**
     *
     */
    private OnClickListener checkMartesListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Alarma alarma = (Alarma) view.getTag();
            alarma.setMartes(!alarma.getMartes());
        }
    };

    private OnClickListener checkMiercolesListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Alarma alarma = (Alarma) view.getTag();
            alarma.setMiercoles(!alarma.getMiercoles());
        }
    };

    private OnClickListener checkJuevesListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Alarma alarma = (Alarma) view.getTag();
            alarma.setJueves(!alarma.getJueves());
        }
    };

    private OnClickListener checkViernesListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Alarma alarma = (Alarma) view.getTag();
            alarma.setViernes(!alarma.getViernes());
        }
    };

    private OnClickListener checkSabadosListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Alarma alarma = (Alarma) view.getTag();
            alarma.setSabados(!alarma.getSabados());
        }
    };

    private OnClickListener checkDomingosListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Alarma alarma = (Alarma) view.getTag();
            alarma.setDomingos(!alarma.getDomingos());
        }
    };



}
