package com.example.FINCALC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
public class APRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_p_r);


        String [] loanType={"year","month"};
        Spinner loanSpinner= findViewById(R.id.termSpinner);
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, loanType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loanSpinner.setAdapter(adapter);
    }
}