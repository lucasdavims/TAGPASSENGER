package com.br.tcc.tagpassenger.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.br.tcc.tagpassenger.R;
import com.br.tcc.tagpassenger.features.controlpassenger.ControlPassengerActivity;

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
            case R.id.navigation_control_passenger:

                Intent intent = new Intent(HomeActivity.this,
                        ControlPassengerActivity.class);
                startActivity(intent);

                Toast.makeText(this, "Option Control of passenger",
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
    }

}
