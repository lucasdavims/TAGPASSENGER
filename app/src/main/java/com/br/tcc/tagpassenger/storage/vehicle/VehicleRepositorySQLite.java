package com.br.tcc.tagpassenger.storage.vehicle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.tcc.tagpassenger.storage.DatabaseHelper;

/**
 * Created by Davi on 27/09/2020.
 */

public class VehicleRepositorySQLite extends SQLiteOpenHelper {

    private static VehicleRepositorySQLite sInstance;
    private static final int VERSAO = DatabaseHelper.DATABASE_VERSION;
    public static final String TABELA = DatabaseHelper.TABLE_VEHICLE;
    public static final String DATABASE = DatabaseHelper.DATABASE_NAME;
    public static final String[] COLS = {DatabaseHelper.KEY_ID, DatabaseHelper.KEY_VEHICLE_PLATE, DatabaseHelper.KEY_VEHICLE_MODEL};

    public static synchronized VehicleRepositorySQLite getInstance(Context context){

        if (sInstance == null){
            sInstance = new VehicleRepositorySQLite(context.getApplicationContext());
        }

        return sInstance;
    }


    public VehicleRepositorySQLite(Context context) {
        super(context, DATABASE, null, VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
