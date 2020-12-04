package com.axt.FINCALC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class temperatureActivity extends AppCompatActivity {
    private Spinner spin;
    private TextView input,celsiusOut,kelvinOut,fahrenheitOut,rankineOut;
    private Button calculate,clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        //========================Drop Down Button===============================================//
        String[] tempUnits = { "celsius", "kelvin", "fahrenheit", "rankine"};
        spin = (Spinner) findViewById(R.id.tempSpinner);
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tempUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        //==========================findViewById==================================================//
        input=findViewById(R.id.tempInput);
        celsiusOut=findViewById(R.id.celciusAnswer);
        fahrenheitOut=findViewById(R.id.farenheitAnswer);
        kelvinOut=findViewById(R.id.kelvinAnswer);
        rankineOut=findViewById(R.id.rankineAnswer);
        calculate=findViewById(R.id.calculateTemp);
        clear=findViewById(R.id.clearTemp);

        //===========================On clear button========================================//
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                celsiusOut.setText("");
                fahrenheitOut.setText("");
                kelvinOut.setText("");
                rankineOut.setText("");
            }
        });

        //=================After pressing calculate button========================================//
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(calculate.getWindowToken(), 0);
                float a= (float) (9.0/5.0);
                float b= (float) (5.0/9.0);

                if (input.getText().toString().equals("")) {
                    input.setError("Enter value");
                }
                else
                {
                    float inputValue=Float.parseFloat(input.getText().toString());
                    float celsius=0,fahrenheit=0,kelvin=0,rankine=0;
                    //=================Converting any given unit of tempr in kelvin===================//
                    if (spin.getSelectedItem().toString() == "celsius"){
                        kelvin= (float) (inputValue+273.15);
                    }
                    else if(spin.getSelectedItem().toString()=="kelvin") {
                        kelvin=inputValue;
                    }
                    else if(spin.getSelectedItem().toString()=="fahrenheit"){
                        kelvin= (float) ((inputValue+459.67)*(b));
                    }
                    else if(spin.getSelectedItem().toString()=="rankine"){
                        kelvin=inputValue*(b);
                    }
                    //============Converting seconds to other units===================================//
                    fahrenheit= (float) ((kelvin*(a))-459.67);
                    rankine=kelvin*(a);
                    celsius= (float) (kelvin-273.15);

                    //=================Output the results=============================================//
                    celsiusOut.setText(String.format("%.2f",celsius));
                    kelvinOut.setText(String.format("%.2f",kelvin));
                    fahrenheitOut.setText(String.format("%.2f",fahrenheit));
                    rankineOut.setText(String.format("%.2f",rankine));;
                    //===============================END==============================================//
                }
            }
        });
    }
}