package com.example.FINCALC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class timeActivity extends AppCompatActivity {
    private EditText input;
    private Button calculate,clear;
    private Spinner spin;
    private TextView second,minute,hour,day,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        //========================Drop Down Button===============================================//
        String[] timeUnits = { "seconds", "minutes", "hours", "days", "year" };
        spin = (Spinner) findViewById(R.id.timeSpinner);
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        //==========================findViewById==================================================//
        input=findViewById(R.id.editTextNumberDecimal);
        calculate=findViewById(R.id.calculateButton);
        minute=findViewById(R.id.minutesAnswer);
        second=findViewById(R.id.secondAnswer);
        hour=findViewById(R.id.hoursAnswers);
        day=findViewById(R.id.daysAnswer);
        year=findViewById(R.id.yearAnswer);
        clear=findViewById(R.id.clearButtonTime);

        //===========================On clear button========================================//
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                minute.setText("");
                second.setText("");
                hour.setText("");
                day.setText("");
                year.setText("");
            }
        });

        //=================After pressing calculate button========================================//
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(calculate.getWindowToken(), 0);

                if (input.getText().toString().equals("")) {
                    input.setError("Enter value");
                }
                else {

                    float inputNum = Float.parseFloat(input.getText().toString()); // converting input into a float
                    float minutes = 0, hours = 0, days = 0, years = 0, seconds = 0; // initializing output values to be 0
                    //=================Converting any given unit of time in seconds===================//
                    if (spin.getSelectedItem().toString() == "seconds")
                        seconds = inputNum;
                    else if (spin.getSelectedItem().toString() == "minutes")
                        seconds = inputNum * 60;
                    else if (spin.getSelectedItem().toString() == "hours")
                        seconds = inputNum * 3600;
                    else if (spin.getSelectedItem().toString() == "days")
                        seconds = inputNum * 24 * 3600;
                    else if (spin.getSelectedItem().toString() == "year")
                        seconds = inputNum * 24 * 3600 * 365;
                    //============Converting seconds to other units===================================//
                    hours = seconds / 3600;
                    days = hours / 24;
                    years = days / 365;
                    minutes = seconds / 60;
                    //=================Output the results=============================================//
                    minute.setText(String.valueOf(minutes));
                    second.setText(String.valueOf(seconds));
                    hour.setText(String.valueOf(hours));
                    day.setText(String.valueOf(days));
                    year.setText(String.valueOf(years));
                    //===============================END==============================================//
                }

            }
        });
    }
}