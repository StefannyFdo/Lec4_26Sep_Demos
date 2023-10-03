package com.example.lec3demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    final String TAG = "Concert Demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextNumTix = findViewById(R.id.editTextNumTix);
        Button btnBookConcert = findViewById(R.id.btnBookConcert);
        Spinner spinnerConcertType = findViewById(R.id.spinnerConcertType);

        spinnerConcertType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0){
                    Toast.makeText(MainActivity.this,
                            "Selected Rock Band", Toast.LENGTH_SHORT).show();
                } else if (i == 1){
                    Toast.makeText(MainActivity.this,
                            "Selected Jazz Band", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Selected Blues Band", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Practice Exercise: Create an empty views activity
        //ResultsActivity - in that activity, put a Textview (txtViewResults)
        //Set constraints on all four sides to parent
        //set layout height, width to 0dp (match constraint)

        btnBookConcert.setOnClickListener((View view) -> {
               if (editTextNumTix.getText().toString().isEmpty()){
                   Toast.makeText(this, "Number of tickets must be entered", Toast.LENGTH_SHORT).show();
               } else {
                   try {
                       int numTix = Integer.parseInt(
                               editTextNumTix.getText().toString());
                       int index = spinnerConcertType.getSelectedItemPosition();
                       double cost = 0;
                       switch(index){
                           case 0:
                               cost = numTix*79.99;
                               break;
                           case 1:
                               cost = numTix*69.99;
                               break;
                           case 2:
                               cost = numTix*59.99;
                               break;
                       }
                       DecimalFormat df = new DecimalFormat("$#.##");
                       String outputCostTxt = df.format(cost);

                       Toast.makeText(this, outputCostTxt, Toast.LENGTH_SHORT).show();

                       //Create a bundle of data
                       //Create intent and Put it in the intent object
                       //Use the intent to start next activity

                       Bundle bundle = new Bundle();
                       bundle.putInt("NUMTIX",numTix);
                       bundle.putString("TYPE",
                               spinnerConcertType.getSelectedItem().toString());
                       bundle.putDouble("COST",cost);

                       Intent myResultsIntent = new Intent(MainActivity.this,
                                                ResultsActivity.class);
                       myResultsIntent.putExtras(bundle);
                       startActivity(myResultsIntent);
                   } catch (Exception ex){
                       ex.printStackTrace();
                       Log.d(TAG, "Error in parse/number of tickets");
                       Toast.makeText(this, "Number of tickets must be whole number > 0", Toast.LENGTH_SHORT).show();
                   }
               }

        });

    }
}