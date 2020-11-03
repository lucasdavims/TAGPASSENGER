package com.br.tcc.tagpassenger.storage.instituition;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.tcc.tagpassenger.domain.instituition.Instituition;
import com.br.tcc.tagpassenger.storage.DatabaseHelper;

/**
 * Created by Davi on 27/09/2020.
 */

public class InstituitionRepositorySQLite extends SQLiteOpenHelper {

    private static InstituitionRepositorySQLite sInstance;
    public static final int VERSAO = DatabaseHelper.DATABASE_VERSION;
    public static final String TABELA = DatabaseHelper.TABLE_INSTITUITION;
    public static final String DATABASE = DatabaseHelper.DATABASE_NAME;
    public static final String[] COLS = {DatabaseHelper.KEY_ID, DatabaseHelper.KEY_INSTITUITION_NAME, DatabaseHelper.KEY_INSTITUITION_ADDRESS};

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

    public Instituition getById(Long id){

        Cursor cursor = getWritableDatabase().query(TABELA, COLS,
                "ID=?",new String[] {String.valueOf(id)},null,null,null,null);

        Instituition i = new Instituition();
        while(cursor.moveToNext())
        {
            i.setId(cursor.getLong(0));
            i.setName(cursor.getString(1));
            i.setAddress(cursor.getString(2));
        }
        return i;
    };
}
