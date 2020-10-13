package com.br.tcc.tagpassenger.features.registerpassenger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.tcc.tagpassenger.R;
import com.br.tcc.tagpassenger.domain.instituition.Instituition;
import com.br.tcc.tagpassenger.domain.passenger.Passenger;
import com.br.tcc.tagpassenger.domain.tag.Tag;
import com.br.tcc.tagpassenger.storage.passenger.PassengerRepositorySQLite;

import java.util.ArrayList;

public class RegisterPassengerActivity extends AppCompatActivity {

    EditText edtName, edtCpf, edtRg, edtTag;
    Spinner spinInstituition;
    Button btnSave, buttonFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_passenger);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        registerComponents();
        initSpinner();

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                PassengerRepositorySQLite passengerRepository = new
                        PassengerRepositorySQLite(getApplicationContext());

                passengerRepository.persist(getPassenger());

                finish();

                Toast.makeText(RegisterPassengerActivity.this, "Your new passenger has been registered!!!", Toast.LENGTH_LONG).show();
            }
        });

        buttonFind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setSerialTag("TESTESERIALNUMBER");
                Toast.makeText(RegisterPassengerActivity.this, "You got the serial number from tag! (Mock)", Toast.LENGTH_LONG).show();
            }
        });
    }

    public Passenger getPassenger(){
        Passenger passenger = new Passenger();

        passenger.setName(getName());
        passenger.setCpf(getCpf());
        passenger.setRg(getRg());
        passenger.setTag(getTag());
        passenger.setInstituition(getInstituition());

        return passenger;
    }

    private void registerComponents() {

        edtName = (EditText) findViewById(R.id.edtName);
        edtCpf = (EditText) findViewById(R.id.edtCpf);
        edtRg = (EditText) findViewById(R.id.edtRg);
        edtTag = (EditText) findViewById(R.id.edtTag);
        spinInstituition = (Spinner) findViewById(R.id.spinInstituition);
        btnSave = (Button) findViewById(R.id.btnSave);
        buttonFind = (Button) findViewById(R.id.buttonFind);

    }

    public void initSpinner(){

        //passengers instituition
        ArrayList<String> arrayList1 = new ArrayList<String>();

        arrayList1.add("Faesa");
        arrayList1.add("Uvv");
        arrayList1.add("Multivix");

        ArrayAdapter<String> adp = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_dropdown_item,arrayList1);

        spinInstituition.setAdapter(adp);
    };

    public String getName(){
        return edtName.getEditableText().toString();
    };

    public String getCpf(){
        return edtCpf.getEditableText().toString();
    };

    public String getRg(){
        return edtRg.getEditableText().toString();
    };

    public Instituition getInstituition(){

        String selectedText = spinInstituition.getSelectedItem().toString();

        return null;
    }

    public Tag getTag(){
        String serialTag = edtTag.getEditableText().toString();

        return null;
    };

    public void setSerialTag(String serialTag){
        edtTag.setText(serialTag);
    }

}
