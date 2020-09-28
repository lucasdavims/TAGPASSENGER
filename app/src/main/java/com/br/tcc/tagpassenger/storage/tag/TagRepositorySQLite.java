package com.br.tcc.tagpassenger.storage.tag;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.tcc.tagpassenger.storage.instituition.InstituitionRepositorySQLite;

/**
 * Created by Davi on 27/09/2020.
 */

public class TagRepositorySQLite extends SQLiteOpenHelper {

    private static TagRepositorySQLite sInstance;
    private static final int VERSAO = 1;
    private static final String TABELA = "TAG";
    private static final String DATABASE = "TAGPASSENGER";
    private static final String[] COLS = {"id", "serialNumber", "passenger"};

    public static synchronized TagRepositorySQLite getInstance(Context context){

        if (sInstance == null){
            sInstance = new TagRepositorySQLite(context.getApplicationContext());
        }

        return sInstance;
    }


    public TagRepositorySQLite(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
