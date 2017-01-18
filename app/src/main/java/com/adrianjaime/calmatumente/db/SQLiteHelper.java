package com.adrianjaime.calmatumente.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by emaneff on 26/12/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calmatumente.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "alarmas";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_HORA = "hora";
    public static final String COLUMN_MINUTO = "minuto";
    public static final String COLUMN_LUNES = "lunes";
    public static final String COLUMN_MARTES = "martes";
    public static final String COLUMN_MIERCOLES = "miercoles";
    public static final String COLUMN_JUEVES = "jueves";
    public static final String COLUMN_VIERNES = "viernes";
    public static final String COLUMN_SABADOS = "sabados";
    public static final String COLUMN_DOMINGOS = "domingos";

    //Sentencia SQL para crear la tabla de Usuarios
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_HORA + " integer not null, "
            + COLUMN_MINUTO + " integer not null, "
            + COLUMN_LUNES + " integer not null, "
            + COLUMN_MARTES + " integer not null, "
            + COLUMN_MIERCOLES + " integer not null, "
            + COLUMN_JUEVES + " integer not null, "
            + COLUMN_VIERNES + " integer not null, "
            + COLUMN_SABADOS + " integer not null, "
            + COLUMN_DOMINGOS + " integer not null);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + versionAnterior + " to "
                        + versionNueva + ", which will destroy all old data");
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Alarmas" + TABLE_NAME);
        //Se crea la nueva versión de la tabla
        onCreate(db);
    }

}
