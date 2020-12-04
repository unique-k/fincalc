package com.axt.FINCALC;

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

public class ForceActivity extends AppCompatActivity {
    private Button calculate, clear;
    private TextView input, NewtonsVar,poundVar, dyneVar,kipVar;
    private Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force);

        //========================Drop Down Button===============================================//
        String[] forceUnits = { "Newtons", "pounds-force", "dyne", "kip"};
        spin = (Spinner) findViewById(R.id.forceSpinner);
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, forceUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        //==========================findViewById==================================================//
        input=findViewById(R.id.forceInput);
        NewtonsVar=findViewById(R.id.NewtonAnswer);
        poundVar=findViewById(R.id.poundAnswer);
        dyneVar=findViewById(R.id.dyneAnswer);
        kipVar=findViewById(R.id.kipAnswer);

        clear=findViewById(R.id.clearForce);
        calculate=findViewById(R.id.calculateForce);

        //===========================On clear button========================================//
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                NewtonsVar.setText("");
                poundVar.setText("");
                dyneVar.setText("");
                kipVar.setText("");
            }
        });

        //=================After pressing calculate button========================================//
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(calculate.getWindowToken(), 0);

                if (input.getText().toString().equals(""))
                    input.setError("Enter value");
                else
                {
                    float inputValue=Float.parseFloat(input.getText().toString());
                    float Newtons=0,dyne=0,kip=0,poundForce=0;
                    //=================Converting any given unit of force into Newtons===================//
                    if (spin.getSelectedItem().toString() == "Newtons")
                        Newtons=inputValue;
                    else if(spin.getSelectedItem().toString()=="dyne")
                        Newtons= (float) (inputValue/100000);
                    else if(spin.getSelectedItem().toString()=="pounds-force")
                        Newtons= (float) (inputValue*4.448);
                    else if(spin.getSelectedItem().toString()=="kip")
                        Newtons= (float) (inputValue*4448.22);

                    //============Converting atm to other units===================================//
                    kip= (float) (Newtons/4448.22);
                    dyne= (float) (Newtons*100000);
                    poundForce= (float) (Newtons/4.448);

                    //=================Output the results=============================================//
                    kipVar.setText(String.valueOf(kip));
                    NewtonsVar.setText(String.valueOf(Newtons));
                    poundVar.setText(String.valueOf(poundForce));
                    dyneVar.setText(String.valueOf(dyne));
                    //===============================END==============================================//
                }
            }
        });
    }
}