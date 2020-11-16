package com.br.tcc.tagpassenger.storage.tag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.tcc.tagpassenger.domain.passenger.Passenger;
import com.br.tcc.tagpassenger.domain.tag.Tag;
import com.br.tcc.tagpassenger.storage.DatabaseHelper;
import com.br.tcc.tagpassenger.storage.instituition.InstituitionRepositorySQLite;

/**
 * Created by Davi on 27/09/2020.
 */

public class TagRepositorySQLite extends SQLiteOpenHelper {

    private static TagRepositorySQLite sInstance;
    public static final int VERSAO = DatabaseHelper.DATABASE_VERSION;
    public static final String TABELA = DatabaseHelper.TABLE_TAG;
    public static final String DATABASE = DatabaseHelper.DATABASE_NAME;
    public static final String[] COLS = {DatabaseHelper.KEY_ID,DatabaseHelper.KEY_TAG_SERIAL_NUMBER, DatabaseHelper.KEY_PASSENGER_ID};

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

    public Tag getTagBySerialNumber(String rfidTag){
        Cursor cursor = getWritableDatabase().query(TABELA, COLS,
                DatabaseHelper.KEY_TAG_SERIAL_NUMBER + "=?",new String[] {String.valueOf(rfidTag)},null,null,null,null);
        Tag tag = new Tag();
        Passenger passenger;
        while(cursor.moveToNext())
        {
            passenger = new Passenger();
            tag.setId(cursor.getLong(0));
            tag.setSerialNumber(cursor.getString(1));
            passenger.setId(cursor.getLong(2));
            tag.setPassenger(passenger);
            return tag;
        }

        return null;

    }

    public Tag persist(Tag tag){

        ContentValues values = new ContentValues();
        values.put(COLS[2], tag.getSerialNumber());
        values.put(COLS[3], tag.getPassenger().getId());

        Long newId = getWritableDatabase().insert(TABELA, null, values);

        tag.setId(newId);

        return tag;
    };
}
