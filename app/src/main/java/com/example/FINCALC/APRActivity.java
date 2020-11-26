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

public class APRActivity extends AppCompatActivity {
    private Button clear, calculate;
    private TextView loanVar,termVar,rateVar,chargesVar,aprOut,monthlyPaymentOut,totalPaymentOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_p_r);

        //==========================findViewById==================================================//
        clear=findViewById(R.id.clearApr);
        calculate=findViewById(R.id.calculateAPR);
        loanVar=findViewById(R.id.loanInput);
        termVar=findViewById(R.id.termInput);
        rateVar=findViewById(R.id.rateaprInput);
        chargesVar=findViewById(R.id.chargesInput);
        aprOut=findViewById(R.id.aprOutput);
        monthlyPaymentOut=findViewById(R.id.monthlyOutput);
        totalPaymentOut=findViewById(R.id.totalOutput);

        //===========================On clear button========================================//
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loanVar.setText("");
                termVar.setText("");
                rateVar.setText("");
                chargesVar.setText("");
                aprOut.setText("");
                monthlyPaymentOut.setText("");
                totalPaymentOut.setText("");
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(calculate.getWindowToken(), 0);

                float loan,term,rate,charges,apr=0,monthlyPayment=0,totalPayment=0;

                if (loanVar.getText().toString().equals("")) {
                    loanVar.setError("Enter value");
                }
                else if (termVar.getText().toString().equals("")) {
                    termVar.setError("Enter value");
                }
                else if (rateVar.getText().toString().equals("")) {
                    rateVar.setError("Enter value");
                }
                else if (chargesVar.getText().toString().equals("")) {
                    chargesVar.setError("Enter value");
                }

                else {

                    try {
                        loan = Float.parseFloat(loanVar.getText().toString());
                        term = Float.parseFloat(termVar.getText().toString());
                        rate = Float.parseFloat(rateVar.getText().toString());
                        charges = Float.parseFloat(chargesVar.getText().toString());

                        float rate_per_month,num_of_months,temp,power_variable,temp_payment,payment,temp_interest_paid,interest_paid;


                        //monthlyPayment= (float) ((loan*((rate/100)/12))/(1-Math.pow((1+(rate/100*12)),-12*term)));

                        /*rate_per_month = rate / 1200;
                        num_of_months = term * 12;
                        temp = rate_per_month + 1;
                        power_variable = (float) Math.pow(temp, num_of_months);
                        temp_payment = (loan * rate_per_month * power_variable) / (power_variable - 1);
                        payment = (float) (Math.round(temp_payment * 100.00) / 100.00);
                        temp_interest_paid = (num_of_months * payment) - ;
                        interest_paid = (float) (Math.round(temp_interest_paid * 100.00) / 100.00);*/
                        apr=(((((loan+charges)*rate*term)/100)+charges)/(loan*term))*100;
                        //apr=

                        //totalPayment= (float) ((loan+charges)*rate*(Math.pow((1+rate),(term*12)))/((Math.pow(1+(rate/100),term*12))-1));

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    aprOut.setText(String.valueOf(apr));
                    monthlyPaymentOut.setText(String.valueOf(monthlyPayment));
                    totalPaymentOut.setText(String.valueOf(totalPayment));
                }

            }

        });
    }
}