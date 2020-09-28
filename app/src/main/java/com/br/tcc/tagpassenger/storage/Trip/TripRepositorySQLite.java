package com.br.tcc.tagpassenger.storage.Trip;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Davi on 27/09/2020.
 */

public class TripRepositorySQLite extends SQLiteOpenHelper {

    private static TripRepositorySQLite sInstance;
    private static final int VERSAO = 1;
    private static final String TABELA = "TRIP";
    private static final String DATABASE = "TAGPASSENGER";
    private static final String[] COLS = {"id", "begin", "end", "vehicle", "passengers"};

    public static synchronized TripRepositorySQLite getInstance(Context context){

        if (sInstance == null){
            sInstance = new TripRepositorySQLite(context.getApplicationContext());
        }

        return sInstance;
    }


    public TripRepositorySQLite(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
