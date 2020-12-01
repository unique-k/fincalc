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

public class lengthActivity extends AppCompatActivity {
    private EditText input;
    private Button calculate,clear;
    private Spinner spin;
    private TextView meter, kilometer, inch, feet, mile, yard,centimeter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
        //========================Drop Down Button===============================================//
        String[] lengthUnits = { "meters", "kilometers", "inches", "feet", "miles", "yards","centimeters"};
        spin = (Spinner) findViewById(R.id.lengthSpinner);
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lengthUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        //==========================findViewById==================================================//
        input=findViewById(R.id.editTextLength);
        calculate=findViewById(R.id.calculateButtonLength);
        meter=findViewById(R.id.meterAnswer);
        kilometer=findViewById(R.id.kmAnswer);
        inch=findViewById(R.id.inchAnswer);
        feet=findViewById(R.id.feetAnswer);
        mile=findViewById(R.id.mileAnswer);
        yard=findViewById(R.id.yardAnswer);
        centimeter=findViewById(R.id.cmAnswer);
        clear=findViewById(R.id.clearButtonLength);

        //===========================On clear button========================================//
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                meter.setText("");
                kilometer.setText("");
                inch.setText("");
                feet.setText("");
                mile.setText("");
                yard.setText("");
                centimeter.setText("");
                clear.setText("");
            }
        });


        //=================After pressing calculate button========================================//
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(calculate.getWindowToken(), 0);
                //==========Output 0 if pressed calculate without any input=======================//
                if (input.getText().toString().equals("")) {
                    input.setError("Enter value");
                }
                else
                {
                    float inputNum = Float.parseFloat(input.getText().toString()); // converting input into a float
                    float meters = 0, kilometers = 0, inches = 0, feets = 0, miles = 0, yards = 0, centimeters = 0;


                    //=================Converting any given unit of time in meters===================//
                    if (spin.getSelectedItem().toString() == "meters")
                        meters = inputNum;
                    else if (spin.getSelectedItem().toString() == "kilometers")
                        meters = inputNum * 1000;
                    else if (spin.getSelectedItem().toString() == "inches")
                        meters = (float) (inputNum * 0.0254);
                    else if (spin.getSelectedItem().toString() == "feet")
                        meters = (float) (inputNum * 0.3048);
                    else if (spin.getSelectedItem().toString() == "miles")
                        meters = (float) (inputNum * 1609.344);
                    else if (spin.getSelectedItem().toString() == "yards")
                        meters = (float) (inputNum * 0.9144);
                    else if (spin.getSelectedItem().toString() == "centimeters")
                        meters = (float) (inputNum * 0.01);
                    //============Converting meters to other units===================================//
                    kilometers = meters / 1000;
                    inches = (float) (meters / 0.0254);
                    feets = (float) (meters / 0.3048);
                    miles = (float) (meters / 1609.344);
                    yards = (float) (meters / 0.9144);
                    centimeters = (float) (meters * 100);
                    //=================Output the results=============================================//
                    meter.setText(String.valueOf(meters));
                    kilometer.setText(String.valueOf(kilometers));
                    inch.setText(String.valueOf(inches));
                    feet.setText(String.valueOf(feets));
                    mile.setText(String.valueOf(miles));
                    yard.setText(String.valueOf(yards));
                    centimeter.setText(String.valueOf(centimeters));
                    //===============================END==============================================//

                    //yard.setText("a");

                }
            }
        });
    }
}