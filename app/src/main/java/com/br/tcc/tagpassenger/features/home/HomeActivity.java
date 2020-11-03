package com.br.tcc.tagpassenger.features.home;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.br.tcc.tagpassenger.R;
import com.br.tcc.tagpassenger.features.controltrip.ControlTripActivity;
import com.br.tcc.tagpassenger.features.registerpassenger.RegisterPassengerActivity;
import com.br.tcc.tagpassenger.network.bluetooth.ArduinoBluetoothManager;
import com.br.tcc.tagpassenger.storage.DatabaseHelper;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()){
            case R.id.navigation_home:

                Toast.makeText(this, "Option Home",
                        Toast.LENGTH_LONG).show();
                return false;
            case R.id.navigation_control_trip:

                Intent intent = new Intent(HomeActivity.this,
                        ControlTripActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                Toast.makeText(this, "Option Control of trip",
                        Toast.LENGTH_LONG).show();
                return false;

            case R.id.navigation_register_passenger:

                Intent intent2 = new Intent(HomeActivity.this,
                        RegisterPassengerActivity.class);
                startActivity(intent2);

                Toast.makeText(this, "Option Register passenger",
                        Toast.LENGTH_LONG).show();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseHelper DBHelper = new DatabaseHelper(getApplicationContext());

       /*ArduinoBluetoothManager arduinoBluetoothManager = ArduinoBluetoothManager.getInstance(getApplicationContext());
        arduinoBluetoothManager.bluetoothOn();

        registerReceiver(arduinoBluetoothManager.getBlReceiver(), new IntentFilter(BluetoothDevice.ACTION_FOUND));

        arduinoBluetoothManager.discover();*/




    }

}
