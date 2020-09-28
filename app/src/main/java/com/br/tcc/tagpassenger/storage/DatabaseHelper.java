package com.br.tcc.tagpassenger.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Davi on 23/09/2020.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database Name
    private static final String DATABASE_NAME = "TAGPASSENGER";

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Table Names
    private static final String TABLE_INSTITUITION ="instituition";
    private static final String TABLE_MOTORIST="motorist";
    private static final String TABLE_VEHICLE="vehicle";
    private static final String TABLE_PASSENGER="passenger";
    private static final String TABLE_TAG="tag";
    private static final String TABLE_TRIP="trip";
    private static final String TABLE_TRIP_PASSENGER="trip_passenger";

    //Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_PASSENGER_ID = "passenger_id";

    //Instituition Table - column names
    private static final String KEY_INSTITUITION_NAME = "name";
    private static final String KEY_INSTITUITION_ADDRESS = "address";

    //Motorist Table - column names
    private static final String KEY_MOTORIST_CPF = "cpf";
    private static final String KEY_MOTORIST_RG = "rg";
    private static final String KEY_MOTORIST_CNH = "cnh";
    private static final String KEY_MOTORIST_NAME = "name";

    //Vehicle Table - column names
    private static final String KEY_VEHICLE_PLACA = "plate";
    private static final String KEY_VEHICLE_MODELO = "model";
    private static final String KEY_MOTORIST_ID = "motorist_id";

    //Tag Table - column names
    private static final String KEY_TAG_SERIAL_NUMBER = "serialNumber";

    //Passenger Table - column names
    private static final String KEY_PASSENGER_NAME = "name";
    private static final String KEY_PASSENGER_CPF = "cpf";
    private static final String KEY_PASSENGER_RG = "rg";
    private static final String KEY_TAG_ID = "tag_id";
    private static final String KEY_INSTITUITION_ID = "instituition_id";

    //Trip Table
    private static final String KEY_TRIP_BEGIN = "begin";
    private static final String KEY_TRIP_END = "end";
    private static final String KEY_VEHICLE_ID = "vehicle_id";

    //Trip_Passenger Table - column names
    private static final String KEY_TRIP_ID = "trip_id";


    private static final String CREATE_TABLE_INSTITUITION = "CREATE TABLE " + TABLE_INSTITUITION +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_INSTITUITION_NAME+" TEXT NOT NULL," +
            " "+KEY_INSTITUITION_ADDRESS+" TEXT NOT NULL" +
            " );";

    private static final String CREATE_TABLE_MOTORIST = "CREATE TABLE " + TABLE_MOTORIST +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_MOTORIST_CPF+" TEXT NOT NULL," +
            " "+KEY_MOTORIST_RG+" TEXT NOT NULL," +
            " "+KEY_MOTORIST_CNH+" TEXT NOT NULL," +
            " "+KEY_MOTORIST_NAME+" TEXT NOT NULL" +
            " );";

    private static final String CREATE_TABLE_VEHICLE = "CREATE TABLE " + TABLE_VEHICLE +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_VEHICLE_PLACA+" TEXT NOT NULL," +
            " "+KEY_VEHICLE_MODELO+" TEXT NOT NULL," +
            " "+KEY_MOTORIST_ID+" INTEGER" +
            " FOREIGN KEY("+KEY_MOTORIST_ID+") REFERENCES "+TABLE_MOTORIST+"("+KEY_ID+")"+
            " );";

    private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_TAG_SERIAL_NUMBER+" TEXT NOT NULL," +
            " "+KEY_TRIP_END+" TEXT NOT NULL," +
            " "+KEY_PASSENGER_ID+" INTEGER" +
            " FOREIGN KEY("+KEY_PASSENGER_ID+") REFERENCES "+TABLE_PASSENGER+"("+KEY_ID+")"+
            " );";

    private static final String CREATE_TABLE_TRIP = "CREATE TABLE " + TABLE_TRIP +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_TRIP_BEGIN+" TEXT NOT NULL," +
            " "+KEY_TRIP_END+" TEXT NOT NULL," +
            " "+KEY_MOTORIST_ID+" INTEGER" +
            " FOREIGN KEY("+KEY_VEHICLE_ID+") REFERENCES "+TABLE_VEHICLE+"("+KEY_ID+")"+
            " );";

    private static final String CREATE_TABLE_PASSENGER = "CREATE TABLE " + TABLE_PASSENGER +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_PASSENGER_CPF+" TEXT NOT NULL," +
            " "+KEY_PASSENGER_RG+" TEXT NOT NULL," +
            " "+KEY_PASSENGER_NAME+" TEXT NOT NULL," +
            " "+KEY_INSTITUITION_ID+" INTEGER," +
            " "+KEY_TAG_ID+" INTEGER" +
            " FOREIGN KEY("+KEY_INSTITUITION_ID+") REFERENCES "+TABLE_INSTITUITION+"("+KEY_ID+")"+
            " FOREIGN KEY("+KEY_TAG_ID+") REFERENCES "+TABLE_TAG+"("+KEY_ID+")"+
            " );";

    private static final String CREATE_TABLE_TRIP_PASSENGER = "CREATE TABLE " + TABLE_TRIP_PASSENGER +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_TAG_SERIAL_NUMBER+" TEXT NOT NULL," +
            " "+KEY_TRIP_END+" TEXT NOT NULL," +
            " "+KEY_PASSENGER_ID+" INTEGER," +
            " "+KEY_TRIP_ID+" INTEGER" +
            " FOREIGN KEY("+KEY_PASSENGER_ID+") REFERENCES "+TABLE_PASSENGER+"("+KEY_ID+")"+
            " FOREIGN KEY("+KEY_TRIP_ID+") REFERENCES "+TABLE_TRIP+"("+KEY_ID+")"+
            " );";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INSTITUITION);
        db.execSQL(CREATE_TABLE_MOTORIST);
        db.execSQL(CREATE_TABLE_VEHICLE);
        db.execSQL(CREATE_TABLE_TAG);
        db.execSQL(CREATE_TABLE_TRIP);
        db.execSQL(CREATE_TABLE_PASSENGER);
        db.execSQL(CREATE_TABLE_TRIP_PASSENGER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP_PASSENGER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSENGER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOTORIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTITUITION);

        this.onCreate(db);

    }
}
