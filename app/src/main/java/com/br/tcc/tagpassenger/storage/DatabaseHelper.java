package com.br.tcc.tagpassenger.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Davi on 23/09/2020.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database Name
    public static final String DATABASE_NAME = "TAGPASSENGER";

    //Database Version
    public static final int DATABASE_VERSION = 2;

    //Table Names
    public static final String TABLE_INSTITUITION ="instituition";
    public static final String TABLE_MOTORIST="motorist";
    public static final String TABLE_VEHICLE="vehicle";
    public static final String TABLE_PASSENGER="passenger";
    public static final String TABLE_TAG="tag";
    public static final String TABLE_TRIP="trip";
    public static final String TABLE_TRIP_PASSENGER="trip_passenger";

    //Common column names
    public static final String KEY_ID = "id";
    public static final String KEY_PASSENGER_ID = "passenger_id";

    //Instituition Table - column names
    public static final String KEY_INSTITUITION_NAME = "name";
    public static final String KEY_INSTITUITION_ADDRESS = "address";

    //Motorist Table - column names
    public static final String KEY_MOTORIST_CPF = "cpf";
    public static final String KEY_MOTORIST_RG = "rg";
    public static final String KEY_MOTORIST_CNH = "cnh";
    public static final String KEY_MOTORIST_NAME = "name";

    //Vehicle Table - column names
    public static final String KEY_VEHICLE_PLATE = "plate";
    public static final String KEY_VEHICLE_MODEL = "model";
    public static final String KEY_MOTORIST_ID = "motorist_id";

    //Tag Table - column names
    public static final String KEY_TAG_SERIAL_NUMBER = "serial_number";

    //Passenger Table - column names
    public static final String KEY_PASSENGER_NAME = "name";
    public static final String KEY_PASSENGER_CPF = "cpf";
    public static final String KEY_PASSENGER_RG = "rg";
    public static final String KEY_TAG_ID = "tag_id";
    public static final String KEY_INSTITUITION_ID = "instituition_id";

    //Trip Table
    public static final String KEY_TRIP_BEGIN = "begin";
    public static final String KEY_TRIP_END = "end";
    public static final String KEY_VEHICLE_ID = "vehicle_id";

    //Trip_Passenger Table - column names
    public static final String KEY_TRIP_ID = "trip_id";


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
            " "+ KEY_VEHICLE_PLATE +" TEXT NOT NULL," +
            " "+ KEY_VEHICLE_MODEL +" TEXT NOT NULL," +
            " "+KEY_MOTORIST_ID+" INTEGER," +
            " FOREIGN KEY("+KEY_MOTORIST_ID+") REFERENCES "+TABLE_MOTORIST+"("+KEY_ID+")"+
            " );";

    private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_TAG_SERIAL_NUMBER+" TEXT NOT NULL," +
            " "+KEY_PASSENGER_ID+" INTEGER," +
            " FOREIGN KEY("+KEY_PASSENGER_ID+") REFERENCES "+TABLE_PASSENGER+"("+KEY_ID+")"+
            " );";

    private static final String CREATE_TABLE_TRIP = "CREATE TABLE " + TABLE_TRIP +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_TRIP_BEGIN+" TEXT NOT NULL," +
            " "+KEY_TRIP_END+" TEXT," +
            " "+KEY_VEHICLE_ID+" INTEGER NOT NULL," +
            " "+KEY_TRIP_ID+" INTEGER," +
            " FOREIGN KEY("+KEY_VEHICLE_ID+") REFERENCES "+TABLE_VEHICLE+"("+KEY_ID+"),"+
            " FOREIGN KEY("+KEY_TRIP_ID+") REFERENCES "+TABLE_TRIP+"("+KEY_ID+")"+
            " );";

    private static final String CREATE_TABLE_PASSENGER = "CREATE TABLE " + TABLE_PASSENGER +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_PASSENGER_CPF+" TEXT NOT NULL," +
            " "+KEY_PASSENGER_RG+" TEXT NOT NULL," +
            " "+KEY_PASSENGER_NAME+" TEXT NOT NULL," +
            " "+KEY_INSTITUITION_ID+" INTEGER," +
            " "+KEY_TAG_ID+" INTEGER," +
            " FOREIGN KEY("+KEY_INSTITUITION_ID+") REFERENCES "+TABLE_INSTITUITION+"("+KEY_ID+"),"+
            " FOREIGN KEY("+KEY_TAG_ID+") REFERENCES "+TABLE_TAG+"("+KEY_ID+")"+
            " );";

    private static final String CREATE_TABLE_TRIP_PASSENGER = "CREATE TABLE " + TABLE_TRIP_PASSENGER +
            "( "+KEY_ID+" INTEGER PRIMARY KEY," +
            " "+KEY_PASSENGER_ID+" INTEGER," +
            " "+KEY_TRIP_ID+" INTEGER," +
            " FOREIGN KEY("+KEY_PASSENGER_ID+") REFERENCES "+TABLE_PASSENGER+"("+KEY_ID+"),"+
            " FOREIGN KEY("+KEY_TRIP_ID+") REFERENCES "+TABLE_TRIP+"("+KEY_ID+")"+
            " );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase();
        getReadableDatabase();
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

        //carga teste
        db.execSQL("INSERT INTO INSTITUITION (id,name,address) VALUES (1,'FAESA', 'Av. Vitória, 2220 - Monte Belo, Vitória')");
        db.execSQL("INSERT INTO INSTITUITION (id,name,address) VALUES (2,'UVV', 'Av. Comissário José Dantas de Melo, 21 - Boa Vista II, Vila Velha')");
        db.execSQL("INSERT INTO INSTITUITION (id,name,address) VALUES (3,'Multivix - Serra', 'R. Barão do Rio Branco, 120 - Colina de Laranjeiras, Serra')");
        db.execSQL("INSERT INTO INSTITUITION (id,name,address) VALUES (4,'UFES', 'Av. Fernando Ferrari, 514 - Goiabeiras, Vitória')");
        db.execSQL("INSERT INTO INSTITUITION (id,name,address) VALUES (5,'FDV', 'R. Juiz Alexandre Martins de Castro Filho, 215 - Santa Lucia, Vitória')");
        db.execSQL("INSERT INTO INSTITUITION (id,name,address) VALUES (6,'UniSales', 'Av. Vitória, 950 - Forte São João, Vitória')");

        db.execSQL("INSERT INTO MOTORIST (id,cpf,rg,cnh,name) VALUES (1,'628.543.380-14','41.521.025-2','37139627764','Fábio Juan Nunes')");
        db.execSQL("INSERT INTO MOTORIST (id,cpf,rg,cnh,name) VALUES (2,'885.253.665-52','48.932.002-8','58383053820','Erick Sebastião Bryan Sales')");

        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (1,null,'09002E4EA2CB')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (2,null,'03001G2GC5DA')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (3,null,'07003C4CE2TX')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (4,null,'02006H2HJ5NM')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (5,null,'01005Z4ZS2YP')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (6,null,'05007U2UK5MX')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (7,null,'03302E4EA2CB')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (8,null,'02201G2GC5DA')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (9,null,'04403C4CE2TX')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (10,null,'05506H2HJ5NM')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (11,null,'06605Z4ZS2YP')");
        db.execSQL("INSERT INTO TAG (id,passenger_id,serial_number) VALUES (12,null,'07707U2UK5MX')");

        db.execSQL("INSERT INTO VEHICLE (id,motorist_id,plate,model) VALUES (1,1,'NAL-6496','Buggy Plus 1.6 8V')");
        db.execSQL("INSERT INTO VEHICLE (id,motorist_id,plate,model) VALUES (2,2,'FAW-0087','HOVER CUV 2.4 16V 5p Mec')");

        db.execSQL("INSERT INTO TRIP (id,vehicle_id,begin,end,trip_id) VALUES (1,1,'2020-11-02 10:20:05.123', '2020-11-02 18:35:09.563',null)");
        db.execSQL("INSERT INTO TRIP (id,vehicle_id,begin,end,trip_id) VALUES (2,1,'2020-11-03 10:10:00.242', null,1)");

        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (1,1,1,'348.098.242-50','23.795.983-5','Eduardo Kevin da Luz')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (2,1,2,'915.539.636-44','14.397.382-4','Yasmin Elisa Isabela Gonçalves')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (3,2,3,'698.864.417-20','34.105.323-5','Henrique Marcos Vinicius de Paula')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (4,2,4,'937.683.365-14','50.099.269-1','Davi Theo Bryan Barbosa')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (5,3,5,'909.314.686-63','32.996.645-5','Julia Stefany Gomes')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (6,3,6,'024.817.989-61','28.152.288-1','Sara Pietra Vitória Farias')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (7,4,7,'089.602.046-09','16.757.850-9','Sônia Kamilly Heloise Rocha')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (8,4,8,'276.613.412-39','33.635.541-5','Nathan Diego Jorge das Neves')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (9,5,9,'030.252.991-85','34.940.753-8','Murilo Nicolas Victor Fernandes')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (10,5,10,'365.326.384-04','24.786.837-1','Vitor Gabriel Gustavo Cardoso')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (11,6,11,'542.184.424-28','46.850.730-9','Priscila Beatriz Assunção')");
        db.execSQL("INSERT INTO PASSENGER (id,instituition_id,tag_id,cpf,rg,name) VALUES (12,6,12,'440.816.666-95','22.882.862-4','Gustavo Murilo Enzo Nascimento')");

        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (1,1,1)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (2,2,1)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (3,3,1)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (4,4,1)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (5,5,1)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (6,6,1)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (7,7,1)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (8,8,1)");

        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (9,1,2)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (10,2,2)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (15,7,2)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (16,8,2)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (17,9,2)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (18,10,2)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (19,11,2)");
        db.execSQL("INSERT INTO TRIP_PASSENGER(id,passenger_id,trip_id) VALUES (20,12,2)");





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
