package com.br.tcc.tagpassenger.features.controltrip;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.br.tcc.tagpassenger.R;
import com.br.tcc.tagpassenger.domain.passenger.Passenger;
import com.br.tcc.tagpassenger.domain.trip.Trip;
import com.br.tcc.tagpassenger.features.home.HomeActivity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

public class ControlTripActivity extends AppCompatActivity {

    private TableLayout mTableLayout;
    private ProgressDialog mProgressBar;
    private Dialog mDialog;
    private GetCurrentTripUseCase getCurrentTripUseCase;
    private StartNewTripUseCase startNewTripUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_trip);

        getCurrentTripUseCase = new GetCurrentTripUseCase(getApplicationContext());
        startNewTripUseCase = new StartNewTripUseCase(getApplicationContext());

        mDialog = new Dialog(this);
        mProgressBar = new ProgressDialog(this);
        mTableLayout = (TableLayout) findViewById(R.id.tableTrips);
        mTableLayout.setStretchAllColumns(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bem vindo ao Controle de viagens!").
                setMessage("O que deseja fazer em relação a viagem?").
                setNeutralButton("Continuar atual", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startLoadData();
                    }
                }).
                setPositiveButton("Nova viagem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Start New Trip Use Case
                        try {
                            startNewTripUseCase.execute();
                            startLoadData();
                        } catch (Exception e) {
                            mDialog.dismiss();

                            Toast.makeText(getApplicationContext(), "Error to start a new trip: "+e.getMessage(),
                                    Toast.LENGTH_LONG).show();

                            mDialog.show();

                        }

                    }
                }).setNegativeButton("Nada", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ControlTripActivity.this,
                                HomeActivity.class);
                        startActivity(intent);
                    }
                });
        mDialog = builder.create();

        mDialog.show();


    }

    public void startLoadData() {
        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Fetching current trip..");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new LoadDataTask().execute(0);
    }

    public void loadData() {
        //grid cofigs
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 0, smallTextSize =0, mediumTextSize = 0;
        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int)
                getResources().getDimension(R.dimen.font_size_medium);
        ;
        TextView textSpacer = null;
        mTableLayout.removeAllViews();

        CurrentTripDto currentTrip = getCurrentTripUseCase.execute();

        List<Passenger> data = currentTrip.getPassengers();

        int rows = currentTrip.getPassengers().size();

        //set number of passengers
        getSupportActionBar().setTitle("Passageiros (" + rows + ")");

        //Formarter configs
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        // -1 means heading row
        for(int i = -1; i < rows; i ++) {
            Passenger row = null;
            if (i > -1)
                row = data.get(i);
            else {
                textSpacer = new TextView(this);
                textSpacer.setText("");
            }
            // data columns 1
            final TextView tv = new TextView(this);
            tv.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.LEFT);
            tv.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv.setText("Nome");
                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv.setText(String.valueOf(row.getName()));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }



            // data columns 3
            final LinearLayout layInstituition = new LinearLayout(this);
            layInstituition.setOrientation(LinearLayout.VERTICAL);
            //layInstituition.setPadding(0, 10, 0, 10);
            layInstituition.setBackgroundColor(Color.parseColor("#f8f8f8"));

            final TextView tv3 = new TextView(this);
            if (i == -1) {
                tv3.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 15, 0, 15);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                tv3.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 0, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
            tv3.setGravity(Gravity.TOP);
            if (i == -1) {
                tv3.setText("Instituição");
                tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
            }
            else {
                tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                tv3.setText(row.getInstituition().getName());
            }
            layInstituition.addView(tv3);
            if (i > -1) {
                final TextView tv3b = new TextView(this);
                tv3b.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv3b.setGravity(Gravity.RIGHT);
                tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv3b.setPadding(5, 1, 0, 5);
                tv3b.setTextColor(Color.parseColor("#aaaaaa"));
                tv3b.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv3b.setText(row.getInstituition().getAddress());
                layInstituition.addView(tv3b);
            }

            // data columns 4
            final TextView tv2 = new TextView(this);
            if (i == -1) {
                tv2.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                tv2.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
            tv2.setGravity(Gravity.LEFT);
            tv2.setPadding(0, 15, 0, 15);
            if (i == -1) {
                tv2.setText("Presente Ida?");
                tv2.setBackgroundColor(Color.parseColor("#f0f0f0"));
            }
            else {
                tv2.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                if (row.isPresentGoing()){
                    tv2.setBackgroundColor(Color.parseColor("#daf7da"));
                    tv2.setTextColor(Color.parseColor("#000000"));
                    tv2.setText("Sim");

                }else{
                    tv2.setBackgroundColor(Color.parseColor("#f7d5d5"));
                    tv2.setTextColor(Color.parseColor("#000000"));
                    tv2.setText("Não");
                }

            }

            // data columns 2
            final TextView tv4 = new TextView(this);
            if (i == -1) {
                tv4.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                tv4.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
            tv4.setGravity(Gravity.LEFT);
            tv4.setPadding(0, 15, 0, 15);
            if (i == -1) {
                tv4.setText("Presente Volta?");
                tv4.setBackgroundColor(Color.parseColor("#f0f0f0"));
            }
            else {
                tv4.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                if(row.isPresentBack()){
                    tv4.setBackgroundColor(Color.parseColor("#daf7da"));
                    tv4.setTextColor(Color.parseColor("#000000"));
                    tv4.setText("Sim");
                }else{
                    tv4.setBackgroundColor(Color.parseColor("#f7d5d5"));
                    tv4.setTextColor(Color.parseColor("#000000"));
                    tv4.setText("Não");
                }

            }

            // data columns 2
            final TextView tv5 = new TextView(this);
            if (i == -1) {
                tv5.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv5.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                tv5.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv5.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
            tv5.setGravity(Gravity.LEFT);
            tv5.setPadding(0, 15, 0, 15);
            if (i == -1) {
                tv5.setText("Desembarque");
                tv5.setBackgroundColor(Color.parseColor("#f0f0f0"));
            }
            else {
                tv5.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                tv5.setId(row.getId().intValue());
                tv5.setClickable(true);
                tv5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DisembarkPassengerUseCase disembarkPassengerUC = new DisembarkPassengerUseCase(getApplicationContext());
                        disembarkPassengerUC.setPassengerId(v.getId());

                        try {
                            disembarkPassengerUC.execute();

                            startLoadData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                if(row.isLanding()){
                    tv5.setBackgroundColor(Color.parseColor("#daf7da"));
                    tv5.setTextColor(Color.parseColor("#000000"));
                    tv5.setText("Sim");
                }else{
                    tv5.setBackgroundColor(Color.parseColor("#f7d5d5"));
                    tv5.setTextColor(Color.parseColor("#000000"));
                    tv5.setText("Não");
                }

            }

            // add table row
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new
                    TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin,
                    bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);

            //setGridsRows
            tr.addView(tv);
            tr.addView(layInstituition);
            //tr.addView(layAmounts);
            tr.addView(tv2);
            tr.addView(tv4);
            tr.addView(tv5);

            if (i > -1) {
                tr.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TableRow tr = (TableRow) v;
                    }
                });
            }
            mTableLayout.addView(tr, trParams);
            if (i > -1) {
                // add separator row
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new
                        TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin,
                        rightRowMargin, bottomRowMargin);
                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 4;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(1);
                trSep.addView(tvSep);
                mTableLayout.addView(trSep, trParamsSep);
            }
        }
    }
    class LoadDataTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            mProgressBar.hide();
            loadData();
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
        }
    }

}
