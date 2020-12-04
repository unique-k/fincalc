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

public class InterestActivity extends AppCompatActivity {
    private Spinner spin;
    private Button clear, calculate;
    private TextView principleVar,timeVar,rateVar,totalAmountOut,totalInterestOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        //========================Drop Down Button===============================================//
        String[] interestType = { "simple interest","compounded semi-annually", "compounded annually"};
        spin = (Spinner) findViewById(R.id.dropdownInterest);
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, interestType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        //==========================findViewById==================================================//
        clear=findViewById(R.id.clear_buttonIC);
        calculate=findViewById(R.id.calculateButtonIC);
        principleVar=findViewById(R.id.principleInput);
        timeVar=findViewById(R.id.timeInput);
        rateVar=findViewById(R.id.rateInput);
        totalAmountOut=findViewById(R.id.totalAmountOutput);
        totalInterestOut=findViewById(R.id.totalInterestOutput);

        //===========================On clear button========================================//
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principleVar.setText("");
                timeVar.setText("");
                rateVar.setText("");
                totalInterestOut.setText("");
                totalAmountOut.setText("");
            }
        });
        //==========================On calculate button========================================//
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(calculate.getWindowToken(), 0);
                double principle,time,rate,totalAmount = 0,totalInterest=0;

                if(principleVar.getText().toString().equals(""))
                    principleVar.setError("Enter Value");
                else if(timeVar.getText().toString().equals(""))
                    timeVar.setError("Enter Value");
                else if(rateVar.getText().toString().equals(""))
                    rateVar.setError("Enter Value");
                else
                {
                    if(Double.parseDouble(timeVar.getText().toString())>100)
                        timeVar.setError("time can't be more than 100 years");
                    else
                    {
                        //=========================Converting user input to float==============================//
                        principle=Float.parseFloat(principleVar.getText().toString());
                        time=Float.parseFloat(timeVar.getText().toString());
                        rate=Float.parseFloat(rateVar.getText().toString());

                        if(spin.getSelectedItem().toString()=="simple interest")
                        {
                            totalInterest=(principle*time*rate)/100;
                            totalAmount=totalInterest+principle;
                        }
                        else if(spin.getSelectedItem().toString()=="compounded semi-annually")
                        {
                            totalAmount= (double) (principle*(Math.pow((1+(rate/200)),(2*time))));
                            totalInterest=totalAmount-principle;
                        }
                        else if(spin.getSelectedItem().toString()=="compounded annually")
                        {
                            totalAmount= (double) (principle*(Math.pow((1+(rate/100)),time)));
                            totalInterest=totalAmount-principle;
                        }
                       //=========================Displaying Results==============================//
                        totalAmountOut.setText(String.valueOf(totalAmount));
                        totalInterestOut.setText(String.valueOf(totalInterest));
                    }
                }
            }
        });
    }
}