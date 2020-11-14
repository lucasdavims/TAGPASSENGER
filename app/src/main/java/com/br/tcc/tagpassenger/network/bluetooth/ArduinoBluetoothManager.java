package com.br.tcc.tagpassenger.network.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Davi on 08/09/2020.
 */

public class ArduinoBluetoothManager {

    private static final UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier

    private static final UUID BT_MODULE_UUID_SERVER = UUID.fromString("00001101-0000-1000-8000-00805F9B34FC");

    //instance of bluettoomanager
    private static ArduinoBluetoothManager sInstance;

    // #defines for identifying shared types between calling functions
    private final static int REQUEST_ENABLE_BT = 1; // used to identify adding bluetooth names
    public final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    private final static int CONNECTING_STATUS = 3; // used in bluetooth handler to identify message status

    private BluetoothAdapter mBTAdapter;
    private Set<BluetoothDevice> mPairedDevices;
    private ArrayAdapter<String> mBTArrayAdapter;

    private Handler mHandler; // Our main handler that will receive callback notifications

    private BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path

    private ConnectionThread connect;

    private Context context;

    public static synchronized ArduinoBluetoothManager getInstance(Context context){

        if (sInstance == null){
            sInstance = new ArduinoBluetoothManager(context.getApplicationContext());
        }

        return sInstance;
    }

    public ArduinoBluetoothManager(Context context) {
        this.context = context;
        this.mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBTArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
    }

    public void bluetoothOn(){
        if (!mBTAdapter.isEnabled()) {

            mBTAdapter.enable();

            Log.e(ArduinoBluetoothManager.class.getName(), "Bluetooth turned on");
            Toast.makeText(getApplicationContext(),"Bluetooth turned on",Toast.LENGTH_SHORT).show();

        }
        else{
            Log.e(ArduinoBluetoothManager.class.getName(), "Bluetooth is already on");
            Toast.makeText(getApplicationContext(),"Bluetooth is already on", Toast.LENGTH_SHORT).show();
        }
    }

    public void bluetoothOff(){
        mBTAdapter.disable(); // turn off
        Toast.makeText(getApplicationContext(),"Bluetooth turned Off", Toast.LENGTH_SHORT).show();
    }


    public void connect() throws IOException {

        connect = new ConnectionThread("78:44:05:92:82:D2");
        connect.start();

        try {
            Thread.sleep(1000);
        } catch (Exception E) {
            E.printStackTrace();
        }


    }

    public BluetoothAdapter getmBTAdapter() {
        return mBTAdapter;
    }

    public void setmBTAdapter(BluetoothAdapter mBTAdapter) {
        this.mBTAdapter = mBTAdapter;
    }

    public Set<BluetoothDevice> getmPairedDevices() {
        return mPairedDevices;
    }

    public void setmPairedDevices(Set<BluetoothDevice> mPairedDevices) {
        this.mPairedDevices = mPairedDevices;
    }

    public ArrayAdapter<String> getmBTArrayAdapter() {
        return mBTArrayAdapter;
    }

    public void setmBTArrayAdapter(ArrayAdapter<String> mBTArrayAdapter) {
        this.mBTArrayAdapter = mBTArrayAdapter;
    }

    public Handler getmHandler() {
        return mHandler;
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public BluetoothSocket getmBTSocket() {
        return mBTSocket;
    }

    public void setmBTSocket(BluetoothSocket mBTSocket) {
        this.mBTSocket = mBTSocket;
    }

    private Context getApplicationContext() {
        return this.context;
    }



}
