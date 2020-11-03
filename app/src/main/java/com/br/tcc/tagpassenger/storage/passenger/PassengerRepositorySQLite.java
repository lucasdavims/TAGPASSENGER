package com.br.tcc.tagpassenger.storage.passenger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.tcc.tagpassenger.domain.instituition.Instituition;
import com.br.tcc.tagpassenger.domain.passenger.Passenger;
import com.br.tcc.tagpassenger.domain.tag.Tag;
import com.br.tcc.tagpassenger.domain.trip.Trip;
import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;
import com.br.tcc.tagpassenger.storage.DatabaseHelper;
import com.br.tcc.tagpassenger.storage.instituition.InstituitionRepositorySQLite;
import com.br.tcc.tagpassenger.storage.tag.TagRepositorySQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Davi on 08/09/2020.
 */

public class PassengerRepositorySQLite extends SQLiteOpenHelper {

    private static PassengerRepositorySQLite sInstance;
    private static final int VERSAO = DatabaseHelper.DATABASE_VERSION;
    private static final String TABELA = DatabaseHelper.TABLE_PASSENGER;
    private static final String TABELA_TRIP_PASSENGER = DatabaseHelper.TABLE_TRIP_PASSENGER;
    private static final String DATABASE = DatabaseHelper.DATABASE_NAME;
    private static final String[] COLS = {DatabaseHelper.KEY_ID, DatabaseHelper.KEY_PASSENGER_CPF, DatabaseHelper.KEY_PASSENGER_RG, DatabaseHelper.KEY_PASSENGER_NAME,DatabaseHelper.KEY_INSTITUITION_ID, DatabaseHelper.KEY_TAG_ID};

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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

            //set id of tag
            tag.setId(cursor.getLong(6));
            passenger.setTag(tag);

            passengers.add(passenger);
        }

        cursor.close();

        return null;
    }

    public Passenger persist(Passenger passenger) {

        ContentValues values = new ContentValues();

        values.put(COLS[2], passenger.getCpf());
        values.put(COLS[3], passenger.getRg());
        values.put(COLS[4], passenger.getName());
        values.put(COLS[5], passenger.getInstituition().getId());
        values.put(COLS[6], passenger.getTag().getId());

        Long  newId = getWritableDatabase().insert(TABELA, null, values);

        passenger.setId(newId);

        return passenger;
    }

    public List<Passenger> getByTripWithRelationships(Trip trip) {
        String searchTripPassengers = "Select * from "+TABELA+" p " +
                                        "Join "+TABELA_TRIP_PASSENGER+ " tp on (p.id = tp.passenger_id)" +
                                        "Where tp.trip_id = ? ";

        List<Passenger> passengers = new ArrayList<>();

        Cursor c = getWritableDatabase().rawQuery(searchTripPassengers, new String[]{trip.getId().toString()});
        if (c.moveToFirst()) {
            do {
                // Passing values
                Passenger p = new Passenger();
                p.setId(c.getLong(0));
                p.setCpf(c.getString(1));
                p.setRg(c.getString(2));
                p.setName(c.getString(3));

                p.setInstituition(getInstituitionById(c.getLong(4)));

                p.setTag(getTagById(c.getLong(5)));

                passengers.add(p);

                // Do something Here with values
            } while (c.moveToNext());


        }

        return passengers;
    }


    public Instituition getInstituitionById(Long id){

        Instituition i = new Instituition();

        if(id != null) {
            Cursor cursor = getWritableDatabase().query(InstituitionRepositorySQLite.TABELA, InstituitionRepositorySQLite.COLS,
                    "ID=?", new String[]{String.valueOf(id)}, null, null, null, null);

            while (cursor.moveToNext()) {
                i.setId(cursor.getLong(0));
                i.setName(cursor.getString(1));
                i.setAddress(cursor.getString(2));
            }

        }

        return i;
    };

    public Tag getTagById(Long id){

        Tag t = new Tag();

        if(id != null) {
            Cursor cursor = getWritableDatabase().query(TagRepositorySQLite.TABELA, TagRepositorySQLite.COLS,
                    "ID=?", new String[]{String.valueOf(id)}, null, null, null, null);

            while (cursor.moveToNext()) {
                t.setId(cursor.getLong(0));
                t.setSerialNumber(cursor.getString(1));
            }

        }

        return t;
    }
}
