package com.br.tcc.tagpassenger.storage.vehicle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.tcc.tagpassenger.storage.instituition.InstituitionRepositorySQLite;

/**
 * Created by Davi on 27/09/2020.
 */

public class VehicleRepositorySQLite extends SQLiteOpenHelper {

    private static VehicleRepositorySQLite sInstance;
    private static final int VERSAO = 1;
    private static final String TABELA = "VEHICLE";
    private static final String DATABASE = "TAGPASSENGER";
    private static final String[] COLS = {"id", "placa", "modelo", "passengers"};

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
