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

public class MassActivity extends AppCompatActivity {
    public Button calculate, clear;
    public Spinner spin;
    public TextView input,kgVar, gramsVar,slugVar,poundsVar,tonsVar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mass);

        //========================Drop Down Button===============================================//
        String[] massUnits = { "kilograms", "grams", "pounds", "slugs"," US tons"};
        spin = (Spinner) findViewById(R.id.massSpinner);
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, massUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        //==========================findViewById==================================================//
        input=findViewById(R.id.massInput);
        kgVar=findViewById(R.id.kgAnswer);
        gramsVar=findViewById(R.id.gramsAnswer);
        slugVar=findViewById(R.id.slugAnswer);
        poundsVar=findViewById(R.id.poundsAnswer);
        tonsVar=findViewById(R.id.tonAnswer);
        clear=findViewById(R.id.clearMass);
        calculate=findViewById(R.id.calculateMass);

        //===========================On clear button========================================//
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kgVar.setText("");
                gramsVar.setText("");
                slugVar.setText("");
                poundsVar.setText("");
                tonsVar.setText("");
                input.setText("");
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
                    float kg=0,grams=0,slugs=0,pounds=0,tons=0;
                    //=================Converting any given unit of mass into kilograms===================//
                    if (spin.getSelectedItem().toString() == "kilograms")
                        kg=inputValue;
                    else if(spin.getSelectedItem().toString()=="grams")
                        kg= (float) (inputValue/1000);
                    else if(spin.getSelectedItem().toString()=="slugs")
                        kg= (float) ((float) inputValue*14.594);
                    else if(spin.getSelectedItem().toString()=="pounds")
                        kg= (float) (inputValue/2.205);
                    else if(spin.getSelectedItem().toString()=="US tons")
                        kg=inputValue*907;

                    //============Converting atm to other units===================================//
                    slugs= (float) (kg/14.594);
                    tons= (float) (kg/907);
                    pounds= (float) (kg*2.205);
                    grams= (float)(kg*1000);

                    //=================Output the results=============================================//
                    kgVar.setText(String.valueOf(kg));
                    gramsVar.setText(String.valueOf(grams));
                    tonsVar.setText(String.valueOf(tons));
                    poundsVar.setText(String.valueOf(pounds));
                    slugVar.setText(String.valueOf(slugs));
                    //===============================END==============================================//
                }
            }
        });
    }
}