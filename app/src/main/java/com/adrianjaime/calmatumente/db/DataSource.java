package com.adrianjaime.calmatumente.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.adrianjaime.calmatumente.pojo.Alarma;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emaneff on 26/12/2016.
 */
public class DataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_HORA,
            SQLiteHelper.COLUMN_MINUTO,
            SQLiteHelper.COLUMN_LUNES,
            SQLiteHelper.COLUMN_MARTES,
            SQLiteHelper.COLUMN_MIERCOLES,
            SQLiteHelper.COLUMN_JUEVES,
            SQLiteHelper.COLUMN_VIERNES,
            SQLiteHelper.COLUMN_SABADOS,
            SQLiteHelper.COLUMN_DOMINGOS
    };

    /**
     *
     * @param context
     */
    public DataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    /**
     *
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     *
     */
    public void close() {
        dbHelper.close();
    }

    /**
     *
     * @param alarma
     * @return
     */
    public Alarma create(Alarma alarma) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_HORA, alarma.getHora());
        values.put(SQLiteHelper.COLUMN_MINUTO, alarma.getMinuto());
        values.put(SQLiteHelper.COLUMN_LUNES, alarma.getLunes() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_MARTES, alarma.getMartes() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_MIERCOLES, alarma.getMiercoles() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_JUEVES, alarma.getJueves() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_VIERNES, alarma.getViernes() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_SABADOS, alarma.getSabados() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_DOMINGOS, alarma.getDomingos() ? 1 : 0);

        long insertId = database.insert(SQLiteHelper.TABLE_NAME, null, values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_NAME,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Alarma newAlarma = cursorToAlarma(cursor);
        cursor.close();
        return newAlarma;
    }

    /**
     *
     * @param alarma
     */
    public void update(Alarma alarma) {
        long id = alarma.getId();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_HORA, alarma.getHora());
        values.put(SQLiteHelper.COLUMN_MINUTO, alarma.getMinuto());
        values.put(SQLiteHelper.COLUMN_LUNES, alarma.getLunes() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_MARTES, alarma.getMartes() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_MIERCOLES, alarma.getMiercoles() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_JUEVES, alarma.getJueves() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_VIERNES, alarma.getViernes() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_SABADOS, alarma.getSabados() ? 1 : 0);
        values.put(SQLiteHelper.COLUMN_DOMINGOS, alarma.getDomingos() ? 1 : 0);

        System.out.println("Alarma update with id: " + id);
        database.update(SQLiteHelper.TABLE_NAME, values, SQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    /**
     *
     * @param alarma
     */
    public void delete(Alarma alarma) {
        long id = alarma.getId();
        System.out.println("Alarma deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_NAME, SQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    /**
     *
     * @return
     */
    public ArrayList<Alarma> getAll() {
        ArrayList<Alarma> alarmas = new ArrayList<Alarma>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Alarma alarma = cursorToAlarma(cursor);
            alarmas.add(alarma);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return alarmas;
    }

    /**
     *
     * @param cursor
     * @return
     */
    private Alarma cursorToAlarma(Cursor cursor) {
        Alarma alarma = new Alarma();
        alarma.setId(cursor.getLong(0));
        alarma.setHora(cursor.getInt(1));
        alarma.setMinuto(cursor.getInt(2));
        alarma.setLunes(cursor.getInt(3) == 1 ? true : false);
        alarma.setMartes(cursor.getInt(4) == 1 ? true : false);
        alarma.setMiercoles(cursor.getInt(5) == 1 ? true : false);
        alarma.setJueves(cursor.getInt(6) == 1 ? true : false);
        alarma.setViernes(cursor.getInt(7) == 1 ? true : false);
        alarma.setSabados(cursor.getInt(8) == 1 ? true : false);
        alarma.setDomingos(cursor.getInt(9) == 1 ? true : false);

        return alarma;
    }

}
