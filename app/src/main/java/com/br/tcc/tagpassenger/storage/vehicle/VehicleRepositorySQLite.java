package com.br.tcc.tagpassenger.storage.vehicle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.tcc.tagpassenger.domain.motorist.Motorist;
import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;
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


    public Vehicle getVeiculo() {

        String query = "select * from vehicle";
        Cursor c = getWritableDatabase().rawQuery(query, null);
        Vehicle vehicle = new Vehicle();
        Motorist motorist = new Motorist();

        if (c.moveToFirst()) {
            do {
                vehicle.setId(c.getLong(0));
                vehicle.setPlate(c.getString(1));
                vehicle.setModel(c.getString(2));
                motorist.setId(c.getLong(3));
                vehicle.setMotorist(motorist);
            } while (c.moveToNext());
        }
        return vehicle;
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
