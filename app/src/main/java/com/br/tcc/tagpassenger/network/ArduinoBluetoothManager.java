package com.br.tcc.tagpassenger.network;

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

import com.br.tcc.tagpassenger.features.home.HomeActivity;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Davi on 08/09/2020.
 */

public class ArduinoBluetoothManager {

    private static final UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier

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
    private ConnectedThread mConnectedThread; // bluetooth background worker thread to send and receive data
    private BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path

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

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        try {
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            return (BluetoothSocket) m.invoke(device, BT_MODULE_UUID);
        } catch (Exception e) {
            Log.e(ArduinoBluetoothManager.class.getName(), "Could not create Insecure RFComm Connection",e);
        }
        return  device.createRfcommSocketToServiceRecord(BT_MODULE_UUID);
    }

    public void bluetoothOn(){
        if (!mBTAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

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

    public void discover(){
        // Check if the device is already discovering
        if(mBTAdapter.isDiscovering()){
            mBTAdapter.cancelDiscovery();
            Toast.makeText(getApplicationContext(),"Discovery stopped",Toast.LENGTH_SHORT).show();
        }
        else{
            if(mBTAdapter.isEnabled()) {
                mBTArrayAdapter.clear(); // clear items
                mBTAdapter.startDiscovery();
                Toast.makeText(getApplicationContext(), "Discovery started", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
            }
        }
    }

    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name to the list
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                mBTArrayAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), device.getName() + " - " + device.getAddress(), Toast.LENGTH_SHORT).show();
                Log.e(ArduinoBluetoothManager.class.getName(),device.getName() + " - " + device.getAddress());
            }
        }
    };

    private void listPairedDevices(){
        mBTArrayAdapter.clear();
        mPairedDevices = mBTAdapter.getBondedDevices();
        if(mBTAdapter.isEnabled()) {
            // put it's one to the adapter
            for (BluetoothDevice device : mPairedDevices)
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

            Toast.makeText(getApplicationContext(), "Show Paired Devices", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
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

    public ConnectedThread getmConnectedThread() {
        return mConnectedThread;
    }

    public void setmConnectedThread(ConnectedThread mConnectedThread) {
        this.mConnectedThread = mConnectedThread;
    }

    public BluetoothSocket getmBTSocket() {
        return mBTSocket;
    }

    public void setmBTSocket(BluetoothSocket mBTSocket) {
        this.mBTSocket = mBTSocket;
    }

    public BroadcastReceiver getBlReceiver() {
        return blReceiver;
    }

    private Context getApplicationContext() {
        return this.context;
    }



}
