package com.example.lec3demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultsActivity extends AppCompatActivity {
    final String TAG = "Concert Demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        try {
            //Parcelable - way to pass object from one activity to another

            int numTix = 0;
            Bundle bundle = getIntent().getExtras();
            double costR = bundle.getDouble("COST", 0);
            numTix = getIntent().getExtras().getInt("NUMTIX", 0);
            String concertType = bundle.getString("TYPE","NOTHING");
            DecimalFormat df = new DecimalFormat("$#.##");
            String outputStr = "Concert Type: " + concertType + "\n"
                                + "Number of Tickets: " + numTix + "\n"
                                + "Total Cost: " + df.format(costR);

            TextView txtViewResults = findViewById(R.id.txtViewResults);
            txtViewResults.setText(outputStr);
            txtViewResults.setGravity(Gravity.CENTER);
            txtViewResults.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);

        } catch (Exception ex){
            ex.printStackTrace();
            Log.d(TAG,ex.getMessage());
        }
    }
}