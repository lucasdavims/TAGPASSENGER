package com.br.tcc.tagpassenger.storage.instituition;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Davi on 27/09/2020.
 */

public class InstituitionRepositorySQLite extends SQLiteOpenHelper {

    private static InstituitionRepositorySQLite sInstance;
    private static final int VERSAO = 1;
    private static final String TABELA = "INSTITUITION";
    private static final String DATABASE = "TAGPASSENGER";
    private static final String[] COLS = {"id", "name", "address", "passengers"};

    public static synchronized InstituitionRepositorySQLite getInstance(Context context){

        if (sInstance == null){
            sInstance = new InstituitionRepositorySQLite(context.getApplicationContext());
        }

        return sInstance;
    }


    public InstituitionRepositorySQLite(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
