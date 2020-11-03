package com.br.tcc.tagpassenger.storage.motorist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.tcc.tagpassenger.storage.DatabaseHelper;

/**
 * Created by Davi on 27/09/2020.
 */

public class MotoristRepositorySQLite extends SQLiteOpenHelper {

    private static MotoristRepositorySQLite sInstance;
    private static final int VERSAO = DatabaseHelper.DATABASE_VERSION;
    private static final String TABELA = DatabaseHelper.TABLE_MOTORIST;
    private static final String DATABASE = DatabaseHelper.DATABASE_NAME;
    private static final String[] COLS = {DatabaseHelper.KEY_ID, DatabaseHelper.KEY_MOTORIST_CPF, DatabaseHelper.KEY_MOTORIST_RG, DatabaseHelper.KEY_MOTORIST_CNH, DatabaseHelper.KEY_MOTORIST_NAME};

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
