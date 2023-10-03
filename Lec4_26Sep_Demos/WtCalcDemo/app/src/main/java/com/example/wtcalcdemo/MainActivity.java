package com.example.wtcalcdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtViewResults;
    EditText editTextInputWt;
    Button btnConvertWt;
    RadioGroup radGroupConv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher_wt_round);
        actionBar.setTitle(R.string.txtTitle);

        txtViewResults = findViewById(R.id.txtViewResults);
        editTextInputWt = findViewById(R.id.editTextInputWt);
        radGroupConv = findViewById(R.id.radGroupConv);
        btnConvertWt = findViewById(R.id.btnConvertWt);

        btnConvertWt.setOnClickListener((View view) -> {
            if (radGroupConv.getCheckedRadioButtonId() == -1){
                Toast.makeText(this, "Please check conversion type",
                        Toast.LENGTH_SHORT).show();
            } else {
                double inputWt = 0, outputWt = 0;
                try {
                    inputWt =
                            Double.parseDouble(editTextInputWt
                                        .getText().toString());
                    if (inputWt <= 0) {
                        Toast.makeText(this, "Input Weight must be > 0",
                                Toast.LENGTH_SHORT).show();
                    } else if (radGroupConv.getCheckedRadioButtonId()
                                        == R.id.radBtnKgsToLbs){
                        if (inputWt > 500){
                            Toast.makeText(this, "Baggage limit exceeded",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //I forgot to add units at the end in class, so have added
                            //it here
                            outputWt = inputWt*2.2;
                            txtViewResults
                                    .setText(String.format("Converted Wt: %.2f Lbs", outputWt));
                        }
                    } else if (radGroupConv.getCheckedRadioButtonId() == R.id.radBtnLbsToKgs) {
                        if (inputWt > 1000){
                            Toast.makeText(this, "Baggage limit exceeded",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //I forgot to add the unit in class
                            outputWt = inputWt / 2.2;
                            txtViewResults
                                    .setText(String.format("Converted Wt: %.2f Kgs", outputWt));
                        }
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                    Log.d("WT DEMO",ex.getMessage());
                }
            }
        });
    }
}