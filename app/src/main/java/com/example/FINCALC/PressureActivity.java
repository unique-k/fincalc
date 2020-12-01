package com.example.FINCALC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PressureActivity extends AppCompatActivity {
    private Spinner spin;
    private Button calculate, clear;
    private TextView input, atmVar,barVar,ftVar,hgVar,PascalVar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);

        //========================Drop Down Button===============================================//
        String[] pressUnits = { "atm", "bar", "ft. H20", "mm Hg","Pascal"};
        spin = (Spinner) findViewById(R.id.pressureSpinner);
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pressUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        //==========================findViewById==================================================//
        input=findViewById(R.id.pressureInput);
        atmVar=findViewById(R.id.atmAnswer);
        barVar=findViewById(R.id.barAnswer);
        ftVar=findViewById(R.id.ftAnswer);
        hgVar=findViewById(R.id.mmHgAnswer);
        PascalVar=findViewById(R.id.PascalAnswer);
        clear=findViewById(R.id.clearPressure);
        calculate=findViewById(R.id.calculatePressure);

        //===========================On clear button========================================//
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                atmVar.setText("");
                barVar.setText("");
                ftVar.setText("");
                hgVar.setText("");
                PascalVar.setText("");
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
                else
                {
                    float inputValue=Float.parseFloat(input.getText().toString());
                    float atm=0,bar=0,ft=0,hg=0,Pascal=0;
                    //=================Converting any given unit of pressure into atm===================//
                    if (spin.getSelectedItem().toString() == "atm")
                        atm=inputValue;
                    else if(spin.getSelectedItem().toString()=="bar")
                        atm= (float) (inputValue/1.013);
                    else if(spin.getSelectedItem().toString()=="ft. H20")
                        atm= (float) ((float) inputValue*0.029499793782403822);
                    else if(spin.getSelectedItem().toString()=="mm Hg")
                        atm=inputValue/760;
                    else if(spin.getSelectedItem().toString()=="Pascal")
                        atm=inputValue/101325;

                    //============Converting atm to other units===================================//
                    bar= (float) (atm*1.013);
                    ft= (float) (atm*33.899524252);
                    hg= (float) (atm*760);
                    Pascal= (float)(atm*101325);

                    //=================Output the results=============================================//
                    atmVar.setText(String.format("%.2f",atm));
                    barVar.setText(String.format("%.2f",bar));
                    ftVar.setText(String.format("%.2f",ft));
                    PascalVar.setText(String.format("%.2f",Pascal));
                    hgVar.setText(String.format("%.2f",hg));
                    //===============================END==============================================//
                }
            }
        });
    }
}