package com.br.tcc.tagpassenger.storage.passenger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.tcc.tagpassenger.domain.instituition.Instituition;
import com.br.tcc.tagpassenger.domain.passenger.Passenger;
import com.br.tcc.tagpassenger.domain.tag.Tag;
import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Davi on 08/09/2020.
 */

public class PassengerRepositorySQLite extends SQLiteOpenHelper {

    private static PassengerRepositorySQLite sInstance;
    private static final int VERSAO = 1;
    private static final String TABELA = "PASSENGER";
    private static final String DATABASE = "TAGPASSENGER";
    private static final String[] COLS = {"id", "cpf", "rg", "name", "instituition", "vehicle", "tag"};

    public static synchronized PassengerRepositorySQLite getInstance(Context context){

        if (sInstance == null){
            sInstance = new PassengerRepositorySQLite(context.getApplicationContext());
        }

        return sInstance;
    }

    public PassengerRepositorySQLite(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String ddl =
                "CREATE TABLE " + TABELA +
                        "(id INTEGER PRIMARY KEY," +
                        " cpf TEXT NOT NULL," +
                        " rg TEXT NOT NULL," +
                        " name TEXT NOT NULL," +
                        " instituition INTEGER" +
                        " vehicle INTEGER" +
                        " tag INTEGER" +
                        " FOREIGN KEY(instituition) REFERENCES instituition(id)"+
                        " FOREIGN KEY(vehicle) REFERENCES vehicle(id)"+
                        " FOREIGN KEY(tag) REFERENCES tag(id)"+
                        ");";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);

    }

    /**
     * Get passenger is implemented without relationships full
     * @param id
     * @return
     */
    public Passenger getPassengerLazy(long id){

        Cursor cursor = getWritableDatabase().query(TABELA, COLS,
                "ID=?",new String[] {String.valueOf(id)},null,null,null,null);

        Passenger passenger = new Passenger();
        Instituition instituition = new Instituition();
        Vehicle vehicle = new Vehicle();
        Tag tag = new Tag();

        while(cursor.moveToNext())
        {
            passenger.setId(cursor.getLong(0));
            passenger.setCpf(cursor.getString(1));
            passenger.setRg(cursor.getString(2));
            passenger.setName(cursor.getString(3));

            //set id of instituition
            instituition.setId(cursor.getLong(4));
            passenger.setInstituition(instituition);

            //set id of vehicle
            vehicle.setId(cursor.getLong(5));
            passenger.setVehicle(vehicle);

            //set id of tag
            tag.setId(cursor.getLong(6));
            passenger.setTag(tag);
        }

        cursor.close();

        return passenger;
    }

    public List<Passenger> getPassengersLazy(){

        Cursor cursor = getWritableDatabase().query(TABELA, COLS,
                 null,null,null,null,null);

        List<Passenger> passengers = new ArrayList<>();

        while(cursor.moveToNext())
        {
            Passenger passenger = new Passenger();
            Instituition instituition = new Instituition();
            Vehicle vehicle = new Vehicle();
            Tag tag = new Tag();

            passenger.setId(cursor.getLong(0));
            passenger.setCpf(cursor.getString(1));
            passenger.setRg(cursor.getString(2));
            passenger.setName(cursor.getString(3));

            //set id of instituition
            instituition.setId(cursor.getLong(4));
            passenger.setInstituition(instituition);

            //set id of vehicle
            vehicle.setId(cursor.getLong(5));
            passenger.setVehicle(vehicle);

            //set id of tag
            tag.setId(cursor.getLong(6));
            passenger.setTag(tag);

            passengers.add(passenger);
        }

        cursor.close();

        return null;
    }


}
