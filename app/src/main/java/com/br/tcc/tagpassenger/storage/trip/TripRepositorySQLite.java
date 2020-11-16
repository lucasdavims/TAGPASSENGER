package com.br.tcc.tagpassenger.storage.trip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.br.tcc.tagpassenger.domain.trip.Trip;
import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;
import com.br.tcc.tagpassenger.storage.DatabaseHelper;
import com.br.tcc.tagpassenger.storage.vehicle.VehicleRepositorySQLite;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Davi on 27/09/2020.
 */

public class TripRepositorySQLite extends SQLiteOpenHelper {

    private static TripRepositorySQLite sInstance;
    private static final int VERSAO = DatabaseHelper.DATABASE_VERSION;
    public static final String TABELA = DatabaseHelper.TABLE_TRIP;
    public static final String DATABASE = DatabaseHelper.DATABASE_NAME;
    public static final String[] COLS = {DatabaseHelper.KEY_ID, DatabaseHelper.KEY_TRIP_BEGIN, DatabaseHelper.KEY_TRIP_END, DatabaseHelper.KEY_VEHICLE_ID, DatabaseHelper.KEY_TRIP_ID};

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


    public Trip getCurrentTrip(){
        Cursor cursor = getWritableDatabase().query(TABELA, COLS,
                "begin is not null AND end is null",null,null,null,null,null);

        Trip trip = new Trip();

        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while(cursor.moveToNext()){

            trip.setId(cursor.getLong(0));
            try {
                trip.setBegin(iso8601Format.parse(cursor.getString(1)));
            } catch (Exception e) {
               //no converterDate
            }
            try {
                trip.setEnd(iso8601Format.parse(cursor.getString(2)));
            } catch (Exception e) {
                //no converterDate
            }

            trip.setVehicle(getVehicleById(cursor.getLong(3)));

            try {
                if(cursor.getLong(4) > 0){
                    trip.setTrip(new Trip(cursor.getLong(4)));
                }
            } catch (Exception e) {
                //no converterDate
            }


        }

        return trip;
    }

    public Trip getCurrentTripByPassengerId(Long passengerId){
        Cursor cursor = getWritableDatabase().rawQuery("select t.id, t.trip_id from trip t " +
                "join trip_passenger tp " +
                "on t.id = tp.trip_id " +
                "join passenger p " +
                "on p.id = tp.passenger_id " +
                "where t.end is null and " +
                "p.id = ?",new String[]{String.valueOf(passengerId)});
        return null;
    }

    public Vehicle getVehicleById(Long id){
        Vehicle v = new Vehicle();
        if (id != null) {

            Cursor cursor = getWritableDatabase().query(VehicleRepositorySQLite.TABELA, VehicleRepositorySQLite.COLS,
                    "ID=?", new String[]{String.valueOf(id)}, null, null, null, null);


            while (cursor.moveToNext()) {
                v.setId(cursor.getLong(0));
                v.setPlate(cursor.getString(1));
                v.setModel(cursor.getString(2));
            }
        }
        return v;
    }

    public int update(Trip trip){

        ContentValues args = new ContentValues();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        args.put(COLS[1], dateFormat.format(trip.getBegin().getTime()));

        if(trip.getEnd() != null)
            args.put(COLS[2], dateFormat.format(trip.getEnd()));

        args.put(COLS[3], trip.getVehicle().getId());

        if (trip.getTrip() != null)
            args.put(COLS[4], trip.getTrip().getId());

        return getWritableDatabase().update(TABELA, args,"ID=?",
                new String[] {String.valueOf(trip.getId())});
    }

    public Trip persist(Trip trip){
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        values.put(COLS[1], dateFormat.format(trip.getBegin().getTime()));

        if(trip.getEnd() != null)
            values.put(COLS[2], dateFormat.format(trip.getEnd()));

        values.put(COLS[3], trip.getVehicle().getId());

        if (trip.getTrip() != null)
            values.put(COLS[4], trip.getTrip().getId());

        Long result = getWritableDatabase().insert(TABELA, null, values);

        trip.setId(result);

        return trip;
    }
}
