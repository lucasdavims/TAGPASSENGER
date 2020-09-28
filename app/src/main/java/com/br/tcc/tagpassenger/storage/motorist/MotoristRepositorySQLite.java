package com.br.tcc.tagpassenger.storage.motorist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Davi on 27/09/2020.
 */

public class MotoristRepositorySQLite extends SQLiteOpenHelper {

    private static MotoristRepositorySQLite sInstance;
    private static final int VERSAO = 1;
    private static final String TABELA = "MOTORIST";
    private static final String DATABASE = "TAGPASSENGER";
    private static final String[] COLS = {"id", "cpf", "rg", "cnh", "name", "vehicles"};

    public static synchronized MotoristRepositorySQLite getInstance(Context context){

        if (sInstance == null){
            sInstance = new MotoristRepositorySQLite(context.getApplicationContext());
        }

        return sInstance;
    }


    public MotoristRepositorySQLite(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
